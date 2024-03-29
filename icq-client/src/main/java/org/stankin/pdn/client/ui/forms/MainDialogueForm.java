package org.stankin.pdn.client.ui.forms;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainDialogueForm extends JFrame {
    private JPanel mainPanel;
    private JList userList;
    private JPanel userListPanel;
    private JTabbedPane tabbedPane1;
    private JButton refreshButton;
    private JLabel userListLabel;
    private JScrollPane scrollPane;
    private JTextField searchField;

    public MainDialogueForm() {
        setContentPane(mainPanel);
        scrollPane.setViewportView(userList);
        addFilter();
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

    private void addFilter() {
        userList.setAutoscrolls(true);
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filter();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filter();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filter();
            }

            private void filter() {
                String filter = searchField.getText();
                filterModel((DefaultListModel<String>) userList.getModel(), filter);
            }
        });
    }

    private void filterModel(DefaultListModel<String> model, String filter) {
        String[] defaultValues = new String[model.size()];
        for (int i = 0; i < model.toArray().length; i++) {
            defaultValues[i] = model.get(i);
        }
        for (String s : defaultValues) {
            if (!s.startsWith(filter)) {
                if (model.contains(s)) {
                    model.removeElement(s);
                }
            } else {
                if (!model.contains(s)) {
                    model.addElement(s);
                }
            }
        }
    }

    public static void main(String[] args) {
        MainDialogueForm mainDialogueForm = new MainDialogueForm();
        mainDialogueForm.setBounds(600, 300, 600, 500);
        mainDialogueForm.setVisible(true);
        mainDialogueForm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add(i + "asd12");
        }
        DefaultListModel<String> defaultListModel = new DefaultListModel<>();
        defaultListModel.addAll(list);
        mainDialogueForm.getUserList().setModel(defaultListModel);

        TabMessageForm newTab = new TabMessageForm("name");
        newTab.getSendButton().addActionListener(e -> {
            newTab.getTextArea().append("\n" + newTab.getTextField1().getText());
            newTab.getTextField1().setText("");
        });

        mainDialogueForm.getTabbedPane1().addTab("one", newTab.getMainPanel());
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
        userListPanel = new JPanel();
        userListPanel.setLayout(new GridLayoutManager(4, 1, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(userListPanel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        userListLabel = new JLabel();
        userListLabel.setText("Список пользователей");
        userListPanel.add(userListLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        refreshButton = new JButton();
        refreshButton.setLabel("Обновить");
        refreshButton.setText("Обновить");
        userListPanel.add(refreshButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        userListPanel.add(panel1, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        scrollPane = new JScrollPane();
        scrollPane.setVisible(true);
        panel1.add(scrollPane, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        userList = new JList();
        panel1.add(userList, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        searchField = new JTextField();
        searchField.setToolTipText("Введите имя");
        userListPanel.add(searchField, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tabbedPane1 = new JTabbedPane();
        tabbedPane1.setEnabled(true);
        tabbedPane1.setTabLayoutPolicy(0);
        tabbedPane1.setTabPlacement(1);
        tabbedPane1.setToolTipText("Выберите пользователя справа");
        mainPanel.add(tabbedPane1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

}
