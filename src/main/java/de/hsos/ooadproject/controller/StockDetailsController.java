package de.hsos.ooadproject.controller;

import de.hsos.ooadproject.Router;
import de.hsos.ooadproject.api.StockManager;
import de.hsos.ooadproject.datamodel.HistoryPoint;
import de.hsos.ooadproject.datamodel.Stock;
import de.hsos.ooadproject.interfaces.Routable;
import javafx.beans.binding.Bindings;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class StockDetailsController extends Routable {
  @FXML
  private Label lbStockName, lbSymbol, lbVortag, lbBid, lbAsk, lbPercent, lbTime, lbPlusMinus;
  @FXML
  private LineChart<String, Number> lineChart;
  private XYChart.Series<String, Number> series;
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

    this.series = new XYChart.Series<>();

    // Live chart data on changes
    stock.getHistory().addListener((ListChangeListener<HistoryPoint>) c -> {
      series.getData().clear();
      for (int i = 0; i < stock.getHistory().size(); i++) {
        series.getData().add(new XYChart.Data<>(
                        stock.getHistory().get(i).getDate(),
                        stock.getHistory().get(i).getAsk()
                )
        );
      }

    });
    this.lineChart.setCreateSymbols(false);
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

  public void stockBuy(ActionEvent actionEvent) throws IOException {
    Router.getInstance().pushRoute("stockBuy", this.stock);
  }

  public void stockSell(ActionEvent actionEvent) throws IOException {
    Router.getInstance().pushRoute("stockSell", this.stock);
  }

  public void setHistoryLastWeek(ActionEvent e) {
    LocalDateTime start = LocalDateTime.now().minus(1, ChronoUnit.WEEKS);
    LocalDateTime end = LocalDateTime.now();
    this.series.setName("1 Woche");
    StockManager.getInstance().setStockHistory(stock, start, end, ChronoUnit.HALF_DAYS);
  }

  public void setHistoryLastMonth(ActionEvent e) {
    LocalDateTime start = LocalDateTime.now().minus(1, ChronoUnit.MONTHS);
    LocalDateTime end = LocalDateTime.now();
    this.series.setName("1 Monat");
    StockManager.getInstance().setStockHistory(stock, start, end, ChronoUnit.DAYS);
  }

  public void setHistoryLastSixMonth(ActionEvent e) {
    LocalDateTime start = LocalDateTime.now().minus(6, ChronoUnit.MONTHS);
    LocalDateTime end = LocalDateTime.now();
    this.series.setName("6 Monate");
    StockManager.getInstance().setStockHistory(stock, start, end, ChronoUnit.DAYS);
  }

  public void setHistoryLastYear(ActionEvent e) {
    LocalDateTime start = LocalDateTime.now().minus(1, ChronoUnit.YEARS);
    LocalDateTime end = LocalDateTime.now();
    this.series.setName("1 Jahr");
    StockManager.getInstance().setStockHistory(stock, start, end, ChronoUnit.DAYS);
  }

  public void setHistoryLastThreeYear(ActionEvent e) {
    LocalDateTime start = LocalDateTime.now().minus(3, ChronoUnit.YEARS);
    LocalDateTime end = LocalDateTime.now();
    this.series.setName("3 Jahre");
    StockManager.getInstance().setStockHistory(stock, start, end, ChronoUnit.DAYS);
  }

  public void setHistoryLastFiveYear(ActionEvent e) {
    LocalDateTime start = LocalDateTime.now().minus(5, ChronoUnit.YEARS);
    LocalDateTime end = LocalDateTime.now();
    this.series.setName("5 Jahre");
    StockManager.getInstance().setStockHistory(stock, start, end, ChronoUnit.DAYS);
  }

}
