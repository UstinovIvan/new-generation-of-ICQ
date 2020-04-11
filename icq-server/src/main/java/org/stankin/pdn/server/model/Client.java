package org.stankin.pdn.server.model;

import com.google.common.base.Objects;
import org.stankin.pdn.server.handler.ClientHandler;

import java.net.SocketAddress;
import java.util.HashMap;
import java.util.Map;

public class Client {

    private String name;
    private SocketAddress address;
    private Map<String, SocketAddress> connectionList;
    private ClientHandler handler;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SocketAddress getAddress() {
        return address;
    }

    public void setAddress(SocketAddress address) {
        this.address = address;
    }

    public Map<String, SocketAddress> getConnectionList() {
        if (this.connectionList == null) {
            this.connectionList = new HashMap<>();
        }
        return connectionList;
    }

    public void setConnectionList(Map<String, SocketAddress> connectionList) {
        this.connectionList = connectionList;
    }

    public ClientHandler getHandler() {
        return handler;
    }

    public void setHandler(ClientHandler handler) {
        this.handler = handler;
    }

    //TODO: Обдумать возможность сверять только по адресу, чтобы исключить возможность двух входов с одного адреса
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equal(name, client.name) &&
                Objects.equal(address, client.address);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, address);
    }
}
