package org.stankin.pdn.client.worker;

import org.stankin.pdn.client.packet.Packet;

public interface ServerWorker {

    void disconnectedFromChannel();

    void acceptPacket(Packet packet);

    void sendPacket(Packet packet);
}
