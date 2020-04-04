package org.stankin.pdn.server.worker;

import org.stankin.pdn.server.packet.Packet;

public interface ClientWorker {

    void disconnectedFromChannel();

    void acceptPacket(Packet packet);
}
