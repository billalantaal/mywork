package com.example.bilal.madical.model.callback;

import com.example.bilal.madical.model.pojo.Doctor;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Bilal on 1/6/2017.
 */

public interface doc {
    @GET("Madical/getdoctor.php")
    Call<List<Doctor>> getDataDoc();

}
