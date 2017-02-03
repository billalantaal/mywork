package com.example.bilal.madical.model.pojo;

/**
 * Created by bila on 2/3/2017.
 */

public class Word {
    String time_slot;
    int D_id;

    public Word(String time_slot, int d_id) {
        this.time_slot = time_slot;
        D_id = d_id;
    }

    public String getTime_slot() {
        return time_slot;
    }

    public int getD_id() {
        return D_id;
    }
}
