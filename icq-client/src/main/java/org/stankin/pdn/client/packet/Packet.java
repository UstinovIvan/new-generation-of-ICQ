package org.stankin.pdn.client.packet;

import org.jboss.netty.buffer.ChannelBuffer;

import java.io.IOException;

public abstract class Packet {

    public static Packet read(ChannelBuffer buffer) throws IOException {
        int id = buffer.readUnsignedShort(); // Получаем ID пришедшего пакета, чтобы определить, каким классом его читать
        //Определяет, что за пакет - логин, сообщение или что-то еще. Через instanceof
        Packet packet = getPacket(id); // Получаем инстанс пакета с этим ID
        if (packet == null)
            throw new IOException("Bad packet ID: " + id); // Если произошла ошибка и такого пакета не может быть, генерируем исключение
        packet.get(buffer); // Читаем в пакет данные из буфера
        return packet;
    }

    public static void write(Packet packet, ChannelBuffer buffer) {
        buffer.writeChar(packet.getID()); // Отправляем ID пакета
        packet.send(buffer); // Отправляем данные пакета
    }

    private static Packet getPacket(int id) {//TODO: Использовать другой механизм
        switch (id) {
            case 1:
                return new Packet1LoginRequest();
            case 11:
                return new Packet1LoginSuccess();
            case 12:
                return new Packet1LoginFailed();
            case 20:
                return new Packet2UsersListRequest();
            case 21:
                return new Packet2UsersListResponse();
            case 31:
                return new Packet3PairCreate();
            case 5:
                return new Packet5Message();
            case 255:
                return new Packet255Disconnect();
            default:
                return null;
        }
    }

    // Функции, которые должен реализовать каждый класс пакета
    public abstract void get(ChannelBuffer buffer);

    public abstract void send(ChannelBuffer buffer);

    public abstract int getID();
}