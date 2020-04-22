package org.stankin.pdn.client.worker;

import org.jboss.netty.channel.Channel;
import org.stankin.pdn.client.context.AppContext;
import org.stankin.pdn.client.handler.ServerHandler;
import org.stankin.pdn.client.packet.Packet;
import org.stankin.pdn.client.packet.Packet5EncrytedData;
import org.stankin.pdn.client.packet.Packet5File;
import org.stankin.pdn.client.packet.Packet5Message;
import org.stankin.pdn.crypto.api.model.EncrytedData;

import java.io.File;
import java.io.IOException;
import java.security.PublicKey;

/**
 * Обработчик входящих и исходящих сообщений и файлов
 */
public class MessageWorker implements ServerWorker {

    protected ServerHandler handler;
    protected Channel channel;

    private AppContext context = AppContext.getInstance();

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
        String decryptedMessage = decryptMessage(messagePacket.getMessage(),
                context.getConnectionList().get(((Packet5Message) packet).getFrom()).getPublicKey(),
                String.class);

        context.getConnectionList()
                .get(messagePacket.getFrom()).getUserTab().getTextArea()
                .append("\n" + messagePacket.getFrom() + ": " + decryptedMessage);
    }

    @Override
    public void sendPacket(Packet packet) {
        channel.write(packet);
    }

    @Override
    public void sendMessage(String message, String to) {
        Packet5EncrytedData messagePacket = new Packet5Message();
        messagePacket.setMessage(encryptMessage(message, context
                .getConnectionList().get(to).getPublicKey()));
        messagePacket.setTo(to);

        sendPacket(messagePacket);
    }

    @Override
    public void sendFile(File file, String to) {
        Packet5EncrytedData messagePacket = new Packet5File();
        messagePacket.setTo(to);
        EncrytedData encrytedData = encryptFile(file, context.getConnectionList().get(to).getPublicKey());

        if (encrytedData != null) {
            messagePacket.setMessage(encrytedData);
            sendPacket(messagePacket);
        }
    }

    private void acceptFile(Packet5EncrytedData packet) {
        File file = decryptMessage(packet.getMessage(), context.getConnectionList()
                .get(packet.getFrom()).getPublicKey(), File.class);

        context.getConnectionList()
                .get(packet.getFrom()).getUserTab().getTextArea()
                .append("\n" + packet.getFrom() + " прислал файл. Он находится здесь: "
                        + file.getAbsolutePath());
    }


    private EncrytedData encryptMessage(String message, PublicKey publicKey) {
        return context.getCryptoApi().encryteMessage(message, publicKey);
    }

    private EncrytedData encryptFile(File file, PublicKey publicKey) {
        try {
            return context.getCryptoApi().encryteMessage(file, publicKey);
        } catch (IOException e) {
            this.handler.getUi().showError("Ошибка при отправке файла: " + e.getMessage()
                    + "\nОбратитесь к разработчику");
            e.printStackTrace();
        }

        return null;
    }

    private <T> T decryptMessage(EncrytedData message, PublicKey publicKey, Class<T> clazz) {
        T decryteMessage = null;
        try {
            decryteMessage = clazz.cast(context.getCryptoApi().decryteData(message, publicKey));
        } catch (Exception e) {
            handler.getUi().showError("Ошибка при расшифровке сообщения: " + e.getMessage() +
                    "\nОбратитесь к разработчику");
            e.printStackTrace();
        }

        return decryteMessage;
    }
}
