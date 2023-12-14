package org.mangorage.servermanager.gui;

import org.mangorage.servermanager.configurations.LazyProcess;

import javax.swing.*;

public class ButtonList extends JList<String> {
    private final ButtonListModel MODEL = new ButtonListModel();

    public ButtonList() {
        setModel(MODEL);
    }

    public void add(LazyProcess process) {
        this.MODEL.add(process);
    }
}
