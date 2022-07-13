module de.hsos.ooadproject {
  requires javafx.controls;
  requires javafx.fxml;
  requires javafx.web;

  requires org.controlsfx.controls;
  requires com.dlsc.formsfx;
  requires org.kordamp.ikonli.javafx;
  requires eu.hansolo.tilesfx;
  requires MaterialFX;
  requires org.kordamp.ikonli.material2;

  opens de.hsos.ooadproject to javafx.fxml;
  exports de.hsos.ooadproject;
  exports de.hsos.ooadproject.controller;
  opens de.hsos.ooadproject.controller to javafx.fxml;
  exports de.hsos.ooadproject.model;
  opens de.hsos.ooadproject.model to javafx.fxml;
}