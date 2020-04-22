package org.stankin.pdn.client.worker;

import org.jboss.netty.channel.Channel;
import org.stankin.pdn.client.context.AppContext;
import org.stankin.pdn.client.handler.ServerHandler;
import org.stankin.pdn.client.model.ConnectedUser;
import org.stankin.pdn.client.packet.Packet;
import org.stankin.pdn.client.packet.Packet250UserDisconnected;
import org.stankin.pdn.client.packet.Packet3PairCreate;
import org.stankin.pdn.client.ui.forms.TabMessageForm;

import javax.swing.*;
import java.security.PublicKey;
import java.util.UUID;

/**
 * Обработчик запросов на создание пары
 */
public class PairWorker implements ServerWorker {

    protected ServerHandler handler;
    protected Channel channel;

    public PairWorker(ServerHandler handler, Channel channel) {
        this.handler = handler;
        this.channel = channel;
    }

    @Override
    public void acceptPacket(Packet packet) {
        Packet3PairCreate responsePacket = (Packet3PairCreate) packet;
        System.out.println("Получен пакет от " + ((Packet3PairCreate) packet).getFrom());

        if (responsePacket.getNeedAnswer() == 1) {
            this.sendPacket(
                    new Packet3PairCreate()
                            .withTo(responsePacket.getFrom())
                            .withPublicKey(generatePublicKey())
                            .withNeedAnswer((short) -1)
            );

        } else {
            System.out.println("На пакет от " + responsePacket.getFrom() + " не нужен ответ");
        }

        addNewConnect(responsePacket);
    }

    @Override
    public void disconnectedFromChannel() {

    }

    /**
     * Удаление пользователя из локального контекста, а так же закрытие связанных с ним вкладок,
     * если тот отключился от сервера
     *
     * @param packet - {@link Packet250UserDisconnected}
     */
    void removeUser(Packet packet) {
        Packet250UserDisconnected userDisconnected = (Packet250UserDisconnected) packet;
        ConnectedUser user = AppContext.getInstance().getConnectionList()
                .getOrDefault(userDisconnected.getUsername(), null);

        if (user != null) {
            JTabbedPane tabbedPane = (JTabbedPane) user.getUserTab().getMainPanel().getParent();
            tabbedPane.remove(user.getUserTab().getMainPanel());

            AppContext.getInstance().getConnectionList().remove(userDisconnected.getUsername());
            handler.getUi().showError("Пользователь " + userDisconnected.getUsername() + " отключился");
        }
    }

    @Override
    public void sendPacket(Packet packet) {
        Packet3PairCreate pairRequest = (Packet3PairCreate) packet;
        if (isConnectExist(pairRequest.getTo())) {
            this.handler.getUi().showError("Соединение уже установлено");
            return;
        }
        System.out.println("Отправлен пакет " + pairRequest.getTo());
        channel.write(packet);
    }

    /**
     * Заполнение пакета собственным открытым ключом
     *
     * @param packet - {@link Packet3PairCreate}
     */
    void fillSecurityAndSend(Packet packet) {
        sendPacket(((Packet3PairCreate) packet)
                .withPublicKey(generatePublicKey())
                .withNeedAnswer((byte) 1));
    }

    private boolean isConnectExist(String user) {
        return AppContext.getInstance().getConnectionList().containsKey(user);
    }

    /**
     * Добавление в контекст, а так же на UI пользователя, с которым была создана пара и
     * произведен обмен открытыми ключами
     *
     * @param packet - {@link Packet3PairCreate}
     */
    private void addNewConnect(Packet3PairCreate packet) {
        TabMessageForm tabForm = handler.getUi().addUserTab(packet.getFrom());
        AppContext.getInstance().getConnectionList().put(packet.getFrom(),
                new ConnectedUser(packet.getPublicKey(), tabForm));

        System.out.println("Foreign key = " + packet.getPublicKey());

    }

    //TODO: заглушка. Реализовать
    private PublicKey generatePublicKey() {
        return AppContext.getInstance().getCryptoApi().getPublicKey();
    }
}
