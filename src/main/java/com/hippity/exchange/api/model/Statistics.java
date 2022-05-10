package com.hippity.exchange.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Statistics {
    @SerializedName("sell_count")
    List sellCount;
    @SerializedName("buy_count")
    List buyCount;
    @SerializedName("avg_sell")
    List sellRate;
    @SerializedName("avg_buy")
    List buyRate;


    public List getSellCount(){return sellCount;}
    public List getBuyCount(){return buyCount;}
    public List getSellRate(){return sellRate;}
    public List getBuyRate(){return buyRate;}

}
