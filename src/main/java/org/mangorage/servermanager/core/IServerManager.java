package org.mangorage.servermanager.core;

import org.mangorage.servermanager.core.process.LazyProcess;

public interface IServerManager {
    void registerProcess(LazyProcess process);
    void unRegisterProcess(LazyProcess process);
    void init();

    void quit();
}
