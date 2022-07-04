package ch.teko.awesomegroup.fallstudie_tcp_chat;

import java.io.Serializable;

/**
 * Klasse, um eine Nachricht zu verwalten.
 */
public class Message implements Serializable {
    
    private String username;
    private String message;
    private String type;

    /**
     * Konstruktor. Setzt Benutzername, Nachricht und Typ.
     * @param username
     * @param message
     * @param type
     */
    public Message(String username, String message, String type) {
        this.username = username;
        this.message = message;
        this.type = type;
    }

    /**
     * Gibt den Typ einer Nachricht zurück.
     * @return Typ als String
     */
    public String getType() {
        return type;
    }

    /**
     * Gibt die Nachricht zurück.
     * @return Nachricht als String
     */
    public String getMessage() {
        return message;
    }

    /**
     * Gibt den Benutzername zurück.
     * @return Benutzername als String
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setzt die Nachricht.
     * @param message - Nachricht als String
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Setzt den Benutzername.
     * @param username - Benutzername als String
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Setzt den Typ.
     * @param type - Typ als String
     */
    public void setType(String type) {
        this.type = type;
    }
}
