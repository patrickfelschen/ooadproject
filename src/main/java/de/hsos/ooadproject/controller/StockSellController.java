package de.hsos.ooadproject.controller;

import de.hsos.ooadproject.Router;
import de.hsos.ooadproject.api.UserManager;
import de.hsos.ooadproject.datamodel.Depot;
import de.hsos.ooadproject.datamodel.Stock;
import de.hsos.ooadproject.interfaces.Routable;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * verantwortlich: Janek Büscher
 * mitwirkend: Patrick Felschen
 */
public class StockSellController extends Routable implements Initializable {
  @FXML
  private Label lbStockName, lbAsk, lbAmount;
  @FXML
  private Spinner<Integer> spAmount;
  private Depot depot;
  private Stock stock;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    UserManager userManager = UserManager.getInstance();
    this.depot = userManager.getDepot();
  }

  private void setStock(Stock stock) {
    this.stock = stock;

    // Spinner einstellen
    SpinnerValueFactory<Integer> valueFactory =
            new SpinnerValueFactory
                    .IntegerSpinnerValueFactory(0, this.depot.getPosten(stock).getNumber());

    this.spAmount.setValueFactory(valueFactory);

    // Ask Wert mit Anzahl multiplizieren
    NumberBinding total = stock.askProperty().multiply(
            ReadOnlyIntegerProperty.readOnlyIntegerProperty(
                    spAmount.valueProperty()
            )
    );

    // Labels setzen
    this.lbStockName.setText(stock.getName());
    this.lbAmount.textProperty().bind(Bindings.convert(total));
    this.lbAsk.textProperty().bind(Bindings.convert(stock.askProperty()));
  }

  @FXML
  private void sellStock(ActionEvent e) throws IOException {
    this.depot.removePosten(this.stock, this.spAmount.getValue());
    Router.getInstance().pushRoute("portfolio");
  }

  @FXML
  private void cancel(ActionEvent e) {
    Router.getInstance().popAllPopups();
  }

  @Override
  public void setData(Object data) {
    if (data == null) return;
    setStock((Stock) data);
  }
}
