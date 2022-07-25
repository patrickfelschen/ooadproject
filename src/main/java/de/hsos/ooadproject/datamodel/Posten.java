package de.hsos.ooadproject.datamodel;

public class Posten {
  private Stock stock;
  private int number;

  public Posten(Stock stock, int number) {
    this.stock = stock;
    this.number = number;
  }

  public Stock getStock() {
    return stock;
  }

  public void setStock(Stock stock) {
    this.stock = stock;
  }

  public int getNumber() {
    return number;
  }

  public void setNumber(int number) {
    this.number = number;
  }
}
