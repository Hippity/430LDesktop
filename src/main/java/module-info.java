module com.hippity.exchange {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires gson;
    requires retrofit2.converter.gson;
    requires retrofit2;
    requires java.prefs;
    opens com.hippity.exchange.api.model to javafx.base , gson;
    opens com.hippity.exchange to javafx.fxml;
    exports com.hippity.exchange;
    exports com.hippity.exchange.rates;
    opens com.hippity.exchange.rates to javafx.fxml;
    opens com.hippity.exchange.login to javafx.fxml;
    opens com.hippity.exchange.register to javafx.fxml;
    opens com.hippity.exchange.transactions to javafx.fxml;
        }