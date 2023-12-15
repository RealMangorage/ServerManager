package org.mangorage.servermanager.utils;

import org.mangorage.servermanager.core.misc.IInvoker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

public class FireableArrayList<T> extends ArrayList<T> {
    private final Object lock = new Object();
    private final List<IInvoker> LISTENERS = new CopyOnWriteArrayList<>();

    public void register(IInvoker listener) {
        synchronized (lock) {
            LISTENERS.add(listener);
        }
    }

    public void invoke() {
        LISTENERS.forEach(IInvoker::invoke);
    }

    @Override
    public boolean add(T t) {
        var a = super.add(t);
        invoke();
        return a;
    }
}
