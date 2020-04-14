package org.stankin.pdn.server.worker;

import org.jboss.netty.channel.Channel;
import org.stankin.pdn.client.packet.*;
import org.stankin.pdn.server.context.ServerContext;
import org.stankin.pdn.server.handler.ClientHandler;
import org.stankin.pdn.server.model.Client;

public class PairClientWorker extends AbstractClientWorker {

    private ClientListWorker clientListWorker;
    private Client client;

    PairClientWorker(ClientHandler handler, Channel channel, Client client, ClientListWorker clientListWorker) {
        super(handler, channel);
        this.client = client;
        this.clientListWorker = clientListWorker;
    }

    @Override
    public void disconnectedFromChannel() {
        ServerContext.getInstance().removeClient(client);
        channel.close();
    }

    @Override
    public void acceptPacket(Packet packet) {
        if (packet instanceof Packet2UsersListRequest) {
            clientListWorker.acceptPacket(packet);
            return;
        }
        if (packet instanceof Transmittable) {
            transmitPacket((Transmittable) packet);
        }
    }

    private void transmitPacket(Transmittable transmittable) {
        transmittable.setFrom(this.client.getName());

        if (this.client.getConnectionList().containsKey(transmittable.getTo())) {
            this.client.getConnectionList().get(transmittable.getTo()).sendPacket((Packet) transmittable);
        } else {
            Client pair = ServerContext.getInstance().getActiveClient(transmittable.getTo());
            if (pair != null) {

                this.client.getConnectionList().put(pair.getName(), pair.getHandler().getClientWorker());

                Packet packet = (Packet) transmittable;
                pair.getHandler().getClientWorker().sendPacket(packet);
            }
        }
    }
}
