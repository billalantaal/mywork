package com.example.bilal.madical;

/**
 * Created by Bilal on 12/26/2016.
 */


import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.bilal.madical.model.helper.Constants;
import com.example.bilal.madical.model.helper.Session;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Bilal on 4/21/2016.
 */
public class BackGroundTaskAdmin extends AsyncTask<String, Void, String> {
    AlertDialog alertDialog;
    Context ctx;
    public String usr;

    String uname = "", pass = "", Combine = "";
    String[] unames;
    String[] passes;


    BackGroundTaskAdmin(
            Context ctx) {
        this.ctx = ctx;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(ctx).create();
        alertDialog.setTitle("Login Info");
    }

    @Override
    protected String doInBackground(String... params) {

       /* String reg_url = "http://10.0.2.2/WebApp/Admin_reg.php";
        String login_url = "http://10.0.2.2/WebApp/login.php";
        String ret_url = "http://10.0.2.2/WebApp/retrieve.php";
*/

        String s = Constants.BASE_URL;
        String reg_url = s + "Madical/Admin_reg.php";
        String login_url = s + "Madical/login.php";
        String ret_url = s + "Madical/retrieve.php";


        String method = params[0];

        if (method.equals("Register")) {

            String firstname = params[1];
            String lastname = params[2];
            String username = params[3];
            String password = params[4];
            String email = params[5];
//            String phone=params[6];
//            String status=params[7];
//            String desig=params[8];
//            String created=params[9];

            try {
                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);

                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));

                String data =
                        URLEncoder.encode("first_name", "UTF-8") + "=" + URLEncoder.encode(firstname, "UTF-8") + "&" +
                                URLEncoder.encode("last_name", "UTF-8") + "=" + URLEncoder.encode(lastname, "UTF-8") + "&" +
                                URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&" +
                                URLEncoder.encode("user_pass", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8") + "&" +
                                URLEncoder.encode("user_email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();

                InputStream IS = httpURLConnection.getInputStream();
                IS.close();

                return "Successfully registered";

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (method.equals("login")) {
            String username = params[1];
            String password = params[2];
            usr=username;
            try {
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));

                String data = URLEncoder.encode("User_Name", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&" +
                        URLEncoder.encode("Password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();

                InputStream IS = httpURLConnection.getInputStream();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(IS, "iso-8859-1"));
                StringBuilder sb = new StringBuilder();

                String response = "";
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    response += line;
                    sb.append(response);


                }

                bufferedReader.close();
                IS.close();

                httpURLConnection.disconnect();

                if (!response.equals("Login Faild....Try Again")) {
                    JSONArray jsonArray = new JSONArray(response);
                    int totalCount = jsonArray.length();
                    for (int i = 0; i < totalCount; i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        LoginActivity.adminLog.put("Admin_Id", jsonObject.getString("Admin_Id"));
                        LoginActivity.adminLog.put("First_Name", jsonObject.getString("First_Name"));
                        LoginActivity.adminLog.put("Last_Name", jsonObject.getString("Last_Name"));
                        LoginActivity.adminLog.put("User_Name", jsonObject.getString("User_Name"));
                        LoginActivity.adminLog.put("Password", jsonObject.getString("Password"));
                        LoginActivity.adminLog.put("Email", jsonObject.getString("Email"));
                     /*   LoginActivity.adminLog.put("Phone", jsonObject.getString("Phone"));
                        LoginActivity.adminLog.put("Status", jsonObject.getString("Status"));
                        LoginActivity.adminLog.put("Designation", jsonObject.getString("Designation"));
                        LoginActivity.adminLog.put("Created_By", jsonObject.getString("Created_By"));*/

                    }


                    return "successful_login";

                } else {
                    return "unsuccessfull_login";
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (method.equals("Retrieve")) {
            //String IDS=params[1];
            //String NAME=params[1];
            //String PASS=params[2];
            try {
                URL url = new URL(ret_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);


                if (httpURLConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    Log.d("", "http response code is " + httpURLConnection.getResponseCode());

                    return null;
                }
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"), 8);
                String response = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    response += line;
                }


                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                JSONArray jsonArray = new JSONArray(response);
                int totalCount = jsonArray.length();
                for (int i = 0; i < totalCount; i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    uname += jsonObject.getString("User_Name") + ":";
                    pass += jsonObject.getString("Password") + ":";
                    //FNAME=uname;
                    //LNAME=pass;


                    Combine += (i + 1) + " Name '" + uname + "', \n" + "Password '" + pass + "', \n\n\n\n";

                }


                return "Retrieve";


            } catch (Exception e) {
                Toast.makeText(ctx, e.getMessage() + ", Ex3", Toast.LENGTH_SHORT).show();
            }
        }


        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {

        if (result.equals("Successfully registered")) {
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        } else if (result.equals("Retrieve")) {

            unames = uname.split(":");
            passes = pass.split(":");
            alertDialog.setMessage(Combine);
            alertDialog.show();
        } else if (result.equals("successful_login")) {


            Session.setDefaults("username",usr,ctx);
            Intent i = new Intent(ctx, AdmincontrolActivity.class);
            ctx.startActivity(i);
            // Toast.makeText(ctx,result,Toast.LENGTH_LONG).show();
        } else {
            alertDialog.setMessage(result);
            alertDialog.show();


            //RegisterUserActivity registerUserActivity=new RegisterUserActivity();
            //registerUserActivity.Reg_User();
        }


    }
}
