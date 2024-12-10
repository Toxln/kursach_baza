package com.gui.controller;

import com.SQLsupport.DBClass.Product;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Vector;

import static com.gui.Constants.*;
import static com.gui.LanguageSupport.*;
import static com.gui.LanguageSupport.PRODUCTS_COMPARISON_SUCCESSFUL_TEXT;

public class AdminProductsController extends AdminMenuController{
    private ObservableList<Product> dataFromServer, selectableProductList;
    private String successfulAdd,successfulCompr;

    @FXML
    private Button addCountButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TextField costField;

    @FXML
    private TextField countField;

    @FXML
    private Button createProductButton;

    @FXML
    private Button deleteCountButton;

    @FXML
    private Label headLabel;

    @FXML
    private Button languageButton;

    @FXML
    private TextField manufField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField numberField;

    @FXML
    private TextField typeField;

    @FXML
    private TableColumn<Product, Float> costColumn;

    @FXML
    private Label messageLabel;

    @FXML
    private TableColumn<Product, Integer> countColumn;

    @FXML
    private TableColumn<Product, Integer> idColumn;

    @FXML
    private TableColumn<Product, Integer> manufacturerColumn;

    @FXML
    private TableView<Product> productsTable;

    @FXML
    private TableColumn<Product, String> nameColumn;

    @FXML
    private TableColumn<Product, String> typeColumn;

    @FXML
    private TextField searchField;

    @FXML
    private Button searchButton;

    @FXML
    private Button createReport;

    @FXML
    void initialize(){

        dataFromServer = FXCollections.observableArrayList();
        selectableProductList = FXCollections.observableArrayList();
        idColumn.setCellValueFactory(new PropertyValueFactory<>("idstudent"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("first_name"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("last_name"));
        costColumn.setCellValueFactory(new PropertyValueFactory<>("ave_grade"));
        countColumn.setCellValueFactory(new PropertyValueFactory<>("iduser"));
        manufacturerColumn.setCellValueFactory(new PropertyValueFactory<>("idscholarship"));

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

    public void updateTable(){
        dataFromServer.clear();
        dataFromServer.addAll(super.client.receiveProducts());
        productsTable.setItems(dataFromServer);
        searchField.setText("");

        costField.setText("");
        nameField.setText("");
        typeField.setText("");
        numberField.setText("");
        manufField.setText("");

        countField.setText("");
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
                        String selectableName = selectableProductList.get(0).getFirst_name();
                        searchField.setText(selectableName);
                        messageLabel.setText(" ");
                    }
                }
        );

        searchButton.setOnMouseClicked(event -> {
            selectOneProduct();
            this.updateTable();
        });

        createReport.setOnMouseClicked(event -> {createReport();});

        createProductButton.setOnMouseClicked(event -> {createProduct();});
        deleteButton.setOnMouseClicked(event -> {deleteOneProduct();});
        deleteCountButton.setOnMouseClicked(event -> {updateCount(-1);});
        addCountButton.setOnMouseClicked(event -> {updateCount(1);});

        languageButton.setOnMouseClicked(event -> {
            int language_count1=client.isRussianLanguage()?LANGUAGE_ENGLISH:LANGUAGE_RUSSIAN;
            this.switchLanguage(language_count1);
            client.switchLanguage();
        });
    }

    private void createReport()
    {
       client.sendDataToServer("print basket");
       client.sendDataToServer(" ");
       client.receiveFilePath();
    }

    private void updateCount(int value) {
        Product product=selectableProductList.get(0);
        if (product==null)
            return;
        int newCount=value * Integer.parseInt(countField.getText());
        int id=product.getIdstudent();
        client.sendDataToServer("edit product count");
        client.sendDataToServer(id+" "+newCount);

        if(client.receiveResult()){
            this.selectAllProducts();
        }
    }

    private void deleteOneProduct() {
        Product product=selectableProductList.get(0);
        if (product==null)
            return;
        client.sendDataToServer("delete one product");
        int id=product.getIdstudent();
        client.sendDataToServer(Integer.toString(id));

        if(client.receiveResult()){
            this.selectAllProducts();
        }
    }

    private void createProduct() {
        String iduser, avegrade, first_name, scholarship, coefficient, last_name;

        iduser = client.getUserProfile().getId().toString();
        avegrade = costField.getText();
        first_name = nameField.getText();
        last_name = typeField.getText();

        Float ag = Float.parseFloat(avegrade), coef = 0f;
        Float schShip = 0f;
        for(int i = 0;i<=10;i++)
        {
            if (ag < i)
            {
                if (i < 7)
                {
                    coef = 1.0f;
                }
                if (i == 8)
                {
                    coef = 1.2f;
                }
                if (i == 9)
                {
                    coef = 1.4f;
                }
                if (i == 10)
                {
                    coef = 1.6f;
                }
                break;
            }
        }
        schShip = 100 * coef;
        numberField.setText(schShip.toString());
        manufField.setText(coef.toString());
        scholarship = numberField.getText();
        coefficient = manufField.getText();

        int idstudent = 0;
        TableView<Product> tableView = productsTable.getSelectionModel().getTableView();
        ObservableList<Product> observableList = tableView.getItems();
        for (var it:
             observableList) {
            idstudent = it.getIdstudent();
        }
        idstudent++;

        client.sendDataToServer("create product");
        client.sendDataToServer(idstudent +"@@@" + iduser+"@@@"+first_name+"@@@"+last_name+"@@@"+avegrade+"@@@"+scholarship+"@@@"+coefficient);

        if(client.receiveResult()){
            this.selectAllProducts();
            //messageLabel.setText("Создан");
        }
        else
            System.out.println("oops");
    }

    @Override
    protected void switchLanguage(int language_count){
        super.switchLanguage(language_count);
        headLabel.setText(LABEL_PRODUCTS_TEXT[language_count]);
        idColumn.setText(PRODUCTS_NUMBER_TEXT[language_count]);
        nameColumn.setText(PRODUCTS_NAME_TEXT[language_count]);
        typeColumn.setText(PRODUCTS_TYPE_TEXT[language_count]);
        costColumn.setText(PRODUCTS_COST_TEXT[language_count]);
        countColumn.setText(PRODUCTS_COUNT_TEXT[language_count]);
        manufacturerColumn.setText(PRODUCTS_MANUF_TEXT[language_count]);
        searchButton.setText(PRODUCTS_SEARCH_TEXT[language_count]);
        searchField.setPromptText(PRODUCTS_CHOOSE_PR_TEXT[language_count]);
        successfulAdd=PRODUCTS_ADD_SUCCESSFUL_TEXT[language_count];
        successfulCompr=PRODUCTS_COMPARISON_SUCCESSFUL_TEXT[language_count];
    }

}
