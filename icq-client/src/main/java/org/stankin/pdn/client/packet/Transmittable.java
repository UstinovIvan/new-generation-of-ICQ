package org.stankin.pdn.client.packet;

public interface Transmittable {

    String getTo();
    String getFrom();

    void setTo(String to);
    void setFrom(String from);

}
