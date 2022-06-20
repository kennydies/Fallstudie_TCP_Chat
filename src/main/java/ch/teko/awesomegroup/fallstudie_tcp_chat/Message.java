package ch.teko.awesomegroup.fallstudie_tcp_chat;

import java.io.Serializable;

public class Message implements Serializable {
    
    private String username;
    private String message;
    private String type;

    public Message(String username, String message, String type) {
        this.username = username;
        this.message = message;
        this.type = type;
    }

    public String getType() {
        return type;
    }
    public String getMessage() {
        return message;
    }
    public String getUsername() {
        return username;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setType(String type) {
        this.type = type;
    }
}
