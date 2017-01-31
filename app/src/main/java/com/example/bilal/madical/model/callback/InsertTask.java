package com.example.bilal.madical.model.callback;

import com.example.bilal.madical.model.pojo.Product;
import com.example.bilal.madical.model.pojo.Task;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by bila on 1/30/2017.
 */

public interface InsertTask {
    @FormUrlEncoded
    @POST("Madical/inserttask.php")
    Call<Task> inserttsk(@Field("date") String date,
                         @Field("user") String user,
                         @Field("doctor") String doctor,
                         @Field("doctor1") String doctor1,
                         @Field("doctor2") String doctor2,
                         @Field("time") String time,
                         @Field("time1") String time1,
                         @Field("time2") String time2);
}
