package org.mangorage.servermanager;

import org.mangorage.servermanager.configurations.IServerManager;
import org.mangorage.servermanager.configurations.LazyProcess;
import org.mangorage.servermanager.gui.ServerManagerGUI;

import java.util.HashSet;

/*

    ID
    Working Directory
    Program Stuff "java -jar server.jar" etc
 */


public class ServerManager {
    private static final HashSet<LazyProcess> PROCESSES = new HashSet<>();
    private static IServerManager serverManager;


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

        // setup server Manager variable
        serverManager = new ServerManagerGUI();
        PROCESSES.forEach(process -> serverManager.registerProcess(process));
        serverManager.init();
        serverManager.registerProcess(LazyProcess.MAIN);
    }
}
