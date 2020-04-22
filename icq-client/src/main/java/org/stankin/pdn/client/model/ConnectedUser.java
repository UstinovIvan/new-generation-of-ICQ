package org.stankin.pdn.client.model;

import org.stankin.pdn.client.ui.forms.TabMessageForm;

import java.security.PublicKey;

/**
 * Модель пользователя, с которым установлено соединение
 */
public class ConnectedUser {

    private PublicKey publicKey;
    private TabMessageForm userTab;

    public ConnectedUser(PublicKey publicKey, TabMessageForm userTab) {
        this.publicKey = publicKey;
        this.userTab = userTab;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public TabMessageForm getUserTab() {
        return userTab;
    }
}
