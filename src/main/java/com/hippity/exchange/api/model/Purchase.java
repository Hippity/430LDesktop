package com.hippity.exchange.api.model;

import com.google.gson.annotations.SerializedName;

public class Purchase {
    @SerializedName("itemId")
    public Integer id;

    public Purchase(Integer input){
        id = input;
    }

}
