package de.hsos.ooadproject.controller;

import de.hsos.ooadproject.Router;
import de.hsos.ooadproject.api.UserManager;
import de.hsos.ooadproject.datamodel.Depot;
import de.hsos.ooadproject.datamodel.Stock;
import de.hsos.ooadproject.interfaces.Routable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StockSellController extends Routable implements Initializable {
  @FXML
  private Label lbStockName, lbAsk, lbAmount;
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
    this.lbAsk.setText(String.valueOf(stock.getAsk()));
    this.lbAmount.setText("0.0");

    // Bind listener to properties and update the UI
    this.stock.bidProperty().addListener(
            (observable, oldValue, newValue) -> {
              this.lbAsk.setText(String.valueOf(newValue));
              this.lbAmount.setText(String.valueOf(newValue.floatValue() * this.spAmount.getValue()));
            }
    );

    SpinnerValueFactory<Integer> valueFactory =
            new SpinnerValueFactory.IntegerSpinnerValueFactory(0, this.depot.getPosten(stock).getNumber());

    this.spAmount.setValueFactory(valueFactory);
    this.spAmount.valueProperty().addListener(
            (obs, oldValue, newValue) -> {
              this.lbAmount.setText(String.valueOf(stock.getBid() * newValue));
            }
    );

  }

  public void sellStock(ActionEvent e) throws IOException {
    this.depot.removePosten(this.stock, this.spAmount.getValue());
    Router.getInstance().pushRoute("portfolio");
  }

  public void cancel(ActionEvent e) {
    Router.getInstance().popAllPopups();
  }

  @Override
  public void setData(Object data) {
    if (data == null) return;
    setStock((Stock) data);
  }
}
