package ch.teko.awesomegroup.fallstudie_tcp_chat;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * Klasse, die die Liste mit den Nachrichten handelt. 
 */
public class ChatController {

    private static int MAX = 10;
    private static Semaphore sem = new Semaphore(MAX);
    private ArrayList<Message> chatHistory = new ArrayList<>();

    /**
     * Gibt eine Liste mit Chatnachrichten zurück. Mittels Semaphore wird sichergestellt, dass nie mehr als 10 Clients gleichzeitig die Liste anfordern können. 
     * @param amount - Anzahl der Nachrichten, die zurückgegeben werden sollen.
     * @return Liste mit Chatnachrichten
     */
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

    /**
     * Fügt eine Nachricht der Liste hinzu. Falls bereits 50 Nachrichten in der Liste gespeichert sind, wird die älteste gelöscht. Mittels Semaphore wird sichergestellt, dass während dem Speichervorgang niemand von der Liste lesen kann.
     * @param message - Nachricht, welche in der Liste gespeichert werden soll.
     */
    public void saveMessage(Message message) {
        try {
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
