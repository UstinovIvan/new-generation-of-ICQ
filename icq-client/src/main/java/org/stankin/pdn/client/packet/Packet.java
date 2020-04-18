package org.stankin.pdn.client.packet;

import org.jboss.netty.buffer.ChannelBuffer;

import java.io.IOException;

/**
 * Базовый класс для всех пакетов
 */
public abstract class Packet {

    /**
     * Определение валидности пакета
     *
     * @param buffer - Входной буфер
     * @return - Инстанс пакета для обработки
     * @throws IOException - Проверка валидации
     */
    public static Packet read(ChannelBuffer buffer) throws IOException {
        int id = buffer.readUnsignedShort(); // Получаем ID пришедшего пакета, чтобы определить, каким классом его читать
        //Определяет, что за пакет - логин, сообщение или что-то еще. Через instanceof
        Packet packet = getPacket(id); // Получаем инстанс пакета с этим ID
        if (packet == null)
            throw new IOException("Bad packet ID: " + id); // Если произошла ошибка и такого пакета не может быть, генерируем исключение
        packet.get(buffer); // Читаем в пакет данные из буфера
        return packet;
    }

    /**
     * Отправка пакета
     *
     * @param packet - Отправляемый пакет
     * @param buffer - Буфер, через который нужно отправить пакет
     */
    public static void write(Packet packet, ChannelBuffer buffer) {
        buffer.writeChar(packet.getID()); // Отправляем ID пакета
        packet.send(buffer); // Отправляем данные пакета
    }

    /**
     * Получение инстанса пакета
     *
     * @param id - Уникальный идентификатор пакета
     * @return - Экземпляр пакета, если найдено совпадение идентификаторов, null если нет
     */
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
            case 50:
                return new Packet5Message();
            case 51:
                return new Packet5File();
            case 250:
                return new Packet250UserDisconnected();
            case 255:
                return new Packet255Disconnect();
            default:
                return null;
        }
    }

    /**
     * Обработка и считывание данных из буфера в пакет
     *
     * @param buffer - Входящий буфер
     */
    public abstract void get(ChannelBuffer buffer);

    /**
     * Обработка и записывание данных из пакета в буфер
     *
     * @param buffer - Исходящий буфер
     */
    public abstract void send(ChannelBuffer buffer);

    /**
     * Получение уникального идентификатора пакета
     *
     * @return - ID пакета
     */
    public abstract int getID();
}