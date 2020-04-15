package org.stankin.pdn.client.ui.forms;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import java.awt.*;

public class TabMessageForm extends JPanel {
    private JPanel mainPanel;
    private JTextField textField1;
    private JButton fileButton;
    private JButton sendButton;
    private JButton fileAttachButton;
    private JPanel bottomPanel;
    private JTextArea textArea;

    public TabMessageForm(String name) {
        super();
        this.setName(name);
        this.mainPanel.setName(name);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public JButton getSendButton() {
        return sendButton;
    }

    public JButton getFileAttachButton() {
        return fileAttachButton;
    }

    public JTextArea getTextArea() {
        return textArea;
    }



    public static void main(String[] args) {
        MainDialogueForm mainDialogueForm = new MainDialogueForm();
        mainDialogueForm.setBounds(600, 300, 600, 500);
        mainDialogueForm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainDialogueForm.setVisible(true);

        TabMessageForm newTab = new TabMessageForm("name");
        //newTab.setPreferredSize(new Dimension(100, 100));
        //mainDialogueForm.getTabbedPane1().add();
        mainDialogueForm.getTabbedPane1().addTab("zalupa", newTab.getMainPanel());


        mainDialogueForm.setVisible(true);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.setAutoscrolls(true);
        mainPanel.setOpaque(false);
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(bottomPanel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        textField1 = new JTextField();
        textField1.setAlignmentX(0.0f);
        textField1.setAlignmentY(0.0f);
        textField1.setMargin(new Insets(2, 6, 2, 6));
        textField1.setToolTipText("Введите ваше сообщение");
        bottomPanel.add(textField1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        sendButton = new JButton();
        sendButton.setText("Отправить");
        bottomPanel.add(sendButton, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        fileButton = new JButton();
        fileButton.setText("Файл");
        bottomPanel.add(fileButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFocusable(false);
        mainPanel.add(textArea, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

}
