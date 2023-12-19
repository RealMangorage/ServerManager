package org.mangorage.servermanager.core;

import org.mangorage.servermanager.core.process.LazyProcess;
import org.mangorage.servermanager.utils.DirtyArrayList;
import org.mangorage.servermanager.utils.DirtyList;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class ServerManager implements IServerManager {
    private final DirtyList<LazyProcess> PROCESSES = DirtyArrayList.create(new CopyOnWriteArrayList<>());

    @Override
    public void registerProcess(LazyProcess process) {
        PROCESSES.add(process);
    }

    @Override
    public void unRegisterProcess(LazyProcess process) {
        PROCESSES.remove(0);
    }

    @Override
    public void init() {

    }

    @Override
    public void quit() {
        PROCESSES.forEach(LazyProcess::forceStopProcess);
    }

    @Override
    public DirtyList<LazyProcess> getProcesses() {
        return PROCESSES;
    }
}
