package org.stankin.pdn.client.packet;

public abstract class TransmittablePacket extends Packet implements Transmittable {

    protected String from;
    protected String to;

    @Override
    public String getFrom() {
        return from;
    }

    @Override
    public void setFrom(String from) {
        this.from = from;
    }

    @Override
    public String getTo() {
        return to;
    }

    @Override
    public void setTo(String to) {
        this.to = to;
    }
}
