package org.stankin.pdn.client.worker;

import org.jboss.netty.channel.Channel;
import org.stankin.pdn.client.context.AppContext;
import org.stankin.pdn.client.handler.ServerHandler;
import org.stankin.pdn.client.packet.Packet;
import org.stankin.pdn.client.packet.Packet1LoginFailed;
import org.stankin.pdn.client.packet.Packet2UsersListRequest;
import org.stankin.pdn.client.packet.Packet2UsersListResponse;

import java.io.File;

/**
 * Базовая реализация обработчика {@link ServerWorker}
 */
public class ServerWorkerImpl implements ServerWorker {

    protected ServerHandler handler;
    protected Channel channel;

    private PairWorker pairWorker;
    private MessageWorker messageWorker;

    private boolean authorities = false;

    public ServerWorkerImpl(ServerHandler handler, Channel channel) {
        this.handler = handler;
        this.channel = channel;

        this.pairWorker = new PairWorker(this.getHandler(), this.channel);
        this.messageWorker = new MessageWorker(this.getHandler(), this.channel);
    }

    @Override
    public void disconnectedFromChannel() {

    }

    @Override
    public void acceptPacket(Packet packet) {
        System.out.println("Получен пакет с ID = " + packet.getID());

        if (authorities) {
            if (packet.getID() == 21) {
                updateOnlineUsers((Packet2UsersListResponse) packet);
                this.handler.getUi().refreshUserList(AppContext.getInstance().getOnlineUsers());
                return;
            }
            if (packet.getID() == 31) {
                this.pairWorker.acceptPacket(packet);
                return;
            }
            if (packet.getID() == 50 || packet.getID() == 51) {
                this.messageWorker.acceptPacket(packet);
                return;
            }

            if (packet.getID() == 250) {
                this.pairWorker.removeUser(packet);
            }

        } else {
            checkAuthorization(packet);
        }

    }

    @Override
    public void sendPacket(Packet packet) {
        if (packet.getID() == 31) {
            pairWorker.fillSecurityAndSend(packet);
            return;
        }

        channel.write(packet);
    }

    @Override
    public void sendMessage(String message, String to) {
        messageWorker.sendMessage(message, to);
    }

    @Override
    public void sendFile(File file, String to) {
        messageWorker.sendFile(file, to);
    }

    private void checkAuthorization(Packet packet) {
        if (packet.getID() == 11) {
            authorities = true;

            handler.getUi().showMainForm();

            Packet clientListPacket = new Packet2UsersListRequest();
            channel.write(clientListPacket);
        } else if (packet.getID() == 12) {
            handler.getUi().showError(((Packet1LoginFailed) packet).getReason());
        }
    }

    private void updateOnlineUsers(Packet2UsersListResponse packet) {
        AppContext.getInstance().setOnlineUsers(packet.getUsersList());
    }

    public ServerHandler getHandler() {
        return handler;
    }

    public Channel getChannel() {
        return channel;
    }
}
