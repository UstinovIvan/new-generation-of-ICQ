package org.stankin.pdn.client.ui;

import org.stankin.pdn.client.ClientApp;
import org.stankin.pdn.client.packet.Packet;
import org.stankin.pdn.client.packet.Packet1LoginRequest;
import org.stankin.pdn.client.worker.ServerWorker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class MainWindow extends JFrame {

    private final ClientApp clientApp;
    private final MainWindow instance;
    private ServerWorker worker;

    private JFrame activeFrame;

    public MainWindow(ClientApp clientApp) throws HeadlessException {
        this.clientApp = clientApp;
        this.instance = this;

        setBounds(600, 300, 600, 500);
        setTitle("Corporate Messenger v1");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        connectWindow();
    }

    private void connectWindow() {
        StartForm frame = new StartForm();

        frame.getButton1().addActionListener(e -> {
            clientApp.startClient(instance);
            System.out.println("Click!");
        });
        add(frame.getPanel1());
        setVisible(true);
    }

    public void showLoginPage() {
        clearAll();
        LoginForm frame = new LoginForm();

        frame.getSendButton().addActionListener(e -> {
            if (!frame.getLoginField().getText().trim().isEmpty() &&
                    !frame.getLoginField().getText().trim().isEmpty()) {

                Packet loginPacket = new Packet1LoginRequest(frame.getLoginField().getText(),
                        new String(frame.getPasswordField().getPassword()));
                worker.sendPacket(loginPacket);
            }
            System.out.println("Send Click");
        });

        add(frame.getPanel1());
        setVisible(true);
    }

    public void showError(String error) {
        ErrorForm frame = new ErrorForm();
        frame.setBounds(900, 550, 200, 150);

        frame.getErrorMessage().setText(error);
        frame.getOkButton().addActionListener(e -> {
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        });

        frame.setVisible(true);
    }

    private void clearAll() {
        getContentPane().removeAll();
        repaint();
    }

    public void setWorker(ServerWorker worker) {
        this.worker = worker;
    }
}
