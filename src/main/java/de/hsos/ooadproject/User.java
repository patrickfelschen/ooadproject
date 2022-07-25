package de.hsos.ooadproject;

import java.util.ArrayList;
import java.util.List;

public class User {
  public static final List<String> watchListStockIds = new ArrayList<>();

  public User() {
  }

  public List<String> getWatchListStockIds() {
    return watchListStockIds;
  }

  public void addStockToWatchList(String stockId) {
    watchListStockIds.add(stockId);
  }

  public void removeStockToWatchList(String stockId) {
    watchListStockIds.remove(stockId);
  }

}
