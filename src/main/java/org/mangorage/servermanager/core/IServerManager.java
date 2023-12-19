package org.mangorage.servermanager.core;

import org.mangorage.servermanager.core.process.LazyProcess;
import org.mangorage.servermanager.utils.DirtyList;

import java.util.ArrayList;
import java.util.Arrays;

public interface IServerManager {
    default void registerProcesses(LazyProcess process, LazyProcess... processes) {
        registerProcess(process);
        Arrays.stream(processes).forEach(this::registerProcess);
    }
    default void unRegisterProcesses(LazyProcess process, LazyProcess... processes) {
        unRegisterProcess(process);
        Arrays.stream(processes).forEach(this::unRegisterProcess);
    }

    void registerProcess(LazyProcess process);
    void unRegisterProcess(LazyProcess process);
    void init();
    void quit();
    DirtyList<LazyProcess> getProcesses();
}
