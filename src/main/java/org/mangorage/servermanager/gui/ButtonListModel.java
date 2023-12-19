package org.mangorage.servermanager.gui;

import org.mangorage.servermanager.core.process.LazyProcess;
import org.mangorage.servermanager.utils.DirtyList;

import javax.swing.*;
import java.util.List;

public class ButtonListModel extends AbstractListModel<LazyProcess> {
    private final List<LazyProcess> LIST;

    @SuppressWarnings("unchecked")
    public ButtonListModel(DirtyList<LazyProcess> list) {
        this.LIST = list.getOriginal();
        list.listen((a, b) -> {
            update(b);
        });
    }

    public void update(DirtyList.PropertyUpdate propertyUpdate) {
        switch (propertyUpdate) {
            case ADD -> fireIntervalAdded(this, LIST.size() - 1, LIST.size() - 1);
            case REMOVE -> fireIntervalRemoved(this, LIST.size() - 1, LIST.size() - 1);
            case NORMAL -> fireContentsChanged(this, LIST.size() - 1, LIST.size() - 1);
        }
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
