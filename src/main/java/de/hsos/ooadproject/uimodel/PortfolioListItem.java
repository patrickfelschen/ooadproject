package de.hsos.ooadproject.uimodel;

import de.hsos.ooadproject.MainApp;
import de.hsos.ooadproject.datamodel.Stock;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class PortfolioListItem extends ListCell<Stock> {
    @FXML
    private Label nameLabel, latestPrice, latestPriceValue, amountInEUR, amountInEURValue, amountInPercent, amountInPercentValue;
    @FXML
    private GridPane gridPane;
    private FXMLLoader loader;

    @Override
    protected void updateItem(Stock stock, boolean empty) {
        super.updateItem(stock, empty);

        if(empty || stock == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (loader == null) {
                loader = new FXMLLoader(MainApp.class.getResource("portfolio-list-item.fxml"));
                loader.setController(this);

                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            nameLabel.setText(stock.getName());
            latestPrice.setText(Float.toString(stock.getVortag()));

            setText(null);
            setGraphic(gridPane);
        }
    }
}
