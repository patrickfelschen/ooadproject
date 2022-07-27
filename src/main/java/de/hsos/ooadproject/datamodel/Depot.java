package de.hsos.ooadproject.datamodel;

import java.util.ArrayList;
import java.util.List;

public class Depot {
  private final List<Posten> posten;

  public Depot() {
    this.posten = new ArrayList<>();
  }

  public Posten getPosten(Stock stock) {
    for (Posten p1 : posten) {
      if (p1.getStock().equals(stock)) {
        return p1;
      }
    }
    return new Posten(new Stock("", "", 0, 0, 0, 0, 0, ""), 0);
  }

  public List<Posten> getAllPosten() {
    return posten;
  }

  public float getAskValue() {
    float val = 0;
    for (Posten p : this.posten) {
      val += p.getAskValue();
    }
    return val;
  }

  /**
   * @param stock
   * @param number
   */
  public void addPosten(Stock stock, int number) {
    for (Posten p : this.posten) {
      // Posten existiert, Aktienanzahl erhöhen
      if (p.getStock().equals(stock)) {
        p.setNumber(p.getNumber() + number);
        return;
      }
    }

    // Posten existiert nicht, Posten neu anlegen
    this.posten.add(new Posten(stock, number));
  }

  /**
   * @param stock
   * @param number
   */
  public void removePosten(Stock stock, int number) {
    for (Posten p : this.posten) {
      if (p.getStock().equals(stock)) {
        // Posten entfernen
        if (p.getNumber() == number) {
          this.posten.remove(p);
        }
        // Aktienanzahl zu gering
        if (p.getNumber() < number) {
          System.out.println("Anzahl der Aktien zu gering!");
          return;
        }
        // Aktienanzahl verringern
        p.setNumber(p.getNumber() - number);
        break;
      }
    }

  }
}
