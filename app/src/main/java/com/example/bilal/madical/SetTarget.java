package com.example.bilal.madical;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bilal.madical.model.adapter.ProAdapter;
import com.example.bilal.madical.model.adapter.ProAdapterForTarget;
import com.example.bilal.madical.model.callback.IProduct;
import com.example.bilal.madical.model.helper.Constants;
import com.example.bilal.madical.model.pojo.Product;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SetTarget extends AppCompatActivity {


    Calendar myCalendar = Calendar.getInstance();
    EditText t1;
    TextView txt;
    ListView listprofortrgt;
    List<Product> prolistfortrgt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_target);
        t1 = (EditText) findViewById(R.id.setdate);
        txt= (TextView) findViewById(R.id.useridtrgt);
        listprofortrgt= (ListView) findViewById(R.id.listtarget);

        int mydata = getIntent().getIntExtra("bila",1);
        txt.setText(""+mydata);

        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        t1.setText(sdf.format(myCalendar.getTime()));


        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO Auto-generated method stub
                new DatePickerDialog(SetTarget.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        getProduct();

    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };


    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        t1.setText(sdf.format(myCalendar.getTime()));
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

                prolistfortrgt = response.body();
                ProAdapterForTarget proadpt = new ProAdapterForTarget(getApplicationContext(), R.layout.pro_item_trgt, prolistfortrgt);
                listprofortrgt.setAdapter(proadpt);
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
