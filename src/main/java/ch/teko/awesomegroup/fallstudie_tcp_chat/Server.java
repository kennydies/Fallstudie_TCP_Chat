package ch.teko.awesomegroup.fallstudie_tcp_chat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Klasse des Servers für die Verwaltung des Server Sockets und der eingehenden Client Verbindungen 
 */
public class Server {

    private static ChatController chatController = new ChatController();
    
     /**
     * Main Methode des Servers. Erstellt ein Socket und wartet auf Client anfragen.
     * Wenn ein Client sich verbindet wird ein ThreadHandler instanziert.
     */
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