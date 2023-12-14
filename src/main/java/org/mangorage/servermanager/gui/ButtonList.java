package org.mangorage.servermanager.gui;

import org.mangorage.servermanager.core.process.LazyProcess;
import org.mangorage.servermanager.utils.FireableArrayList;

import javax.swing.*;
import java.util.ArrayList;

public class ButtonList extends JList<LazyProcess> {
    private final ButtonListModel MODEL;

    public ButtonList(FireableArrayList<LazyProcess> arrayList) {
        this.MODEL = new ButtonListModel(arrayList);
        setModel(MODEL);
    }

    public void add(LazyProcess process) {
        MODEL.add(process);
    }
}
