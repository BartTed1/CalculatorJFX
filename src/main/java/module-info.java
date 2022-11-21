module com.example.introjfx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens lab.jfx to javafx.fxml;
    exports lab.jfx;
}