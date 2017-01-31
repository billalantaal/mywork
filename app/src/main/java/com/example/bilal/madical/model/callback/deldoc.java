package com.example.bilal.madical.model.callback;

import com.example.bilal.madical.model.pojo.Doctor;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Bilal on 1/13/2017.
 */

public interface deldoc {


    @FormUrlEncoded
    @POST("Madical/deletedoc.php")
    Call<Doctor> Delete(@Field("id") int id);
}
