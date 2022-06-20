package ch.teko.awesomegroup.fallstudie_tcp_chat;

import java.io.*;
import java.net.Socket;
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
        ObjectOutputStream objectOutput = null;
        
        try {
            objectOutput = new ObjectOutputStream(client_socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true){
            try {
//                if (objectOutput != null) {
                    objectOutput.writeObject(message);
                    objectOutput.flush();
//                }
                ObjectInputStream objectInput = new ObjectInputStream(client_socket.getInputStream());
                Message object = (Message) objectInput.readObject();
                List<Message> chatHistory = new List<Message>() {
                    @Override
                    public int size() {
                        return 0;
                    }

                    @Override
                    public boolean isEmpty() {
                        return false;
                    }

                    @Override
                    public boolean contains(Object o) {
                        return false;
                    }

                    @Override
                    public Iterator<Message> iterator() {
                        return null;
                    }

                    @Override
                    public Object[] toArray() {
                        return new Object[0];
                    }

                    @Override
                    public <T> T[] toArray(T[] a) {
                        return null;
                    }

                    @Override
                    public boolean add(Message message) {
                        return false;
                    }

                    @Override
                    public boolean remove(Object o) {
                        return false;
                    }

                    @Override
                    public boolean containsAll(Collection<?> c) {
                        return false;
                    }

                    @Override
                    public boolean addAll(Collection<? extends Message> c) {
                        return false;
                    }

                    @Override
                    public boolean addAll(int index, Collection<? extends Message> c) {
                        return false;
                    }

                    @Override
                    public boolean removeAll(Collection<?> c) {
                        return false;
                    }

                    @Override
                    public boolean retainAll(Collection<?> c) {
                        return false;
                    }

                    @Override
                    public void clear() {

                    }

                    @Override
                    public Message get(int index) {
                        return null;
                    }

                    @Override
                    public Message set(int index, Message element) {
                        return null;
                    }

                    @Override
                    public void add(int index, Message element) {

                    }

                    @Override
                    public Message remove(int index) {
                        return null;
                    }

                    @Override
                    public int indexOf(Object o) {
                        return 0;
                    }

                    @Override
                    public int lastIndexOf(Object o) {
                        return 0;
                    }

                    @Override
                    public ListIterator<Message> listIterator() {
                        return null;
                    }

                    @Override
                    public ListIterator<Message> listIterator(int index) {
                        return null;
                    }

                    @Override
                    public List<Message> subList(int fromIndex, int toIndex) {
                        return null;
                    }
                };
                chatHistory.add(object);

                System.out.println(object);
                System.out.println(chatHistory);
                
                String result = "";
//                for (Message m : chatHistory) {
//                    result += m.getUsername() + ": \t" + m.getMessage() + "\n";
//                }
                result += object.getUsername() + ": \t" + object.getMessage() + "\n";

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
