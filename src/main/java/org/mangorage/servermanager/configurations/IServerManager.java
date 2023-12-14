package org.mangorage.servermanager.configurations;

public interface IServerManager {
    void registerProcess(LazyProcess process);
    void unRegisterProcess(LazyProcess process);
    void init();
}
