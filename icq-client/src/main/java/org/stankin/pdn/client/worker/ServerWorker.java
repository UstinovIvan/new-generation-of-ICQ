package org.stankin.pdn.client.worker;

import org.stankin.pdn.client.packet.Packet;

import java.io.File;

/**
 * Интерфейс обработчика команд и пакетов, полученных от сервера
 */
public interface ServerWorker {

    void disconnectedFromChannel();

    void acceptPacket(Packet packet);

    void sendPacket(Packet packet);

    void sendMessage(String message, String to);

    void sendFile(File file, String to);
}
