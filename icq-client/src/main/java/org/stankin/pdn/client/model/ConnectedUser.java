package org.stankin.pdn.client.model;

import org.stankin.pdn.client.ui.forms.TabMessageForm;

/**
 * Модель пользователя, с которым установлено соединение
 */
public class ConnectedUser {

    private String publicKey;
    private TabMessageForm userTab;

    public ConnectedUser(String publicKey, TabMessageForm userTab) {
        this.publicKey = publicKey;
        this.userTab = userTab;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public TabMessageForm getUserTab() {
        return userTab;
    }
}
