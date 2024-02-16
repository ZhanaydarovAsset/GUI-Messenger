package org.example.server;

import org.example.client.Client;
import org.example.common.Utils;

import java.util.ArrayList;
import java.util.List;

public class Server implements ServerInterface {
    private static boolean serverRun;
    List<Client> clients;

    public Server(ServerInterface serverInterface) {
        clients = new ArrayList<>();
        serverRun = false;
    }


    public boolean clientConnect(Client client) {
        if (!serverRun) {
            return false;
        }
        clients.add(client);
        return true;
    }

    private void sendAllClient(String text) {
        for (Client client : clients) {
            client.receive(text);
        }
    }


    public static boolean getServerIsRun() {
        return serverRun;
    }

    @Override
    public void stop() {
        if (serverRun) {
            serverRun = false;
        }
    }

    @Override
    public void start() {
        if (!serverRun) {
            serverRun = true;
        }
    }

    @Override
    public void connectToUser(Client client) {
        if(serverRun) {
            if (!clients.contains(client)) {
                clients.add(client);
            }
        }
    }

    @Override
    public void disconnectUser(Client client) {
        clients.remove(client);
    }

    @Override
    public void sendMessage() {
        sendAllClient(Utils.readLog());
    }
}
