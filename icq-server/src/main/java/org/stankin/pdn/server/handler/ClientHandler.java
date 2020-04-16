package org.stankin.pdn.server.handler;

import org.jboss.netty.channel.*;
import org.stankin.pdn.client.packet.Packet;
import org.stankin.pdn.server.worker.AuthorizationClientWorker;
import org.stankin.pdn.server.worker.ClientWorker;

public class ClientHandler extends SimpleChannelUpstreamHandler {

    private ClientWorker clientWorker;

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        super.channelConnected(ctx, e);

        /*
          Реализовать следующую логику:
          При создании канала инициализируем воркера-авторизатора, который получает пакет авторизации и делает дела
          Успех - присваивает следующего. Неудача - остается бдеть.
          И так идем по жизненному циклу пользователя в системе
         */
        clientWorker = new AuthorizationClientWorker(this, e.getChannel());

        System.out.println("channel connected");
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        super.messageReceived(ctx, e);

        if (e.getChannel().isOpen()) {
            clientWorker.acceptPacket((Packet) e.getMessage());
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        super.exceptionCaught(ctx, e);
        System.out.println("exception");
    }

    @Override
    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        super.channelDisconnected(ctx, e);

        clientWorker.disconnectedFromChannel();
        System.out.println("channel disconnected");
    }

    public ClientWorker getClientWorker() {
        return clientWorker;
    }

    public void setClientWorker(ClientWorker clientWorker) {
        this.clientWorker = clientWorker;
    }
}
