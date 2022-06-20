package ch.teko.awesomegroup.fallstudie_tcp_chat;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class ChatController {

    private static int MAX = 10;
    private static Semaphore sem = new Semaphore(MAX);
    private ArrayList<Message> chatHistory = new ArrayList<>();

    public List<Message> getHistory(int amount) {
        List<Message> result = new ArrayList<>();
        try {
            sem.acquire(1);
            if (chatHistory.size() > amount) {
                result = chatHistory.subList(chatHistory.size() - amount, chatHistory.size());
            } else {
                result = chatHistory;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            sem.release(1);
        }
        return result;
    }

    public void saveMessage(Message message) {
        try {
            System.out.println(sem.availablePermits());
            sem.acquire(MAX);
            if (chatHistory.size() > 50){
                chatHistory.remove(0);
            }
            chatHistory.add(message);
            sem.release(MAX);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
