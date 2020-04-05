package org.stankin.pdn.server.model;

import com.google.common.base.Objects;

import java.net.SocketAddress;
import java.util.List;

public class Client {

    private String name;
    private SocketAddress address;
    private List<Client> connectionList;

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

    public List<Client> getConnectionList() {
        return connectionList;
    }

    public void setConnectionList(List<Client> connectionList) {
        this.connectionList = connectionList;
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
