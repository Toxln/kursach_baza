package com.gui.controller;

import com.SQLsupport.DBClass.Product;
import com.SQLsupport.DBClass.Purchase;
import com.SQLsupport.DBClass.Rebate;
import com.SQLsupport.DBClass.User;
import com.implementation.client.OwnClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Vector;

import static com.gui.Constants.*;

public class UserBasketContoller extends UserMenuController{

    private ObservableList<Product> list, selectableManufacturerList;
    boolean isHaveMark;

    @FXML
    private Button addMarkButton;

    @FXML
    private Button pieChartButton;

    @FXML
    private TextField markField;

    @FXML
    private TextField manufacturerField;

    @FXML
    private TableColumn<Product, Integer> idmark;

    @FXML
    private TableColumn<Product, String> first_name;

    @FXML
    private TableColumn<Product, String> last_name;

    @FXML
    private TableColumn<Product, Integer> maths;

    @FXML
    private TableView<Product> manufacturerTable;

    @FXML
    private TableColumn<Product, Integer> programming;

    @FXML
    private TableColumn<Product, Integer> unity;

    @FXML
    private TableColumn<Product, Integer> course_work;

    @FXML
    void initialize() {

        this.initMainScene();
        isHaveMark=false;

        idmark.setCellValueFactory(new PropertyValueFactory<Product,Integer>("idmark"));
        first_name.setCellValueFactory(new PropertyValueFactory<Product,String>("first_name"));
        last_name.setCellValueFactory(new PropertyValueFactory<Product,String>("last_name"));
        maths.setCellValueFactory(new PropertyValueFactory<Product,Integer>("maths"));
        programming.setCellValueFactory(new PropertyValueFactory<Product,Integer>("programming"));
        unity.setCellValueFactory(new PropertyValueFactory<Product,Integer>("unity"));
        course_work.setCellValueFactory(new PropertyValueFactory<Product,Integer>("course_work"));


        SelectAllManufacturers();

    }

    public void SelectAllManufacturers(){
        super.client.sendDataToServer("select all marks");
        super.client.sendDataToServer(" ");
        Vector<Product> manufacturers = super.client.receiveProducts();
        list.clear();
        list.addAll(manufacturers);
        manufacturerTable.setItems(list);
    }

    public void initMainScene(){

        super.initMainScene();

        list= FXCollections.observableArrayList();
        selectableManufacturerList=FXCollections.observableArrayList();

        manufacturerTable.getSelectionModel().selectedItemProperty().addListener(
                (obs,oldSelection,newSelection)->{
                    if(newSelection!=null){
                        selectableManufacturerList.clear();
                        selectableManufacturerList.add(manufacturerTable.getSelectionModel().getSelectedItem());
                        Integer selectableName = selectableManufacturerList.get(0).getIdmark();
                        manufacturerField.setText(selectableName.toString());
                    }
                }
        );

        pieChartButton.setOnMouseClicked(event -> {switchScene(event,PIE_CHART_FXML); });

        addMarkButton.setOnMouseClicked(event -> {
            if(isHaveMark)
                return;
            int mark=0;
            try {
                mark=Integer.parseInt(markField.getText());
            }catch (NumberFormatException e){
                System.out.println(e.getMessage());
            }
            if(mark>10||mark<0){
                markField.setText("Некорректно");
                return;
            }

            super.client.sendDataToServer("add mark to manufacturer");
            super.client.sendDataToServer(manufacturerField.getText()+"@@@"+markField.getText());
            if(super.client.receiveResult()){
                SelectAllManufacturers();
                isHaveMark=true;
            }


        });
    }
}


