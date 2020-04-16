package org.stankin.pdn.client.worker;

import org.jboss.netty.channel.Channel;
import org.stankin.pdn.client.context.AppContext;
import org.stankin.pdn.client.handler.ServerHandler;
import org.stankin.pdn.client.packet.Packet;
import org.stankin.pdn.client.packet.Packet5File;
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
        if (packet.getID() == 51) {
            acceptFile((Packet5File) packet);
            return;
        }

        Packet5Message messagePacket = (Packet5Message) packet;
        String decryptedMessage = decryptMessage(messagePacket.getMessage());

        AppContext.getInstance().getConnectionList()
                .get(messagePacket.getFrom()).getUserTab().getTextArea()
                .append("\n" + messagePacket.getFrom() + ": " + decryptedMessage);
    }

    @Override
    public void sendPacket(Packet packet) {
        channel.write(packet);
    }

    void sendMessage(Packet packet) {
        Packet5Message messagePacket = (Packet5Message) packet;
        String encryptedMessage = encryptMessage(messagePacket.getMessage());
        messagePacket.setMessage(encryptedMessage);

        sendPacket(messagePacket);
    }

    void sendFile(Packet packet) {
        //TODO: реализовать шифрование
        sendPacket(packet);
    }

    private void acceptFile(Packet5File packet5File) {
        AppContext.getInstance().getConnectionList()
                .get(packet5File.getFrom()).getUserTab().getTextArea()
                .append("\n" + packet5File.getFrom() + " прислал файл. Он находится здесь: "
                        + packet5File.getFile().getAbsolutePath());
    }

    //TODO: Заглушки. Реализовать
    private String encryptMessage(String message) {
        return message;
    }

    private String decryptMessage(String message) {
        return message;
    }
}
