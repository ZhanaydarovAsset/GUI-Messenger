package org.example.server;

import org.example.client.Client;
import org.example.common.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ServerWindow extends JFrame  implements ServerInterface{
    private static final int WIDTH = 350;
    private static final int HEIGHT = 400;
    private JButton btnStart, btnStop;
    private JTextArea textArea;
    Server server;


    public ServerWindow(){
        server = new Server(this);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Server");
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        panelCreat();
        setVisible(true);
    }


    private void panelCreat(){
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);
        add(creatButtons(), BorderLayout.SOUTH);

    }
    private JPanel creatButtons(){
        JPanel bottomPanel = new JPanel(new GridLayout(1, 2));

        btnStart = new JButton("Start");
        btnStop = new JButton("Stop");

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Server.getServerIsRun()){
                    textArea.append("Сервер остановлен \n");
                     server.stop();
                    Utils.writeToLog(textArea.getText());
                }
            }
        });
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!Server.getServerIsRun()){
                    textArea.append("Сервер запущен \n");
                    server.start();
                    Utils.writeToLog(textArea.getText());
                }
            }
        });
        bottomPanel.add(btnStart);
        bottomPanel.add(btnStop);

        return bottomPanel;
    }

    @Override
    public void stop() {

    }

    @Override
    public void start() {

    }

    @Override
    public void connectToUser(Client client) {

    }

    @Override
    public void disconnectUser(Client client) {

    }

    @Override
    public void sendMessage() {

    }
}
