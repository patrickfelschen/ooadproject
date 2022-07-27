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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * StockListController implementiert die Logik der Tabelle zur Übersicht aller Aktien.
 */
public class StockListTableController implements Initializable {
  final ObservableList<Stock> stockList = FXCollections.observableArrayList();
  @FXML
  private TableColumn<Stock, String> colName, colSymbol, colVortag, colBid, colAsk, colPercent, colPlusMinus, colTime, colAction;
  @FXML
  private TableView<Stock> stockListTable;
  @FXML
  private MFXTextField searchField;

  /**
   * Erzeugt Tabelle und füllt sie mit Daten.
   *
   * @param location  The location used to resolve relative paths for the root object, or
   *                  {@code null} if the location is not known.
   * @param resources The resources used to localize the root object, or {@code null} if
   *                  the root object was not localized.
   */
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    UserManager userManager = UserManager.getInstance();
    StockManager stockManager = StockManager.getInstance();
    stockList.setAll(stockManager.getStockList());

    // Erstellen von Tabelleneinträgen (spaltenweise). Einträge sind über Übergebene Properties an Felder aus Stock gebunden.
    colName.setCellValueFactory(new PropertyValueFactory<>("name"));
    colSymbol.setCellValueFactory(new PropertyValueFactory<>("symbol"));
    colVortag.setCellValueFactory(new PropertyValueFactory<>("vortag"));
    colBid.setCellValueFactory(new PropertyValueFactory<>("bid"));
    colAsk.setCellValueFactory(new PropertyValueFactory<>("ask"));
    colPercent.setCellValueFactory(new PropertyValueFactory<>("percent"));
    colPlusMinus.setCellValueFactory(new PropertyValueFactory<>("plusMinus"));
    colTime.setCellValueFactory(new PropertyValueFactory<>("time"));

    // Erstellen eines Buttons um Aktie zur Watchlist hinzuzufügen.
    colAction.setCellFactory(tc -> new TableCell<>() {
      private final MFXButton btn = new MFXButton("★");

      {
        btn.setStyle("-fx-background-color: #FAC710");
        btn.setButtonType(ButtonType.RAISED);
        btn.setOnAction(e -> {
          Stock data = getTableView().getItems().get(getIndex());
          //System.out.println(data);
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

    // Ruft beim anklicken einer Reihe die Detailübersicht der Aktie auf.
    stockListTable.setRowFactory(tv -> {
      TableRow<Stock> row = new TableRow<>();
      row.setOnMouseClicked(event -> {
        if (!row.isEmpty()) {
          Stock rowData = row.getItem();
          //System.out.println(rowData.getName());
          try {
            showStockDetailScreen(rowData);
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
        }
      });
      return row;
    });

    // Filtern der List nach eigegebenem Suchwort.
    FilteredList<Stock> filteredStockList = new FilteredList<>(stockList);

    this.searchField.textProperty().addListener((observable, oldValue, newValue) -> {
      filteredStockList.setPredicate(stock -> {
        if (newValue.isEmpty() || newValue.isBlank()) {
          return true;
        }

        String searchKeyword = newValue.toLowerCase();

        if (stock.getName().toLowerCase().contains(searchKeyword)) {
          return true;
        } else return stock.getSymbol().toLowerCase().contains(searchKeyword);
      });
    });

    stockListTable.setItems(filteredStockList);

  }

  void showStockDetailScreen(Stock stock) throws IOException {
    Router.getInstance().pushRoute("stockDetails", stock);
  }
}
