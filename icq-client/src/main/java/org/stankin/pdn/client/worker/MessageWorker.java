package org.stankin.pdn.client.worker;

import org.jboss.netty.channel.Channel;
import org.stankin.pdn.client.context.AppContext;
import org.stankin.pdn.client.handler.ServerHandler;
import org.stankin.pdn.client.packet.Packet;
import org.stankin.pdn.client.packet.Packet5Message;

public class MessageWorker implements ServerWorker {

    protected ServerHandler handler;
    protected Channel channel;

    MessageWorker(ServerHandler handler, Channel channel) {
        this.handler = handler;
        this.channel = channel;
    }

    @Override
    public void disconnectedFromChannel() {

    }

    @Override
    public void acceptPacket(Packet packet) {
        Packet5Message messagePacket = (Packet5Message) packet;
        String decryptedMessage = decryptMessage(messagePacket.getMessage());

        AppContext.getInstance().getConnectionList()
                .get(messagePacket.getFrom()).getUserTab().getTextArea()
                .append("\n" + messagePacket.getFrom() + ": " + decryptedMessage);
    }

    @Override
    public void sendPacket(Packet packet) {
        Packet5Message messagePacket = (Packet5Message) packet;
        String encryptedMessage = encryptMessage(messagePacket.getMessage());
        messagePacket.setMessage(encryptedMessage);

        channel.write(messagePacket);
    }

    //TODO: Заглушки. Реализовать
    private String encryptMessage(String message) {
        return message;
    }

    private String decryptMessage(String message) {
        return message;
    }
}
