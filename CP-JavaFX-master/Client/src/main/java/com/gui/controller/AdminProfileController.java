package com.gui.controller;

import com.SQLsupport.DBClass.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Vector;

import static com.gui.Constants.*;
import static com.gui.Constants.LANGUAGE_RUSSIAN;
import static com.gui.LanguageSupport.*;
import static com.gui.LanguageSupport.PROFILE_USET_TEXT;

public class AdminProfileController extends AdminMenuController{
    private boolean isEdit;
    private String admin,userStr;

    @FXML
    private Label addressLabel;

    @FXML
    private Label firstNameLabel;

    @FXML
    private Label lastNameLabel;

    @FXML
    private Label loginLabel;

    @FXML
    private Label moneyLabel;

    @FXML
    private Label passwordLabel;

    @FXML
    private Label phoneLabel;

    @FXML
    private Label roleLabel;


    @FXML
    private TextField addressField;

    @FXML
    private Button changeProfileButton;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField loginField;

    @FXML
    private TextField moneyField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField phoneField;;

    @FXML
    private TextField roleField;

    @FXML
    private Button faqButton;


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
            Vector<User> users= client.receiveUsers();
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
        languageButton.setOnMouseClicked(event -> {
            int language_count1=client.isRussianLanguage()?LANGUAGE_ENGLISH:LANGUAGE_RUSSIAN;
            this.switchLanguage(language_count1);
            client.switchLanguage();
        });
        faqButton.setOnMouseClicked(event -> {switchScene(event,ADMIN_FAQ_FXML);});
    }


    public void initFields(){
        User user=client.getUserProfile();
        String role=(user.getRole()>0)?admin:userStr;
        loginField.setText(user.getLogin());
        passwordField.setText(user.getPassword());
        roleField.setText(role);
        firstNameField.setText(user.getFirstName());
        lastNameField.setText(user.getLastName());
        phoneField.setText(user.getPhone());
    }

    @Override
    protected void switchLanguage(int language_count){
        super.switchLanguage(language_count);
        headLabel.setText(LABEL_PROFILE_TEXT[language_count]);
        loginLabel.setText(PROFILE_LOGIN_TEXT[language_count]);
        passwordLabel.setText(PROFILE_PASSWORD_TEXT[language_count]);
        firstNameLabel.setText(PROFILE_FIRST_NAME_TEXT[language_count]);
        lastNameLabel.setText(PROFILE_LAST_NAME_TEXT[language_count]);
        roleLabel.setText(PROFILE_ROLE_TEXT[language_count]);
        phoneLabel.setText(PROFILE_PHONE_TEXT[language_count]);
        changeProfileButton.setText(PROFILE_CHANGE_TEXT[language_count]);
        admin=PROFILE_ADMIN_TEXT[language_count];
        userStr=PROFILE_USET_TEXT[language_count];
    }
}
