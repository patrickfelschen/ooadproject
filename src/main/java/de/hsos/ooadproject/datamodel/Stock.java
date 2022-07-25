package de.hsos.ooadproject.datamodel;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Stock {
  private final StringProperty name;
  private final StringProperty symbol;
  private final FloatProperty vortag;
  private final FloatProperty bid;
  private final FloatProperty ask;
  private final FloatProperty percent;
  private final FloatProperty plusMinus;
  private final StringProperty time;

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

  public void setSymbol(String symbol) {
    this.symbol.set(symbol);
  }

  public void setVortag(float vortag) {
    this.vortag.set(vortag);
  }

  public void setBid(float bid) {
    this.bid.set(bid);
  }

  public void setAsk(float ask) {
    this.ask.set(ask);
  }

  public void setPercent(float percent) {
    this.percent.set(percent);
  }

  public void setPlusMinus(float plusMinus) {
    this.plusMinus.set(plusMinus);
  }

  public void setTime(String time) {
    this.time.set(time);
  }

  public void setName(String name) {
    this.name.set(name);
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
