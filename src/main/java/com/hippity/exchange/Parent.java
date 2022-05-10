package com.hippity.exchange;

import com.hippity.exchange.api.ExchangeService;
import com.hippity.exchange.api.model.User;
import com.hippity.exchange.login.Login;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
public class Parent implements Initializable, OnPageCompleteListener{

    public BorderPane borderPane;
    public Button transactionButton;
    public Button loginButton;
    public Button registerButton;
    public Button logoutButton;
    public Button exchanges;
    public Button cases;
    public Button stats;
    public Button adminButton;

    public TransCountChart statsChart = new TransCountChart();
    public Stage stage1 = new Stage();

    public ExchangeRateChart rateChart = new ExchangeRateChart();
    public Stage stage2 = new Stage();

    public static Boolean admin = false;

    private void updateNavigation() {
        boolean authenticated = Authentication.getInstance().getToken() !=
                null;
        transactionButton.setManaged(authenticated);
        transactionButton.setVisible(authenticated);
        loginButton.setManaged(!authenticated);
        loginButton.setVisible(!authenticated);
        registerButton.setManaged(!authenticated);
        registerButton.setVisible(!authenticated);
        logoutButton.setManaged(authenticated);
        logoutButton.setVisible(authenticated);
        exchanges.setManaged(authenticated);
        exchanges.setVisible(authenticated);
        adminButton.setManaged(authenticated);
        adminButton.setVisible(authenticated);
    }


    @Override
    public void onPageCompleted() {
        swapContent(Section.RATES);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateNavigation();
    }
    public void ratesSelected() {
        swapContent(Section.RATES);
    }
    public void transactionsSelected() {
        swapContent(Section.TRANSACTIONS);
    }
    public void loginSelected() {
        swapContent(Section.LOGIN);
    }
    public void registerSelected() {
        swapContent(Section.REGISTER);
    }
    public void makeExchanges() {swapContent(Section.EXCHANGES);}

    public void showStats() {
        statsChart.start(stage1);
    }

    public void showChanges() {
        rateChart.start(stage2);
        }

    public void showAdminLog() {

        ExchangeService.exchangeApi().getInfo("Bearer " +
                Authentication.getInstance().getToken()).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {


                if (response.body().getAdmin()){

                    Parent.admin = true;

                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable throwable) {
            }
        });

        if (admin){
            swapContent(Section.ADMINLOG);
        }
    }

    public void logoutSelected() {
        Authentication.getInstance().deleteToken();
        swapContent(Section.RATES);
        admin = false;
    }
    private void swapContent(Section section) {
        try {
            URL url = getClass().getResource(section.getResource());
            FXMLLoader loader = new FXMLLoader(url);
            borderPane.setCenter(loader.load());
            if (section.doesComplete()) {
                ((PageCompleter)
                        loader.getController()).setOnPageCompleteListener(this);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        updateNavigation();
    }
    public enum Section {
        RATES,
        TRANSACTIONS,
        LOGIN,
        REGISTER,
        EXCHANGES,
        STATS,
        CHANGES,
        ADMINLOG;

        public boolean doesComplete() {
            return switch (this) {
                case LOGIN, REGISTER -> true;
                default -> false;
            };
        }

        public String getResource() {
            return switch (this) {
                case RATES ->
                        "/com/hippity/exchange/rates/rates.fxml";
                case TRANSACTIONS ->
                        "/com/hippity/exchange/transactions/transactions.fxml";
                case LOGIN ->
                        "/com/hippity/exchange/login/login.fxml";
                case REGISTER ->
                        "/com/hippity/exchange/register/register.fxml";
                case EXCHANGES ->
                        "/com/hippity/exchange/exchanges/exchanges.fxml";
                case STATS ->
                        "/com/hippity/exchange/stats/stats.fxml";
                case CHANGES ->
                        "/com/hippity/exchange/changes/changes.fxml";
                case ADMINLOG ->
                        "/com/hippity/exchange/adminLogin/adminLogin.fxml";
                default -> null;
            };
        }

    }
}
