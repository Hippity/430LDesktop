package com.hippity.exchange.login;

import com.hippity.exchange.Authentication;
import com.hippity.exchange.OnPageCompleteListener;
import com.hippity.exchange.PageCompleter;
import com.hippity.exchange.Parent;
import com.hippity.exchange.api.ExchangeService;
import com.hippity.exchange.api.model.Token;
import com.hippity.exchange.api.model.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.Dictionary;

public class Login implements PageCompleter {
    public TextField usernameTextField;
    public TextField passwordTextField;
    private OnPageCompleteListener onPageCompleteListener;

    public void setOnPageCompleteListener(OnPageCompleteListener onPageCompleteListener) {
        this.onPageCompleteListener = onPageCompleteListener;
    }

    public void login(ActionEvent actionEvent) {
        User user = new User(usernameTextField.getText(),
                passwordTextField.getText());
        ExchangeService.exchangeApi().authenticate(user).enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                Authentication.getInstance().saveToken(response.body().getToken());


                ExchangeService.exchangeApi().getInfo("Bearer " +
                        Authentication.getInstance().getToken()).enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {


                        if (response.body().getAdmin()){

                            Parent.admin = true;

                        }

                        Platform.runLater(() -> {
                            onPageCompleteListener.onPageCompleted();
                        });
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable throwable) {
                    }
                });



                Platform.runLater(() -> {
                    onPageCompleteListener.onPageCompleted();
                });
            }

            @Override
            public void onFailure(Call<Token> call, Throwable throwable) {
            }
        });
    }
}
