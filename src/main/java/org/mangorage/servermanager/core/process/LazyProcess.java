package org.mangorage.servermanager.core.process;

import org.mangorage.servermanager.core.misc.IOutput;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

/*
    Lazy as in not always being actually ran, but capable of being spun up.
 */

public final class LazyProcess {
    public static LazyProcess create(String ID, String directory, String args) {
        return new LazyProcess(new ProcessBuilder().directory(new File(directory)).command(args.split(" ")), ID);
    }

    public static final byte[] lineSeperator = System.lineSeparator().getBytes();
    public final Object lock = new Object();
    private final ProcessBuilder processBuilder;
    private final String id;
    private final StringBuilder log = new StringBuilder();
    private Process runningProcess;
    private IOutput output;

    private LazyProcess(ProcessBuilder builder, String id) {
        this.processBuilder = builder;
        this.id = id;
    }

    public Process getRunningProcess() {
        return runningProcess;
    }

    public String getLazyID() {
        return id;
    }

    public void start() {
        synchronized (lock) {
            if (runningProcess != null) return;
            var thread = new Thread(() -> {
                try {
                    log.delete(0, log.length());
                    runningProcess = processBuilder.start();

                    try (var is = new InputStreamReader(runningProcess.getInputStream())) {
                        try (var reader = new BufferedReader(is)) {
                            String line;
                            while ((line = reader.readLine()) != null) {
                                log.append(line).append("\n");
                                if (output != null) output.set(log.toString());
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

    public void printInput(String s) {
        if (runningProcess == null) return;
        byte[] data = s.getBytes();
        var os = runningProcess.getOutputStream();
        try {
            os.write(data, 0, data.length);
            os.write(lineSeperator, 0, lineSeperator.length);
            os.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getLog() {
        return log.toString();
    }

    public void hookOutput(IOutput output) {
        this.output = Objects.requireNonNullElseGet(output, () -> s -> {});
    }

    public void forceStopProcess() {
        if (runningProcess != null && runningProcess.isAlive()) runningProcess.destroy();
    }

    @Override
    public String toString() {
        return "ID: " + id + " Status: " + (runningProcess != null && runningProcess.isAlive() ? "running" : "dead");
    }
}
