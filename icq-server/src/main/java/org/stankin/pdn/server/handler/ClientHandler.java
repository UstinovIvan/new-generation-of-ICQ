package org.stankin.pdn.server.handler;

import org.jboss.netty.channel.*;
import org.stankin.pdn.server.packet.Packet;
import org.stankin.pdn.server.worker.ClientWorker;
import org.stankin.pdn.server.worker.ClientWorkerImpl;

public class ClientHandler extends SimpleChannelUpstreamHandler {

    private ClientWorker clientWorker;

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        super.channelConnected(ctx, e);

        clientWorker = new ClientWorkerImpl(this, e.getChannel());
        System.out.println("channel connected");
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        super.messageReceived(ctx, e);

        if (e.getChannel().isOpen()) {
            clientWorker.acceptPacket((Packet) e.getMessage());
        }

        System.out.println("message recieved");
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
}
