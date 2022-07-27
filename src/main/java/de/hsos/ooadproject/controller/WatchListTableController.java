package de.hsos.ooadproject.controller;

import de.hsos.ooadproject.Router;
import de.hsos.ooadproject.api.StockManager;
import de.hsos.ooadproject.api.UserManager;
import de.hsos.ooadproject.datamodel.Stock;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.enums.ButtonType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WatchListTableController implements Initializable {
  final ObservableList<Stock> aktien = FXCollections.observableArrayList();
  @FXML
  private TableColumn<Stock, String> colName, colSymbol, colVortag, colBid, colAsk, colPercent, colPlusMinus, colTime, colAction;
  @FXML
  private TableView<Stock> watchListTable;
  @FXML
  private MFXTextField searchField;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    UserManager userManager = UserManager.getInstance();
    StockManager stockManager = StockManager.getInstance();
    aktien.setAll(stockManager.getWatchList(userManager.getWatchListStockIds()));

    colName.setCellValueFactory(new PropertyValueFactory<>("name"));
    colSymbol.setCellValueFactory(new PropertyValueFactory<>("symbol"));
    colVortag.setCellValueFactory(new PropertyValueFactory<>("vortag"));
    colBid.setCellValueFactory(new PropertyValueFactory<>("bid"));
    colAsk.setCellValueFactory(new PropertyValueFactory<>("ask"));
    colPercent.setCellValueFactory(new PropertyValueFactory<>("percent"));
    colPlusMinus.setCellValueFactory(new PropertyValueFactory<>("plusMinus"));
    colTime.setCellValueFactory(new PropertyValueFactory<>("time"));

    colAction.setCellFactory(tc -> new TableCell<>() {
      private final MFXButton btn = new MFXButton("✖");

      {
        btn.setStyle("-fx-background-color: #eb4034");
        btn.setButtonType(ButtonType.RAISED);
        btn.setOnAction(e -> {
          Stock data = getTableView().getItems().get(getIndex());
          //System.out.println(data);
          userManager.removeStockFromWatchList(data.getSymbol());
          aktien.setAll(stockManager.getWatchList(userManager.getWatchListStockIds()));
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

    watchListTable.setRowFactory(tv -> {
      TableRow<Stock> row = new TableRow<>();
      row.setOnMouseClicked(event -> {
        if (!row.isEmpty()) {
          Stock rowData = row.getItem();
          //System.out.println(rowData.getName());
          try {
            showSockDetailsScreen(event, rowData);
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
        }
      });
      return row;
    });

    watchListTable.setItems(aktien);

    FilteredList<Stock> filteredAktien = new FilteredList<>(aktien);

    this.searchField.textProperty().addListener((observable, oldValue, newValue) -> {
      filteredAktien.setPredicate(stock -> {
        if (newValue.isEmpty() || newValue.isBlank()) {
          return true;
        }

        String searchKeyword = newValue.toLowerCase();

        if (stock.getName().toLowerCase().contains(searchKeyword)) {
          return true;
        } else return stock.getSymbol().toLowerCase().contains(searchKeyword);
      });
    });

    watchListTable.setItems(filteredAktien);
  }

  void showSockDetailsScreen(MouseEvent e, Stock stock) throws IOException {
    Router.getInstance().pushRoute("stockDetails", stock);
  }
}

/* Quellen:
  https://riptutorial.com/javafx/example/27946/add-button-to-tableview
  https://docs.oracle.com/javafx/2/ui_controls/table-view.htm
*/
