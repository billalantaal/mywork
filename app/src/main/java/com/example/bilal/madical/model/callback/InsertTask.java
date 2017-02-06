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
    @POST("test/test.php")
    Call<Task> inserttsk(@Field("date") String date,
                         @Field("user") String user,
                         @Field("jsndata") String jsndata);
}
