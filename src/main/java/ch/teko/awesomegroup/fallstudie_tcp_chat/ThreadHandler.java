package ch.teko.awesomegroup.fallstudie_tcp_chat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadHandler extends Thread{
    
    private Socket con_socket;
    private ChatController chatController;
    
    public ThreadHandler (Socket con_socket, ChatController chatController){
        this.con_socket = con_socket;
        this.chatController = chatController;
    }

    @Override
    public void run() {
        Boolean isRunning = true;
        while(isRunning) {
            try {
                ObjectInputStream objectInput = new ObjectInputStream(con_socket.getInputStream());
                Message message = (Message)objectInput.readObject();
                
                System.out.println(message.getUsername());
                
                switch (message.getType()) {
                    case "register":
                        System.out.println("registered");
                        break;
                    case "send":
                        System.out.println("send");
                        chatController.saveMessage(message);
                        break;
                    case "get":
                        System.out.println("get");
                        
                        ObjectOutputStream objectOutput = new ObjectOutputStream(con_socket.getOutputStream());
                        objectOutput.writeObject(chatController.getHistory(50));
                        objectOutput.flush();
                    
                        break;
                    default:
                        System.out.println("Invalid request: " + message.getType());
                }
            } catch (Exception e) {
                try {
                    isRunning = false;
                    con_socket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                e.printStackTrace();
            }
        }        
    }
}