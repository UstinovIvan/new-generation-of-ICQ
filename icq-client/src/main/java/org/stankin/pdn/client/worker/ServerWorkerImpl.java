package org.stankin.pdn.client.worker;

import org.stankin.pdn.client.handler.ServerHandler;
import org.stankin.pdn.client.packet.Packet1LoginFailed;
import org.stankin.pdn.client.packet.Packet2UsersListRequest;
import org.stankin.pdn.client.packet.Packet2UsersListResponse;
import org.stankin.pdn.client.packet.Packet;

import org.jboss.netty.channel.Channel;

import java.util.List;

public class ServerWorkerImpl implements ServerWorker {

    private ServerHandler handler;
    private Channel channel;

    private List<String> clientList;

    private List<String> activeClientList;

    private boolean authorities = false;

    public ServerWorkerImpl(ServerHandler handler, Channel channel) {
        this.handler = handler;
        this.channel = channel;
    }

    @Override
    public void disconnectedFromChannel() {

    }

    @Override
    public void acceptPacket(Packet packet) {
        System.out.println("Получен пакет с ID = " + packet.getID());

        if (authorities) {
            if (packet.getID() == 20) {
                clientList = ((Packet2UsersListResponse) packet).getUsersList();
            }




        } else {
            checkAuthorization(packet);
        }

    }

    @Override
    public void sendPacket(Packet packet) {
        channel.write(packet);
    }

    private void checkAuthorization(Packet packet) {
        if (packet.getID() == 11) {
            authorities = true;

            Packet clientListPacket = new Packet2UsersListRequest();
            channel.write(clientListPacket);
        } else if (packet.getID() == 12) {
            handler.getUi().showError(((Packet1LoginFailed) packet).getReason());
        }
    }
}
