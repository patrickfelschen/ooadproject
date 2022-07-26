package de.hsos.ooadproject.datamodel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Posten {
  private final ObjectProperty<Stock> stock;
  private final IntegerProperty number;

  public Posten(Stock stock, int number) {
    this.stock = new SimpleObjectProperty<>(this, "stock", stock);
    this.number = new SimpleIntegerProperty(this, "number", number);
  }

  public ObjectProperty<Stock> stockProperty() {
    return stock;
  }

  public Stock getStock() {
    return stock.get();
  }

  public void setStock(Stock stock) {
    this.stock.set(stock);
  }

  public IntegerProperty numberProperty() {
    return number;
  }

  public int getNumber() {
    return number.get();
  }

  public void setNumber(int number) {
    this.number.set(number);
  }

  public float getAskValue() {
    return number.get() * stock.get().getAsk();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Posten posten = (Posten) o;

    if (!stock.equals(posten.stock)) return false;
    return number.equals(posten.number);
  }

  @Override
  public int hashCode() {
    int result = stock.hashCode();
    result = 31 * result + number.hashCode();
    return result;
  }
}
