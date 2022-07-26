package de.hsos.ooadproject.datamodel;

public class HistoryPoint {
  private String date;
  private float ask;

  public HistoryPoint(String date, float ask) {
    this.date = date;
    this.ask = ask;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public float getAsk() {
    return ask;
  }

  public void setAsk(float ask) {
    this.ask = ask;
  }
}
