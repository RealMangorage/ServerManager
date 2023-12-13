package org.mangorage.servermanager.gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.FlowLayout;

public class MyWindow extends JFrame {
    private JButton button1;
    private JButton button2;
    private JButton button3;

    public MyWindow() {
        // Set up the frame
        setTitle("My Window");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create buttons
        button1 = new JButton("Button 1");
        button2 = new JButton("Button 2");
        button3 = new JButton("Button 3");

        // Set up layout
        setLayout(new FlowLayout());

        // Add buttons to the frame
        add(button1);
        add(button2);
        add(button3);
    }
}