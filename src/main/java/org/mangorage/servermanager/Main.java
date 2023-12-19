package org.mangorage.servermanager;

import org.mangorage.servermanager.core.IServerManager;
import org.mangorage.servermanager.core.ServerManager;
import org.mangorage.servermanager.core.process.LazyProcess;
import org.mangorage.servermanager.gui.ServerManagerGUI;

import javax.swing.*;
import java.io.File;

/*

    ID
    Working Directory
    Program Stuff "java -jar server.jar" etc
 */


public class Main {
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
        serverManager = new ServerManager();
        serverManager.init();
        serverManager.registerProcesses(
                LazyProcess.create("MC Server A", "E:\\servers\\a", "java -jar server.jar nogui"),
                LazyProcess.create("MC Server B", "E:\\servers\\b", "java -jar server.jar nogui"),
                LazyProcess.create("Factorio", "E:\\servers\\factorio", "E:\\servers\\Factorio\\bin\\x64\\factorio.exe --start-server-load-latest")
        );

        SwingUtilities.invokeLater(() -> {

            // Handle loading UI Style
            try {
                for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }

                    System.out.println(info.getName());
                }
            } catch (ClassNotFoundException | IllegalAccessException | UnsupportedLookAndFeelException |
                     InstantiationException ex) {
                java.util.logging.Logger.getLogger(ServerManagerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }

            ServerManagerGUI GUI = new ServerManagerGUI(serverManager);
            GUI.setVisible(true);
        });

    }
}
