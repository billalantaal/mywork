package com.example.bilal.madical;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UserLoginActivity extends AppCompatActivity {

    EditText et_username, et_password;
    String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        et_username = (EditText) findViewById(R.id.usernameu_et);
        et_password = (EditText) findViewById(R.id.userpassu_et);


    }

 /*   public void user_login(View view) {
        username = et_username.getText().toString();
        password = et_password.getText().toString();

        if (username.equals("") && password.equals("")) {

            Toast.makeText(UserLoginActivity.this, "username and password fileds are empty", Toast.LENGTH_LONG).show();
        } else if (username.equals("")) {
            Toast.makeText(UserLoginActivity.this, "Plzzz enter the username", Toast.LENGTH_SHORT).show();

        } else if (password.equals("")) {
            Toast.makeText(UserLoginActivity.this, "Plzzz Enter the password", Toast.LENGTH_SHORT).show();
            // getCurrentFocus().equals(password);
        } else {
            String method = "login";

            BackgroundTaskUser backgroundTaskUser = new BackgroundTaskUser(this);
            backgroundTaskUser.execute(method, username, password);
            //finish();
        }

    }
*/
}


