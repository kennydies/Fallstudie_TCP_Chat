package ch.teko.awesomegroup.fallstudie_tcp_chat;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ClientController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}