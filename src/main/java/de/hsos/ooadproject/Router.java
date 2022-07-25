package de.hsos.ooadproject;

import de.hsos.ooadproject.interfaces.Routable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Router {
  private static Router singleInstance = null;
  private final Map<String, String> routes;
  private final Stack<String> navStack;
  private Stage primaryStage;

  private Router() {
    this.routes = new HashMap<>();
    this.navStack = new Stack<>();

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

  private void navigate(String routeName, Object data) throws IOException {
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

    primaryStage.setMinHeight(400);
    primaryStage.setMinWidth(720);

    this.primaryStage.show();
  }

  public void popRoute(Object data) throws IOException {
    if (navStack.isEmpty()) return;
    navStack.pop();
    String routeName = navStack.peek();
    this.navigate(routeName, data);
  }

  public void popRoute() throws IOException {
    popRoute(null);
  }

  public void pushRoute(String routeName, Object data) throws IOException {
    if (!routes.containsKey(routeName)) return;
    this.navigate(routeName, data);
    this.navStack.push(routeName);
  }

  public void pushRoute(String routeName) throws IOException {
    pushRoute(routeName, null);
  }
}
