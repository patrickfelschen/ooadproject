package de.hsos.ooadproject.controller;

import de.hsos.ooadproject.Router;
import de.hsos.ooadproject.datamodel.Stock;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WatchListTableController implements Initializable {
  final ObservableList<Stock> aktien = FXCollections.observableArrayList(
          new Stock("Allianz", "DE0008404005", 177.96f, 0.0f, 0.0f, 0.0f, 0.0f, "00:00:00"),
          new Stock("Basler", "DE0005102008", 88.70f, 0.0f, 0.0f, 0.0f, 0.0f, "00:00:00")
  );
  @FXML
  private TableColumn<Stock, String> colName, colSymbol, colVortag, colBid, colAsk, colPercent, colPlusMinus, colTime;
  @FXML
  private TableView<Stock> watchListTable;

  @Override
  public void initialize(URL location, ResourceBundle resources) {

    colName.setCellValueFactory(new PropertyValueFactory<>("name"));
    colSymbol.setCellValueFactory(new PropertyValueFactory<>("symbol"));
    colVortag.setCellValueFactory(new PropertyValueFactory<>("vortag"));
    colBid.setCellValueFactory(new PropertyValueFactory<>("bid"));
    colAsk.setCellValueFactory(new PropertyValueFactory<>("ask"));
    colPercent.setCellValueFactory(new PropertyValueFactory<>("percent"));
    colPlusMinus.setCellValueFactory(new PropertyValueFactory<>("plusMinus"));
    colTime.setCellValueFactory(new PropertyValueFactory<>("time"));

    watchListTable.setRowFactory(tv -> {
      TableRow<Stock> row = new TableRow<>();
      row.setOnMouseClicked(event -> {
        if (!row.isEmpty()) {
          Stock rowData = row.getItem();
          System.out.println(rowData.getName());
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
  }

  void showSockDetailsScreen(MouseEvent e, Stock stock) throws IOException {
    Router.getInstance().pushRoute("stockDetails", stock);
  }
}

/* Quellen:
  https://riptutorial.com/javafx/example/27946/add-button-to-tableview
  https://docs.oracle.com/javafx/2/ui_controls/table-view.htm
*/
