package org.stankin.pdn.client.worker;

import org.stankin.pdn.client.handler.ServerHandler;
import org.stankin.pdn.client.packet.Client1LoginFailed;
import org.stankin.pdn.client.packet.Client2ClientList;
import org.stankin.pdn.client.packet.ClientPacket;

import org.jboss.netty.channel.Channel;

public class ServerWorkerImpl implements ServerWorker {

    private ServerHandler handler;
    private Channel channel;

    private boolean authorities = false;

    public ServerWorkerImpl(ServerHandler handler, Channel channel) {
        this.handler = handler;
        this.channel = channel;
    }

    @Override
    public void disconnectedFromChannel() {

    }

    @Override
    public void acceptPacket(ClientPacket packet) {
        System.out.println("Получен пакет с ID = " + packet.getID());

        if (authorities) {

        } else {
            checkAuthorization(packet);
        }

    }

    @Override
    public void sendPacket(ClientPacket packet) {
        channel.write(packet);
    }

    private void checkAuthorization(ClientPacket packet) {
        if (packet.getID() == 11) {
            authorities = true;

            ClientPacket clientListPacket = new Client2ClientList();
            channel.write(clientListPacket);
        } else if (packet.getID() == 12) {
            handler.getUi().showError(((Client1LoginFailed) packet).getReason());
        }
    }
}
