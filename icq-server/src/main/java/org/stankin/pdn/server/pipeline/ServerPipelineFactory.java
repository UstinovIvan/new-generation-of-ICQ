package org.stankin.pdn.server.pipeline;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.stankin.pdn.server.coder.PacketFrameDecoder;
import org.stankin.pdn.server.coder.PacketFrameEncoder;
import org.stankin.pdn.server.handler.ClientHandler;

public class ServerPipelineFactory implements ChannelPipelineFactory {

    @Override
    public ChannelPipeline getPipeline() {
        PacketFrameDecoder decoder = new PacketFrameDecoder();
        PacketFrameEncoder encoder = new PacketFrameEncoder();
        return Channels.pipeline(decoder, encoder, new ClientHandler());
    }
}
