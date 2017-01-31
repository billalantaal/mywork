package com.example.bilal.madical.model.callback;

import com.example.bilal.madical.model.pojo.Doctor;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Bilal on 1/11/2017.
 */

public interface RegDoc {

    @FormUrlEncoded
    @POST("Madical/AddDoctor.php")
    Call<Doctor> insert(@Field("name") String name,
                        @Field("designation") String designaation,
                        @Field("phone") String phone,
                        @Field("address") String address,
                        @Field("speciality") String speciality);

}
