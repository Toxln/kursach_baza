package com.gui.controller;

import com.SQLsupport.DBClass.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import static com.gui.Constants.*;

public class AdminUsersController extends AdminMenuController{


    @FXML
    private Button blockButton;

    @FXML
    private Button unblockButton;

    @FXML
    private TableColumn<User, String> fnameColumn;

    @FXML
    private TableColumn<User, String> lnameColumn;

    @FXML
    private TableColumn<User, String> loginColumn;

    @FXML
    private TableColumn<User, String> passwordColumn;

    @FXML
    private TableColumn<User, String> phoneColumn;

    @FXML
    private TableColumn<User, Integer> roleColumn;


    @FXML
    private TableView<User> usersTable;
    private ObservableList<User> dataFromServer;
    private ObservableList<User> selectableUserList;


    public AdminUsersController() {
    }

    @FXML
    void initialize() {

        dataFromServer = FXCollections.observableArrayList();
        selectableUserList = FXCollections.observableArrayList();
        loginColumn.setCellValueFactory(new PropertyValueFactory<>("login"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        fnameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lnameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));

        this.initMainScene();
        this.selectAllUsers();

    }

    private void selectAllUsers() {
        client.sendDataToServer("select all users");
        client.sendDataToServer("1");
        this.updateTable();
    }

    private void updateTable() {
        dataFromServer.clear();
        dataFromServer.addAll(super.client.receiveUsers());
        usersTable.setItems(dataFromServer);
    }

    public void initMainScene(){

        super.initMainScene();

        blockButton.setOnMouseClicked(event -> {blocking();});
        unblockButton.setOnMouseClicked((event -> {unblocking();}));

        languageButton.setOnMouseClicked(event -> {
            int language_count1=client.isRussianLanguage()?LANGUAGE_ENGLISH:LANGUAGE_RUSSIAN;
            this.switchLanguage(language_count1);
            client.switchLanguage();
        });

        usersTable.getSelectionModel().selectedItemProperty().addListener(
                (obs,oldSelection,newSelection)->{
                    if(newSelection!=null){
                        selectableUserList.clear();
                        selectableUserList.add(usersTable.getSelectionModel().getSelectedItem());
                    }
                }
        );


    }


    private void blocking(){
        if(selectableUserList.size()==0)
            return;
        int id=selectableUserList.get(0).getId();
        client.sendDataToServer("edit role user");
        client.sendDataToServer(id+" "+"-1");

        if(client.receiveResult())
            selectAllUsers();
    }

    private void unblocking(){
        if(selectableUserList.size()==0)
            return;
        int id=selectableUserList.get(0).getId();
        client.sendDataToServer("edit role user");
        client.sendDataToServer(id+" "+"0");

        if(client.receiveResult()){
            selectAllUsers();
        }

    }

    protected void switchLanguage(int language_count) {

        //headLabel.setText("");
    }

}

