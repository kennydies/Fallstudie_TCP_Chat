package ch.teko.awesomegroup.fallstudie_tcp_chat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static ChatController chatController = new ChatController();
    
    public static void main(String[] args) {
        System.out.println("Start Server");
        
        try {
            ServerSocket server_socket = new ServerSocket(1200);
            while(true) {
                Socket con_socket = server_socket.accept();

                if(con_socket.isConnected()){
                    ThreadHandler t = new ThreadHandler(con_socket, chatController);
                    t.start();
                } 

            }
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}