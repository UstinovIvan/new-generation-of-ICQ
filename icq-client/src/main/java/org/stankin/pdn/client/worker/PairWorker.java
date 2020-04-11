package org.stankin.pdn.client.worker;

import org.stankin.pdn.client.context.AppContext;
import org.stankin.pdn.client.handler.ServerHandler;
import org.stankin.pdn.client.packet.Packet;
import org.stankin.pdn.client.packet.Packet2PairResponse;

public class PairWorker implements ServerWorker {

    private ServerHandler handler;

    PairWorker(ServerHandler handler) {
        this.handler = handler;
    }

    @Override
    public void disconnectedFromChannel() {

    }

    @Override
    public void acceptPacket(Packet packet) {
        Packet2PairResponse responsePacket = (Packet2PairResponse) packet;
        System.out.println(((Packet2PairResponse) packet).getUsername());
        if (isSuccessPacket(responsePacket)) {
            if (!checkIsConnectExist(responsePacket)) {
                addNewConnect(responsePacket);
            } else {
                this.handler.getUi().showError("Соединение уже установлено");
            }

        } else {
            this.handler.getUi().showError(responsePacket.getReason());
        }
    }

    @Override
    public void sendPacket(Packet packet) {

    }

    private boolean checkIsConnectExist(Packet2PairResponse packet) {
        return AppContext.getInstance().getConnectionList().containsKey(packet.getUsername());
    }

    private void addNewConnect(Packet2PairResponse packet) {
        AppContext.getInstance().getConnectionList().put(packet.getUsername(), packet.getSocketAddress());

        //Добавить вкладку на ui
    }

    private boolean isSuccessPacket(Packet2PairResponse packet) {
        return packet.getReason() == null;
    }
}
