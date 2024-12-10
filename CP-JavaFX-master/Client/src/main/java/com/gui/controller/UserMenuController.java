package com.gui.controller;

import java.io.IOException;

import com.gui.MainMenuGUI;
import com.implementation.client.OwnClient;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static com.gui.Constants.*;

public class UserMenuController {

    protected OwnClient client;
    protected Parent root;
    protected Stage stage;
    protected Scene scene;

    @FXML
    protected Button basketButton;

    @FXML
    protected AnchorPane headerPane;

    @FXML
    protected Button languageButton;

    @FXML
    protected Button profileButton;

    @FXML
    protected Button closeButton;

    @FXML
    protected VBox leftPane;

    @FXML
    protected AnchorPane mainPane;

    @FXML
    protected Button manufacturerButton;

    @FXML
    protected Button productButton;

    @FXML
    protected Label headLabel;

    @FXML
    protected Button themeButton;

    public UserMenuController() {
    }

    @FXML
    void initialize() {
        initMainScene();
        headLabel.setText("Добро пожаловать, "+client.getUserProfile().getLogin()+"!");
    }

    public void initMainScene(){

        client = OwnClient.getInstance();

        String path=!client.isDarkTheme()?LIGHT_THEME_PATH:DARK_THEME_PATH;
        switchTheme(path);

        productButton.setOnMouseClicked(event -> {switchScene(event,USER_PRODUCTS_FXML);});
        manufacturerButton.setOnMouseClicked(event->{switchScene(event,USER_MANUFACTURER_FXML); });
        basketButton.setOnMouseClicked(event->{switchScene(event,USER_BASKET_FXML); });
        profileButton.setOnMouseClicked(event -> {switchScene(event,USER_PROFILE_FXML);});
        closeButton.setOnMouseClicked(event->{
            client.sendDataToServer("exit");
            client.sendDataToServer(" ");
            client.close();
            stage = (Stage)closeButton.getScene().getWindow();
            stage.close();
        });

        themeButton.setOnMouseClicked((event)->{
            String path1=client.isDarkTheme()?LIGHT_THEME_PATH:DARK_THEME_PATH;
            switchTheme(path1);
            client.switchTheme();
        });
    }

    public void switchTheme(String themePath){
        ObservableList<String> styleSheets=headerPane.getStylesheets();

        String css = MainMenuGUI.class.getResource(themePath).toExternalForm();
        styleSheets.add(css);

        if(styleSheets.size()>1)
            styleSheets.remove(0);

        headerPane.getStyleClass().add("header");
        mainPane.getStyleClass().add("main");
        leftPane.getStyleClass().add("left");
    }

    public void switchScene(MouseEvent event, String path){
        try {
            root = FXMLLoader.load(MainMenuGUI.class.getResource(path));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

