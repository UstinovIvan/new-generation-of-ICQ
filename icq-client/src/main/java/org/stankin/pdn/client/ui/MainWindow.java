package org.stankin.pdn.client.ui;

import org.jboss.netty.channel.Channel;
import org.stankin.pdn.client.ClientApp;
import org.stankin.pdn.client.packet.Client1LoginPacket;
import org.stankin.pdn.client.packet.ClientPacket;
import org.stankin.pdn.client.worker.ServerWorker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {

    private final ClientApp clientApp;
    private final MainWindow instance;
    private ServerWorker worker;

    public MainWindow(ClientApp clientApp) throws HeadlessException {
        this.clientApp = clientApp;
        this.instance = this;

        setWindowOption();
    }

    private void setWindowOption() {
        setBounds(600, 300, 600, 500);
        setTitle("Client");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

//        jtaTextAreaMessage = new JTextArea();
//        jtaTextAreaMessage.setEditable(false);
//        jtaTextAreaMessage.setLineWrap(true);
//        JScrollPane jsp = new JScrollPane(jtaTextAreaMessage);
//        add(jsp, BorderLayout.CENTER);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel firstPanel = new JPanel();
        firstPanel.setLayout(new GridLayout(4, 4));
        firstPanel.setMaximumSize(new Dimension(300, 250));

        JButton jbConnect = new JButton("Подключиться");
        //jbConnect.setPreferredSize(new Dimension(100, 100));
        firstPanel.add(jbConnect, BorderLayout.CENTER);

        mainPanel.add(firstPanel);
        setContentPane(mainPanel);

        jbConnect.addActionListener(e -> {
            clientApp.startClient(instance);
            System.out.println("Click!");
        });

        mainPanel.setVisible(true);
        setVisible(true);
    }

    public void showError(String message) {
        JFrame errorFrame = new JFrame("Error");
        errorFrame.setBounds(600, 300, 300, 250);
        //errorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel jlMessage = new JLabel("Сообщение:");

        errorFrame.add(jlMessage, BorderLayout.NORTH);

        jlMessage.setText(message);
        //add(errorFrame);

        errorFrame.setVisible(true);
    }

    public void showLoginPage() {
        clearAll();

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel firstPanel = new JPanel();
        firstPanel.setLayout(new GridLayout(4, 4));
        firstPanel.setMaximumSize(new Dimension(300, 250));

        JTextField jtfLogin = new JTextField("Логин: ");
        JTextField jtfPassword = new JTextField("Пароль: ");
        JButton jbSend = new JButton("Отправить");

        jbSend.addActionListener(e -> {
            if (!jtfLogin.getText().trim().isEmpty() && !jtfPassword.getText().trim().isEmpty()) {
                ClientPacket loginPacket = new Client1LoginPacket(jtfLogin.getText(), jtfPassword.getText());
                worker.sendPacket(loginPacket);
            }
            System.out.println("Send Click");
        });

        firstPanel.add(jtfLogin, BorderLayout.NORTH);
        firstPanel.add(jtfPassword, BorderLayout.CENTER);
        firstPanel.add(jbSend, BorderLayout.SOUTH);

        mainPanel.add(firstPanel);
        mainPanel.setVisible(true);
        setContentPane(mainPanel);
        setVisible(true);
        //repaint();
    }


    private void clearAll() {
        getContentPane().removeAll();
        repaint();
    }

    public void setWorker(ServerWorker worker) {
        this.worker = worker;
    }
}
