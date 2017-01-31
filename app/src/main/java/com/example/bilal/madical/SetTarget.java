package com.example.bilal.madical;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class SetTarget extends AppCompatActivity {


    TextView t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_target);
        t1 = (TextView) findViewById(R.id.userfortarget);
        String mydata = getIntent().getExtras().getString("bila");
        t1.setText(mydata);

    }
}
