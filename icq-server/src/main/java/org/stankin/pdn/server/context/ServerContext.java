package org.stankin.pdn.server.context;

import org.stankin.pdn.server.model.Client;

import java.util.*;

public class ServerContext {

    private static ServerContext instance;

    private final Map<String, Client> clientList = new HashMap<>();

    private final List<String> clientNames = new ArrayList<>();

    private ServerContext() {
    }

    public static ServerContext getInstance() {
        if (instance == null) {
            instance = new ServerContext();
        }

        return instance;
    }

    public Map<String, Client> getClientList() {
        return clientList;
    }

    public List<String> getClientNames() {
        return clientNames;
    }
}
