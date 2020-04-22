package org.stankin.pdn.client.packet;

import org.jboss.netty.buffer.ChannelBuffer;

/**
 * Пакет для клиента, содержащий файл
 */
public class Packet5File extends Packet5EncrytedData {

    private final int ID = 51;

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
