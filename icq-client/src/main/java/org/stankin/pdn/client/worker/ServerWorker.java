package org.stankin.pdn.client.worker;

import org.stankin.pdn.client.packet.Packet;

/**
 * Интерфейс обработчика команд и пакетов, полученных от сервера
 */
public interface ServerWorker {

    void disconnectedFromChannel();

    void acceptPacket(Packet packet);

    void sendPacket(Packet packet);
}
