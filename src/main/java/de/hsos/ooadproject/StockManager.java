package de.hsos.ooadproject;

import de.hsos.ooadproject.datamodel.Stock;

import java.util.ArrayList;
import java.util.List;

public class StockManager {
  public static List<Stock> stockList = new ArrayList<>(
          List.of(
                  new Stock("Allianz", "DE0008404005", 177.96f, 0.0f, 0.0f, 0.0f, 0.0f, "00:00:00"),
                  new Stock("Apple", "DE0008404006", 178.96f, 0.3f, 0.0f, 0.0f, 0.0f, "05:00:00")
          )
  );

  public StockManager() {
    Thread updateThread = new Thread(() -> {
      int time = 0;
      while (true) {
        try {
          Thread.sleep(1000);
          //stockList.get(0).setName(String.valueOf(time++));
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    });
    updateThread.setDaemon(true);
    updateThread.start();
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
