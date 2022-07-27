package de.hsos.ooadproject.controller;

import de.hsos.ooadproject.Router;
import de.hsos.ooadproject.api.UserManager;
import de.hsos.ooadproject.datamodel.Depot;
import de.hsos.ooadproject.datamodel.Stock;
import de.hsos.ooadproject.interfaces.Routable;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StockBuyController extends Routable implements Initializable {
    @FXML
    private Label lbStockName, lbBid, lbAmount, max;
    @FXML
    private Spinner<Integer> spAmount;
    //private IntegerSpinnerValueFactory spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE);
    private Depot depot;
    private Stock stock;

    public void initialize(URL location, ResourceBundle resources) {
        UserManager userManager = UserManager.getInstance();
        this.depot = userManager.getDepot();
    }

    public void setStock(Stock stock) {
        this.stock = stock;
        this.lbStockName.setText(stock.getName());
        this.lbBid.setText(String.valueOf(stock.getBid()));
        this.lbAmount.setText("0.0");

        // Bind listener to properties and update the UI
        this.stock.bidProperty().addListener((observable, oldValue, newValue) ->
                Platform.runLater(() -> {
                    lbBid.setText(String.valueOf(newValue));
                    this.lbAmount.setText(String.valueOf(newValue.floatValue() * this.spAmount.getValue()));
                })
        );

        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE);
        spAmount.setValueFactory(valueFactory);
        spAmount.valueProperty().addListener((obs, oldValue, newValue) ->
                this.lbAmount.setText(String.valueOf(stock.getBid() * newValue)));
    }


    public void buyStock(ActionEvent e) throws IOException {
        this.depot.addPosten(this.stock, this.spAmount.getValue());
        Router.getInstance().pushRoute("portfolio");
    }

    @FXML
    void cancel(ActionEvent e) throws IOException {
        Router.getInstance().popRoute(this.stock);
    }

    @Override
    public void setData(Object data) {
        setStock((Stock) data);
    }
}
