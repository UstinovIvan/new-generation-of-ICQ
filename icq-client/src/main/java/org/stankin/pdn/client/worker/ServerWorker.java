package org.stankin.pdn.client.worker;

import org.stankin.pdn.client.packet.ClientPacket;

public interface ServerWorker {

    void disconnectedFromChannel();

    void acceptPacket(ClientPacket packet);

    void sendPacket(ClientPacket packet);
}
