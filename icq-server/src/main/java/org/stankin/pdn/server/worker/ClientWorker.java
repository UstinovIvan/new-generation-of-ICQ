package org.stankin.pdn.server.worker;

import org.stankin.pdn.client.packet.Packet;

public interface ClientWorker {

    void disconnectedFromChannel();

    void acceptPacket(Packet packet);

    void sendPacket(Packet packet);
}
