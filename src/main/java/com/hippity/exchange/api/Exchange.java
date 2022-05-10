package com.hippity.exchange.api;

import com.hippity.exchange.api.model.*;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

import java.util.List;

public interface Exchange {
    @GET("/exchangeRate")
    Call<ExchangeRates> getExchangeRates();
    @POST("/signUp")
    Call<User> addUser(@Body User user);
    @POST("/authentication")
    Call<Token> authenticate(@Body User user);
    @POST("/transaction")
    Call<Object> addTransaction(@Body Transaction transaction, @Header("Authorization") String authorization);
    @GET("/transaction")
    Call<List<Transaction>> getTransactions(@Header("Authorization") String authorization);

    @GET("/stats")
    Call<Statistics> getStats();
    @POST("/addItem")
    Call<Item> addItem(@Body Item item,@Header("Authorization") String authorization);
    @GET("/getItems")
    Call<List<Item>> getItems(@Header("Authorization") String authorization);
    @POST("/purchase")
    Call<Purchase> purchase(@Body Purchase purchase, @Header("Authorization") String authorization);
    @POST("/addMoney")
    Call<Balance> addMoney(@Body Balance balance);
    @GET("/userInfo")
    Call<User> getInfo(@Header("Authorization") String authorization);

}

