package org.mangorage.servermanager.utils;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public interface DirtyList<E> extends List<E> {
    enum PropertyUpdate {
        REMOVE,
        ADD,
        NORMAL
    }
    void listen(BiConsumer<List<E>, PropertyUpdate> listener);
    List<E> getOriginal();
}
