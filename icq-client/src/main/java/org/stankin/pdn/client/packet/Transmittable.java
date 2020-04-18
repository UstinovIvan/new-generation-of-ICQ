package org.stankin.pdn.client.packet;

/**
 * Интерфейс указывает серверу на то, что пакет нужно перенаправить другому пользователю
 */
public interface Transmittable {

    String getTo();

    String getFrom();

    void setTo(String to);

    void setFrom(String from);

}
