package ch.teko.awesomegroup.fallstudie_tcp_chat;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javafx.scene.control.TextArea;
import javafx.fxml.FXML;

public class ClientThread extends Thread {

    private Socket client_socket;
    @FXML private TextArea area_history;

    public ClientThread(Socket client_socket, TextArea area_history) {
        this.client_socket = client_socket;
        this.area_history = area_history;

    }
    
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
                // Message object = (Message) objectInput.readObject();
                // List<Message> chatHistory = new ArrayList<>();
                ArrayList<Message> chatHistory = (ArrayList<Message>) objectInput.readObject();

                // System.out.println(object);
                System.out.println(chatHistory);
                
                String result = "";
               for (Message m : chatHistory) {
                   result += m.getUsername() + ": \t" + m.getMessage() + "\n";
               }
               //result += object.getUsername() + ": \t" + object.getMessage() + "\n";

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
