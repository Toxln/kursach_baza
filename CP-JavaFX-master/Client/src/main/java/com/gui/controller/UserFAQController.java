package com.gui.controller;

import com.SQLsupport.DBClass.Faq;
import com.implementation.client.OwnClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import static com.gui.Constants.*;

public class UserFAQController extends UserMenuController{

    private ObservableList<Faq> dataFromServer;

    @FXML
    private TableColumn<Faq, String> answersColumn;

    @FXML
    private TableView<Faq> faqTable;

    @FXML
    private TableColumn<Faq, String> questionColumn;

    @FXML
    private Button backButton;

    @FXML
    public void initialize(){

        this.initMainScene();
        dataFromServer= FXCollections.observableArrayList();

        questionColumn.setCellValueFactory(new PropertyValueFactory<>("question"));
        answersColumn.setCellValueFactory(new PropertyValueFactory<>("answer"));

        client.sendDataToServer("select all faq");
        client.sendDataToServer(" ");
        dataFromServer.addAll(super.client.receiveFAQ());
        faqTable.setItems(dataFromServer);

    }

    @Override
    public void initMainScene(){
        super.initMainScene();
        backButton.setOnMouseClicked(event -> {super.switchScene(event,USER_PROFILE_FXML);});
    }


}
