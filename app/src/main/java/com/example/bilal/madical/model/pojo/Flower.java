package com.example.bilal.madical.model.pojo;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Flower implements Serializable {

    @SerializedName("User_Id")
    @Expose
    private int User_Id;
    @SerializedName("User_Name")
    @Expose    private String User_Name;

    public int getUser_Id() {
        return User_Id;
    }
    public void setUser_Id(int User_Id) {
        this.User_Id = User_Id;
    }
    public String getUser_Name() {
        return User_Name;
    }
    public void setUser_Name(String User_Name) {
        this.User_Name = User_Name;
    }


}
