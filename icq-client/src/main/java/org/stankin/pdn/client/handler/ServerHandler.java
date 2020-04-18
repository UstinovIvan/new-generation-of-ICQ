package org.stankin.pdn.client.handler;

import org.jboss.netty.channel.*;
import org.stankin.pdn.client.packet.Packet;
import org.stankin.pdn.client.ui.MainWindow;
import org.stankin.pdn.client.worker.ServerWorker;
import org.stankin.pdn.client.worker.ServerWorkerImpl;

/**
 * Обработчик канала с сервером
 */
public class ServerHandler extends SimpleChannelUpstreamHandler {

    private ServerWorker mainWorker;

    private MainWindow ui;

    public ServerHandler(MainWindow ui) {
        this.ui = ui;
    }

    /**
     * Обработка успешного соединения
     *
     * @param ctx - Контекст
     * @param e   - Событие
     * @throws Exception - Любое исключение, возникшее в этом обработчике
     */
    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        super.channelConnected(ctx, e);

        System.out.println("channel connected");
        mainWorker = new ServerWorkerImpl(this, e.getChannel());

        ui.showLoginPage();
        ui.setWorker(mainWorker);
    }

    /**
     * Получение сообщения
     *
     * @param ctx - Контекст
     * @param e   - Сообщение
     * @throws Exception - Любое исключение, возникшее в этом обработчике
     */
    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        super.messageReceived(ctx, e);

        mainWorker.acceptPacket((Packet) e.getMessage());
        System.out.println("message from server recieved");
    }

    /**
     * Обработка исключений
     *
     * @param ctx - Контекст
     * @param e   - Исключение
     * @throws Exception - Любое исключение, возникшее в этом обработчике
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        super.exceptionCaught(ctx, e);

        String message = e.getCause().getMessage();

        if (message.length() == 0) {
            message = "Неизвестная ошибка: " + e.getCause();
        }

        ui.showError(message);
        System.out.println("exception");
    }

    /**
     * Обработка разъединения с сервером
     *
     * @param ctx - Контекст
     * @param e   - Событие
     * @throws Exception - Любое исключение, возникшее в этом обработчике
     */
    @Override
    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        super.channelDisconnected(ctx, e);

        mainWorker.disconnectedFromChannel();
        ui.showError("Сервер разорвал соединение");
        ui.connectWindow();
        System.out.println("channel disconnected");
    }

    public MainWindow getUi() {
        return this.ui;
    }
}
