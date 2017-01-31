package com.example.bilal.madical;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UserRecord extends AppCompatActivity {

    EditText fname, lname, uname, pass, phone, status, created;
    String Fname, Lname, Uname, Upass, Uphone, Ustatus, Ucreated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_record);

        fname = (EditText) findViewById(R.id.firstname);
        lname = (EditText) findViewById(R.id.lastname);
        uname = (EditText) findViewById(R.id.username);
        pass = (EditText) findViewById(R.id.passwrod);
        phone = (EditText) findViewById(R.id.phone);
        created = (EditText) findViewById(R.id.created);
        status = (EditText) findViewById(R.id.status);
        fname.setText(Admin_Info.profilesArray.get(Admin_Info.profileupdateid).get("First_Name").toString());
        lname.setText(Admin_Info.profilesArray.get(Admin_Info.profileupdateid).get("Last_Name").toString());
        uname.setText(Admin_Info.profilesArray.get(Admin_Info.profileupdateid).get("User_Name").toString());
        pass.setText(Admin_Info.profilesArray.get(Admin_Info.profileupdateid).get("Password").toString());
        phone.setText(Admin_Info.profilesArray.get(Admin_Info.profileupdateid).get("Phone").toString());
        status.setText(Admin_Info.profilesArray.get(Admin_Info.profileupdateid).get("Status").toString());
        created.setText(Admin_Info.profilesArray.get(Admin_Info.profileupdateid).get("Created_By").toString());

    }

    public void adduser(View view) {

        startActivity(new Intent(this, RegisterUserActivity.class));
    }

    public void update(View view) {
        Fname = fname.getText().toString();
        Lname = lname.getText().toString();
        Uname = uname.getText().toString();
        Upass = pass.getText().toString();
        Uphone = phone.getText().toString();
        Ucreated = created.getText().toString();
        Ustatus = status.getText().toString();
        if (Fname.equals("") && Lname.equals("") && Uname.equals("") && Upass.equals("") && Uphone.equals("") && Ustatus.equals("") && Ucreated.equals("")) {
            Toast.makeText(UserRecord.this, "Plzz Complete the required fields:", Toast.LENGTH_SHORT).show();
        } else if (Fname.equals("")) {
            Toast.makeText(UserRecord.this, "Plzz Enter the First Name:", Toast.LENGTH_SHORT).show();
        } else if (Lname.equals("")) {
            Toast.makeText(UserRecord.this, "Plzz Enter the Last Name:", Toast.LENGTH_SHORT).show();
        } else if (Uname.equals("")) {
            Toast.makeText(UserRecord.this, "Plzz Enter the User Name:", Toast.LENGTH_SHORT).show();
        } else if (Upass.equals("")) {
            Toast.makeText(UserRecord.this, "Plzz Enter the Password:", Toast.LENGTH_SHORT).show();
        } else if (Uphone.equals("")) {
            Toast.makeText(UserRecord.this, "Plzz Enter the Cell No:", Toast.LENGTH_SHORT).show();
        } else if (Ucreated.equals("")) {
            Toast.makeText(UserRecord.this, "Plzz Enter the Created_By:", Toast.LENGTH_SHORT).show();
        } else if (Ustatus.equals("")) {
            Toast.makeText(UserRecord.this, "Plzzz enter the Status:", Toast.LENGTH_SHORT).show();
        } else {


            String method = "Update";

            BackgroundTaskUser backgroundTaskUser = new BackgroundTaskUser(this);
            backgroundTaskUser.execute(method, Fname, Lname, Uname, Upass, Uphone, Ucreated, Ustatus);

            finish();
        }


    }

    public void deleteuser(View view) {

       /* Fname=fname.getText().toString();
        Lname=lname.getText().toString();
      */
        Uname = uname.getText().toString();
       /* Upass=pass.getText().toString();
        Uphone=phone.getText().toString();
        Ucreated=created.getText().toString();
        Ustatus=status.getText().toString();
*/


        String method = "Delete";

        BackgroundTaskUser backgroundTaskUser = new BackgroundTaskUser(this);
/*
            backgroundTaskUser.execute(method, Fname, Lname, Uname, Upass, Uphone, Ucreated, Ustatus);
*/
        backgroundTaskUser.execute(method, Uname);

        finish();


    }


}

