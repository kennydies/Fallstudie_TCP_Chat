package ch.teko.awesomegroup.fallstudie_tcp_chat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ClientController {

    @FXML private TextField txt_username;
    @FXML private TextArea area_history;
    @FXML private TextArea area_message;

    @FXML private void connectButtonAction(ActionEvent event) {
        System.out.println("connect button test");
        System.out.println("name: " + txt_username.getText());
        
    }

    @FXML private void sendButtonAction(ActionEvent event) {
        System.out.println("send button test");
        System.out.println("message: " + area_message.getText());
    }


}