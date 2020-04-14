package org.stankin.pdn.client.context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppContext {

    private static AppContext instance;

    private String username;

    private List<String> onlineUsers;

    private Map<String, String> connectionList = new HashMap<>();

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

    public Map<String, String> getConnectionList() {
        return connectionList;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
