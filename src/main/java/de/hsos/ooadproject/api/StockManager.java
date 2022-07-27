package de.hsos.ooadproject.api;

import de.hsos.ooadproject.datamodel.HistoryPoint;
import de.hsos.ooadproject.datamodel.Stock;
import javafx.application.Platform;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

/**
 * StockManager stellt eine künstliche API Schnittstelle dar.
 */
public class StockManager {
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
  private static StockManager singleInstance = null;

  /**
   * Füllt den Preisverlauf der Aktien mit Daten und ändert in einem Thread kontinuierlich die Werte der Aktien mit zufälligen Daten.
   */
  private StockManager() {
    Random rand = new Random();

    // Random Werte setzen
    Thread updateThread = new Thread(() -> {
      while (true) {
        try {
          Platform.runLater(() -> {
            Calendar cal = Calendar.getInstance();
            DecimalFormat df = new DecimalFormat("#.00");
            for (Stock s : stockList) {
              s.setVortag(s.getVortag() + rand.nextFloat(0, 1));
              s.setBid(s.getBid() + rand.nextFloat(0, 1));
              s.setAsk(s.getAsk() + rand.nextFloat(0, 1));
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

  public void setStockHistory(Stock stock, LocalDateTime startDate, LocalDateTime endDate, ChronoUnit interval) {
    List<LocalDateTime> datesInRange = new ArrayList<>();

    // Tage zwischen Daten berechnen
    LocalDateTime copyStart = startDate;
    while (copyStart.isBefore(endDate)) {
      datesInRange.add(copyStart);
      copyStart = copyStart.plus(1, interval);
    }

    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    Random rand = new Random();
    List<HistoryPoint> historyPoints = new ArrayList<>();

    // Random StockDaten pro Tag generieren
    float val = 1;
    for (LocalDateTime d : datesInRange) {
      String date = dateFormatter.format(d);
      val += rand.nextFloat(-1, 1);
      historyPoints.add(new HistoryPoint(date, val));
    }

    // Stock updaten
    stock.setHistory(historyPoints);
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
