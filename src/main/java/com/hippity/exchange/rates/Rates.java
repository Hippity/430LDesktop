package com.hippity.exchange.rates;


import com.hippity.exchange.Authentication;
import com.hippity.exchange.api.ExchangeService;
import com.hippity.exchange.api.model.ExchangeRates;
import com.hippity.exchange.api.model.Transaction;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Rates {
        public Label buyUsdRateLabel;
        public Label sellUsdRateLabel;
        public TextField lbpTextField;
        public TextField usdTextField;
        public ToggleGroup transactionType;
        public TextField lbpInput;
        public TextField usdInput;
        public ToggleGroup calculateType;
        public Boolean dir = true;

        public void initialize() {
            fetchRates();
        }

        private void fetchRates() {
                ExchangeService.exchangeApi().getExchangeRates().enqueue(new Callback<ExchangeRates>() {
                        @Override
                        public void onResponse(Call<ExchangeRates> call, Response<ExchangeRates> response) {
                                ExchangeRates exchangeRates = response.body();
                                Platform.runLater(() -> {
                                        buyUsdRateLabel.setText(exchangeRates.lbpToUsd.toString());
                                        sellUsdRateLabel.setText(exchangeRates.usdToLbp.toString());
                                });}
                        @Override
                        public void onFailure(Call<ExchangeRates> call, Throwable throwable) {
                        }
                });
        }

        public void addTransaction(ActionEvent actionEvent) {
                if (usdTextField.getText().length() >0  && lbpTextField.getText().length() > 0) {
                        Transaction transaction = new Transaction(
                                Float.parseFloat(usdTextField.getText()),
                                Float.parseFloat(lbpTextField.getText()),
                                ((RadioButton) transactionType.getSelectedToggle()).getText().equals("Sell USD")
                        );

                        String userToken = Authentication.getInstance().getToken();
                        String authHeader = userToken != null ? "token: " + userToken : null;

                        ExchangeService.exchangeApi().addTransaction(transaction, authHeader).enqueue(new Callback<Object>() {
                                @Override
                                public void onResponse(Call<Object> call, Response<Object> response) {
                                        fetchRates();
                                        Platform.runLater(() -> {
                                                usdTextField.setText("");
                                                lbpTextField.setText("");
                                        });
                                }

                                @Override
                                public void onFailure(Call<Object> call, Throwable throwable) {
                                }
                        });

                }

        }
        public void calculate(ActionEvent actionEvent){

                if (((RadioButton) calculateType.getSelectedToggle()).getText().equals("Buy USD")){
                        if (dir && usdInput.getText().length() > 0){
                                lbpInput.setText(String.valueOf(Float.parseFloat(usdInput.getText()) / Float.parseFloat(sellUsdRateLabel.getText())));
                        }
                        else if (!dir && lbpInput.getText().length() > 0){
                                usdInput.setText(String.valueOf(Float.parseFloat(lbpInput.getText()) * Float.parseFloat(sellUsdRateLabel.getText())));
                        }
                }
                else {
                        if (dir && usdInput.getText().length() > 0){
                                 lbpInput.setText( String.valueOf(Float.parseFloat(usdInput.getText()) * Float.parseFloat(buyUsdRateLabel.getText())));
                        }
                        else if (!dir && lbpInput.getText().length() > 0){
                                usdInput.setText(String.valueOf(Float.parseFloat(lbpInput.getText())/Float.parseFloat(buyUsdRateLabel.getText())));
                        }
                }


        }
        public void changeDir(ActionEvent actionEvent){
                dir = !dir;
                if (dir){
                        lbpInput.setDisable(true);
                        usdInput.setDisable(false);
                }
                else{
                        lbpInput.setDisable(false);
                        usdInput.setDisable(true);
                }

        }
}


