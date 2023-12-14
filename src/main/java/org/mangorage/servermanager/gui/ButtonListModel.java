package org.mangorage.servermanager.gui;

import org.mangorage.servermanager.configurations.LazyProcess;

import javax.swing.*;
import java.util.ArrayList;

public class ButtonListModel extends AbstractListModel<String> {
    private final ArrayList<LazyProcess> LIST = new ArrayList<>();

    public ButtonListModel() {
        LIST.add(LazyProcess.MAIN);
        LIST.add(LazyProcess.MAIN);
        LIST.add(LazyProcess.MAIN);
    }

    public void add(LazyProcess lazyProcess) {
        LIST.add(lazyProcess);
    }

    @Override
    public int getSize() {
        return LIST.size();
    }

    @Override
    public String getElementAt(int index) {
        return LIST.get(index).getProcessID();
    }
}
