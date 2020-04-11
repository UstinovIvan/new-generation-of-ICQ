package org.stankin.pdn.client.ui;

import org.stankin.pdn.client.ClientApp;
import org.stankin.pdn.client.packet.Packet;
import org.stankin.pdn.client.packet.Packet1LoginRequest;
import org.stankin.pdn.client.packet.Packet2PairRequest;
import org.stankin.pdn.client.packet.Packet2UsersListRequest;
import org.stankin.pdn.client.ui.forms.*;
import org.stankin.pdn.client.worker.ServerWorker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.util.List;

public class MainWindow extends JFrame {

    private final ClientApp clientApp;
    private final MainWindow instance;
    private ServerWorker worker;

    private MainDialogueForm mainDialogueForm;

    public MainWindow(ClientApp clientApp) throws HeadlessException {
        this.clientApp = clientApp;
        this.instance = this;

        setBounds(600, 300, 600, 500);
        setTitle("Corporate Messenger v1");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        connectWindow();
    }

    public void connectWindow() {
        clearAll();
        StartForm frame = new StartForm();

        frame.getButton().addActionListener(e -> {
            clientApp.startClient(instance);
            System.out.println("Click!");
        });
        add(frame.getPanel());
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

        add(frame.getPanel());
        setVisible(true);
    }

    public void showMainForm() {
        clearAll();
        mainDialogueForm = new MainDialogueForm();
        add(mainDialogueForm.getMainPanel());

        mainDialogueForm.getRefreshButton().addActionListener(e -> worker.sendPacket(new Packet2UsersListRequest()));
        mainDialogueForm.getUserList().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JList list = (JList) e.getSource();
                if (e.getClickCount() == 2) {
                    int index = list.locationToIndex(e.getPoint());
                    worker.sendPacket(new Packet2PairRequest()
                            .withUserToPair((String) list.getModel().getElementAt(index)));
                }
            }
        });

        setVisible(true);
    }

    public void showError(String error) {
        ErrorForm frame = new ErrorForm();
        frame.setBounds(900, 550, 200, 150);

        frame.getErrorMessage().setText(error);
        frame.getOkButton().addActionListener(e -> frame.dispatchEvent(
                new WindowEvent(frame, WindowEvent.WINDOW_CLOSING)));

        frame.setVisible(true);
    }

    public void refreshUserList(List<String> users) {
        DefaultListModel<String> defaultListModel = new DefaultListModel<>();
        defaultListModel.addAll(users);
        mainDialogueForm.getUserList().setModel(defaultListModel);
    }

    public void addUserTab(String username) {
        mainDialogueForm.getTabbedPane1().addTab(username, new TabMessageForm().getMainPanel());
    }

    private void clearAll() {
        getContentPane().removeAll();
        repaint();
    }

    public void setWorker(ServerWorker worker) {
        this.worker = worker;
    }
}
