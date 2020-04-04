package org.stankin.pdn.client.pipeline;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.stankin.pdn.client.coder.PacketFrameDecoder;
import org.stankin.pdn.client.coder.PacketFrameEncoder;
import org.stankin.pdn.client.handler.ServerHandler;

public class ClientPipelineFactory implements ChannelPipelineFactory {

    @Override
    public ChannelPipeline getPipeline() {
        PacketFrameDecoder decoder = new PacketFrameDecoder();
        PacketFrameEncoder encoder = new PacketFrameEncoder();

        ChannelPipeline p = Channels.pipeline();
        p.addLast("encoder", encoder);
        p.addLast("decoder", decoder);
        p.addLast("logic",   new ServerHandler());
        return p;
    }
}
