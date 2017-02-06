package com.example.bilal.madical;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.bilal.madical.model.adapter.adapter;
import com.example.bilal.madical.model.callback.api;
import com.example.bilal.madical.model.helper.Constants;
import com.example.bilal.madical.model.pojo.Flower;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ManageUserTask extends AppCompatActivity {

    List<Flower> flowerList;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_user_task);

        listView = (ListView) findViewById(R.id.userlistmanage);

        getFeedUser();
    }


    public void getFeedUser() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api service = retrofit.create(api.class);

        Call<List<Flower>> call = service.getData();
        call.enqueue(new Callback<List<Flower>>() {

            @Override
            public void onResponse(Call<List<Flower>> call, Response<List<Flower>> response) {


                flowerList = response.body();

                adapter adapt = new adapter(getApplicationContext(), R.layout.item_file, flowerList);
                listView.setAdapter(adapt);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        try {
                            String myvalue = "bil";
                            Flower flower = flowerList.get(position);
                            int itemValue = flower.getUser_Id();
                            Intent intent = new Intent(getApplicationContext(), ManageTask.class);
                            intent.putExtra(myvalue, itemValue);
                            startActivity(intent);

                        } catch (ClassCastException e) {

                            Log.d("onResponse", "There is an error");
                            e.printStackTrace();
                        }
                    }
                });


            }

            @Override
            public void onFailure(Call<List<Flower>> call, Throwable t) {


            }
        });
    }

}
