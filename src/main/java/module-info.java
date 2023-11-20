module com.example.sisten_chat {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.sql;

    opens com.example.sisten_chat to javafx.fxml;
    exports com.example.sisten_chat;
}