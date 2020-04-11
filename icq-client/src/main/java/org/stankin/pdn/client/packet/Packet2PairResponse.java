package org.stankin.pdn.client.packet;

import org.jboss.netty.buffer.ChannelBuffer;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

public class Packet2PairResponse extends Packet {

    private final int ID = 31;

    private String username;
    private SocketAddress socketAddress;
    private String reason;

    @Override
    public void get(ChannelBuffer buffer) {
        short answer = buffer.readShort();
        StringBuilder builder = new StringBuilder();

        if (answer == 1) {
            String address;
            int port;

            int addressLength = buffer.readShort();
            while (addressLength != 0) {
                builder.append(buffer.readChar());
                addressLength--;
            }
            port = buffer.readInt();
            address = builder.toString();
            this.socketAddress = new InetSocketAddress(address, port);

            builder = new StringBuilder();
            int usernameLength = buffer.readShort();
            while (usernameLength != 0) {
                builder.append(buffer.readChar());

                usernameLength--;
            }

            this.username = builder.toString();
        } else {
            int reasonLength = buffer.readShort();
            while (reasonLength != 0) {
                builder.append(buffer.readChar());
                reasonLength--;
            }

            reason = builder.toString();
        }
    }

    @Override
    public void send(ChannelBuffer buffer) {
        if (socketAddress == null) {
            buffer.writeShort(-1);
            int reasonLength = reason.length();
            buffer.writeShort(reasonLength);
            for (int i = 0; i < reasonLength; i++) {
                buffer.writeChar(reason.charAt(i));
            }
        } else {
            buffer.writeShort(1);
            InetSocketAddress inetSocketAddress = ((InetSocketAddress) socketAddress);
            String address = inetSocketAddress.getHostString();
            int port = inetSocketAddress.getPort();

            buffer.writeShort(address.length());
            for (int i = 0; i < address.length(); i++) {
                buffer.writeChar(address.charAt(i));
            }
            buffer.writeInt(port);

            int usernameLength = username.length();
            buffer.writeShort(usernameLength);
            for (int i = 0; i < usernameLength; i++) {
                buffer.writeChar(username.charAt(i));
            }
        }
    }

    @Override
    public int getID() {
        return this.ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Packet2PairResponse withUsername(String username) {
        this.username = username;
        return this;
    }

    public SocketAddress getSocketAddress() {
        return socketAddress;
    }

    public void setSocketAddress(SocketAddress socketAddress) {
        this.socketAddress = socketAddress;
    }

    public Packet2PairResponse withSocketAddress(SocketAddress socketAddress) {
        this.socketAddress = socketAddress;
        return this;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Packet2PairResponse withReason(String reason) {
        this.reason = null;
        return this;
    }
}
