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
                PrintWriter writer = new PrintWriter(client_socket.getOutputStream(), true);

                writer.println(command);

                BufferedReader reader = new BufferedReader(new InputStreamReader(client_socket.getInputStream()));
                System.out.println(reader.readLine());

                writer.flush();
                client_socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
