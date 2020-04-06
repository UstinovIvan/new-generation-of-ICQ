package org.stankin.pdn.server.context;

import org.stankin.pdn.server.model.Client;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ServerContext {

    private static ServerContext instance;

    private final Set<Client> clientList = new HashSet<>();

    private final List<String> clientNames = new ArrayList<>();

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

    public List<String> getClientNames() {
        return clientNames;
    }
}
