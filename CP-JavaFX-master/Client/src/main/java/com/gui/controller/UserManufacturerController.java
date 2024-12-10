package com.gui.controller;

import java.util.Vector;

import com.SQLsupport.DBClass.Manufacturer;
import com.implementation.client.OwnClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import static com.gui.Constants.*;

public class UserManufacturerController extends UserMenuController{

    private ObservableList<Manufacturer> list, selectableManufacturerList;
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
    private TableColumn<Manufacturer, Integer> countryColumn;

    @FXML
    private TableColumn<Manufacturer, Integer> emailColumn;

    @FXML
    private TableColumn<Manufacturer, Integer> idColumn;

    @FXML
    private TableView<Manufacturer> manufacturerTable;

    @FXML
    private TableColumn<Manufacturer, Integer> nameColumn;

    @FXML
    private TableColumn<Manufacturer, Integer> ratingColumn;

    @FXML
    void initialize() {

        this.initMainScene();
        isHaveMark=false;

        idColumn.setCellValueFactory(new PropertyValueFactory<Manufacturer,Integer>("idstudent"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Manufacturer,Integer>("first_name"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<Manufacturer,Integer>("last_name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<Manufacturer,Integer>("ave_grade"));
        ratingColumn.setCellValueFactory(new PropertyValueFactory<Manufacturer,Integer>("idscholarship"));


        SelectAllManufacturers();

    }

    public void SelectAllManufacturers(){
        super.client.sendDataToServer("select all manufacturer");
        super.client.sendDataToServer(" ");
        Vector<Manufacturer> manufacturers = super.client.receiveManufacturers();
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
                        String selectableName = selectableManufacturerList.get(0).getFirst_name();
                        manufacturerField.setText(selectableName);
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
