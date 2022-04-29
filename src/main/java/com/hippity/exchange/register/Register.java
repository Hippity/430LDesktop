package com.hippity.exchange.register;

import com.hippity.exchange.Authentication;
import com.hippity.exchange.OnPageCompleteListener;
import com.hippity.exchange.PageCompleter;
import com.hippity.exchange.api.ExchangeService;
import com.hippity.exchange.api.model.Token;
import com.hippity.exchange.api.model.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register implements PageCompleter {
    private OnPageCompleteListener onPageCompleteListener;
    public TextField usernameTextField;
    public TextField passwordTextField;
    public void setOnPageCompleteListener(OnPageCompleteListener onPageCompleteListener) {
        this.onPageCompleteListener = onPageCompleteListener;
    }

    public void register(ActionEvent actionEvent) {
        User user = new User(usernameTextField.getText(), passwordTextField.getText());
        ExchangeService.exchangeApi().addUser(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                ExchangeService.exchangeApi().authenticate(user).enqueue(new Callback<Token>() {
                    @Override
                    public void onResponse(Call<Token> call, Response<Token> response) {
                        Authentication.getInstance().saveToken(response.body().getToken());
                        Platform.runLater(() -> {
                            onPageCompleteListener.onPageCompleted();
                        });
                    }
                    @Override
                    public void onFailure(Call<Token> call, Throwable throwable) {
                                                                                                                                                 }
                                                                                                                                             });
            }
            @Override
            public void onFailure(Call<User> call, Throwable throwable) {
            }
        });
    }
}