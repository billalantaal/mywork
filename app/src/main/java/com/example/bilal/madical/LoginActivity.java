package com.example.bilal.madical;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bilal.madical.model.helper.Session;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText et_username, et_password;
    public static HashMap<String, String> adminLog;
    //TextView attempts;
    //Button login;
    //int attempts_made=5;

    String s;
    String username, password;
    ListView listView;
    Spinner spinner;
    String userlog;
    Button button;

//    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        spinner = (Spinner) findViewById(R.id.loginspinner);

        button= (Button) findViewById(R.id.loginbtn);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.login_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userlog.matches("Admin")){
                    loginadmin();
                }
                else {
                    user_login();
                }
            }
        });





                /*setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String userlog= (String) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),""+userlog,Toast.LENGTH_LONG).show();
            }
        });*/



        s=Session.getDefaults("username" ,this);
        if(s!=null){
            Intent intent=new Intent(this,AdmincontrolActivity.class);
            startActivity(intent);
        }

        adminLog = new HashMap<String, String>();
        et_username = (EditText) findViewById(R.id.username_et);
        et_password = (EditText) findViewById(R.id.password_et);


        //attempts=(TextView)findViewById(R.id.attemptstv);
        //login=(Button)findViewById(R.id.loginbtn);
        //attempts.setText(Integer.toString(attempts_made));
        //Reg_User();

    }


    protected boolean isonline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

    public void loginadmin() {
        //startActivity(new Intent(this, AdmincontrolActivity.class));

        username = et_username.getText().toString();
        password = et_password.getText().toString();

        if (username.equals("") && password.equals("")) {

            Toast.makeText(LoginActivity.this, "username and password fileds are empty", Toast.LENGTH_LONG).show();
        } else if (username.equals("")) {
            Toast.makeText(LoginActivity.this, "Plzzz enter the username", Toast.LENGTH_SHORT).show();
        } else if (password.equals("")) {
            Toast.makeText(LoginActivity.this, "Plzzz Enter the password", Toast.LENGTH_SHORT).show();
            // getCurrentFocus().equals(password);
        } else {
            if (isonline()) {


                String method = "login";
                BackGroundTaskAdmin backGroundTaskAdmin = new BackGroundTaskAdmin(this);
                backGroundTaskAdmin.execute(method, username, password);
            } else {
                Toast.makeText(this, "Network isn't Availble", Toast.LENGTH_LONG).show();
            }
        }


    }

    public void user_login() {
        username = et_username.getText().toString();
        password = et_password.getText().toString();

        if (username.equals("") && password.equals("")) {

            Toast.makeText(LoginActivity.this, "username and password fileds are empty", Toast.LENGTH_LONG).show();
        } else if (username.equals("")) {
            Toast.makeText(LoginActivity.this, "Plzzz enter the username", Toast.LENGTH_SHORT).show();

        } else if (password.equals("")) {
            Toast.makeText(LoginActivity.this, "Plzzz Enter the password", Toast.LENGTH_SHORT).show();
            // getCurrentFocus().equals(password);
        } else {
            if (isonline()) {
                String method = "login";

                BackgroundTaskUser backgroundTaskUser = new BackgroundTaskUser(this);
                backgroundTaskUser.execute(method, username, password);
                //finish();
            }
            else {
                Toast.makeText(this, "Network isn't Availble", Toast.LENGTH_LONG).show();
            }
        }

    }


    /*public void Retrieve(View view)
    {
        String method="Retrieve";
        BackGroundTaskAdmin backGroundTaskAdmin=new BackGroundTaskAdmin(this);
        backGroundTaskAdmin.execute(method);

    }*/

    /*public void show(View view)
    {
        listView = (ListView) findViewById(R.id.admin_list);

        // Defined Array values to show in ListView
        String[] values = new String[] { "Android List View",
                "Adapter implementation",
                "Simple List View In Android",
                "Create List View Android",
                "Android Example",
                "List View Source Code",
                "List View Array Adapter",
                "Android Example List View"
        };

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);


        // Assign adapter to ListView
        listView.setAdapter(adapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                String itemValue = (String) listView.getItemAtPosition(position);

                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
                        .show();

            }

        });
    }*/


    public void sample(View view) {

        //startActivity(new Intent(this,Admin_Info.class));

    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        userlog= parent.getItemAtPosition(position).toString();
//        Toast.makeText(this,""+userlog,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}



