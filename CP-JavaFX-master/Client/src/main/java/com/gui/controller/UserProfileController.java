package com.gui.controller;

import java.util.Vector;

import com.SQLsupport.DBClass.User;
import com.implementation.client.OwnClient;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import static com.gui.Constants.*;

public class UserProfileController extends UserMenuController{

    private boolean isEdit;

    @FXML
    private Label firstNameLabel;

    @FXML
    private Label lastNameLabel;

    @FXML
    private Label loginLabel;

    @FXML
    private Label passwordLabel;

    @FXML
    private Label phoneLabel;

    @FXML
    private Label roleLabel;

    @FXML
    private Button addMoneyButton;

    @FXML
    private Button changeProfileButton;

    @FXML
    private Button faqButton;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField loginField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField phoneField;;

    @FXML
    private TextField roleField;


    @FXML
    void initialize() {
        isEdit=false;


        this.initMainScene();

        this.initFields();
        this.addActions();

    }

    public void addActions(){
        loginField.setOnMouseClicked(event -> {isEdit=true;});
        passwordField.setOnMouseClicked(event -> {isEdit=true;});
        firstNameField.setOnMouseClicked(event -> {isEdit=true;});
        lastNameField.setOnMouseClicked(event -> {isEdit=true;});
        phoneField.setOnMouseClicked(event -> {isEdit=true;});

    }

    private void editProfile() {
        if(!isEdit)
            return;
        client.sendDataToServer("edit user");
        String newProfile=
                client.getUserProfile().getId() +"@@@"+
                loginField.getText()+"@@@"+
                passwordField.getText()+"@@@"+
                firstNameField.getText()+"@@@"+
                lastNameField.getText()+"@@@"+
                phoneField.getText();
        client.sendDataToServer(newProfile);
        if(client.receiveResult()){
            client.sendDataToServer("signIn");
            client.sendDataToServer(loginField.getText()+" "+passwordField.getText());
            Vector<User>users= client.receiveUsers();
            if(users!=null){
                client.setUserProfile(users.elementAt(0));
                initFields();
            }
        }
    }

    @Override
    public void initMainScene(){
        super.initMainScene();
        changeProfileButton.setOnMouseClicked(event -> {editProfile();});
        faqButton.setOnMouseClicked(event -> {switchScene(event,USER_FAQ_FXML);});
    }


    public void initFields(){
        User user=client.getUserProfile();
        String role=(user.getRole()>0)?"Администратор":"Покупатель";
        loginField.setText(user.getLogin());
        passwordField.setText(user.getPassword());
        roleField.setText(role);
        firstNameField.setText(user.getFirstName());
        lastNameField.setText(user.getLastName());
        phoneField.setText(user.getPhone());
    }

}

