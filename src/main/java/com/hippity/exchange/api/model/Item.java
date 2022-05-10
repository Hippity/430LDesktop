package com.hippity.exchange.api.model;
import com.google.gson.annotations.SerializedName;

public class Item {
    @SerializedName("id")
    public Integer id;
    @SerializedName("lbpAmount")
    public Float lbpAmount;
    @SerializedName("usdAmount")
    public Float usdAmount;
    @SerializedName("sell")
    public Boolean sell;
    @SerializedName("bought")
    public Integer boughtAmount;
    @SerializedName("user_id")
    public String userID;

    public Item(Float lbp, Float usd, Boolean s){
        lbpAmount = lbp;
        usdAmount = usd;
        sell = s;
    }

    public Integer getId(){return id;}
    public Float getLbpAmount(){return lbpAmount;}
    public Float getUsdAmount(){return usdAmount;}
    public Boolean getSell(){return sell;}
    public Integer getBoughtAmount(){return boughtAmount;}
    public String getUserID(){return userID;}

    }
