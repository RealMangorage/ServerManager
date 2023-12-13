package org.mangorage.servermanager.configurations;

import org.mangorage.servermanager.ServerManager;
import org.mangorage.servermanager.utils.ProcessNotRegisteredException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
    Lazy as in not always being actually ran, but capable of being spun up.
 */

public class LazyProcess extends Thread {
    private final ProcessBuilder processBuilder;
    private final String id;

    private Process runningProcess;

    public LazyProcess(ProcessBuilder builder, String id) {
        this.processBuilder = builder;
        this.id = id;
        builder.inheritIO();
    }

    @Override
    public void run() {
        try {
            if (!ServerManager.isRegistered(this)) throw new ProcessNotRegisteredException();
            runningProcess = processBuilder.start();

            try (var is = new InputStreamReader(runningProcess.getInputStream())) {
                try (var reader = new BufferedReader(is)) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                    }
                }
            }

            runningProcess.waitFor();
            System.out.println("[%s] has shutdown".formatted(id));
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void forceStopProcess() {
        if (runningProcess != null && runningProcess.isAlive()) runningProcess.destroy();
    }
}
