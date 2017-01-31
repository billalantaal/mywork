package com.example.bilal.madical;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bilal.madical.model.callback.RegDoc;
import com.example.bilal.madical.model.callback.deldoc;
import com.example.bilal.madical.model.callback.updatedoctor;
import com.example.bilal.madical.model.helper.Constants;
import com.example.bilal.madical.model.pojo.Doctor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InsertDoctor extends AppCompatActivity {
    EditText DtId;
    EditText Dt1;
    EditText Dt2;
    EditText Dt3;
    EditText Dt4;
    EditText Dt5;
    Button Db;
    Button Ud;
    Button DD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_doctor);

        ConfigViews();
        coming();


        Db.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddDoctor();
            }
        });

        Ud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateDoctor();
            }
        });

        DD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteDoctor();
            }
        });

    }

    private void ConfigViews() {

        DtId = (EditText) findViewById(R.id.iddoc);
        Dt1 = (EditText) findViewById(R.id.name_doc);
        Dt2 = (EditText) findViewById(R.id.desig_doc);
        Dt3 = (EditText) findViewById(R.id.phone_doc);
        Dt4 = (EditText) findViewById(R.id.address_doc);
        Dt5 = (EditText) findViewById(R.id.speciality_doc);
        Db = (Button) findViewById(R.id.register_doc);
        Ud = (Button) findViewById(R.id.updatedoc);
        DD = (Button) findViewById(R.id.deletedoc);

    }

    private void coming() {
        Doctor model = (Doctor) getIntent().getSerializableExtra("bbb");
        DtId.setText("" + model.getId());
        Dt1.setText(model.getName());
        Dt2.setText(model.getDesignation());
        Dt3.setText(model.getPhone());
        Dt4.setText(model.getAddress());
        Dt5.setText(model.getSpeciality());

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

    private void UpdateDoctor() {
        Retrofit updatedoctors = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        updatedoctor update_doc = updatedoctors.create(updatedoctor.class);
        Doctor doctor = new Doctor();
        doctor.setId(Integer.parseInt(DtId.getText().toString()));
        doctor.setName(Dt1.getText().toString());
        doctor.setDesignation(Dt2.getText().toString());
        doctor.setPhone(Dt3.getText().toString());
        doctor.setAddress(Dt4.getText().toString());
        doctor.setSpeciality(Dt5.getText().toString());


        Call<Doctor> cll = update_doc.update(
                doctor.getId(),
                doctor.getName(),
                doctor.getDesignation(),
                doctor.getPhone(),
                doctor.getAddress(),
                doctor.getSpeciality());

        cll.enqueue(new Callback<Doctor>() {
            @Override
            public void onResponse(Call<Doctor> call, Response<Doctor> response) {
                Toast.makeText(getApplicationContext(), "update", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Doctor> call, Throwable t) {

            }
        });

    }

    private void DeleteDoctor() {
        Retrofit deldoctors = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        deldoc delete_doc = deldoctors.create(deldoc.class);
        Doctor doctor = new Doctor();
        doctor.setId(Integer.parseInt(DtId.getText().toString()));


        Call<Doctor> delcll = delete_doc.Delete(
                doctor.getId());

        delcll.enqueue(new Callback<Doctor>() {
            @Override
            public void onResponse(Call<Doctor> call, Response<Doctor> response) {
                Toast.makeText(getApplicationContext(), "Delete", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Doctor> call, Throwable t) {

            }
        });

    }
}
