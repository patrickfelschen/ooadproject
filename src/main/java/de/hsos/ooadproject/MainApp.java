package de.hsos.ooadproject;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainApp extends Application {
  @Override
  public void start(Stage stage) throws Exception {
    stage.setTitle("Aktienverwaltung");

    Image image = new Image(String.valueOf(MainApp.class.getResource("img/icon.png")));
    stage.getIcons().add(image);

    Router.bind(stage);
    Router.getInstance().pushRoute("watchList");
  }
}
