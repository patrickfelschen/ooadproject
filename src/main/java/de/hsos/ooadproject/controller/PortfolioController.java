package de.hsos.ooadproject.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

import java.net.URL;
import java.util.ResourceBundle;

public class PortfolioController extends Routable implements Initializable {
    @FXML
    PieChart chart;
    private ObservableList<PieChart.Data> chartData;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.chartData = FXCollections.observableArrayList(
                new PieChart.Data("Allianz", 6.5F),
                new PieChart.Data("Apple", 40.0F),
                new PieChart.Data("Amazon", 53.5F)
        );

        this.chart.setData(chartData);
    }
}
