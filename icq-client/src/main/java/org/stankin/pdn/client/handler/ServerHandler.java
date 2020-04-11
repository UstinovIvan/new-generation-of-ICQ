package org.stankin.pdn.client.handler;

import org.jboss.netty.channel.*;
import org.stankin.pdn.client.packet.Packet;
import org.stankin.pdn.client.ui.MainWindow;
import org.stankin.pdn.client.worker.ServerWorker;
import org.stankin.pdn.client.worker.ServerWorkerImpl;

public class ServerHandler extends SimpleChannelUpstreamHandler {

    private ServerWorker mainWorker;

    private MainWindow ui;

    public ServerHandler(MainWindow ui) {
        this.ui = ui;
    }

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        super.channelConnected(ctx, e);

        System.out.println("channel connected");
        mainWorker = new ServerWorkerImpl(this, e.getChannel());

        ui.showLoginPage();
        ui.setWorker(mainWorker);
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        super.messageReceived(ctx, e);

        mainWorker.acceptPacket((Packet)e.getMessage());
        System.out.println("message from server recieved");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        super.exceptionCaught(ctx, e);

        ui.showError(e.getCause().getMessage());
        System.out.println("exception");
    }

    @Override
    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        super.channelDisconnected(ctx, e);

        mainWorker.disconnectedFromChannel();
        System.out.println("channel disconnected");
    }

    public MainWindow getUi() {
        return this.ui;
    }
}
