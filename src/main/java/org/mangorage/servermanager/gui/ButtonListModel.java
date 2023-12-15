package org.mangorage.servermanager.gui;

import org.mangorage.servermanager.core.process.LazyProcess;
import org.mangorage.servermanager.utils.FireableArrayList;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ButtonListModel extends AbstractListModel<LazyProcess> {
    private final ArrayList<LazyProcess> LIST;

    public ButtonListModel(FireableArrayList<LazyProcess> list) {
        this.LIST = list;
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                update();
            }
        }, 0, 50);
    }

    public void update() {
        fireIntervalAdded(this, LIST.size() - 1, LIST.size() - 1);
    }

    public void add(LazyProcess lazyProcess) {
        LIST.add(lazyProcess);
    }

    @Override
    public int getSize() {
        return LIST.size();
    }

    @Override
    public LazyProcess getElementAt(int index) {
        return LIST.get(index);
    }
}
