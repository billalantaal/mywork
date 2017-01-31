package com.example.bilal.madical.model.UserFragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bilal.madical.InsertPro;
import com.example.bilal.madical.R;
import com.example.bilal.madical.model.adapter.ProAdapter;
import com.example.bilal.madical.model.callback.IProduct;
import com.example.bilal.madical.model.helper.Constants;
import com.example.bilal.madical.model.pojo.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ProductUser extends Fragment {

    List<Product> prolist;
    ListView listpro;





    public ProductUser() {
        // Required empty public constructor
    }




 /*   @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View RootView= inflater.inflate(R.layout.fragment_product_user, container, false);

        listpro= (ListView) RootView.findViewById(R.id.userproduct);

        getProduct();

        return RootView;

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
                ProAdapter proadpt = new ProAdapter(getActivity(), R.layout.item_file, prolist);
                listpro.setAdapter(proadpt);
  /*              listpro.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Product product1= prolist.get(position);
                        Intent intent=new Intent(getActivity(),InsertPro.class);
                        intent.putExtra("pro",product1);
                        startActivity(intent);

                        Toast.makeText(getActivity(),""+product1.getName(),Toast.LENGTH_LONG).show();
                    }
                });*/



            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }






}
