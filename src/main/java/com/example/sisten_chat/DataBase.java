package com.example.sisten_chat;

import javafx.scene.control.Alert;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBase {
    public static void createDB() {
        try (
                Connection connection = DriverManager.getConnection("jdbc:sqlite:" + "Chat.db");
                Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS Клиент (Почта PRIMARY KEY, Пароль VARCHAR, Телефон INTEGER, Адрес VARCHAR, Дата VARCHAR, ФИО VARCHAR)");
            statement.execute("CREATE TABLE IF NOT EXISTS Принятые (Id INTEGER PRIMARY KEY, ФИО VARCHAR, ПочтаОтпр  VARCHAR,ПочтаПол VARCHAR,ДатаПодачи VARCHAR, Сообщение VARCHAR)");
            statement.execute("CREATE TABLE IF NOT EXISTS Отправленные (Id INTEGER PRIMARY KEY, ФИО VARCHAR, ПочтаОтпр  VARCHAR,ПочтаПол VARCHAR,ДатаПодачи VARCHAR, Сообщение VARCHAR)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void NewUser(String loginText, String passwordText, String numberText, String adressText, String fioText, String dataText) {
        try (
                Connection connection = DriverManager.getConnection("jdbc:sqlite:" + "Chat.db");
                Statement statement = connection.createStatement()) {
            statement.execute("INSERT INTO Клиент (Почта, Пароль, Телефон, Адрес, Дата, ФИО) VALUES (\"" + loginText + "\",\"" + passwordText + "\",\"" + numberText + "\",\"" + adressText + "\",\"" + dataText + "\",\"" + fioText + "\")");
        } catch (SQLException e) {
            try {
                BufferedWriter w = new BufferedWriter(new FileWriter(new File("asdasdasd.txt")));
                w.write(e.getMessage());
            } catch (IOException ex) {
            }
            throw new RuntimeException(e);
        }
    }

    public static int getUserByMail(String mail, String password) {
        if (mail.equals("dudoroff.k@yandex.ru") && password.equals("qwertyuiop7")) {
            return 2; // Специфичная почта и пароль
        }

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + "Chat.db");
             Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery("SELECT Пароль FROM Клиент WHERE Почта=\"" + mail + "\"");
            while (result.next()) {
                String storedPassword = result.getString("Пароль");
                if (storedPassword.equals(password)) {
                    return 1; // Пароль совпадает с введенным
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return 0; // Если не найдено совпадений
    }

    public static String[] getUerInfo(String mail) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + "Chat.db");
             Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery("SELECT * FROM Клиент WHERE Почта=\"" + mail + "\"");
            while (result.next()) {
                return new String[]{result.getString("ФИО"), result.getString("Дата"), result.getString("Адрес"), result.getString("Телефон"), result.getString("Почта"), result.getString("Пароль")};
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public static void NewZayav(String loginText, String numberText, String adressText, String fioText, String dataText, String datapodText, String zayavText, String status) {
        try (

                Connection connection = DriverManager.getConnection("jdbc:sqlite:" + "Vodokanal.db");
                Statement statement = connection.createStatement()) {
            statement.execute("INSERT INTO Заявление (ФИО, Телефон, Адрес, ДатаРождения, Почта,ДатаПодачи, Заявка , Статус) VALUES (\"" + fioText + "\",\"" + numberText + "\",\"" + adressText + "\",\"" + dataText + "\",\"" + loginText + "\",\"" + datapodText + "\",\"" + zayavText + "\",\"" + status + "\")");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String[]> getUerZayav(String mail) {
        List<String[]> userZayavList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + "Vodokanal.db");
             Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery("SELECT * FROM Заявление WHERE Почта=\"" + mail + "\"");
            while (result.next()) {
                String id = result.getString("Id");
                String fio = result.getString("ФИО");
                String date = result.getString("ДатаПодачи");
                String zayav = result.getString("Заявка");
                String number = result.getString("Телефон");
                String address = result.getString("Адрес");
                String borndate = result.getString("ДатаРождения");
                String status = result.getString("Статус");
                userZayavList.add(new String[]{id, fio, mail, date, zayav, number, address, borndate, status});
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return userZayavList;
    }

    public static String getUserLogin() {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + "Chat.db");
             Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery("SELECT * FROM Клиент");
            while (result.next()) {
                return new String(result.getString("Почта"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return "none";
    }

    public static void insertSentMessage(String userName, String senderEmail, String receiverEmail, String sendingDate, String message) {
        try (
                Connection connection = DriverManager.getConnection("jdbc:sqlite:" + "Vodokanal.db");
                Statement statement = connection.createStatement()) {
            statement.execute("INSERT INTO Отправленные (ФИО, ПочтаОтпр, ПочтаПол, ДатаПодачи, Сообщение) VALUES (\"" + userName + "\",\"" + senderEmail + "\",\"" + receiverEmail + "\",\"" + sendingDate + "\",\"" + message + "\")");
            System.out.println("Сообщение2");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static String getUserNameByEmail(String mail) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + "Chat.db");
             Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery("SELECT ФИО FROM Клиент WHERE Почта=\"" + mail + "\"");
            System.out.println(mail+"Почта");
            while (result.next()) {
                return result.getString("ФИО");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}