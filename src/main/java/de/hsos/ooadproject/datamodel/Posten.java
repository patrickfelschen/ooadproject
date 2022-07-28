package de.hsos.ooadproject.datamodel;

import javafx.beans.property.*;

/**
 * Posten stellt einen Wert im Portfolio, bestehend aus Aktie und Anzahl, dar.
 */
public class Posten {
  private final ObjectProperty<Stock> stock;
  private final IntegerProperty number;
  private final FloatProperty askValueSum;

  /**
   * Erzeugt einen neuen Posten aus Aktie und Anzahl. Parameter werden in Properties umgewandelt, welche das Koppel an andere Objekte ermöglichen.
   *
   * @param stock  Aktie des Postens.
   * @param number Anzahl der Aktien.
   */
  public Posten(Stock stock, int number) {
    this.stock = new SimpleObjectProperty<>(this, "stock", stock);
    this.number = new SimpleIntegerProperty(this, "number", number);
    this.askValueSum = new SimpleFloatProperty(this, "askValueSum");

    // Binden um Gesamtwert zu berechnen
    this.askValueSum.bind(stock.askProperty().multiply(this.number));
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

  /**
   * @return Gesamter Nachfragewert der Aktien im Posten.
   */
  public FloatProperty askValueSumProperty() {
    return askValueSum;
  }

  public double getAskValueSum() {
    return askValueSum.get();
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
