package org.stankin.pdn.client.worker;

import org.stankin.pdn.client.context.AppContext;
import org.stankin.pdn.client.handler.ServerHandler;
import org.stankin.pdn.client.packet.Packet;
import org.stankin.pdn.client.packet.Packet3PairResponse;

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
        Packet3PairResponse responsePacket = (Packet3PairResponse) packet;
        System.out.println(((Packet3PairResponse) packet).getUsername());
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

    private boolean checkIsConnectExist(Packet3PairResponse packet) {
        return AppContext.getInstance().getConnectionList().containsKey(packet.getUsername());
    }

    private void addNewConnect(Packet3PairResponse packet) {
        AppContext.getInstance().getConnectionList().put(packet.getUsername(), packet.getSocketAddress());

        handler.getUi().addUserTab(packet.getUsername());
    }

    private boolean isSuccessPacket(Packet3PairResponse packet) {
        return packet.getReason() == null;
    }
}
