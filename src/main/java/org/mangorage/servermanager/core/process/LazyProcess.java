package org.mangorage.servermanager.core.process;

import org.mangorage.servermanager.core.IOutput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

/*
    Lazy as in not always being actually ran, but capable of being spun up.
 */

public final class LazyProcess {
    public static final LazyProcess MAIN = new LazyProcess(null, "TEST");
    public final Object lock = new Object();
    private final ProcessBuilder processBuilder;
    private final String id;
    private Process runningProcess;
    private IOutput output = s -> {};

    public LazyProcess(ProcessBuilder builder, String id) {
        this.processBuilder = builder;
        this.id = id;
    }

    public String getProcessID() {
        return id;
    }

    public void start() {
        synchronized (lock) {
            if (runningProcess != null) return;
            var thread = new Thread(() -> {
                try {
                    runningProcess = processBuilder.start();

                    try (var is = new InputStreamReader(runningProcess.getInputStream())) {
                        try (var reader = new BufferedReader(is)) {
                            String line;
                            while ((line = reader.readLine()) != null) {
                                output.print(line);
                            }
                        }
                    }

                    runningProcess.waitFor();
                    runningProcess = null; // No longer running
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            thread.start();
        }
    }

    public void hookOutput(IOutput output) {
        this.output = Objects.requireNonNullElseGet(output, () -> s -> {});
    }

    public void forceStopProcess() {
        if (runningProcess != null && runningProcess.isAlive()) runningProcess.destroy();
    }

    @Override
    public String toString() {
        return id;
    }
}
