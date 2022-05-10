package com.hippity.exchange.adminLogin;

import com.hippity.exchange.OnPageCompleteListener;
import com.hippity.exchange.PageCompleter;
import com.hippity.exchange.api.ExchangeService;
import com.hippity.exchange.api.model.Balance;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class adminLogin implements PageCompleter {
    private OnPageCompleteListener onPageCompleteListener;

    @FXML
    TextField newUSDBalance;
    @FXML
    TextField newLBPBalance;
    @FXML
    TextField acctID;

    public void setOnPageCompleteListener(OnPageCompleteListener onPageCompleteListener) {
        this.onPageCompleteListener = onPageCompleteListener;
    }

    public void addMoney(ActionEvent actionEvent) {

        String user = acctID.getText();
        Float USD = Float.parseFloat(newUSDBalance.getText());
        Float LBP = Float.parseFloat(newLBPBalance.getText());

        Balance x = new Balance(user, USD, LBP);

        ExchangeService.exchangeApi().addMoney(x).enqueue(new Callback<Balance>() {
            @Override
            public void onResponse(Call<Balance> call, Response<Balance> response) {

            }
            @Override
            public void onFailure(Call<Balance> call, Throwable throwable) {
            }
        });
    }


}