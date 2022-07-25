package de.hsos.ooadproject.controller;

import de.hsos.ooadproject.Router;
import de.hsos.ooadproject.api.StockManager;
import de.hsos.ooadproject.api.UserManager;
import de.hsos.ooadproject.datamodel.Stock;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.enums.ButtonType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StockListTableController implements Initializable {
  final ObservableList<Stock> stockList = FXCollections.observableArrayList();
  @FXML
  private TableColumn<Stock, String> colName, colSymbol, colVortag, colBid, colAsk, colPercent, colPlusMinus, colTime, colAction;
  @FXML
  private TableView<Stock> stockListTable;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    UserManager userManager = new UserManager();
    StockManager stockManager = new StockManager();
    stockList.setAll(stockManager.getStockList());

    //s.nameProperty().addListener((observable, oldValue, newValue) -> stockListTable.refresh());

    colName.setCellValueFactory(new PropertyValueFactory<>("name"));
    colSymbol.setCellValueFactory(new PropertyValueFactory<>("symbol"));
    colVortag.setCellValueFactory(new PropertyValueFactory<>("vortag"));
    colBid.setCellValueFactory(new PropertyValueFactory<>("bid"));
    colAsk.setCellValueFactory(new PropertyValueFactory<>("ask"));
    colPercent.setCellValueFactory(new PropertyValueFactory<>("percent"));
    colPlusMinus.setCellValueFactory(new PropertyValueFactory<>("plusMinus"));
    colTime.setCellValueFactory(new PropertyValueFactory<>("time"));

    colAction.setCellFactory(tc -> new TableCell<>() {
      private final MFXButton btn = new MFXButton("★");

      {
        btn.setStyle("-fx-background-color: #FAC710");
        btn.setButtonType(ButtonType.RAISED);
        btn.setOnAction(e -> {
          Stock data = getTableView().getItems().get(getIndex());
          System.out.println(data);
          userManager.addStockToWatchList(data.getSymbol());
        });
      }

      @Override
      protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
          setGraphic(null);
        } else {
          setGraphic(btn);
        }
      }
    });

    stockListTable.setRowFactory(tv -> {
      TableRow<Stock> row = new TableRow<>();
      row.setOnMouseClicked(event -> {
        if (!row.isEmpty()) {
          Stock rowData = row.getItem();
          System.out.println(rowData.getName());
          try {
            showSockDetailsScreen(rowData);
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
        }
      });
      return row;
    });

    stockListTable.setItems(stockList);
  }

  void showSockDetailsScreen(Stock stock) throws IOException {
    Router.getInstance().pushRoute("stockDetails", stock);
  }
}
