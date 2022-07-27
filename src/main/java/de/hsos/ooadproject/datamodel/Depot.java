package de.hsos.ooadproject.datamodel;

import java.util.ArrayList;
import java.util.List;

/**
 * Depot stellt die Werte eines Portfolios dar.
 */
public class Depot {
  private final List<Posten> posten;

  public Depot() {
    this.posten = new ArrayList<>(); //SimpleListProperty<>(this, "posten"); // ArrayList<>();
  }

  /**
   * Sucht aus den verfügbaren Posten nach einer Aktie.
   *
   * @param stock Aktie, nach welcher in den Posten gesucht werden soll.
   * @return Falls Aktie in Posten gefunden wurde, wird der Posten zurückgegeben.
   */
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

  /**
   * Berechnet den Gesamtwert aller Posten.
   *
   * @return Gesamtwert (in Euro).
   */
  public float getValue() {
    float val = 0;
    for (Posten p : this.posten) {
      val += p.getAskValue();
    }
    return val;
  }

  /**
   * Legt einen neuen Posten an, falls noch kein Posten zu der Aktie existiert, erhöht andernfalls die Anzahl der Aktien im Posten.
   *
   * @param stock  Hinzuzufügende Aktie.
   * @param number Anzahl der Aktien.
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
   * Entfernt oder verringert die Anzahl an Aktien in einem Posten.
   * @param stock Zu entfernende oder verringernde Aktie.
   * @param number Anzahl der Aktien.
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
