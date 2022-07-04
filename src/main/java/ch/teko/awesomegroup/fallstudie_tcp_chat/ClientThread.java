package ch.teko.awesomegroup.fallstudie_tcp_chat;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;


import javafx.scene.control.TextArea;
import javafx.fxml.FXML;

/**
 * Klasse, die den Verbindungs Thread handelt und sich um die periodische Aktualisierung des Nachrichtenverlaufs kümmert
 */
public class ClientThread extends Thread {

    private Socket client_socket;
    @FXML private TextArea area_history;

    public ClientThread(Socket client_socket, TextArea area_history) {
        this.client_socket = client_socket;
        this.area_history = area_history;

    }

    /**
     * Startet den Client Thread, aktualisiert den Nachrichtenverlauf jede Sekunde.
     */
    @Override
    public void run() {
        Message message = new Message("", "", "get");
        
        while (true) {
            try {
                ObjectOutputStream objectOutput = null;
                objectOutput = new ObjectOutputStream(client_socket.getOutputStream());
                objectOutput.writeObject(message);
                objectOutput.flush();

                System.out.println("Get request from client");

                ObjectInputStream objectInput = new ObjectInputStream(client_socket.getInputStream());
                
                ArrayList<Message> chatHistory = (ArrayList<Message>) objectInput.readObject();
                
                System.out.println(chatHistory);
                
                String result = "";
               for (Message m : chatHistory) {
                   result += m.getUsername() + ": \t" + m.getMessage() + "\n";
               }
                area_history.setText(result);
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
