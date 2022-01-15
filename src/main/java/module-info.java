module src.bakalarka {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.zaxxer.hikari;
    requires com.google.gson;
    requires com.google.common;
    requires org.slf4j;

    opens src.bakalarka to javafx.fxml;
    exports src.bakalarka;
}