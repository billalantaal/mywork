package com.example.bilal.madical;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bilal.madical.model.adapter.Docadapter;
import com.example.bilal.madical.model.callback.doc;
import com.example.bilal.madical.model.helper.Constants;
import com.example.bilal.madical.model.pojo.Doctor;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ManageDoc extends AppCompatActivity {
    Context ctx;
    List<Doctor> docRetrieve;
    ListView listView1;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_doc);
        listView1=(ListView)findViewById(R.id.showdoc);
        button=(Button) findViewById(R.id.register_doc);
        getDoctor();

    }

    public void registerdoctor(View view){
        Intent intent =new Intent(this,RegisterDoc.class);
        startActivity(intent);
    }

    public void getDoctor() {

        Retrofit docrestadapter = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final doc docapi = docrestadapter.create(doc.class);
        Call<List<Doctor>> call = docapi.getDataDoc();
        call.enqueue(new Callback<List<Doctor>>() {
            @Override
            public void onResponse(final Call<List<Doctor>> call, Response<List<Doctor>> response) {

                docRetrieve = response.body();
                Docadapter adpt = new Docadapter(getApplicationContext(), R.layout.item_file, docRetrieve);
                listView1.setAdapter(adpt);
                listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Doctor doctor1= docRetrieve.get(position);
//                        String mys= doctor1.getSpeciality();
//                        Toast.makeText(getApplicationContext(),""+mys,Toast.LENGTH_LONG).show();
                       /* Intent i =new Intent(ctx,InsertDoctor.class);
                        Bundle b=new Bundle();
                        b.putParcelable("bk", (Parcelable) doctor1);
                        i.putExtra("bb",b);
                        startActivity(i);*/
                        Intent intent=new Intent(getApplicationContext(),InsertDoctor.class);
                        intent.putExtra("bbb",doctor1);
                        startActivity(intent);

                        Toast.makeText(getApplicationContext(),""+doctor1.getId(),Toast.LENGTH_LONG).show();
                    }
                });



            }

            @Override
            public void onFailure(Call<List<Doctor>> call, Throwable t) {

            }
        });
    }
}
