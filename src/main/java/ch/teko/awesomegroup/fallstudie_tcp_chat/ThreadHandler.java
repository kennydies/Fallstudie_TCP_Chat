package ch.teko.awesomegroup.fallstudie_tcp_chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadHandler extends Thread{
    
    private Socket con_socket;
    private ChatController chatController;
    
    public ThreadHandler (Socket con_socket){
        this.con_socket = con_socket;
    }

    @Override
    public void run() {

        // TODO Auto-generated method stub
        super.run();
        


        while(true) {
            try {
                ObjectInputStream objectInput = new ObjectInputStream(con_socket.getInputStream());
                Message message = (Message)objectInput.readObject();
                
                System.out.println(message.getUsername());
                
                switch (message.getType()) {
                    case "register":
                        System.out.println("registered");
                        break;
                    case "send":
                        break;
                    case "get":
                        break;
                    default:
                        System.out.println("Invalid request: " + message.getType());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            // BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(con_socket.getOutputStream()));
            // writer.write(response);
            // writer.newLine();
            // writer.flush();
        }

        // con_socket.close();
    }
}
