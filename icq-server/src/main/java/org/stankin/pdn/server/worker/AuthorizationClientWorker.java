package org.stankin.pdn.server.worker;

import org.jboss.netty.channel.Channel;
import org.stankin.pdn.server.context.ServerContext;
import org.stankin.pdn.server.handler.ClientHandler;
import org.stankin.pdn.server.model.Client;
import org.stankin.pdn.client.packet.Packet;
import org.stankin.pdn.client.packet.Packet1LoginFailed;
import org.stankin.pdn.client.packet.Packet1LoginRequest;
import org.stankin.pdn.client.packet.Packet1LoginSuccess;

import java.util.UUID;

public class AuthorizationClientWorker extends AbstractClientWorker {

    public AuthorizationClientWorker(ClientHandler handler, Channel channel) {
        super(handler, channel);
    }

    @Override
    public void disconnectedFromChannel() {
        channel.close();
    }

    @Override
    public void acceptPacket(Packet packet) {

        ServerContext context = ServerContext.getInstance();

        Packet1LoginRequest loginPacket = (Packet1LoginRequest) packet;

        Client client = new Client();
        client.setName(loginPacket.getLogin());
        client.setAddress(this.channel.getRemoteAddress());

        String uid = UUID.nameUUIDFromBytes(client.getName().getBytes()).toString();
        if (context.getClientList().containsKey(uid)) {//TODO: Заглушка. Реализовать проверку пользователя по базе
            sendFailure("User is already online");
        } else {
            context.getClientList().put(uid, client);
            context.getClientNames().add(client.getName());
            sendSuccess(uid);

            handler.setClientWorker(new ClientListWorker(this.handler, this.channel, client));
        }
    }

    private void sendFailure(String reason) {
        Packet failurePacket = new Packet1LoginFailed().withReason(reason);
        this.channel.write(failurePacket);
    }

    private void sendSuccess(String uid) {
        Packet successPacket = new Packet1LoginSuccess().withUid(uid);
        this.channel.write(successPacket);
    }
}
