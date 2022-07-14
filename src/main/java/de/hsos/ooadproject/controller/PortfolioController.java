package de.hsos.ooadproject.controller;

import de.hsos.ooadproject.Router;
import de.hsos.ooadproject.uimodel.PortfolioListItem;
import de.hsos.ooadproject.datamodel.Stock;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PortfolioController extends Routable implements Initializable {
    @FXML
    PieChart chart;
    @FXML
    ListView<Stock> portfolioList;
    private ObservableList<PieChart.Data> chartData;
    private ObservableList<Stock> listData;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Chart
        this.chartData = FXCollections.observableArrayList(
                new PieChart.Data("Allianz", 6.5F),
                new PieChart.Data("Apple", 40.0F),
                new PieChart.Data("Amazon", 53.5F)
        );

        this.chart.setData(chartData);

        // List
        listData = FXCollections.observableArrayList(
                new Stock("Allianz", "DE0008404005", 177.96f, 0.0f, 0.0f, 0.0f, 0.0f, "00:00:00"),
                new Stock("Apfel", "DE0008404005", 177.96f, 0.0f, 0.0f, 0.0f, 0.0f, "00:00:00"),
                new Stock("Apple", "DE0008404005", 177.96f, 0.0f, 0.0f, 0.0f, 0.0f, "00:00:00"),
                new Stock("Birne", "DE0008404005", 177.96f, 0.0f, 0.0f, 0.0f, 0.0f, "00:00:00")
        );

        portfolioList.setCellFactory(portfolioListView -> {
            PortfolioListItem item = new PortfolioListItem();
            item.setOnMouseClicked(event -> {
                try {
                    Router.getInstance().navigate("stockDetails", item.getItem());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            return item;
        });

        portfolioList.getItems().addAll(listData);
    }
}
