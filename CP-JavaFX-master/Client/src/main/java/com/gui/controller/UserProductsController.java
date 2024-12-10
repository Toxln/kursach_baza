package com.gui.controller;

import com.SQLsupport.DBClass.Product;
import com.SQLsupport.DBClass.Purchase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Vector;

import static com.gui.Constants.*;

public class UserProductsController extends UserMenuController{

    private ObservableList<Product> dataFromServer, selectableProductList;

    @FXML
    private TableColumn<Product, Integer> scholarshipColumn;

    @FXML
    private Label messageLabel;

    @FXML
    private TableColumn<Product, Float> avegradeColumn;

    @FXML
    private TableColumn<Product, Integer> idColumn;

    @FXML
    private TableColumn<Purchase, Float> coefficientColumn;

    @FXML
    private TableView<Product> productsTable;

    @FXML
    private TableColumn<Product, String> fnameColumn;

    @FXML
    private TableColumn<Product, String> lnameColumn;

    @FXML
    private TextField searchField;

    @FXML
    private Button reviewButton;

    @FXML
    private TextField filterField;

    @FXML
    private Button filterButton;

    @FXML
    private Button searchButton;

    @FXML
    void initialize(){

        dataFromServer = FXCollections.observableArrayList();
        selectableProductList = FXCollections.observableArrayList();
        idColumn.setCellValueFactory(new PropertyValueFactory<>("idstudent"));
        fnameColumn.setCellValueFactory(new PropertyValueFactory<>("first_name"));
        lnameColumn.setCellValueFactory(new PropertyValueFactory<>("last_name"));
        avegradeColumn.setCellValueFactory(new PropertyValueFactory<>("ave_grade"));
        scholarshipColumn.setCellValueFactory(new PropertyValueFactory<>("idscholarship"));
        coefficientColumn.setCellValueFactory(new PropertyValueFactory<>("iduser"));
        this.initMainScene();

        this.selectAllProducts();
    }

    public void selectAllProducts(){
        super.client.sendDataToServer("select all products");
        super.client.sendDataToServer(" ");
        this.updateTable();
    }

    public void selectOneProduct(){
        String selectableName= searchField.getText();
        if(selectableName.equals(""))
            return;
        super.client.sendDataToServer("select one product");
        super.client.sendDataToServer(selectableName);

    }

    public void selectProductByManufacturer(){
        String selectableManufacturerName= filterField.getText();
        if(selectableManufacturerName.equals(""))
            return;
        super.client.sendDataToServer("select by manufacturer");
        super.client.sendDataToServer(selectableManufacturerName);
        this.updateTable();
    }

    public void updateTable(){
        dataFromServer.clear();
        dataFromServer.addAll(super.client.receiveProducts());
        productsTable.setItems(dataFromServer);
        filterField.setText("");
        searchField.setText("");
    }

    @Override
    public void initMainScene(){

        super.initMainScene();

        messageLabel.setText(" ");

        productsTable.getSelectionModel().selectedItemProperty().addListener(
                (obs,oldSelection,newSelection)->{
                    if(newSelection!=null){
                        selectableProductList.clear();
                        selectableProductList.add(productsTable.getSelectionModel().getSelectedItem());
                        String selectableName = selectableProductList.get(0).getLast_name();
                        Float selectableManuf = selectableProductList.get(0).getAve_grade();
                        filterField.setText(selectableManuf.toString());
                        searchField.setText(selectableName);
                        messageLabel.setText(" ");
                    }
                }
        );

        searchButton.setOnMouseClicked(event -> {
            selectOneProduct();
            this.updateTable();
        });

        filterButton.setOnMouseClicked(event -> {selectProductByManufacturer();});
        reviewButton.setOnMouseClicked(event -> {
            super.client.setSelectableProductForReview(selectableProductList.get(0));
            super.switchScene(event,PRODUCTS_REVIEW_FXML);});
    }

}
