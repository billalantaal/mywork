package com.example.bilal.madical.model.pojo;

import java.io.Serializable;

/**
 * Created by Bilal on 1/14/2017.
 */

public class Product implements Serializable {
    int id;
    String name;
    String discription;
    String amount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
