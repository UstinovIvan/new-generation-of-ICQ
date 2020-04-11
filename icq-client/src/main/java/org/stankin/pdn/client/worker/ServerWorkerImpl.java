package org.stankin.pdn.client.worker;

import org.stankin.pdn.client.context.AppContext;
import org.stankin.pdn.client.handler.ServerHandler;
import org.stankin.pdn.client.packet.*;

import org.jboss.netty.channel.Channel;

public class ServerWorkerImpl implements ServerWorker {

    private ServerHandler handler;
    private Channel channel;

    private PairWorker pairWorker;

    private boolean authorities = false;

    public ServerWorkerImpl(ServerHandler handler, Channel channel) {
        this.handler = handler;
        this.channel = channel;

        this.pairWorker = new PairWorker(handler);
    }

    @Override
    public void disconnectedFromChannel() {

    }

    @Override
    public void acceptPacket(Packet packet) {
        System.out.println("Получен пакет с ID = " + packet.getID());

        if (authorities) {
            if (packet.getID() == 20) {
                updateOnlineUsers((Packet2UsersListResponse) packet);
            }
            if (packet.getID() == 31) {
                this.pairWorker.acceptPacket(packet);
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

    private void updateOnlineUsers(Packet2UsersListResponse packet) {
        AppContext.getInstance().setOnlineUsers(packet.getUsersList());
    }


}
