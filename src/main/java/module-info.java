module de.hsos.ooadproject {
  requires javafx.controls;
  requires javafx.fxml;
  requires javafx.web;

  requires org.controlsfx.controls;
  requires com.dlsc.formsfx;
  requires org.kordamp.ikonli.javafx;
  requires eu.hansolo.tilesfx;
  requires MaterialFX;

  opens de.hsos.ooadproject to javafx.fxml;
  exports de.hsos.ooadproject;
}