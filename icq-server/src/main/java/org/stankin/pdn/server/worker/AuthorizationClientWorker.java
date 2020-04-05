package org.stankin.pdn.server.worker;

import org.jboss.netty.channel.Channel;
import org.stankin.pdn.server.context.ServerContext;
import org.stankin.pdn.server.handler.ClientHandler;
import org.stankin.pdn.server.model.Client;
import org.stankin.pdn.server.packet.Packet;
import org.stankin.pdn.server.packet.Server1LoginFailed;
import org.stankin.pdn.server.packet.Server1LoginPacket;
import org.stankin.pdn.server.packet.Server1LoginSuccess;

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

        Server1LoginPacket loginPacket = (Server1LoginPacket) packet;

        Client client = new Client();
        client.setName(loginPacket.getLogin());
        client.setAddress(this.channel.getRemoteAddress());

        if (context.getClientList().contains(client)) {//TODO: Заглушка. Реализовать проверку пользователя по базе
            sendFailure("User is already online");
        } else {
            context.getClientList().add(client);
            sendSuccess();

            handler.setClientWorker(new PairClientWorker(this.handler, this.channel, client));
        }
    }

    private void sendFailure(String reason) {
        Packet failurePacket = new Server1LoginFailed(reason);
        this.channel.write(failurePacket);
    }

    private void sendSuccess() {
        Packet successPacket = new Server1LoginSuccess();
        this.channel.write(successPacket);
    }
}
