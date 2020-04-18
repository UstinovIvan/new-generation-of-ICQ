package org.stankin.pdn.client.context;

import org.stankin.pdn.client.model.ConnectedUser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Контекст клиента
 */
public class AppContext {

    private static AppContext instance;

    private String username;

    private List<String> onlineUsers;

    private Map<String, ConnectedUser> connectionList = new HashMap<>();

    private AppContext() {

    }

    public static AppContext getInstance() {
        if (instance == null) {
            instance = new AppContext();
        }

        return instance;
    }

    public List<String> getOnlineUsers() {
        return onlineUsers;
    }

    public void setOnlineUsers(List<String> onlineUsers) {
        this.onlineUsers = onlineUsers;
    }

    public Map<String, ConnectedUser> getConnectionList() {
        return connectionList;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
