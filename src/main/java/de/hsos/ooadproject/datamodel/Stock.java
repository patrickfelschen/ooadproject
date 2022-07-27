package de.hsos.ooadproject.datamodel;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

/**
 * Stock stellt die Werte einer Aktie dar.
 */
public class Stock {

  private final StringProperty name;
  private final StringProperty symbol;
  private final FloatProperty vortag;
  private final FloatProperty bid;
  private final FloatProperty ask;
  private final FloatProperty percent;
  private final FloatProperty plusMinus;
  private final StringProperty time;
  private final ObservableList<HistoryPoint> history;

  /**
   * Erzeugt ein Objekt einer Aktie. Parameter werden in Properties umgewandelt, welche das Koppel an andere Objekte ermöglichen.
   *
   * @param name      Name der Aktie.
   * @param symbol    Symbol der Aktie. Aktie lässt sich darüber eindeutig identifizieren.
   * @param vortag    Preis der Aktie vom Vortag.
   * @param bid       Angebotspreis der Aktie.
   * @param ask       Nachfragepreis der Aktie.
   * @param percent   Prozentuale Preisänderung der Aktie.
   * @param plusMinus Wert der Preisänderung.
   * @param time      Datum der letzten Aktualisierung.
   */
  public Stock(String name, String symbol, float vortag, float bid, float ask, float percent, float plusMinus, String time) {
    this.name = new SimpleStringProperty(name);
    this.symbol = new SimpleStringProperty(symbol);
    this.vortag = new SimpleFloatProperty(vortag);
    this.bid = new SimpleFloatProperty(bid);
    this.ask = new SimpleFloatProperty(ask);
    this.percent = new SimpleFloatProperty(percent);
    this.plusMinus = new SimpleFloatProperty(plusMinus);
    this.time = new SimpleStringProperty(time);
    this.history = FXCollections.observableArrayList();
  }

  public ObservableList<HistoryPoint> getHistory() {
    return history;
  }

  public void setHistory(List<HistoryPoint> history) {
    this.history.clear();
    this.history.addAll(history);
  }

  public StringProperty nameProperty() {
    return name;
  }

  public StringProperty symbolProperty() {
    return symbol;
  }

  public FloatProperty vortagProperty() {
    return vortag;
  }

  public FloatProperty bidProperty() {
    return bid;
  }

  public FloatProperty askProperty() {
    return ask;
  }

  public FloatProperty percentProperty() {
    return percent;
  }

  public FloatProperty plusMinusProperty() {
    return plusMinus;
  }

  public StringProperty timeProperty() {
    return time;
  }

  public String getName() {
    return this.name.get();
  }

  public void setName(String name) {
    this.name.set(name);
  }

  public String getSymbol() {
    return this.symbol.get();
  }

  public void setSymbol(String symbol) {
    this.symbol.set(symbol);
  }

  public float getVortag() {
    return this.vortag.get();
  }

  public void setVortag(float vortag) {
    this.vortag.set(vortag);
  }

  public float getBid() {
    return this.bid.get();
  }

  public void setBid(float bid) {
    this.bid.set(bid);
  }

  public float getAsk() {
    return this.ask.get();
  }

  public void setAsk(float ask) {
    this.ask.set(ask);
  }

  public float getPercent() {
    return this.percent.get();
  }

  public void setPercent(float percent) {
    this.percent.set(percent);
  }

  public float getPlusMinus() {
    return this.plusMinus.get();
  }

  public void setPlusMinus(float plusMinus) {
    this.plusMinus.set(plusMinus);
  }

  public String getTime() {
    return this.time.get();
  }

  public void setTime(String time) {
    this.time.set(time);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Stock stock = (Stock) o;

    if (!name.equals(stock.name)) return false;
    if (!symbol.equals(stock.symbol)) return false;
    if (!vortag.equals(stock.vortag)) return false;
    if (!bid.equals(stock.bid)) return false;
    if (!ask.equals(stock.ask)) return false;
    if (!percent.equals(stock.percent)) return false;
    if (!plusMinus.equals(stock.plusMinus)) return false;
    return time.equals(stock.time);
  }

  @Override
  public int hashCode() {
    int result = name.hashCode();
    result = 31 * result + symbol.hashCode();
    result = 31 * result + vortag.hashCode();
    result = 31 * result + bid.hashCode();
    result = 31 * result + ask.hashCode();
    result = 31 * result + percent.hashCode();
    result = 31 * result + plusMinus.hashCode();
    result = 31 * result + time.hashCode();
    return result;
  }
}
