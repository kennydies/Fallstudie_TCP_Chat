module ch.teko.awesomegroup.fallstudie_tcp_chat {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens ch.teko.awesomegroup.fallstudie_tcp_chat to javafx.fxml;
    exports ch.teko.awesomegroup.fallstudie_tcp_chat;
}