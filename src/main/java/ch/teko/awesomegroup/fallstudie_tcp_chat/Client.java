package ch.teko.awesomegroup.fallstudie_tcp_chat;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        System.out.println("Start Client");

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter command:");

            String command = scanner.nextLine();

            try {
                Socket client_socket = new Socket(InetAddress.getByName("127.0.0.1"), 1200);

                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client_socket.getOutputStream()));
                writer.write(command);
                writer.newLine();
                writer.flush();

                BufferedReader reader = new BufferedReader(new InputStreamReader(client_socket.getInputStream()));
                System.out.println(reader.readLine());

                client_socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void connect() {
        try {
            Socket client_socket = new Socket(InetAddress.getByName("127.0.0.1"), 1200);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void refreshHistory() {

    }

    // for (Message m : chatHistory) {
    //     result += m.getUsername() + ": \t" + m.getMessage() + "\n";
    // }
}
