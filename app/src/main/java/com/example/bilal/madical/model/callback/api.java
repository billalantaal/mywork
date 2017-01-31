package com.example.bilal.madical.model.callback;

import com.example.bilal.madical.model.pojo.Flower;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface api {
    @GET("Madical/getuser.php")
    Call<List<Flower>> getData();
}
