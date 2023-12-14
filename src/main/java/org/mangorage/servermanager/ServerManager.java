package org.mangorage.servermanager;

import org.mangorage.servermanager.core.IServerManager;
import org.mangorage.servermanager.core.process.LazyProcess;
import org.mangorage.servermanager.gui.ServerManagerGUI;

import java.io.File;
import java.util.HashSet;

/*

    ID
    Working Directory
    Program Stuff "java -jar server.jar" etc
 */


public class ServerManager {
    private static IServerManager serverManager;

    public static IServerManager getServerManager() {
        return serverManager;
    }



    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Forcibly stopping all registered Processes.");
            serverManager.quit();
        }));

        // setup server Manager variable
        serverManager = new ServerManagerGUI();
        serverManager.init();

        var builder = new ProcessBuilder().directory(new File("E:\\server")).command("java -jar server.jar".split(" "));
        var lp = new LazyProcess(builder, "Server");
        serverManager.registerProcess(lp);
    }
}
