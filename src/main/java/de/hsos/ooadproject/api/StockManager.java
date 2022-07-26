package de.hsos.ooadproject.api;

import de.hsos.ooadproject.datamodel.Stock;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

  private StockManager() {
    Thread updateThread = new Thread(() -> {
      while (true) {
        try {
          Platform.runLater(() -> {
            Random rand;
            for (Stock s : stockList) {
              rand = new Random();
              s.setVortag(s.getVortag() + rand.nextFloat(0, 1));
              s.setBid(s.getBid() + rand.nextFloat(0, 1));
              s.setAsk(s.getAsk() + rand.nextFloat(0, 1));
              s.setPercent(s.getPercent() + rand.nextFloat(0, 1));
              s.setPlusMinus(s.getPlusMinus() + rand.nextFloat(0, 1));
              s.setTime("00:00:00");
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

  public static StockManager getInstance() {
    if (singleInstance == null) {
      singleInstance = new StockManager();
    }

    return singleInstance;
  }

  public List<Stock> getStockList() {
    return stockList;
  }

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
