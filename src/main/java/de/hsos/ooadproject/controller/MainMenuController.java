package de.hsos.ooadproject.controller;

import de.hsos.ooadproject.Router;
import javafx.event.ActionEvent;

import java.io.IOException;

public class MainMenuController extends Routable {

  public void showWatchList(ActionEvent e) throws IOException {
    Router.getInstance().pushRoute("watchList");
  }

  public void showStockList(ActionEvent e) throws IOException {
    Router.getInstance().pushRoute("stockList");
  }

  public void showPortfolio(ActionEvent e) throws IOException {
    Router.getInstance().pushRoute("portfolio");
  }

  @Override
  public void setData(Object data) {
  }
}
