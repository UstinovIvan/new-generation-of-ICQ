package org.stankin.pdn.client.context;

import java.net.SocketAddress;
import java.util.List;
import java.util.Map;

public class AppContext {

    private static AppContext instance;

    private List<String> onlineUsers;

    private Map<String, SocketAddress> connectionList;

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

    public Map<String, SocketAddress> getConnectionList() {
        return connectionList;
    }

    public void setConnectionList(Map<String, SocketAddress> connectionList) {
        this.connectionList = connectionList;
    }
}
