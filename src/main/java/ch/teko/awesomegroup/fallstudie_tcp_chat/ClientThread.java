package ch.teko.awesomegroup.fallstudie_tcp_chat;

import java.io.BufferedWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.List;
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

        while (true){

            try {

                Message message = new Message("", "", "get");
                try {
                    ObjectOutputStream objectOutput = new ObjectOutputStream(client_socket.getOutputStream());
                    objectOutput.writeObject(message);
                    objectOutput.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                ObjectInputStream objectInput = new ObjectInputStream(client_socket.getInputStream());
                Object object = (List<Message>) objectInput.readObject();
                List<Message> chatHistory = (List<Message>) object;

                String result = "";
                for (Message m : chatHistory) {
                    result += m.getUsername() + ": \t" + m.getMessage() + "\n";
                }

                area_history.setText(result);

                sleep(1000);

            } catch (Exception e) {
                //TODO: handle exception
            }
        }
    }
}
