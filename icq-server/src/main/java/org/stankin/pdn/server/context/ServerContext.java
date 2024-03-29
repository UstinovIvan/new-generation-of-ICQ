package org.stankin.pdn.server.context;

import org.stankin.pdn.server.model.Client;

import java.util.*;

/**
 * Контекст сервера
 */
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

    public Client getActiveClient(String name) {
        for (Client client : clientList.values()) {
            if (client.getName().equals(name)) {
                return client;
            }
        }

        return null;
    }

    public void removeClient(Client client) {
        this.clientNames.remove(client.getName());
        this.clientList.values().remove(client);
        System.out.println(clientList.size());
    }
}
