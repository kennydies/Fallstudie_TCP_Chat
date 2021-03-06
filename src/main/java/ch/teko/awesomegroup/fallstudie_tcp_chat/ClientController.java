package ch.teko.awesomegroup.fallstudie_tcp_chat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Klasse, die Methoden für Client Operationen zur Verfügung stellt 
 */
public class ClientController {

    @FXML private TextField txt_username;
    @FXML private TextArea area_history;
    @FXML private TextArea area_message;
    @FXML private Button btn_connect;

    private Socket client_socket;
    
    @FXML private void connectButtonAction(ActionEvent event) {
        System.out.println("name: " + txt_username.getText());
        
        if (register()) {
            txt_username.setDisable(true);
            btn_connect.setDisable(true);
        }
    }

    @FXML private void sendButtonAction(ActionEvent event) {
        System.out.println("message: " + area_message.getText());
        send();
    }

    /**
     * Setzt eine 'register' Nachricht mit Nutzernamen an den Server ab und
     * initiiert neuen Verbindungs-Thread für den Client
     * @return boolean, Verbindungsversuch erfolgreich (true) oder nicht (false)
     */
    public boolean register() {
        try {
            client_socket = new Socket(InetAddress.getByName("127.0.0.1"), 1200);
            Message m = new Message(txt_username.getText(), "", "register");
            ObjectOutputStream objectOutput = new ObjectOutputStream(client_socket.getOutputStream());
            objectOutput.writeObject(m);
            objectOutput.flush();
            refreshHistory(client_socket);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Setzt eine 'send' Nachricht mit Nutzername und Nachricht an den Server ab
     * Leert Nachrichtenfeld nach Absenden der Nachricht
     */
    public void send() {
        Message m = new Message(txt_username.getText(), area_message.getText(), "send");
        try {
            ObjectOutputStream objectOutput = new ObjectOutputStream(client_socket.getOutputStream());
            objectOutput.writeObject(m);
            objectOutput.flush();
            area_message.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Startet neuen Client Thread 
     * @param client_socket
     */
    public void refreshHistory(Socket client_socket) {
        ClientThread t = new ClientThread(client_socket, area_history);
        t.setDaemon(true);
        t.start();
    }
}