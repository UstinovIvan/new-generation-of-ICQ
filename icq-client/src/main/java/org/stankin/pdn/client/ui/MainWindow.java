package org.stankin.pdn.client.ui;

import org.stankin.pdn.client.ClientApp;
import org.stankin.pdn.client.context.AppContext;
import org.stankin.pdn.client.packet.*;
import org.stankin.pdn.client.ui.forms.*;
import org.stankin.pdn.client.worker.ServerWorker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.List;

/**
 * Класс обработки данных со всех форм
 *
 * @Deprecated - Необходимо разделить создание форм и передавать данные с формы на бэкенд через шлюз
 */

@Deprecated
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

                //TODO: переписать эту хрень
                AppContext.getInstance().setUsername(frame.getLoginField().getText());
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
                    worker.sendPacket(new Packet3PairCreate()
                            .withTo((String) list.getModel().getElementAt(index)));
                }
            }
        });

        setVisible(true);
    }

    public void showError(String error) {
        int widthFrame = error.length() > 10 ? error.length() * 10 : 200;

        ErrorForm frame = new ErrorForm();
        setEnabled(false);
        frame.setBounds(getBounds());
        frame.setSize(new Dimension(widthFrame, 150));
        frame.setAlwaysOnTop(true);

        frame.getErrorMessage().setText(error);
        frame.getOkButton().addActionListener(e -> {
            frame.dispatchEvent(
                    new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            setEnabled(true);
        });

        frame.setVisible(true);
    }

    public void refreshUserList(List<String> users) {
        DefaultListModel<String> defaultListModel = new DefaultListModel<>();
        defaultListModel.addAll(users);
        mainDialogueForm.getUserList().setModel(defaultListModel);
    }

    public TabMessageForm addUserTab(String username) {
        TabMessageForm tabPanel = new TabMessageForm(username);
        tabPanel.getSendButton().addActionListener(e -> {
            String str = tabPanel.getTextField1().getText();

            worker.sendMessage(str, tabPanel.getName());

            tabPanel.getTextArea().append("\nВы: " + str);
            tabPanel.getTextField1().setText("");
        });
        tabPanel.getFileButton().addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            int returnVal = fc.showOpenDialog(this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                System.out.println("Выбран файл " + file);

                worker.sendFile(file, tabPanel.getName());

                tabPanel.getTextArea().append("\nВы отправили файл " + file.getAbsolutePath());
            } else {
                System.out.println("Выбор файла отменен");
            }
        });
        mainDialogueForm.getTabbedPane1().addTab(username, tabPanel.getMainPanel());

        return tabPanel;
    }

    private void clearAll() {
        getContentPane().removeAll();
        repaint();
    }

    public void setWorker(ServerWorker worker) {
        this.worker = worker;
    }
}
