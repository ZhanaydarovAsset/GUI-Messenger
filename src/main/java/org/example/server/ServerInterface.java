package org.example.server;

import org.example.client.Client;

public interface ServerInterface {
    void stop();
    void start();
    void connectToUser(Client client);
    void disconnectUser(Client client);
    void sendMessage();
}
