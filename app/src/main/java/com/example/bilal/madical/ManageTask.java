package com.example.bilal.madical;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bilal.madical.model.adapter.Docadapter;
import com.example.bilal.madical.model.callback.InsertTask;
import com.example.bilal.madical.model.callback.doc;
import com.example.bilal.madical.model.helper.Constants;
import com.example.bilal.madical.model.pojo.Doctor;
import com.example.bilal.madical.model.pojo.Task;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ManageTask extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Calendar myCalendar = Calendar.getInstance();
    String data;


    List<Doctor> docList;
    EditText editText;
    TextView textuser;
    Spinner spinner1;
    Spinner spinner2;
    Spinner spinner3;
    Spinner spinner4;
    Spinner spinner5;
    Spinner spinner6;
    Spinner spinner7;
    Spinner spinner8;
    Spinner spinner9;
    Spinner spinner10;
    Spinner spinner11;
    Spinner spinner12;
    Spinner spinner13;
    String[] myStringArray = new String[13];
    EditText editTm;
    EditText editTm1;
    EditText editTm2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_task);

        configViews();
        getFeedUser();
        getFeedDoctor();
        editText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(ManageTask.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        retrieve();


    }


    private void configViews() {

        editText = (EditText) findViewById(R.id.datetext);
        textuser = (TextView) findViewById(R.id.usertext);
        spinner1 = (Spinner) findViewById(R.id.spinnerdoctor);
        spinner2 = (Spinner) findViewById(R.id.spinnerdoctor1);
        spinner3 = (Spinner) findViewById(R.id.spinnerdoctor2);
        spinner4 = (Spinner) findViewById(R.id.spinnerdoctor3);
        spinner5 = (Spinner) findViewById(R.id.spinnerdoctor4);
        spinner6 = (Spinner) findViewById(R.id.spinnerdoctor5);
        spinner7 = (Spinner) findViewById(R.id.spinnerdoctor6);
        spinner8 = (Spinner) findViewById(R.id.spinnerdoctor7);
        spinner9 = (Spinner) findViewById(R.id.spinnerdoctor8);
        spinner10 = (Spinner) findViewById(R.id.spinnerdoctor9);
        spinner11 = (Spinner) findViewById(R.id.spinnerdoctor10);
        spinner12 = (Spinner) findViewById(R.id.spinnerdoctor11);
        spinner13 = (Spinner) findViewById(R.id.spinnerdoctor12);
        editTm = (EditText) findViewById(R.id.firstTm);
        editTm1 = (EditText) findViewById(R.id.secondTm);
        editTm2 = (EditText) findViewById(R.id.thirdTm);
    }

    public void getFeedUser() {

        data = getIntent().getExtras().getString("bil");
        textuser.setText(data);

    }

    public void getFeedDoctor() {

        Retrofit docrestadapter = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        doc docapi = docrestadapter.create(doc.class);
        Call<List<Doctor>> call = docapi.getDataDoc();
        call.enqueue(new Callback<List<Doctor>>() {
            @Override
            public void onResponse(Call<List<Doctor>> call, Response<List<Doctor>> response) {

                docList = response.body();
                Docadapter adpt = new Docadapter(getApplicationContext(), R.layout.doc_file, docList);
                spinner1.setAdapter(adpt);
                spinner2.setAdapter(adpt);
                spinner3.setAdapter(adpt);
                spinner4.setAdapter(adpt);
                spinner5.setAdapter(adpt);
                spinner6.setAdapter(adpt);
                spinner7.setAdapter(adpt);
                spinner8.setAdapter(adpt);
                spinner9.setAdapter(adpt);
                spinner10.setAdapter(adpt);
                spinner11.setAdapter(adpt);
                spinner12.setAdapter(adpt);
                spinner13.setAdapter(adpt);


            }

            @Override
            public void onFailure(Call<List<Doctor>> call, Throwable t) {

            }
        });
    }

    public void retrieve() {
        spinner1.setOnItemSelectedListener(this);
        spinner2.setOnItemSelectedListener(this);
        spinner3.setOnItemSelectedListener(this);
        spinner4.setOnItemSelectedListener(this);
        spinner5.setOnItemSelectedListener(this);
        spinner6.setOnItemSelectedListener(this);
        spinner7.setOnItemSelectedListener(this);
        spinner8.setOnItemSelectedListener(this);
        spinner9.setOnItemSelectedListener(this);
        spinner10.setOnItemSelectedListener(this);
        spinner11.setOnItemSelectedListener(this);
        spinner12.setOnItemSelectedListener(this);
        spinner13.setOnItemSelectedListener(this);

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
        String myFormat = "yyyy/MM/dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        editText.setText(sdf.format(myCalendar.getTime()));
    }

    private void GoforTarget() {
        Intent in = new Intent(this, SetTarget.class);
        in.putExtra("bila", data);
        startActivity(in);
//        Toast.makeText(this, "bila", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.new_game:
                GoforTarget();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void show(View view) {
        /*for (int i=0;i<=12;i++){
            Toast.makeText(this,""+myStringArray[i],Toast.LENGTH_SHORT).show();
        }*/
     /*   Toast.makeText(this, "" + myStringArray[12], Toast.LENGTH_SHORT).show();
        String bi= editTm.getText().toString();
        Toast.makeText(this, ""+bi, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, ""+textuser.getText(), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, ""+editText.getText(), Toast.LENGTH_SHORT).show();*/

        Retrofit addtask = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        InsertTask insertTask=addtask.create(InsertTask.class);
        Task task=new Task();
        task.setDate(editText.getText().toString());
        task.setUser(textuser.getText().toString());
        task.setDoctor(myStringArray[0]);
        task.setDoctor1(myStringArray[1]);
        task.setDoctor2(myStringArray[2]);
        task.setTime(editTm.getText().toString());
        task.setTime1(editTm1.getText().toString());
        task.setTime2(editTm2.getText().toString());
        task.setDoctors(myStringArray);

        Call<Task> taskCall=insertTask.inserttsk(task.getDate(),
                task.getUser(),
                task.getDoctor(),
                task.getDoctor1(),
                task.getDoctor2(),
                task.getTime(),
                task.getTime1(),
                task.getTime2());
        taskCall.enqueue(new Callback<Task>() {
            @Override
            public void onResponse(Call<Task> call, Response<Task> response) {
                Toast.makeText(getApplicationContext(),"Task",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Task> call, Throwable t) {

            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        int ids = parent.getId();
        if (ids == R.id.spinnerdoctor) {
            Doctor doctor = (Doctor) parent.getItemAtPosition(position);
            myStringArray[0] = doctor.getName().toString();

        }

        if (ids == R.id.spinnerdoctor1) {
            Doctor doctor = (Doctor) parent.getItemAtPosition(position);
            myStringArray[1] = doctor.getName().toString();
        }

        if (ids == R.id.spinnerdoctor2) {
            Doctor doctor = (Doctor) parent.getItemAtPosition(position);
            myStringArray[2] = doctor.getName().toString();
        }

        if (ids == R.id.spinnerdoctor3) {
            Doctor doctor = (Doctor) parent.getItemAtPosition(position);
            myStringArray[3] = doctor.getName().toString();
        }
        if (ids == R.id.spinnerdoctor4) {
            Doctor doctor = (Doctor) parent.getItemAtPosition(position);
            myStringArray[4] = doctor.getName().toString();
        }
        if (ids == R.id.spinnerdoctor5) {
            Doctor doctor = (Doctor) parent.getItemAtPosition(position);
            myStringArray[5] = doctor.getName().toString();
        }
        if (ids == R.id.spinnerdoctor6) {
            Doctor doctor = (Doctor) parent.getItemAtPosition(position);
            myStringArray[6] = doctor.getName().toString();
        }
        if (ids == R.id.spinnerdoctor7) {
            Doctor doctor = (Doctor) parent.getItemAtPosition(position);
            myStringArray[7] = doctor.getName().toString();
        }
        if (ids == R.id.spinnerdoctor8) {
            Doctor doctor = (Doctor) parent.getItemAtPosition(position);
            myStringArray[8] = doctor.getName().toString();
        }
        if (ids == R.id.spinnerdoctor9) {
            Doctor doctor = (Doctor) parent.getItemAtPosition(position);
            myStringArray[9] = doctor.getName().toString();
        }
        if (ids == R.id.spinnerdoctor10) {
            Doctor doctor = (Doctor) parent.getItemAtPosition(position);
            myStringArray[10] = doctor.getName().toString();
        }
        if (ids == R.id.spinnerdoctor11) {
            Doctor doctor = (Doctor) parent.getItemAtPosition(position);
            myStringArray[11] = doctor.getName().toString();
        }

        if (ids == R.id.spinnerdoctor12) {
            Doctor doctor = (Doctor) parent.getItemAtPosition(position);
            myStringArray[12] = doctor.getName().toString();

        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

