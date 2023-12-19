package org.mangorage.servermanager.gui;

import org.mangorage.servermanager.core.process.LazyProcess;
import org.mangorage.servermanager.utils.DirtyList;

import javax.swing.*;
import java.util.List;

public class ButtonList extends JList<LazyProcess> {
    private final ButtonListModel MODEL;

    public ButtonList(DirtyList<LazyProcess> arrayList) {
        this.MODEL = new ButtonListModel(arrayList);
        setModel(MODEL);
    }

    public void add(LazyProcess process) {
        MODEL.add(process);
    }
}
