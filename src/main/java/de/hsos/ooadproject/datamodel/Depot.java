package de.hsos.ooadproject.datamodel;

import java.util.ArrayList;
import java.util.List;

public class Depot {
  private final String id;
  private final List<Posten> posten;

  public Depot(String id) {
    this.id = id;
    this.posten = new ArrayList<>();
  }

  public void addPosten(Stock stock, int number) {
    this.posten.add(new Posten(stock, number));
  }

  public void removePosten(Stock stock, int number) {
    this.posten.remove(stock);
  }
}
