package org.stankin.pdn.client.ui;

import javax.swing.*;
import java.awt.*;

public class StartForm extends JFrame {
    private JButton button1;
    private JPanel panel1;

    public StartForm() {
        setContentPane(panel1);
    }

    public static void main(String[] args) {
        new StartForm();
    }

    public JPanel getPanel1() {
        return panel1;
    }

    public JButton getButton1() {
        return button1;
    }
}
