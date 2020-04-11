package org.stankin.pdn.client.ui;

import javax.swing.*;
import java.awt.*;

public class ErrorForm extends JFrame {
    private JPanel panel1;
    private JButton okButton;
    private JLabel errorMessage;

    public ErrorForm() throws HeadlessException {
        setContentPane(panel1);
    }

    public JButton getOkButton() {
        return okButton;
    }

    public JLabel getErrorMessage() {
        return errorMessage;
    }
}
