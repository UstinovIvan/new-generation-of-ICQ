package org.stankin.pdn.server.handler;

import org.jboss.netty.channel.*;
import org.stankin.pdn.client.packet.Packet;
import org.stankin.pdn.server.worker.AuthorizationClientWorker;
import org.stankin.pdn.server.worker.ClientWorker;

/**
 * Обработчик канала с одним из пользователей
 */
public class ClientHandler extends SimpleChannelUpstreamHandler {

    private ClientWorker clientWorker;

    /**
     * Обработка успешного соединения
     *
     * @param ctx - Контекст
     * @param e   - Событие
     * @throws Exception - Любое исключение, генерируемое в этом обработчике
     */
    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        super.channelConnected(ctx, e);

        clientWorker = new AuthorizationClientWorker(this, e.getChannel());

        System.out.println("channel connected");
    }

    /**
     * Получение сообщения
     *
     * @param ctx - Контекст
     * @param e   - Сообщение
     * @throws Exception - Любое исключение, генерируемое в этом обработчике
     */
    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        super.messageReceived(ctx, e);

        if (e.getChannel().isOpen()) {
            clientWorker.acceptPacket((Packet) e.getMessage());
        }

    }

    /**
     * Обработка исключений
     *
     * @param ctx - Контекст
     * @param e   - Исключение
     * @throws Exception - Любое исключение, генерируемое в этом обработчике
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        super.exceptionCaught(ctx, e);
        System.out.println("exception");
    }

    /**
     * Обработка отключения клиента от сервера
     *
     * @param ctx - Контекст
     * @param e   - Событие
     * @throws Exception - Любое исключение, генерируемое в этом обработчике
     */
    @Override
    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        super.channelDisconnected(ctx, e);

        clientWorker.disconnectedFromChannel();
        System.out.println("channel disconnected");
    }

    public ClientWorker getClientWorker() {
        return clientWorker;
    }

    public void setClientWorker(ClientWorker clientWorker) {
        this.clientWorker = clientWorker;
    }
}
