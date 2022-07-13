package de.hsos.ooadproject;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {
  @Override
  public void start(Stage stage) throws Exception {
    stage.setTitle("Aktienverwaltung");
    Router.bind(stage);
    Router.getInstance().navigate("watchList");
  }
}
