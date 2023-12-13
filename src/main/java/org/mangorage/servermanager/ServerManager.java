package org.mangorage.servermanager;

import org.mangorage.servermanager.configurations.LazyProcess;
import org.mangorage.servermanager.gui.MyWindow;

import java.io.File;
import java.util.HashSet;

/*

    ID
    JavaVersion
    RunDirectory
    Jar
    Args

 */


public class ServerManager {
    private static final HashSet<LazyProcess> PROCESSES = new HashSet<>();


    public static void registerProcess(LazyProcess process) {
        PROCESSES.add(process);
    }

    public static boolean isRegistered(LazyProcess process) {
        return PROCESSES.contains(process);
    }


    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Forcibly stopping all registered Processes.");
            PROCESSES.forEach(LazyProcess::forceStopProcess);
        }));

        var window = new MyWindow();
        window.setVisible(true);

        var builder = new ProcessBuilder().directory(new File("E:\\server")).command("java", "-jar", "E:\\server\\server.jar");
        var lazy = new LazyProcess(builder, "test");

        registerProcess(lazy);

        lazy.start();
        System.out.println("Finished");
    }
}
