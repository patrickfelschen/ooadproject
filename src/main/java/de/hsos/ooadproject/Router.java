package de.hsos.ooadproject;

import de.hsos.ooadproject.controller.Routable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Router {
  private static Router singleInstance = null;
  private final Map<String, String> routes;
  private Stage primaryStage;

  private Router() {
    this.routes = new HashMap<>();

    this.routes.put("watchList", "watch-list-view.fxml");
    this.routes.put("stockList", "stock-list-view.fxml");
    this.routes.put("portfolio", "portfolio-view.fxml");
    this.routes.put("stockDetails", "stock-details-view.fxml");
  }

  public static void bind(Stage primaryStage) throws IOException {
    getInstance().primaryStage = primaryStage;
  }

  public static Router getInstance() {
    if (singleInstance == null) {
      singleInstance = new Router();
    }

    return singleInstance;
  }

  public void navigate(String routeName, Object data) throws IOException {
    String fxmlfile = this.routes.get(routeName);
    FXMLLoader loader = new FXMLLoader(MainApp.class.getResource(fxmlfile));
    Parent root = loader.load();

    Routable routable = loader.getController();
    routable.setData(data);

    double height;
    double width;

    if (primaryStage.getScene() == null) {
      height = 800;
      width = 1000;
    } else {
      height = primaryStage.getScene().getWindow().getHeight();
      width = primaryStage.getScene().getWindow().getWidth();
    }

    Scene scene = new Scene(root);

    this.primaryStage.setScene(scene);

    primaryStage.setHeight(height);
    primaryStage.setWidth(width);

    this.primaryStage.show();
  }

  public void navigate(String routeName) throws IOException {
    navigate(routeName, null);
  }
}
