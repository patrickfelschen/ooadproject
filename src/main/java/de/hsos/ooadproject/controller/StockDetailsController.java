package de.hsos.ooadproject.controller;

import de.hsos.ooadproject.Router;
import de.hsos.ooadproject.datamodel.HistoryPoint;
import de.hsos.ooadproject.datamodel.Stock;
import de.hsos.ooadproject.interfaces.Routable;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

import java.io.IOException;

public class StockDetailsController extends Routable {
  @FXML
  private Label lbStockName, lbSymbol, lbVortag, lbBid, lbAsk, lbPercent, lbTime, lbPlusMinus;
  @FXML
  private LineChart<String, Number> lineChart;
  private Stock stock;

  public void setStock(Stock stock) {
    this.stock = stock;
    this.lbStockName.setText(stock.getName());
    this.lbSymbol.setText(stock.getSymbol());
    this.lbVortag.setText(String.valueOf(stock.getVortag()));
    this.lbAsk.setText(String.valueOf(stock.getAsk()));
    this.lbBid.setText(String.valueOf(stock.getBid()));
    this.lbPercent.setText(String.valueOf(stock.getPercent()));
    this.lbPlusMinus.setText(String.valueOf(stock.getPlusMinus()));
    this.lbTime.setText(stock.getTime());

    // Bind to properties and update the UI
    lbStockName.textProperty().bind(Bindings.convert(stock.nameProperty()));
    lbSymbol.textProperty().bind(Bindings.convert(stock.symbolProperty()));
    lbVortag.textProperty().bind(Bindings.convert(stock.vortagProperty()));
    lbAsk.textProperty().bind(Bindings.convert(stock.askProperty()));
    lbBid.textProperty().bind(Bindings.convert(stock.bidProperty()));
    lbPercent.textProperty().bind(Bindings.convert(stock.percentProperty()));
    lbPlusMinus.textProperty().bind(Bindings.convert(stock.plusMinusProperty()));
    lbTime.textProperty().bind(Bindings.convert(stock.timeProperty()));

    XYChart.Series<String, Number> series = new XYChart.Series<>();

    series.setName("Portfolio");
    //populating the series with data
    for (HistoryPoint hp : stock.getHistory()) {
      series.getData().add(new XYChart.Data<>(hp.getDate(), hp.getAsk()));
    }

    this.lineChart.setAnimated(true);
    this.lineChart.getData().add(series);
  }

  @FXML
  void navigateBack(ActionEvent e) throws IOException {
    Router.getInstance().popRoute();
  }

  @Override
  public void setData(Object data) {
    if (data == null) return;
    setStock((Stock) data);
  }
}
