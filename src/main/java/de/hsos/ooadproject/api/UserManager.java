package de.hsos.ooadproject.api;

import de.hsos.ooadproject.datamodel.Depot;

import java.util.ArrayList;
import java.util.List;

public class UserManager {
  public static Depot depot = new Depot();
  public static final List<String> watchListStockIds = new ArrayList<>();
  private static UserManager singleInstance = null;

  private UserManager() {
  }

  public static UserManager getInstance() {
    if (singleInstance == null) {
      singleInstance = new UserManager();
    }
    return singleInstance;
  }

  public List<String> getWatchListStockIds() {
    return watchListStockIds;
  }

  public void addStockToWatchList(String stockId) {
    if(watchListStockIds.contains(stockId)) { return; }

    watchListStockIds.add(stockId);
  }

  public void removeStockFromWatchList(String stockId) {
    watchListStockIds.remove(stockId);
  }

  public Depot getDepot() {
    return depot;
  }

}
