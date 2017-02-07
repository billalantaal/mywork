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

import com.example.bilal.madical.model.adapter.DocAdptrSpinr;
import com.example.bilal.madical.model.adapter.Docadapter;
import com.example.bilal.madical.model.callback.InsertTask;
import com.example.bilal.madical.model.callback.doc;
import com.example.bilal.madical.model.helper.Constants;
import com.example.bilal.madical.model.pojo.Doctor;
import com.example.bilal.madical.model.pojo.Task;
import com.example.bilal.madical.model.pojo.Word;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
    int data;


    List<Doctor> docList = new ArrayList<>();
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
    int[] myStringArray = new int[13];
    EditText editTm;
    EditText editTm1;
    EditText editTm2;
    List<Word> wordArrayList = new ArrayList<>();
    Word word;

    boolean iCurrentSelection = true;
    boolean iCurrentSelection1 = true;
    boolean iCurrentSelection2 = true;
    boolean iCurrentSelection3 = true;
    boolean iCurrentSelection4 = true;
    boolean iCurrentSelection5 = true;
    boolean iCurrentSelection6 = true;
    boolean iCurrentSelection7 = true;
    boolean iCurrentSelection8 = true;
    boolean iCurrentSelection9 = true;
    boolean iCurrentSelection10 = true;
    boolean[] chk = new boolean[2];

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

        Arrays.fill(chk, true);

