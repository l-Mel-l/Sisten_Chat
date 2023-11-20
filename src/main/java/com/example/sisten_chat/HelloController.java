package com.example.sisten_chat;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.util.Scanner;

public class HelloController {

    @FXML
    private TextArea SoobId;

    @FXML
    private Button OtpsBtn;

    @FXML
    private VBox myApplicationsVBox;

    private BufferedReader in;
    private PrintWriter out;

    private String nickname;

    @FXML
    protected void initialize() {
        Scanner scanner = new Scanner(System.in);
        nickname = DataBase.getUserNameByEmail(InfoBank.currentMail);
        System.out.println("Nickname: " + nickname);
        SoobId.setWrapText(true);

        OtpsBtn.setOnAction(actionEvent -> {
            String message =SoobId.getText();
            sendMessageToServer(message);
            addMessageToChat(nickname, message);
        });

        System.out.print("\"server\" or \"client\"?: ");
        String role = scanner.nextLine();
        if (role.equalsIgnoreCase("server")) {
            runServer();
        } else if (role.equalsIgnoreCase("client")) {
            System.out.print("Enter server's IP: ");
            String serverIP = scanner.nextLine();
            runClient(serverIP);
        } else {
            System.out.println("Read better!");
        }
        scanner.close();
    }

    private void runServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(8070);
            System.out.println("Server is started, waiting for client");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Client is connected from address: " + clientSocket.getInetAddress());

            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);

            out.println("Chat with " + nickname + "!");
            Thread.sleep(200);

            new Thread(() -> {
                try {
                    while (true) {
                        String clientMessage = in.readLine();
                        if (clientMessage == null) {
                            break;
                        }
                        Platform.runLater(() -> addMessageToChat("Server", clientMessage));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void runClient(String serverIP) {
        try {
            Socket socket = new Socket(serverIP, 8070);
            System.out.println("Connected to server");

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            out.println("Chat with " + nickname + "!");
            Thread.sleep(200);

            new Thread(() -> {
                try {
                    while (true) {
                        String serverMessage = in.readLine();
                        if (serverMessage == null) {
                            break;
                        }
                        Platform.runLater(() -> addMessageToChat("Server", serverMessage));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void sendMessageToServer(String message) {
        out.println(message);
    }

    private void addMessageToChat(String sender, String message) {
        Label messageLabel = new Label(sender + ": " + message);
        messageLabel.setWrapText(true);

        if (sender.equals(nickname)) {
            messageLabel.setStyle("-fx-background-color: #4CAF50; -fx-padding: 5px; -fx-text-fill: white;");
            messageLabel.setAlignment(Pos.CENTER_RIGHT);
        } else {
            messageLabel.setStyle("-fx-background-color: #008CBA; -fx-padding: 5px; -fx-text-fill: white;");
            messageLabel.setAlignment(Pos.CENTER_LEFT);
        }

        myApplicationsVBox.getChildren().add(messageLabel);
    }
}