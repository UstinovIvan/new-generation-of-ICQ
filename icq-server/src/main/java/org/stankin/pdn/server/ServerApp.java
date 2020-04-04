package org.stankin.pdn.server;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.execution.OrderedMemoryAwareThreadPoolExecutor;
import org.stankin.pdn.server.pipeline.ServerPipelineFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class ServerApp {

    public static void main(String[] args) {

        String address;
        int port;

        if (args.length != 0) {
            address = args[0];
            port = Integer.parseInt(args[1]);
        } else {
            System.out.println("Аргументы не заполнены!");
            return;
        }


        ExecutorService bossExec = new OrderedMemoryAwareThreadPoolExecutor(1, 400000000, 2000000000, 60, TimeUnit.SECONDS);
        ExecutorService ioExec = new OrderedMemoryAwareThreadPoolExecutor(4 /* число рабочих потоков */, 400000000, 2000000000, 60, TimeUnit.SECONDS);
        ServerBootstrap networkServer = new ServerBootstrap(new NioServerSocketChannelFactory(bossExec, ioExec, 4 /* то же самое число рабочих потоков */));
        networkServer.setOption("backlog", 500);
        networkServer.setOption("connectTimeoutMillis", 10000);
        networkServer.setPipelineFactory(new ServerPipelineFactory());
        Channel channel = networkServer.bind(new InetSocketAddress(address, port));

        System.out.println("Сервер запущен");

    }
}
