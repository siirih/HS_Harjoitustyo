module com.example.hs_harjoitustyo {
    requires javafx.controls;
    requires javafx.fxml;


    opens elainkysely to javafx.fxml;
    exports elainkysely;
}
