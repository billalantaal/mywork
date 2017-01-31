package com.example.bilal.madical;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void admin(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void user(View view)

    {
        startActivity(new Intent(this, UserLoginActivity.class));
    }
}
