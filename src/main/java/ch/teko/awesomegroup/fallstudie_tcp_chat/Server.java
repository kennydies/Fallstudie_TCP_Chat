package ch.teko.awesomegroup.fallstudie_tcp_chat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static int value = 42;
    
    public static void main(String[] args) {
        System.out.println("Start Server");
        String response;
        
        while(true) {
            try {
                ServerSocket server_socket = new ServerSocket(1200);
                Socket con_socket = server_socket.accept();
                
                BufferedReader reader = new BufferedReader(new InputStreamReader(con_socket.getInputStream()));
                String command = reader.readLine();
                
                switch (command) {
                    case "increase":
                        increase();
                        System.out.println("Value increased");
                        response = "Success: value increased";
                        break;
                    case "decrease":
                        decrease();
                        System.out.println("Value decreased");
                        response = "Success: value decreased";
                        break;
                    case "get":
                        int x = getValue();
                        System.out.println("Value being sent to client");
                        response = "Success: value is " + x;
                        break;
                    default:
                        System.out.println("Invalid request: " + command);
                        response = command + " is not a valid command";
                }

                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(con_socket.getOutputStream()));
                writer.write(response);
                writer.newLine();
                writer.flush();

                writer.close();
                reader.close();
                con_socket.close();
                server_socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static int getValue() {
        return value;
    }

    private static void increase() {
        value += 1;
    }
    
    private static void decrease() {
        value -= 1;
    }
}