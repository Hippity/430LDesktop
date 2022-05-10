package com.hippity.exchange.api.model;

import com.google.gson.annotations.SerializedName;

public class Balance {
    @SerializedName("amount_lbp")
    public Float lbp;
    @SerializedName("amount_usd")
    public Float usd;
    @SerializedName("username")
    public String user;

    public Balance(String u, Float us, Float lb){
        user = u;
        usd = us;
        lbp = lb;
    }

}
