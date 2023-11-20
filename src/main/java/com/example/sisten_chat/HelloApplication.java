package com.example.sisten_chat;

import com.example.sisten_chat.DataBase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SisRegister-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 400);
        stage.setResizable(false);
        stage.setTitle("MelChat");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        DataBase.createDB();

        launch();
    }
}


//C:\Users\79623\.jdks\jbr-17.0.6\bin\java.exe -javaagent:C:\Users\79623\AppData\Local\JetBrains\Toolbox\apps\IDEA-C\ch-0\223.8617.56\lib\idea_rt.jar=56257:C:\Users\79623\AppData\Local\JetBrains\Toolbox\apps\IDEA-C\ch-0\223.8617.56\bin -Dfile.encoding=UTF-8 -classpath C:\Users\79623\.m2\repository\org\openjfx\javafx-controls\17.0.2\javafx-controls-17.0.2.jar;C:\Users\79623\.m2\repository\org\openjfx\javafx-graphics\17.0.2\javafx-graphics-17.0.2.jar;C:\Users\79623\.m2\repository\org\openjfx\javafx-base\17.0.2\javafx-base-17.0.2.jar;C:\Users\79623\.m2\repository\org\openjfx\javafx-fxml\17.0.2\javafx-fxml-17.0.2.jar;C:\Users\79623\.m2\repository\org\openjfx\javafx-web\17.0.2\javafx-web-17.0.2.jar;C:\Users\79623\.m2\repository\org\openjfx\javafx-media\17.0.2\javafx-media-17.0.2.jar;C:\Users\79623\IdeaProjects\Sisten_Chat\lib\sqlite-jdbc-3.32.3.8.jar -p C:\Users\79623\.m2\repository\org\openjfx\javafx-fxml\17.0.2\javafx-fxml-17.0.2-win.jar;C:\Users\79623\.m2\repository\org\openjfx\javafx-web\17.0.2\javafx-web-17.0.2-win.jar;C:\Users\79623\.m2\repository\org\openjfx\javafx-graphics\17.0.2\javafx-graphics-17.0.2-win.jar;C:\Users\79623\IdeaProjects\Sisten_Chat\target\classes;C:\Users\79623\.m2\repository\org\openjfx\javafx-base\17.0.2\javafx-base-17.0.2-win.jar;C:\Users\79623\.m2\repository\org\openjfx\javafx-controls\17.0.2\javafx-controls-17.0.2-win.jar;C:\Users\79623\.m2\repository\org\openjfx\javafx-media\17.0.2\javafx-media-17.0.2-win.jar -m com.example.sisten_chat/com.example.sisten_chat.HelloApplication