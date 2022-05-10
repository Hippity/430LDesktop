package com.hippity.exchange.api.model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    Integer id;
    @SerializedName("user_name")
    String username;
    @SerializedName("password")
    String password;
    @SerializedName("is_admin")
    Boolean admin;
    @SerializedName("balance_lbp")
    Float blbp;
    @SerializedName("balance_usd")
    Float busd;
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Boolean getAdmin() {return admin;}
    public Float getBlbp() {return blbp;}
    public Float getBusd() {return busd;}



}
