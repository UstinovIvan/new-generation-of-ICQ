package org.stankin.pdn.server.context;

import org.stankin.pdn.server.model.Client;

import java.util.HashSet;
import java.util.Set;

public class ServerContext {

    private static ServerContext instance;

    private final Set<Client> clientList = new HashSet<>();

    private ServerContext() {
    }

    public static ServerContext getInstance() {
        if (instance == null) {
            instance = new ServerContext();
        }

        return instance;
    }

    public Set<Client> getClientList() {
        return clientList;
    }
}
