package com.gui.controller;

import com.SQLsupport.DBClass.Faq;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import static com.gui.Constants.ADMIN_PROFILE_FXML;
import static com.gui.Constants.USER_PROFILE_FXML;

public class AdminFAQController extends UserMenuController{

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
    private TextField faqAnswer;

    @FXML
    private TextField faqQuestion;

    @FXML
    private Button addFaq;

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
        backButton.setOnMouseClicked(event -> {super.switchScene(event, ADMIN_PROFILE_FXML);});
        addFaq.setOnMouseClicked(event -> {addFaq();});
    }

    private void addFaq()
    {
        client.sendDataToServer("add faq");
        String faq = faqQuestion.getText() + "@@@" + faqAnswer.getText();
        client.sendDataToServer(faq);
        client.receiveResult();
    }


}
