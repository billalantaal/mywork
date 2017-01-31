package com.example.bilal.madical.model.callback;

import com.example.bilal.madical.model.pojo.Doctor;
import com.example.bilal.madical.model.pojo.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Bilal on 1/14/2017.
 */

public interface IProduct {

    @GET("Madical/getproduct.php")
    Call<List<Product>> getProduct();


    @FormUrlEncoded
    @POST("Madical/deleteproduct.php")
    Call<Product> delProduct(@Field("id") int id);


    @FormUrlEncoded
    @POST("Madical/addproduct.php")
    Call<Product> insertpro(@Field("name") String name,
                        @Field("discription") String discription,
                        @Field("amount") String amount);


    @FormUrlEncoded
    @POST("Madical/updateproduct.php")
    Call<Product> updatepro(@Field("id") int id,
                        @Field("name") String name,
                        @Field("discription") String discription,
                        @Field("amount") String amount);

}
