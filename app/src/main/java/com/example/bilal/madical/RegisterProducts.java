package com.example.bilal.madical;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bilal.madical.model.callback.IProduct;
import com.example.bilal.madical.model.helper.Constants;
import com.example.bilal.madical.model.pojo.Product;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterProducts extends AppCompatActivity {

    EditText pt2;
    EditText pt3;
    EditText pt4;
    Button addb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_products);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pt2 = (EditText) findViewById(R.id.name_pro);
        pt3 = (EditText) findViewById(R.id.disc_pro);
        pt4 = (EditText) findViewById(R.id.amount_pro);
        addb = (Button) findViewById(R.id.register_pro);


    }
    public void AddPro(View view) {
        Retrofit addproducts = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IProduct register_pro = addproducts.create(IProduct.class);
        Product product2 = new Product();
        product2.setName(pt2.getText().toString());
        product2.setDiscription(pt3.getText().toString());
        product2.setAmount(pt4.getText().toString());

        Call<Product> calladd = register_pro.insertpro(product2.getName(),
                product2.getDiscription(),
                product2.getAmount());
        calladd.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                Toast.makeText(getApplicationContext(), "register", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {

            }
        });

    }

}
