package org.stankin.pdn.client.packet;

import org.jboss.netty.buffer.ChannelBuffer;

import java.io.*;
import java.nio.file.Files;

/**
 * Пакет для клиента, содержащий файл
 */
public class Packet5File extends TransmittablePacket {

    private final int ID = 51;

    private File file;

    @Override
    public void get(ChannelBuffer buffer) {
        super.get(buffer);

        int length = buffer.readShort();
        String filename = readBuffer(length, buffer);

        length = buffer.readInt();
        try {
            file = new File(filename);

            byte[] fileBytes = new byte[length];
            buffer.readBytes(fileBytes);
            Files.write(file.toPath(), fileBytes);
        } catch (IOException e) {
            System.out.println("Ошибка получения файла");
        }

    }

    @Override
    public void send(ChannelBuffer buffer) {
        super.send(buffer);

        writeBuffer(file.getName(), buffer);

        try {
            byte[] fileBytes = Files.readAllBytes(file.toPath());
            buffer.writeInt(fileBytes.length);
            buffer.writeBytes(fileBytes);

        } catch (IOException e) {
            System.out.println("Ошибка при передаче файла");
        }

    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Packet5File withFile(File file) {
        this.file = file;
        return this;
    }

    @Override
    public int getID() {
        return this.ID;
    }
}
