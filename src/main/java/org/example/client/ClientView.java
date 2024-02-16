package org.example.client;

import org.example.server.Server;
import org.example.server.ServerInterface;
import org.example.server.ServerWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ClientView extends JFrame implements ClientInterface {
    ServerInterface serverInterface;
    private Client client;
    private static final int WIDTH = 350;
    private static final int HEIGHT = 400;

    JTextArea textArea;
    JTextField IPAddress, port, login, tfMessage;
    JPasswordField password;
    JButton btnLogin, btnSend;
    JPanel panelTop;
    private boolean conected;

    public ClientView(ServerInterface serverInterface) {
        setting(serverInterface);


        creatPanel();
        setVisible(true);
    }
    private void setting(ServerInterface serverInterface){
        this.serverInterface = serverInterface;
        setTitle("ChatClient");
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        client = new Client(this, serverInterface);
    }

    private void creatPanel() {

        add(creatPanelTop(), BorderLayout.NORTH);
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);
        add(creatPanelBottom(), BorderLayout.SOUTH);
    }

    private JPanel creatPanelTop() {
        panelTop = new JPanel(new GridLayout(2, 3));
        IPAddress = new JTextField("192.168.0.1.1");
        port = new JTextField("8080");
        login = new JTextField("Ervin");
        password = new JPasswordField("123456");
        btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connectToServer(login.getText());
            }
        });
        panelTop.add(IPAddress);
        panelTop.add(port);
        panelTop.add(new JLabel());
        panelTop.add(login);
        panelTop.add(password);
        panelTop.add(btnLogin);
        add(panelTop, BorderLayout.NORTH);

        return panelTop;
    }

    private JPanel creatPanelBottom() {
        JPanel panelBottom = new JPanel(new GridLayout(1, 2));
        btnSend = new JButton("Send");
        tfMessage = new JTextField();
        tfMessage.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == '\n') {
                    sendMessage();
                }
            }
        });
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        panelBottom.add(tfMessage);
        panelBottom.add(btnSend, BorderLayout.EAST);

        return panelBottom;
    }

    @Override
    public void connectToServer(String name) {
        if(Server.getServerIsRun()){
            client.connect(name);
        }

    }

    @Override
    public void disconnectFromServer() {

    }

    @Override
    public void sendMessage() {
        String message = tfMessage.getText();
        if(!message.isEmpty()){
            client.sendMessage(message);
            tfMessage.setText("");
        }
    }

    @Override
    public void receiveMessage(String message) {
        textArea.append(message + "\n");
    }
}
