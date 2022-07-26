package de.hsos.ooadproject.controller;

import de.hsos.ooadproject.Router;
import de.hsos.ooadproject.api.StockManager;
import de.hsos.ooadproject.api.UserManager;
import de.hsos.ooadproject.datamodel.Depot;
import de.hsos.ooadproject.datamodel.Posten;
import de.hsos.ooadproject.interfaces.Routable;
import de.hsos.ooadproject.uimodel.PortfolioListItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PortfolioController extends Routable implements Initializable {
    @FXML
    public Label portfolioValue;
    @FXML
    private PieChart chart;
    @FXML
    private ListView<Posten> portfolioList;
    private ObservableList<PieChart.Data> chartData;
    private ObservableList<Posten> listData;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.chartData = FXCollections.observableArrayList();

        StockManager stockManager = StockManager.getInstance();
        UserManager userManager = UserManager.getInstance();
        Depot depot = userManager.getDepot();

        depot.addPosten(stockManager.getStockList().get(0), 10);
        depot.addPosten(stockManager.getStockList().get(1), 342);
        depot.addPosten(stockManager.getStockList().get(2), 6);
        depot.addPosten(stockManager.getStockList().get(3), 155);
        depot.addPosten(stockManager.getStockList().get(4), 105);
        depot.addPosten(stockManager.getStockList().get(5), 170);
        depot.addPosten(stockManager.getStockList().get(6), 170);
        depot.addPosten(stockManager.getStockList().get(7), 456);
        depot.addPosten(stockManager.getStockList().get(8), 170);

        portfolioValue.setText(String.valueOf(depot.getValue()));

        // Gesamtwert
        for (Posten p : depot.getPosten()) {
            p.getStock().askProperty().addListener((observable, oldValue, newValue) -> {
                portfolioValue.setText(String.valueOf(depot.getValue()));
            });

            PieChart.Data data = new PieChart.Data(p.getStock().getName(), p.getAskValue() / depot.getValue());
            data.nameProperty().bind(p.getStock().nameProperty());
            data.pieValueProperty().bind(p.getStock().askProperty().divide(depot.getValue()));
            this.chartData.add(data);
        }

        // Chart
        chart.setData(chartData);
        this.chart.setAnimated(true);

        // List
        listData = FXCollections.observableArrayList(depot.getPosten());

        portfolioList.setCellFactory(portfolioListView -> {
            PortfolioListItem item = new PortfolioListItem();

            item.setOnMouseClicked(event -> {
                try {
                    Router.getInstance().pushRoute("stockDetails", item.getItem().getStock());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            return item;
        });
        portfolioList.setItems(listData);
    }
}
