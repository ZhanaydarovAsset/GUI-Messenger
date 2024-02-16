package org.example;

import org.example.client.Client;
import org.example.client.ClientInterface;
import org.example.client.ClientView;
import org.example.server.Server;
import org.example.server.ServerInterface;
import org.example.server.ServerWindow;

public class Main {
    public static void main(String[] args) {

         ServerInterface serverInterface = new ServerWindow();

        new ClientView(serverInterface);
    }
}