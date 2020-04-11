package org.stankin.pdn.client.ui.forms;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainDialogueForm extends JFrame {
    private JPanel mainPanel;
    private JList userList;
    private JPanel userListLabel;
    private JTabbedPane tabbedPane1;
    private JButton refreshButton;

    public MainDialogueForm() {
        setContentPane(mainPanel);
    }

    public JList getUserList() {
        return userList;
    }

    public JTabbedPane getTabbedPane1() {
        return tabbedPane1;
    }

    public JButton getRefreshButton() {
        return refreshButton;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public static void main(String[] args) {
        MainDialogueForm mainDialogueForm = new MainDialogueForm();
        mainDialogueForm.setBounds(600, 300, 600, 500);
        mainDialogueForm.setVisible(true);
        mainDialogueForm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        List<String> list = new ArrayList<>();
        list.add("zalupa");
        list.add("konina");
        list.add("svinina");
        list.add("govno");
        DefaultListModel<String> defaultListModel = new DefaultListModel<>();
        defaultListModel.addAll(list);
        mainDialogueForm.getUserList().setModel(defaultListModel);
        System.out.println(mainDialogueForm.getUserList().getModel());
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
        mainPanel.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        userListLabel = new JPanel();
        userListLabel.setLayout(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(userListLabel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Список пользователей");
        userListLabel.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        userList = new JList();
        final DefaultListModel defaultListModel1 = new DefaultListModel();
        userList.setModel(defaultListModel1);
        userList.setSelectionMode(0);
        userList.putClientProperty("List.isFileList", Boolean.FALSE);
        userListLabel.add(userList, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        refreshButton = new JButton();
        refreshButton.setLabel("Обновить");
        refreshButton.setText("Обновить");
        userListLabel.add(refreshButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(panel1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        tabbedPane1 = new JTabbedPane();
        panel1.add(tabbedPane1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPane1.addTab("Untitled", panel2);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
}
