package org.stankin.pdn.client.coder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.handler.codec.replay.ReplayingDecoder;
import org.jboss.netty.handler.codec.replay.VoidEnum;
import org.stankin.pdn.client.packet.Packet;

public class PacketFrameDecoder extends ReplayingDecoder<VoidEnum> {

    @Override
    public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) {
        ctx.sendUpstream(e);
    }
    @Override
    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
        ctx.sendUpstream(e);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, Channel channel, ChannelBuffer buffer, VoidEnum state) throws Exception {
        return Packet.read(buffer);
    }
}
