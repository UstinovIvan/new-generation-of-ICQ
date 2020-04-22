package org.stankin.pdn.client;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.execution.OrderedMemoryAwareThreadPoolExecutor;
import org.stankin.pdn.client.context.AppContext;
import org.stankin.pdn.client.pipeline.ClientPipelineFactory;
import org.stankin.pdn.client.ui.MainWindow;
import org.stankin.pdn.crypto.exception.InitHandlerException;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class ClientApp {

    private String address;
    private int port;

    private ClientApp(String address, int port) {
        this.address = address;
        this.port = port;
    }

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

        new MainWindow(new ClientApp(address, port));

    }

    public void startClient(MainWindow mainWindow) {
        initCryptoApi();
        SocketAddress socketAddress = new InetSocketAddress(address, port);

        ExecutorService bossExec = new OrderedMemoryAwareThreadPoolExecutor(1, 400000000, 2000000000, 60, TimeUnit.SECONDS);
        ExecutorService ioExec = new OrderedMemoryAwareThreadPoolExecutor(2 /* число рабочих потоков */, 400000000, 2000000000, 60, TimeUnit.SECONDS);
        ClientBootstrap clientBootstrap = new ClientBootstrap(new NioClientSocketChannelFactory(bossExec, ioExec));
        clientBootstrap.setOption("backlog", 500);
        clientBootstrap.setOption("connectTimeoutMillis", 10000);
        clientBootstrap.setOption("remoteAddress", socketAddress);
        clientBootstrap.setOption("tcpNoDelay", true);
        clientBootstrap.setOption("keepAlive", true);
        clientBootstrap.setOption("receiveBufferSize", 1048576);
        clientBootstrap.setPipelineFactory(new ClientPipelineFactory(mainWindow));

        ChannelFuture channel = clientBootstrap.connect(socketAddress).awaitUninterruptibly();

        System.out.println("Клиент запущен");
    }

    private void initCryptoApi() {
        try {
            AppContext.getInstance().getCryptoApi().init();
        } catch (InitHandlerException e) {
            System.out.println("Ошибка инициализации модуля шифрования: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
