package org.stankin.pdn.server.worker;

import org.jboss.netty.channel.Channel;
import org.stankin.pdn.server.handler.ClientHandler;

public abstract class AbstractClientWorker implements ClientWorker{

    ClientHandler handler;
    Channel channel;

    AbstractClientWorker(ClientHandler handler, Channel channel) {
        this.handler = handler;
        this.channel = channel;
    }

    public ClientHandler getHandler() {
        return handler;
    }

    public Channel getChannel() {
        return channel;
    }
}
