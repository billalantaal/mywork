package com.example.bilal.madical;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bilal.madical.model.adapter.Docadapter;
import com.example.bilal.madical.model.adapter.ProAdapter;
import com.example.bilal.madical.model.callback.IProduct;
import com.example.bilal.madical.model.callback.doc;
import com.example.bilal.madical.model.helper.Constants;
import com.example.bilal.madical.model.pojo.Doctor;
import com.example.bilal.madical.model.pojo.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ManageProducts extends AppCompatActivity {

    List<Product> prolist;
    ListView listpro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_products);
        listpro=(ListView)findViewById(R.id.showproduct);
        getProduct();
    }

    public void getProduct() {

        Retrofit productadapter = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final IProduct proapi = productadapter.create(IProduct.class);
        Call<List<Product>> procall = proapi.getProduct();
        procall.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(final Call<List<Product>> call, Response<List<Product>> response) {

                prolist = response.body();
                ProAdapter proadpt = new ProAdapter(getApplicationContext(), R.layout.item_file, prolist);
                listpro.setAdapter(proadpt);
                listpro.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Product product1= prolist.get(position);
                        Intent intent=new Intent(getApplicationContext(),InsertPro.class);
                        intent.putExtra("pro",product1);
                        startActivity(intent);

                        Toast.makeText(getApplicationContext(),""+product1.getName(),Toast.LENGTH_LONG).show();
                    }
                });



            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }

    public void registerproduct(View view){
        Intent intent=new Intent(this,RegisterProducts.class);
        startActivity(intent);

    }
}
