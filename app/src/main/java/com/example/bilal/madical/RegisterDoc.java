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

import com.example.bilal.madical.model.callback.RegDoc;
import com.example.bilal.madical.model.helper.Constants;
import com.example.bilal.madical.model.pojo.Doctor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterDoc extends AppCompatActivity {
    EditText Dt1;
    EditText Dt2;
    EditText Dt3;
    EditText Dt4;
    EditText Dt5;
    Button Db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_doc);
      /*  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/


        Dt1 = (EditText) findViewById(R.id.name_doc);
        Dt2 = (EditText) findViewById(R.id.desig_doc);
        Dt3 = (EditText) findViewById(R.id.phone_doc);
        Dt4 = (EditText) findViewById(R.id.address_doc);
        Dt5 = (EditText) findViewById(R.id.speciality_doc);
        Db = (Button) findViewById(R.id.register_doc);
        Db.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddDoctor();
            }
        });

    }

    private void AddDoctor() {
        Retrofit adddoctors = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RegDoc register_doc = adddoctors.create(RegDoc.class);
        Doctor doctor = new Doctor();
        doctor.setName(Dt1.getText().toString());
        doctor.setDesignation(Dt2.getText().toString());
        doctor.setPhone(Dt3.getText().toString());
        doctor.setAddress(Dt4.getText().toString());
        doctor.setSpeciality(Dt5.getText().toString());


        Call<Doctor> call = register_doc.insert(doctor.getName(),
                doctor.getDesignation(),
                doctor.getPhone(),
                doctor.getAddress(),
                doctor.getSpeciality());

        call.enqueue(new Callback<Doctor>() {
            @Override
            public void onResponse(Call<Doctor> call, Response<Doctor> response) {
                Toast.makeText(getApplicationContext(), "Succsess", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Doctor> call, Throwable t) {

            }
        });

    }

}
