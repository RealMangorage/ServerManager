package org.mangorage.servermanager.core;

import org.mangorage.servermanager.core.process.LazyProcess;
import org.mangorage.servermanager.utils.FireableArrayList;

public class ServerManager implements IServerManager {
    private final FireableArrayList<LazyProcess> PROCESSES = new FireableArrayList<>();

    @Override
    public void registerProcess(LazyProcess process) {
        PROCESSES.add(process);
    }

    @Override
    public void unRegisterProcess(LazyProcess process) {

    }

    @Override
    public void init() {
        // Handle loading
    }

    @Override
    public void quit() {
        PROCESSES.forEach(LazyProcess::forceStopProcess);
    }

    @Override
    public FireableArrayList<LazyProcess> getProcesses() {
        return PROCESSES;
    }
}
