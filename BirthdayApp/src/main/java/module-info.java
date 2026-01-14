module com.example.birthdayapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.birthdayapp to javafx.fxml;
    exports com.example.birthdayapp;
}