package com.example.bilal.madical;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bilal.madical.model.callback.IProduct;
import com.example.bilal.madical.model.callback.updatedoctor;
import com.example.bilal.madical.model.helper.Constants;
import com.example.bilal.madical.model.pojo.Doctor;
import com.example.bilal.madical.model.pojo.Product;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InsertPro extends AppCompatActivity {

    EditText pt1;
    EditText pt2;
    EditText pt3;
    EditText pt4;
    Button addb;
    Button updb;
    Button dltb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_pro);

        ConfigViewsPro();
        Procome();


        dltb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteProduct();
            }
        });

        addb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddPro();
            }
        });

        updb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateProduct();
            }
        });
    }

    public void Procome() {
        Product model1 = (Product) getIntent().getSerializableExtra("pro");
        pt1.setText("" + model1.getId());
        pt2.setText(model1.getName());
        pt3.setText(model1.getDiscription());
        pt4.setText(model1.getAmount());

    }

    public void ConfigViewsPro() {
        pt1 = (EditText) findViewById(R.id.idpro);
        pt2 = (EditText) findViewById(R.id.name_pro);
        pt3 = (EditText) findViewById(R.id.disc_pro);
        pt4 = (EditText) findViewById(R.id.amount_pro);
        addb = (Button) findViewById(R.id.register_pro);
        updb = (Button) findViewById(R.id.updatepro);
        dltb = (Button) findViewById(R.id.deletepro);
    }

    private void DeleteProduct() {
        Retrofit delproduct = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IProduct delete_pro = delproduct.create(IProduct.class);
        Product product1 = new Product();
        product1.setId(Integer.parseInt(pt1.getText().toString()));


        Call<Product> delpro = delete_pro.delProduct(
                product1.getId());
        delpro.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {

            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {

            }
        });


    }

    private void AddProduct(){

    }
    private void AddPro() {
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

    private void UpdateProduct() {
        Retrofit updateproducts = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IProduct update_pro = updateproducts.create(IProduct.class);
        Product product3 = new Product();

        product3.setId(Integer.parseInt(pt1.getText().toString()));
        product3.setName(pt2.getText().toString());
        product3.setDiscription(pt3.getText().toString());
        product3.setAmount(pt4.getText().toString());


        Call<Product> callupdatepro = update_pro.updatepro(
                product3.getId(),
                product3.getName(),
                product3.getDiscription(),
                product3.getAmount()
                );
        callupdatepro.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                Toast.makeText(getApplicationContext(),"Update",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {

            }
        });

    }
}
