module com.example.hs_harjoitustyo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.hs_harjoitustyo to javafx.fxml;
    exports elainkysely;
}