//        iCurrentSelection = spinner1.getSelectedItemPosition();
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

        data = getIntent().getIntExtra("bil", 1);
        Toast.makeText(this, "" + data, Toast.LENGTH_LONG).show();

        textuser.setText("" + data);

    }

    public void getFeedDoctor() {
       /* Doctor doctor1=new Doctor();
        doctor1.setName("select");
        doctor1.setId(1);
        doctor1.setAddress("gfh");
        doctor1.setDesignation("fgj");
        doctor1.setHospitalClinic("ghdfj");
        doctor1.setPhone("gjg");
        doctor1.setSpeciality("gfg");
        docList.add(doctor1);*/

        Retrofit docrestadapter = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        doc docapi = docrestadapter.create(doc.class);
        Call<List<Doctor>> call = docapi.getDataDoc();
        call.enqueue(new Callback<List<Doctor>>() {
            @Override
            public void onResponse(Call<List<Doctor>> call, Response<List<Doctor>> response) {

                Doctor doctor1 = new Doctor();
                doctor1.setName("Select Doctor");
                doctor1.setId(-1);
              /*  doctor1.setAddress("gfh");
                doctor1.setDesignation("fgj");
                doctor1.setHospitalClinic("ghdfj");
                doctor1.setPhone("gjg");
                doctor1.setSpeciality("gfg");*/

                docList = response.body();
                docList.add(0, doctor1);

                DocAdptrSpinr adpt = new DocAdptrSpinr(getApplicationContext(), R.layout.docspinr, docList);
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
        String myFormat = "dd/MM/yyyy"; //In which you need put here
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


//        for(int i=0; i<=2; i++){


        if ((!chk[0]) && (!chk[1])) {

            for (int i = 0; i < 2; i++) {
                word = new Word(editTm.getText().toString(), myStringArray[i]);
                wordArrayList.add(word);


            }
        } else {
            Toast.makeText(this, "please fill all ", Toast.LENGTH_LONG).show();
        }


//        }
       /* word = new Word(editTm1.getText().toString(), myStringArray[1]);
        wordArrayList.add(word);*/
        Gson gson = new GsonBuilder().create();
        JsonArray myCustomArray = gson.toJsonTree(wordArrayList).getAsJsonArray();
        String jsn = myCustomArray.toString();


        Toast.makeText(this, "" + jsn, Toast.LENGTH_LONG).show();


        Retrofit addtask = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        InsertTask insertTask = addtask.create(InsertTask.class);
        Task task = new Task();
        task.setDate(editText.getText().toString());
        task.setUser(textuser.getText().toString());
        task.setJsndoctortime(jsn);

        Call<Task> taskCall = insertTask.inserttsk(task.getDate(),
                task.getUser(),
                task.getJsndoctortime());
        taskCall.enqueue(new Callback<Task>() {
            @Override
            public void onResponse(Call<Task> call, Response<Task> response) {

                Toast.makeText(getApplicationContext(), "ho gya", Toast.LENGTH_LONG).show();
                Log.i("this", "hhhhhhhhhhhhhhhhhhhhhhhhhhhh");
            }

            @Override
            public void onFailure(Call<Task> call, Throwable t) {

                Log.i("this", "lllllllllllllllllllllllllllllllll");
            }
        });

        wordArrayList.clear();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


        int ids = parent.getId();
        if (ids == R.id.spinnerdoctor) {
            Doctor doctor = (Doctor) parent.getItemAtPosition(position);


            if (!iCurrentSelection) {
                /*int i=parent.getSelectedItemPosition();
                if(i==0){

                    Toast.makeText(this,"billl",Toast.LENGTH_LONG).show();
                }*/
                myStringArray[0] = doctor.getId();
                chk[0] = false;

            } else {
                iCurrentSelection = false;
            }



         /*   if(doctor.getName().toString().matches("Dr Shahroz")){
                Toast.makeText(this,"bukd",Toast.LENGTH_LONG).show();
            }*/
//            Word c=wordArrayList.get(0);

        }

        if (ids == R.id.spinnerdoctor1) {
            Doctor doctor = (Doctor) parent.getItemAtPosition(position);

            if (!iCurrentSelection1) {
                myStringArray[1] = doctor.getId();
                chk[1] = false;
            } else {
                iCurrentSelection1 = false;
            }

        }

        if (ids == R.id.spinnerdoctor2) {
            Doctor doctor = (Doctor) parent.getItemAtPosition(position);
            if (!iCurrentSelection2) {
                myStringArray[2] = doctor.getId();
            }
            iCurrentSelection2 = false;
        }

        if (ids == R.id.spinnerdoctor3) {
            Doctor doctor = (Doctor) parent.getItemAtPosition(position);
            if (!iCurrentSelection3) {
                myStringArray[3] = doctor.getId();
            }
            iCurrentSelection3 = false;
        }
        if (ids == R.id.spinnerdoctor4) {
            Doctor doctor = (Doctor) parent.getItemAtPosition(position);

            if (!iCurrentSelection4) {
                myStringArray[4] = doctor.getId();
            }
            iCurrentSelection4 = false;
        }
        if (ids == R.id.spinnerdoctor5) {
            Doctor doctor = (Doctor) parent.getItemAtPosition(position);

            if (!iCurrentSelection5) {
                myStringArray[5] = doctor.getId();

            }
            iCurrentSelection5 = false;
        }
        if (ids == R.id.spinnerdoctor6) {
            Doctor doctor = (Doctor) parent.getItemAtPosition(position);
            if (!iCurrentSelection6) {

                myStringArray[6] = doctor.getId();
            }
            iCurrentSelection6 = false;
        }
        if (ids == R.id.spinnerdoctor7) {
            Doctor doctor = (Doctor) parent.getItemAtPosition(position);

            if (!iCurrentSelection7) {
                myStringArray[7] = doctor.getId();
            }
            iCurrentSelection7 = false;
        }
        if (ids == R.id.spinnerdoctor8) {
            Doctor doctor = (Doctor) parent.getItemAtPosition(position);
            if (!iCurrentSelection8) {
                myStringArray[8] = doctor.getId();

            }
            iCurrentSelection8 = false;
        }
        if (ids == R.id.spinnerdoctor9) {
            Doctor doctor = (Doctor) parent.getItemAtPosition(position);
            if (!iCurrentSelection9) {
                myStringArray[9] = doctor.getId();

            }
            iCurrentSelection9 = false;
        }
        if (ids == R.id.spinnerdoctor10) {
            Doctor doctor = (Doctor) parent.getItemAtPosition(position);
            if (!iCurrentSelection10) {
                myStringArray[10] = doctor.getId();

            }
            iCurrentSelection10 = false;
        }
        if (ids == R.id.spinnerdoctor11) {
            Doctor doctor = (Doctor) parent.getItemAtPosition(position);
            myStringArray[11] = doctor.getId();
        }

        if (ids == R.id.spinnerdoctor12) {
            Doctor doctor = (Doctor) parent.getItemAtPosition(position);
            myStringArray[12] = doctor.getId();

        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

