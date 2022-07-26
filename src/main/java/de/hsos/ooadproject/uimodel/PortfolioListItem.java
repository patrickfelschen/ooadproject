package de.hsos.ooadproject.uimodel;

import de.hsos.ooadproject.MainApp;
import de.hsos.ooadproject.datamodel.Posten;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class PortfolioListItem extends ListCell<Posten> {
    @FXML
    private Label nameLabel, symbolLabel, latestPrice, latestPriceValue, amountInEUR, amountInEURValue, amountInPercent, amountInPercentValue;
    @FXML
    private GridPane gridPane;
    private FXMLLoader loader;

    @Override
    protected void updateItem(Posten posten, boolean empty) {
        super.updateItem(posten, empty);

        if (empty || posten == null) {
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

            nameLabel.setText(posten.getStock().getName()); //.textProperty().bind(posten.getStock().nameProperty());
            symbolLabel.setText(posten.getStock().getSymbol()); //.textProperty().bind(posten.getStock().symbolProperty());
            amountInEURValue.setText(String.valueOf(posten.getAskValue())); // .textProperty().bind(posten.getStock().askProperty().asString());

            setText(null);
            setGraphic(gridPane);
        }
    }
}
