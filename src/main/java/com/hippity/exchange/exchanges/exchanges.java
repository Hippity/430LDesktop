package com.hippity.exchange.exchanges;

import com.hippity.exchange.Authentication;
import com.hippity.exchange.OnPageCompleteListener;
import com.hippity.exchange.PageCompleter;
import com.hippity.exchange.Parent;
import com.hippity.exchange.api.ExchangeService;
import com.hippity.exchange.api.model.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class exchanges implements PageCompleter, Initializable {
    private OnPageCompleteListener onPageCompleteListener;

    public void setOnPageCompleteListener(OnPageCompleteListener onPageCompleteListener) {
        this.onPageCompleteListener = onPageCompleteListener;
    }

    @FXML
    public TextField USDAmount1;
    @FXML
    public TextField LBPAmount1;
    @FXML
    public RadioButton sell1;
    @FXML
    public TextField pickID;
    @FXML
    public Label USDBalance;
    @FXML
    public Label LBPBalance;


    public TableColumn id;
    public TableColumn lbpAmount;
    public TableColumn usdAmount;
    public TableColumn sellbuy;
    public TableColumn user;
    public TableView tableView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ExchangeService.exchangeApi().getInfo("Bearer " +
                Authentication.getInstance().getToken()).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                    USDBalance.setText(response.body().getBusd().toString());
                    LBPBalance.setText(response.body().getBlbp().toString());


            }

            @Override
            public void onFailure(Call<User> call, Throwable throwable) {
            }
        });

        id.setCellValueFactory(new
                PropertyValueFactory<Item, Long>("id"));
        lbpAmount.setCellValueFactory(new
                PropertyValueFactory<Item, Long>("lbpAmount"));
        usdAmount.setCellValueFactory(new
                PropertyValueFactory<Item, Long>("usdAmount"));
        sellbuy.setCellValueFactory(new
                PropertyValueFactory<Item, Long>("sell"));
        user.setCellValueFactory(new
                PropertyValueFactory<Item, String>("userID"));


        ExchangeService.exchangeApi().getItems("Bearer " +
                        Authentication.getInstance().getToken())
                .enqueue(new Callback<List<Item>>() {
                    @Override
                    public void onResponse(Call<List<Item>> call,
                                           Response<List<Item>> response) {
                        tableView.getItems().setAll(response.body());
                    }
                    @Override
                    public void onFailure(Call<List<Item>> call,
                                          Throwable throwable) {
                    }
                });
    }



    public void purchase(ActionEvent actionEvent){

        Integer id = Integer.parseInt(pickID.getText());

        Purchase x = new Purchase(id);

        ExchangeService.exchangeApi().purchase(x, "Bearer " +
                Authentication.getInstance().getToken()).enqueue(new Callback<Purchase>() {
            @Override
            public void onResponse(Call<Purchase> call, Response<Purchase> response) {

            }
            @Override
            public void onFailure(Call<Purchase> call, Throwable throwable) {
            }
        });

    }


    public void addItem(ActionEvent actionEvent) {

        Float USD = Float.parseFloat(USDAmount1.getText());
        Float LBP = Float.parseFloat(LBPAmount1.getText());
        Boolean s = false;
        if (sell1.isSelected()){s = true;}

        Item y = new Item(LBP, USD, s);

        ExchangeService.exchangeApi().addItem(y, "Bearer " +
                Authentication.getInstance().getToken()).enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {

            }
            @Override
            public void onFailure(Call<Item> call, Throwable throwable) {
            }
        });
    }

}