package com.gui.controller;

import java.io.IOException;
import java.util.Vector;

import com.SQLsupport.DBClass.User;
import com.gui.MainMenuGUI;
import com.implementation.client.OwnClient;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import static com.gui.Constants.*;

public class SignInController {

    private Parent root;
    private Stage stage;
    private Scene scene;
    private OwnClient client;

    @FXML
    private ImageView arrowButton;

    @FXML
    private TextField loginTextField;

    @FXML
    private PasswordField passwordFTextiled;

    @FXML
    private Label messageLabel;

    @FXML
    private Button signInButton;

    @FXML
    private Label signInLabel;

    @FXML
    protected AnchorPane mainPane;

    @FXML
    void initialize() {
        arrowButton.setOnMouseClicked(event -> {switchScene(event,MAIN_MENU_FXML);});
        signInButton.setOnMouseClicked(this::checkUser);
        messageLabel.setText(" ");
        mainPane.setOnMouseClicked(event -> {messageLabel.setText(" ");});

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

    public void checkUser(MouseEvent event){
        String login=loginTextField.getText();
        String password=passwordFTextiled.getText();

        if(login.equals("")||password.equals("")){
            messageLabel.setText("Введите логин и пароль");
            return;
        }

        client= OwnClient.getInstance();
        client.sendDataToServer("signIn");
        String dataFromClient=login+" "+password;
        client.sendDataToServer(dataFromClient);

        Vector<User> users = client.receiveUsers();
        if(users!=null){
            client.setUserProfile(users.elementAt(0));
            int role=client.getUserProfile().getRole();
            switch (role){
                case 1 -> switchScene(event,ADMIN_MENU_FXML);
                case 0 -> switchScene(event,USER_MENU_FXML);
                case -1-> messageLabel.setText("Вы заблокированы");
            }
        }
        else{
            messageLabel.setText("Не найден");
        }
    }

}
