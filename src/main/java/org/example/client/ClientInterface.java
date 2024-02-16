package org.example.client;

public interface ClientInterface {

    void connectToServer(String name);
    void disconnectFromServer();
    void sendMessage();
    void receiveMessage(String message);
}
