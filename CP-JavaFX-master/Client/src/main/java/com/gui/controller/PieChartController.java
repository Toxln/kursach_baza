package com.gui.controller;

import com.SQLsupport.DBClass.InformationForPieChart;
import com.implementation.client.OwnClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;

import java.util.Vector;

import static com.gui.Constants.DARK_THEME_PATH;
import static com.gui.Constants.LIGHT_THEME_PATH;

public class PieChartController extends UserMenuController {

    ObservableList<PieChart.Data> dataList;

    @FXML
    private PieChart myPieChart;

    @FXML
    public void initialize() {
        dataList = FXCollections.observableArrayList();
        int[] arr = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        super.initMainScene();

        client.sendDataToServer("select data for pie chart");
        client.sendDataToServer(" ");
        Vector<InformationForPieChart> informationForPieCharts = client.receiveDataForPieChart();

        for (var data : informationForPieCharts) {
            for (int i = 0; i < 11; i += 1) {
                if (data.getAve_grade() < i) {
                    arr[i-1] += 1;
                    break;
                }
            }
        }

        for (var data : informationForPieCharts) {
            for (int i = 0; i < 11; i += 1) {
                if (arr[i] != 0) {
                    dataList.add(new PieChart.Data("От " + i + " до " + (i+1), arr[i]));
                    arr[i] = 0;
                    break;
                }
            }
        }

        myPieChart.setData(dataList);
    }
}
