package com.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static com.gui.Constants.*;
import java.io.IOException;

public class MainMenuGUI extends Application {


    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(MainMenuGUI.class.getResource(MAIN_MENU_FXML));
        String css = MainMenuGUI.class.getResource("darkTheme.css").toExternalForm();
        Scene scene = new Scene(fxmlLoader.load(), WIDTH_WINDOW, HEIGHT_WINDOW);
        scene.getStylesheets().add(css);
        stage.setTitle("");
        stage.setScene(scene);
        stage.show();
    }

    public void run() { Application.launch(); }
}