package org.stankin.pdn.client.packet;

import org.jboss.netty.buffer.ChannelBuffer;

import java.io.*;

public class Packet5File extends TransmittablePacket {

    private final int ID = 51;

    private File file;

    @Override
    public void get(ChannelBuffer buffer) {
        super.get(buffer);

        int nameLength = buffer.readShort();
        String filename = readBuffer(nameLength, buffer);

        byte[] fileBuffer = new byte[4096];
        file = new File(filename);
        try(OutputStream fos = new FileOutputStream(file);
            OutputStream bos = new BufferedOutputStream(fos)) {

            int readInt = 0;
            while ((readInt = buffer.readInt()) != -1) {
                //bos.write(buffer.readInt());
                fos.write(fileBuffer, 0, readInt);
            }
        } catch (IOException e) {
            System.out.println("Ошибка получения файла " + filename);
        }
    }

    @Override
    public void send(ChannelBuffer buffer) {
        super.send(buffer);

        writeBuffer(file.getName(), buffer);

        try (InputStream ios = new FileInputStream(file)) {
            byte[] fileBuffer = new byte[4096];
            int read = 0;
            while ((read = ios.read(fileBuffer)) != -1) {
                buffer.writeInt(read);
            }

        } catch (IOException e) {
            System.out.println("Ошибка отправки файла " + file.getName());
        }
        buffer.writeInt(-1);
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
