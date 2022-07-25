package de.hsos.ooadproject.api;

import java.util.ArrayList;
import java.util.List;

public class UserManager {
  public static final List<String> watchListStockIds = new ArrayList<>();

  public UserManager() {
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
