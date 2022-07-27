package de.hsos.ooadproject.api;

import de.hsos.ooadproject.datamodel.Depot;

import java.util.ArrayList;
import java.util.List;

/**
 * UserManager stellt die Werte eines Nutzers dar.
 */
public class UserManager {
  public static Depot depot = new Depot();
  public static final List<String> watchListStockIds = new ArrayList<>();
  private static UserManager singleInstance = null;

  private UserManager() {
  }

  /**
   * Zur Umsetzung des Singleton Patterns.
   *
   * @return Instanz des UserManagers.
   */
  public static UserManager getInstance() {
    if (singleInstance == null) {
      singleInstance = new UserManager();
    }
    return singleInstance;
  }

  public List<String> getWatchListStockIds() {
    return watchListStockIds;
  }

  /**
   * Fügt ein Aktien-Symbol einmalig einer List hinzu.
   *
   * @param stockId Aktien-Symbol
   */
  public void addStockToWatchList(String stockId) {
    if (watchListStockIds.contains(stockId)) {
      return;
    }

    watchListStockIds.add(stockId);
  }

  /**
   * Entfernt Aktien-Symbol von Liste
   *
   * @param stockId Aktien-Symbol
   */
  public void removeStockFromWatchList(String stockId) {
    watchListStockIds.remove(stockId);
  }

  public Depot getDepot() {
    return depot;
  }

}
