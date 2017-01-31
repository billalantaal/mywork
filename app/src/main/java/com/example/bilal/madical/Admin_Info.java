package com.example.bilal.madical;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

public class Admin_Info extends Activity {

    Context cntx;
    //String[] mobileArray = {"Android","IPhone","WindowsMobile","Blackberry","WebOS","Ubuntu","Windows7","Max OS X"};
    public static ListView listView;

    public static ArrayList<HashMap> profilesArray = new ArrayList<HashMap>();
    public static ArrayList<String> listprofilesname = new ArrayList<String>();
    public static int profileupdateid = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_admin__info);
        cntx = this;
/*

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.adminlistview, mobileArray);

        ListView listView = (ListView) findViewById(R.id.admin_info);
        listView.setAdapter(adapter);

*/


        BackgroundTaskUser b = new BackgroundTaskUser(this);
        b.execute("showprofilelist");

        listView = (ListView) findViewById(R.id.admin_info);

        /*String[] values = new String[] { "Android List View",
                "Adapter implementation",
                "Simple List View In Android",
                "Create List View Android",
                "Android Example",
                "List View Source Code",
                "List View Array Adapter",
                "Android Example List View"
        };*/

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data


    }/*
    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this,"On pasuse Admin Info Activity",Toast.LENGTH_LONG).show();
        finishAffinity();
    }*/
}
