package com.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.SQLsupport.DBClass.User;
import com.gui.MainMenuGUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import static com.gui.Constants.*;

public class MainMenuController {

    private Stage stage;
    private Parent root;
    private Scene scene;

    @FXML
    private AnchorPane headerPane;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Label headLabel;

    @FXML
    private Button registrationButton;

    @FXML
    private Button signInButton;

    @FXML
    void initialize() {
        switchTheme(DARK_THEME_PATH);
    }

    public void switchTheme(String themePath){
        String css = MainMenuGUI.class.getResource(themePath).toExternalForm();
        headerPane.getStylesheets().add(css);
        headerPane.getStyleClass().add("header");
        mainPane.getStyleClass().add("main");
    }

    public void switchToRegisterScene(ActionEvent event) throws IOException{
        root = FXMLLoader.load(MainMenuGUI.class.getResource(REGISTRATION_FXML));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToSignInScene(ActionEvent event) throws IOException{
        root = FXMLLoader.load(MainMenuGUI.class.getResource(SIGN_IN_FXML));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
