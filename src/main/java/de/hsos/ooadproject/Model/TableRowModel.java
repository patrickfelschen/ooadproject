package de.hsos.ooadproject.Model;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Date;

public class TableRowModel extends RecursiveTreeObject<TableRowModel> {
    public static class TableRowBuilder {
        private SimpleStringProperty name, symbol;
        private SimpleFloatProperty vortag, bid, ask, percent, plusMinus;
        private Date time;

        public TableRowBuilder(String name, String symbol) {
            this.name = new SimpleStringProperty(name);
            this.symbol = new SimpleStringProperty(symbol);
        }

        public TableRowBuilder vortag(float vortag) {
            this.vortag = new SimpleFloatProperty(vortag);
            return this;
        }

        public TableRowBuilder bid(float bid) {
            this.bid = new SimpleFloatProperty(bid);
            return this;
        }

        public TableRowBuilder ask(float ask) {
            this.ask = new SimpleFloatProperty(ask);
            return this;
        }

        public TableRowBuilder percent(float percent) {
            this.percent = new SimpleFloatProperty(percent);
            return this;
        }

        public TableRowBuilder plusMinus(float plusMinus) {
            this.plusMinus = new SimpleFloatProperty(plusMinus);
            return this;
        }

        public TableRowBuilder time(Date time) {
            this.time = time;
            return this;
        }

        public TableRowModel build() {
            TableRowModel tableRow = new TableRowModel();
            tableRow.name = this.name;
            tableRow.symbol = this.symbol;
            tableRow.vortag = this.vortag;
            tableRow.bid = this.bid;
            tableRow.ask = this.ask;
            tableRow.percent = this.percent;
            tableRow.plusMinus = this.plusMinus;
            tableRow.time = this.time;

            return tableRow;
        }
    }
    private SimpleStringProperty name, symbol;
    private SimpleFloatProperty vortag, bid, ask, percent, plusMinus;
    private Date time;

    private TableRowModel() {

    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSymbol() {
        return symbol.get();
    }

    public SimpleStringProperty symbolProperty() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol.set(symbol);
    }

    public float getVortag() {
        return vortag.get();
    }

    public SimpleFloatProperty vortagProperty() {
        return vortag;
    }

    public void setVortag(float vortag) {
        this.vortag.set(vortag);
    }

    public float getBid() {
        return bid.get();
    }

    public SimpleFloatProperty bidProperty() {
        return bid;
    }

    public void setBid(float bid) {
        this.bid.set(bid);
    }

    public float getAsk() {
        return ask.get();
    }

    public SimpleFloatProperty askProperty() {
        return ask;
    }

    public void setAsk(float ask) {
        this.ask.set(ask);
    }

    public float getPercent() {
        return percent.get();
    }

    public SimpleFloatProperty percentProperty() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent.set(percent);
    }

    public float getPlusMinus() {
        return plusMinus.get();
    }

    public SimpleFloatProperty plusMinusProperty() {
        return plusMinus;
    }

    public void setPlusMinus(float plusMinus) {
        this.plusMinus.set(plusMinus);
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
