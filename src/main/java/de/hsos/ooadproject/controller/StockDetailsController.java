package de.hsos.ooadproject.controller;

import de.hsos.ooadproject.Router;
import de.hsos.ooadproject.datamodel.Stock;
import de.hsos.ooadproject.interfaces.Routable;
import javafx.application.Platform;
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

    // Bind listener to properties and update the UI
    this.stock.vortagProperty().addListener((observable, oldValue, newValue) -> Platform.runLater(() -> lbVortag.setText(String.valueOf(newValue))));
    this.stock.askProperty().addListener((observable, oldValue, newValue) -> Platform.runLater(() -> lbAsk.setText(String.valueOf(newValue))));
    this.stock.bidProperty().addListener((observable, oldValue, newValue) -> Platform.runLater(() -> lbBid.setText(String.valueOf(newValue))));
    this.stock.percentProperty().addListener((observable, oldValue, newValue) -> Platform.runLater(() -> lbPercent.setText(String.valueOf(newValue))));
    this.stock.vortagProperty().addListener((observable, oldValue, newValue) -> Platform.runLater(() -> lbVortag.setText(String.valueOf(newValue))));
    this.stock.plusMinusProperty().addListener((observable, oldValue, newValue) -> Platform.runLater(() -> lbPlusMinus.setText(String.valueOf(newValue))));

    XYChart.Series<String, Number> series = new XYChart.Series<>();

    series.setName("My portfolio");
    //populating the series with data
    series.getData().add(new XYChart.Data<>("1", 23));
    series.getData().add(new XYChart.Data<>("2", 14));
    series.getData().add(new XYChart.Data<>("3", 15));
    series.getData().add(new XYChart.Data<>("4", 24));
    series.getData().add(new XYChart.Data<>("5", 34));
    series.getData().add(new XYChart.Data<>("6", 36));
    series.getData().add(new XYChart.Data<>("7", 22));
    series.getData().add(new XYChart.Data<>("8", 45));
    series.getData().add(new XYChart.Data<>("9", 43));
    series.getData().add(new XYChart.Data<>("10", 17));
    series.getData().add(new XYChart.Data<>("11", 29));
    series.getData().add(new XYChart.Data<>("12", 25));
    this.lineChart.getData().add(series);
  }

  @FXML
  void navigateBack(ActionEvent e) throws IOException {
    Router.getInstance().popRoute();
  }

  @Override
  public void setData(Object data) {
    setStock((Stock) data);
  }

  public void stockBuy(ActionEvent actionEvent) throws IOException {
    Router.getInstance().pushRoute("stockBuy", this.stock);
  }

  public void stockSell(ActionEvent actionEvent) throws IOException {
    Router.getInstance().pushRoute("stockSell", this.stock);
  }
}
