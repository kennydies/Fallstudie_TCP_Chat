package ch.teko.awesomegroup.fallstudie_tcp_chat;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Klasse für das laden und starten der Client UI.
 */
public class ClientApplication extends Application {
    /**
     * Ladet das fxml File mit den Layout informationen. Erstellt eine Szene und teilt diese sowie ein Titel der Stage zu.
     * @param stage - Haupt-Stage/Fenster der JavaFX Applikation
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClientApplication.class.getResource("chat-client.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Chat Client");
        stage.setScene(scene);
        stage.show();
    }

     /**
     * Main Methode des Clients. Ruft mit launch() die start() Methode auf.
     */
    public static void main(String[] args) {
        launch();
    }
}