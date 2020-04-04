package org.stankin.pdn.server.coder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;
import org.stankin.pdn.server.packet.Packet;

public class PacketFrameEncoder extends OneToOneEncoder {

    @Override
    protected Object encode(ChannelHandlerContext ctx, Channel channel, Object msg) {
        if (!(msg instanceof Packet)) { // Если это не пакет, то просто пропускаем его дальше
            return msg;
        }

        Packet p = (Packet) msg;

        // Создаём динамический буфер для записи в него данных из пакета. Если Вы точно знаете длину пакета,
        // Вам не обязательно использовать динамический буфер —
        // ChannelBuffers предоставляет и буферы фиксированной длинны, они могут быть эффективнее.
        ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();

        Packet.write(p, buffer); // Пишем пакет в буфер
        return buffer; // Возвращаем буфер, который и будет записан в канал
    }
}
