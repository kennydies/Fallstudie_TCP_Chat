package ch.teko.awesomegroup.fallstudie_tcp_chat;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class ChatController {

    private int MAX = 10;
    private Semaphore sem = new Semaphore(MAX);
    private ArrayList<Message> chatHistory = new ArrayList<>();

    public List<Message> getHistory(int amount) {
        List<Message> result = new ArrayList<>();
        try {
            sem.acquire(1);
            result = chatHistory.subList(chatHistory.size() - amount, chatHistory.size());
            sem.release(1);
        } catch (Exception e) {
            //TODO: handle exception
        }
        return result;
    }

    public void saveMessage(Message message) {
        try {
            sem.acquire(MAX);
            if (chatHistory.size() > 50){
                chatHistory.remove(0);
            }
            chatHistory.add(message);
            sem.release(MAX);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
    
}
