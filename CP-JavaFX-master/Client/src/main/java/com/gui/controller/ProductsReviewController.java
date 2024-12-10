package com.gui.controller;

import com.SQLsupport.DBClass.Review;
import com.implementation.client.OwnClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import static com.gui.Constants.DARK_THEME_PATH;
import static com.gui.Constants.LIGHT_THEME_PATH;

public class ProductsReviewController extends UserMenuController{

    private ObservableList<Review> dataFromServer, selectableReviewList;
    private String productName;
    private boolean isHaveReview;

    @FXML
    private Button reviewButton;

    @FXML
    private TableView<Review> reviewsTable;

    @FXML
    private TableColumn<Review, String> reviewColumn;

    @FXML
    private TextField reviewField;

    @FXML
    private TableColumn<Review, String> userColumn;

    @FXML
    public void initialize(){
        this.initMainScene();

        isHaveReview=false;

        productName=super.client.getSelectableProductForReview().getFirst_name();
        super.headLabel.setText("Отзыв по студенту: "+productName);


        dataFromServer = FXCollections.observableArrayList();
        selectableReviewList = FXCollections.observableArrayList();
        userColumn.setCellValueFactory(new PropertyValueFactory<>("nameUser"));
        reviewColumn.setCellValueFactory(new PropertyValueFactory<>("review_text"));

        selectAllReviews();

    }


    public void selectAllReviews(){

        super.client.sendDataToServer("select all reviews");
        super.client.sendDataToServer(productName);
        this.updateTable();
    }

    public void updateTable(){
        if(dataFromServer!=null){
            dataFromServer.clear();
            dataFromServer.addAll(super.client.receiveReviews());
            reviewsTable.setItems(dataFromServer);
        }
    }

    @Override
    public void initMainScene(){

        super.initMainScene();

        reviewButton.setOnMouseClicked(event -> {
            if(!isHaveReview) {
                sendReviewToServer();
                if(client.receiveResult()){
                    selectAllReviews();
                    isHaveReview=true;
                }
            }
        });

/*        productsTable.getSelectionModel().selectedItemProperty().addListener(
                (obs,oldSelection,newSelection)->{
                    if(newSelection!=null){
                        selectableProductList.clear();
                        selectableProductList.add(productsTable.getSelectionModel().getSelectedItem());
                        String selectableName = selectableProductList.get(0).getName();
                        String selectableManuf = selectableProductList.get(0).getNameManufacturer();
                        filterField.setText(selectableManuf);
                        searchField.setText(selectableName);
                    }
                }
        );

        searchButton.setOnMouseClicked(event -> {selectOneProduct();});
        filterButton.setOnMouseClicked(event -> { selectProductByManufacturer();});*/
    }

    private void sendReviewToServer() {
        String text_review=reviewField.getText();
        if(text_review.equals(""))
            return;
        client.sendDataToServer("add review");
        client.sendDataToServer(client.getUserProfile().getId()+"@@@"
                +client.getSelectableProductForReview().getIdstudent()+"@@@"
                +text_review);
    }

}
