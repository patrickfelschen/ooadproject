package de.hsos.ooadproject.model;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;

public class Stock {
  private final SimpleStringProperty name;
  private final SimpleStringProperty symbol;
  private final SimpleFloatProperty vortag;
  private final SimpleFloatProperty bid;
  private final SimpleFloatProperty ask;
  private final SimpleFloatProperty percent;
  private final SimpleFloatProperty plusMinus;
  private final SimpleStringProperty time;

  public Stock(String name, String symbol, float vortag, float bid, float ask, float percent, float plusMinus, String time) {
    this.name = new SimpleStringProperty(name);
    this.symbol = new SimpleStringProperty(symbol);
    this.vortag = new SimpleFloatProperty(vortag);
    this.bid = new SimpleFloatProperty(bid);
    this.ask = new SimpleFloatProperty(ask);
    this.percent = new SimpleFloatProperty(percent);
    this.plusMinus = new SimpleFloatProperty(plusMinus);
    this.time = new SimpleStringProperty(time);
  }

  public String getName() {
    return this.name.get();
  }

  public String getSymbol() {
    return this.symbol.get();
  }

  public float getVortag() {
    return this.vortag.get();
  }

  public float getBid() {
    return this.bid.get();
  }

  public float getAsk() {
    return this.ask.get();
  }

  public float getPercent() {
    return this.percent.get();
  }

  public float getPlusMinus() {
    return this.plusMinus.get();
  }

  public String getTime() {
    return this.time.get();
  }

}
