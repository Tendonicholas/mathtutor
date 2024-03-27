module com.example.math_app {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.sql;
    requires jbcrypt;
    requires dotenv.java;
    requires java.desktop;


    opens com.math_app to javafx.fxml;
    exports com.math_app;
    exports com.math_app.grade1;
    opens com.math_app.grade1 to javafx.fxml;
}