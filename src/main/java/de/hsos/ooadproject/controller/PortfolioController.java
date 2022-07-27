package de.hsos.ooadproject.controller;

import de.hsos.ooadproject.Router;
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

/**
 * PortfolioController implementiert die Logik der Portfolio-List und des Diagramms.
 */
public class PortfolioController extends Routable implements Initializable {
    @FXML
    public Label portfolioValue;
    @FXML
    private PieChart chart;
    @FXML
    private ListView<Posten> portfolioList;
    private ObservableList<PieChart.Data> chartData;
    private ObservableList<Posten> listData;

    /**
     * Beim Aufrufen des Controllers werden Aktienwerte aus den Posten dargestellt.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  {@code null} if the location is not known.
     * @param resources The resources used to localize the root object, or {@code null} if
     *                  the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.chartData = FXCollections.observableArrayList();

        UserManager userManager = UserManager.getInstance();
        Depot depot = userManager.getDepot();

        portfolioValue.setText(String.valueOf(depot.getValue()));

        // Gesamtwert (in Euro) des Depots
        for (Posten p : depot.getAllPosten()) {
            p.getStock().askProperty().addListener((observable, oldValue, newValue) -> {
                portfolioValue.setText(String.valueOf(depot.getValue()));
            });

            // Diagrammdaten erstellen
            PieChart.Data data = new PieChart.Data(p.getStock().getName(), p.getAskValue() / depot.getValue());
            data.nameProperty().bind(p.getStock().nameProperty());
            data.pieValueProperty().bind(p.getStock().askProperty().divide(depot.getValue()));
            this.chartData.add(data);
        }

        // Chart
        chart.setData(chartData);
        this.chart.setAnimated(true);

        // List
        listData = FXCollections.observableArrayList(depot.getAllPosten());

        // Neuer Eintrag einer Liste, Zelle ist Element von Klasse PortfolioListItem
        portfolioList.setCellFactory(portfolioListView -> {
            PortfolioListItem item = new PortfolioListItem();

            item.setOnMouseClicked(event -> {
                try {
                    Router.getInstance().pushRoute("stockDetails", item.getItem().getStock()); // Detailübersicht der ausgewählten Aktie
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            return item;
        });
        portfolioList.setItems(listData);
    }
}
