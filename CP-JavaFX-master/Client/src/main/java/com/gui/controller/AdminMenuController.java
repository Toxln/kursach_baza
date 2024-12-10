package com.gui.controller;

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

import java.io.IOException;

import static com.gui.Constants.*;
import static com.gui.LanguageSupport.*;

public class AdminMenuController {

    protected OwnClient client;
    protected Parent root;
    protected Stage stage;
    protected Scene scene;

    @FXML
    protected Button userButton;

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

    public AdminMenuController() {
    }

    @FXML
    void initialize() {
        initMainScene();

        languageButton.setOnMouseClicked(event -> {
            int language_count1=client.isRussianLanguage()?LANGUAGE_ENGLISH:LANGUAGE_RUSSIAN;
            this.switchLanguage(language_count1);
            client.switchLanguage();
        });
    }

    public void initMainScene(){

        client = OwnClient.getInstance();

        String path=client.isDarkTheme()?DARK_THEME_PATH:LIGHT_THEME_PATH;
        switchTheme(path);

        int language_count=client.isRussianLanguage()?LANGUAGE_RUSSIAN:LANGUAGE_ENGLISH;
        switchLanguage(language_count);

        productButton.setOnMouseClicked(event -> {switchScene(event,ADMIN_PRODUCTS_FXML);});
        manufacturerButton.setOnMouseClicked(event->{switchScene(event,ADMIN_MANUFACTURER_FXML);});
        userButton.setOnMouseClicked(event->{switchScene(event,ADMIN_USERS_FXML); });
        profileButton.setOnMouseClicked(event -> {switchScene(event,ADMIN_PROFILE_FXML);});
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


    protected void switchLanguage(int language_count) {
        profileButton.setText(PROFILE_TEXT[language_count]);
        manufacturerButton.setText(MANUFACTURERS_TEXT[language_count]);
        productButton.setText(PRODUCTS_TEXT[language_count]);
        closeButton.setText(EXIT_TEXT[language_count]);
        themeButton.setText(THEMES_TEXT[language_count]);
        languageButton.setText(LANGUAGE_TEXT[language_count]);
        headLabel.setText(WELCOME_TEXT[language_count]+", "+client.getUserProfile().getLogin()+"!");
    }

    private void switchTheme(String themePath){
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

