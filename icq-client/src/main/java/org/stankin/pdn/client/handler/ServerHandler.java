package org.stankin.pdn.client.handler;

import org.jboss.netty.channel.*;

public class ServerHandler extends SimpleChannelUpstreamHandler {


    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        super.channelConnected(ctx, e);

        System.out.println("channel connected");

    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        super.messageReceived(ctx, e);

        System.out.println("message from server recieved");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        super.exceptionCaught(ctx, e);

        System.out.println(e.getCause().toString());
        ctx.getChannel().close();
        System.out.println("exception");
    }

    @Override
    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        super.channelDisconnected(ctx, e);

        System.out.println("channel disconnected");
    }
}
