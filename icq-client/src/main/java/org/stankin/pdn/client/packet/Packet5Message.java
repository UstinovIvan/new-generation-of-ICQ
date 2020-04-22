package org.stankin.pdn.client.packet;

import org.jboss.netty.buffer.ChannelBuffer;

/**
 * Пакет для клиента, содержащий сообщение
 */
public class Packet5Message extends Packet5EncrytedData {

    private final int ID = 50;

    @Override
    public void get(ChannelBuffer buffer) {
        super.get(buffer);
    }

    @Override
    public void send(ChannelBuffer buffer) {
        super.send(buffer);
    }

    @Override
    public int getID() {
        return this.ID;
    }

}
