package de.hsos.ooadproject.api;

import de.hsos.ooadproject.datamodel.HistoryPoint;
import de.hsos.ooadproject.datamodel.Stock;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

/**
 * StockManager stellt eine künstliche API Schnittstelle dar.
 */
public class StockManager {
  private static StockManager singleInstance = null;
  public static List<Stock> stockList = new ArrayList<>(
          List.of(
                  new Stock("1&1", "DE0005545503", 0, 0, 0, 0, 0, "00:00:00"),
                  new Stock("11880 Solutions", "DE0005118806", 0, 0, 0, 0, 0, "00:00:00"),
                  new Stock("3U", "DE0005167902", 0, 0, 0, 0, 0, "00:00:00"),
                  new Stock("Aareal Bank", "DE0005408116", 0, 0, 0, 0, 0, "00:00:00"),
                  new Stock("Activa Resources", "DE0007471377", 0, 0, 0, 0, 0, "00:00:00"),
                  new Stock("ADVA", "DE0005103006", 0, 0, 0, 0, 0, "00:00:00"),
                  new Stock("Air Berlin", "GB00B128C026", 0, 0, 0, 0, 0, "00:00:00"),
                  new Stock("ALBIS Leasing", "DE0006569403", 0, 0, 0, 0, 0, "00:00:00"),
                  new Stock("All for One Group", "DE0005110001", 0, 0, 0, 0, 0, "00:00:00"),
                  new Stock("Allianz", "DE0008404005", 0, 0, 0, 0, 0, "00:00:00")
          )
  );

  /**
   * Füllt den Preisverlauf der Aktien mit Daten und ändert in einem Thread kontinuierlich die Werte der Aktien mit zufälligen Daten.
   */
  private StockManager() {
    // Random Verlauf generieren
    List<HistoryPoint> hpl = new ArrayList<>();
    Random rand = new Random();
    float val = 1;
    for (int i = 0; i < 200; i++) {
      val += rand.nextFloat(-1, 1);
      hpl.add(new HistoryPoint(i + ". Tag", val));
    }
    for (Stock s : stockList) {
      s.addAllHistoryPoint(hpl);
    }
    // Random Werte setzen
    Thread updateThread = new Thread(() -> {
      while (true) {
        try {
          Platform.runLater(() -> {
            Calendar cal = Calendar.getInstance();
            for (Stock s : stockList) {
              //s.addAllHistoryPoint(hpl);
              s.setVortag(s.getVortag() + rand.nextFloat(0, 1));
              s.setBid(s.getBid() + rand.nextFloat(0, 1));
              s.setAsk(s.getAsk() + rand.nextFloat(-1, 1));
              s.setPercent(s.getPercent() + rand.nextFloat(0, 1));
              s.setPlusMinus(s.getPlusMinus() + rand.nextFloat(0, 1));
              s.setTime(cal.getTime().toString());
            }
          });
          Thread.sleep(5000);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    });
    updateThread.setDaemon(true);
    updateThread.start();
  }

  /**
   * Zur Umsetzung des Singleton Patterns.
   *
   * @return Instanz des StockManagers.
   */
  public static StockManager getInstance() {
    if (singleInstance == null) {
      singleInstance = new StockManager();
    }
    return singleInstance;
  }

  public List<Stock> getStockList() {
    return stockList;
  }

  /**
   * Findet zu einer übergebenen Liste von Aktien-Symbolen die dazugehörigen Aktien-Objekte.
   *
   * @param stockIds Liste von Symbolen, welche eine Aktie identifizieren.
   * @return Liste von Aktien, die zu den Symbolen gehören.
   */
  public List<Stock> getWatchList(List<String> stockIds) {
    List<Stock> watchList = new ArrayList<>();
    for (Stock s : getStockList()) {
      for (String id : stockIds) {
        if (s.getSymbol().equals(id)) {
          watchList.add(s);
          break;
        }
      }
    }
    return watchList;
  }

}
