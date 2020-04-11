package org.stankin.pdn.client.ui;

import javax.swing.*;
import java.awt.*;

public class LoginForm extends JFrame{
    private JTextField loginField;
    private JPanel panel1;
    private JButton sendButton;
    private JPasswordField passwordField;
    private JLabel nameLabel;
    private JLabel passLabel;

    public LoginForm() throws HeadlessException {
        setContentPane(panel1);
    }

    public JTextField getLoginField() {
        return loginField;
    }

    public JPanel getPanel1() {
        return panel1;
    }

    public JButton getSendButton() {
        return sendButton;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }
}
