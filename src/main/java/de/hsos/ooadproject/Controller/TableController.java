package de.hsos.ooadproject.Controller;

import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import de.hsos.ooadproject.Model.TableRowModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class TableController implements Initializable {

    @FXML
    TreeTableView<TableRowModel> stockWatchlist;
    @FXML
    TreeTableColumn<TableRowModel, String> nameCol, symbolCol, vortagCol,  bidCol, askCol, percentCol, plusMinusCol, timeCol;

    private ObservableList<TableRowModel> tableData;

    // https://youtu.be/Qjio2mhT4qs
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // OberservableList erstellen und Daten bereitstellen
        tableData = FXCollections.observableArrayList(
                new TableRowModel.TableRowBuilder("Allianz", "DE0008404005")
                    .vortag(177.95F)
                    .bid(175.46F)
                    .ask(176.38F)
                    .percent(-15.35F)
                    .plusMinus(-2.58F)
                    .time(new Date())
                    .build(),
                new TableRowModel.TableRowBuilder("Apple", "DE0008404005")
                    .vortag(177.95F)
                    .bid(175.46F)
                    .ask(176.38F)
                    .percent(-1.45F)
                    .plusMinus(-2.58F)
                    .time(new Date())
                    .build()
        );

        // Daten einer Tabellen-Zelle mit Spalte verknüpfen
        nameCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("name"));
        symbolCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("symbol"));
        vortagCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("vortag"));
        bidCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("bid"));
        askCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("ask"));
        percentCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("percent"));
        plusMinusCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("plusMinus"));
        timeCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("time"));

        // Datenbaum rekursiv aufbauen
        TreeItem<TableRowModel> root = new RecursiveTreeItem<>(tableData, RecursiveTreeObject::getChildren);

        stockWatchlist.setRoot(root);

    }
}