package com.example.bilal.madical;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.bilal.madical.model.pojo.Doctor;

public class UpdateDoc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_doc);


    }
    private void procome() {
        Doctor model = (Doctor) getIntent().getSerializableExtra("pro");

    }
}
