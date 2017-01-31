package com.example.bilal.madical;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterUserActivity extends Activity {

    EditText ET_firstname, ET_lastname, ET_username, ET_password, ET_phone, ET_created, ET_status;

    String firstname, lastname, username, password, phone, created, status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        ET_firstname = (EditText) findViewById(R.id.firstname_ET);
        ET_lastname = (EditText) findViewById(R.id.lastname_ET);
        ET_username = (EditText) findViewById(R.id.usernam_ET);
        ET_password = (EditText) findViewById(R.id.passwrod_ET);
        ET_phone = (EditText) findViewById(R.id.phone_ET);
        ET_created = (EditText) findViewById(R.id.created_ET);
        ET_status = (EditText) findViewById(R.id.status_ET);

    }

    public void register_user(View view) {
        firstname = ET_firstname.getText().toString();
        lastname = ET_lastname.getText().toString();
        username = ET_username.getText().toString();
        password = ET_password.getText().toString();
        phone = ET_phone.getText().toString();
        created = ET_created.getText().toString();
        status = ET_status.getText().toString();
        if (firstname.equals("") && lastname.equals("") && username.equals("") && password.equals("") && phone.equals("") && status.equals("") && created.equals("")) {
            Toast.makeText(RegisterUserActivity.this, "Plzz Complete the required fields:", Toast.LENGTH_SHORT).show();
        } else if (firstname.equals("")) {
            Toast.makeText(RegisterUserActivity.this, "Plzz Enter the First Name:", Toast.LENGTH_SHORT).show();
        } else if (lastname.equals("")) {
            Toast.makeText(RegisterUserActivity.this, "Plzz Enter the Last Name:", Toast.LENGTH_SHORT).show();
        } else if (username.equals("")) {
            Toast.makeText(RegisterUserActivity.this, "Plzz Enter the User Name:", Toast.LENGTH_SHORT).show();
        } else if (password.equals("")) {
            Toast.makeText(RegisterUserActivity.this, "Plzz Enter the Password:", Toast.LENGTH_SHORT).show();
        } else if (phone.equals("")) {
            Toast.makeText(RegisterUserActivity.this, "Plzz Enter the Cell No:", Toast.LENGTH_SHORT).show();
        } else if (created.equals("")) {
            Toast.makeText(RegisterUserActivity.this, "Plzz Enter the Created_By:", Toast.LENGTH_SHORT).show();
        } else if (status.equals("")) {
            Toast.makeText(RegisterUserActivity.this, "Plzzz ente the Status:", Toast.LENGTH_SHORT).show();
        } else {


            String method = "register";

            BackgroundTaskUser backgroundTaskUser = new BackgroundTaskUser(this);
            backgroundTaskUser.execute(method, firstname, lastname, username, password, phone, created, status);


        }
        finish();


    }


}

