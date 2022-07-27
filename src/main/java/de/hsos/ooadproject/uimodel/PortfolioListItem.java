package de.hsos.ooadproject.uimodel;

import de.hsos.ooadproject.MainApp;
import de.hsos.ooadproject.api.UserManager;
import de.hsos.ooadproject.datamodel.Depot;
import de.hsos.ooadproject.datamodel.Posten;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;

import java.io.IOException;

/**
 * PortfolioListItem stellt ein Listenelement im Portfolio dar.
 */
public class PortfolioListItem extends ListCell<Posten> {
  private final Depot depot;
  @FXML
  private Label nameLabel, symbolLabel, countValue, latestPriceValue, amountInEURValue, amountInPercentValue;
  @FXML
  private GridPane gridPane;
  private FXMLLoader loader;

  public PortfolioListItem() {
    this.depot = UserManager.getInstance().getDepot();
  }

  /**
   * Legt Werte des Listenlements fest
   *
   * @param posten The new item for the cell.
   * @param empty  whether or not this cell represents data from the list. If it
   *               is empty, then it does not represent any domain data, but is a cell
   *               being used to render an "empty" row.
   */
  @Override
  protected void updateItem(Posten posten, boolean empty) {
    super.updateItem(posten, empty);

    // Laden des Views
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

      nameLabel.textProperty().unbind();
      symbolLabel.textProperty().unbind();
      amountInEURValue.textProperty().unbind();

      // Initiale Werte setzen
      nameLabel.setText(posten.getStock().getName());
      symbolLabel.setText(posten.getStock().getSymbol());

      float val = posten.getStock().getAsk() * posten.getNumber();
      amountInEURValue.setText(val + " EUR");
      latestPriceValue.setText(posten.getStock().getAsk() + " EUR");

      float percent = (val / depot.getValue()) * 100.0f;
      amountInPercentValue.setText(percent + " %");

      nameLabel.textProperty().bind(Bindings.convert(posten.getStock().nameProperty()));
      symbolLabel.textProperty().bind(Bindings.convert(posten.getStock().symbolProperty()));
      countValue.textProperty().bind(Bindings.convert(posten.numberProperty()));

      // Auf Änderungen der Aktie reagieren und Labels neu setzen
      posten.getStock().askProperty().addListener((observable, oldValue, newValue) -> {
        float sumVal = newValue.floatValue() * posten.getNumber();
        amountInEURValue.setText(sumVal + " EUR");
        latestPriceValue.setText(oldValue + " EUR");

        float newPercent = (sumVal / depot.getValue()) * 100.0f;
        amountInPercentValue.setText(newPercent + " %");
      });

      setText(null);
      setGraphic(gridPane);
    }
  }
}
