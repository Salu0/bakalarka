module src.bakalarka {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.zaxxer.hikari;


    opens src.bakalarka to javafx.fxml;
    exports src.bakalarka;
}