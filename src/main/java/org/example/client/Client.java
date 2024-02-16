package org.example.client;

import org.example.server.Server;
import org.example.server.ServerInterface;

public class Client {
    private ClientInterface clientInterface;
    private String name;
    ServerInterface server;
    private boolean connected;

    public Client(ClientInterface ci, ServerInterface server){

        this.clientInterface = ci;
        this.server = server;
    }

    public void connect(String name){
        this.name = name;
        clientInterface.connectToServer(name);
    }
    public void disconnect(){
        //
        clientInterface.disconnectFromServer();
    }
    public  void sendMessage(String message){


    }
    public void receive(String message){

        clientInterface.receiveMessage(message);
    }
}
