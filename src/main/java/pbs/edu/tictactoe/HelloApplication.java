package pbs.edu.tictactoe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Objects;

import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseListener;

public class HelloApplication extends Application {
    private final Controller controll = new Controller();
    MouseEvent mouse = null;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("gui.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        scene.getStylesheets().add(HelloApplication.class.getResource("style.css").toExternalForm());
        stage.setTitle("Tic tac toe");
        stage.setScene(scene);
        stage.show();







    }

    public static void main(String[] args) {
        launch();

    }

}