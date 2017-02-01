package com.example.bilal.madical;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import com.example.bilal.madical.model.helper.Constants;
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
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;


/**
 * Created by Bilal on 4/20/2016.
 */
public class BackgroundTaskUser extends AsyncTask<String, Void, String> {
    AlertDialog alertDialog;
    Context ctx;
    private ProgressDialog progress;

    BackgroundTaskUser(Context ctx) {

        this.ctx = ctx;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(ctx).create();
        alertDialog.setTitle("Login Info");


        progress = new ProgressDialog(ctx);
        progress.setMessage("Wait...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // progress.setIndeterminate(true);
        // progress.setProgress(0);
        progress.show();
    }

    @Override
    protected String doInBackground(String... params) {
        /*String reg_url = "http://10.0.2.2/WebApp/Adduser.php";
        String login_url = "http://10.0.2.2/WebApp/loginuser.php";
        String showprofilelist_url = "http://10.0.2.2/WebApp/getuser.php";
        String update_url = "http://10.0.2.2/WebApp/updateuser.php";
        String delete_url = "http://10.0.2.2/WebApp/deleteuser.php";*/


        String s = Constants.BASE_URL;

        String reg_url = s + "Madical/Adduser.php";
        String login_url = s + "Madical/loginuser.php";
        String showprofilelist_url = s + "Madical/getuser.php";
        String update_url = s + "Madical/updateuser.php";
        String delete_url = s + "Madical/deleteuser.php";

        String method = params[0];

        if (method.equals("register")) {

            String firstname = params[1];
            String lastname = params[2];
            String username = params[3];
            String password = params[4];
            String phone = params[5];
            String created = params[6];
            String status = params[7];

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
                                URLEncoder.encode("user_phone", "UTF-8") + "=" + URLEncoder.encode(phone, "UTF-8") + "&" +
                                URLEncoder.encode("created", "UTF-8") + "=" + URLEncoder.encode(created, "UTF-8") + "&" +
                                URLEncoder.encode("status", "UTF-8") + "=" + URLEncoder.encode(status, "UTF-8");

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

                String response = "";
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    response += line;
                }
                bufferedReader.close();
                IS.close();

                httpURLConnection.disconnect();

                if (!response.equals("Login Faild....Try Again")) {
                    return "Logged in";
                } else {
                    return "Login Failed";
                }

                //return response;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (method.equals("showprofilelist")) {
            try {
                URL url = new URL(showprofilelist_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

         /*       OutputStream OS=httpURLConnection.getOutputStream();

                BufferedWriter bufferedWriter =new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));

                String data=URLEncoder.encode("User_Name","UTF-8") + "="+URLEncoder.encode(username,"UTF-8")+"&"+
                        URLEncoder.encode("Password","UTF-8") + "="+URLEncoder.encode(password,"UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();*/


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

                if (!response.equals("nodata")) {
                    Admin_Info.profilesArray.clear();
                    Admin_Info.listprofilesname.clear();
                    JSONArray jsonArray = new JSONArray(response);
                    int totalCount = jsonArray.length();
                    for (int i = 0; i < totalCount; i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        HashMap<String, String> userprofilelist = new HashMap<String, String>();
                        userprofilelist.put("User_Id", jsonObject.getString("User_Id"));
                        userprofilelist.put("First_Name", jsonObject.getString("First_Name"));
                        userprofilelist.put("Last_Name", jsonObject.getString("Last_Name"));
                        userprofilelist.put("User_Name", jsonObject.getString("User_Name"));
                        userprofilelist.put("Password", jsonObject.getString("Password"));
                        userprofilelist.put("Phone", jsonObject.getString("Phone"));
                        userprofilelist.put("Status", jsonObject.getString("Status"));
                        userprofilelist.put("Created_By", jsonObject.getString("Created_By"));
                        Admin_Info.profilesArray.add(userprofilelist);
                        Admin_Info.listprofilesname.add(jsonObject.getString("User_Name"));
                    }


                    return "retriveprofilelist";

                } else {
                    return "unsuccessfullretriveprofile";
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (method.equals("Update")) {
            String firstname = params[1];
            String lastname = params[2];
            String username = params[3];
            String password = params[4];
            String phone = params[5];
            String created = params[6];
            String status = params[7];

            try {
                URL url = new URL(update_url);
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
                                URLEncoder.encode("user_phone", "UTF-8") + "=" + URLEncoder.encode(phone, "UTF-8") + "&" +
                                URLEncoder.encode("created", "UTF-8") + "=" + URLEncoder.encode(created, "UTF-8") + "&" +
                                URLEncoder.encode("status", "UTF-8") + "=" + URLEncoder.encode(status, "UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();

                InputStream IS = httpURLConnection.getInputStream();
                IS.close();

                return "Successfully updated";

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (method.equals("Delete")) {
/*
            String firstname = params[1];
            String lastname=params[2];
*/
            String username = params[1];
/*
            String password=params[4];
            String phone=params[5];
            String created=params[6];
            String status=params[7];
*/

            try {
                URL url = new URL(delete_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);

                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));

                String data = URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();

                InputStream IS = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(IS, "iso-8859-1"));
                String response = "";
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    response += line;


                }

                bufferedReader.close();
                IS.close();

                httpURLConnection.disconnect();


                return response;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    //@Override
    protected void onPostExecute(String res) {
        progress.hide();


        if (res.equals("Successfully registered")) {
            Toast.makeText(ctx, res, Toast.LENGTH_LONG).show();
        } else if (res.equals("retriveprofilelist")) {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(ctx,
                    android.R.layout.simple_list_item_1, android.R.id.text1, Admin_Info.listprofilesname);


            // Assign adapter to ListView
            Admin_Info.listView.setAdapter(adapter);

            // ListView Item Click Listener
            Admin_Info.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {

                    // ListView Clicked item index
                    int itemPosition = position;

                    // ListView Clicked item value
                    String itemValue = (String) Admin_Info.listView.getItemAtPosition(position);

                    // Show Alert
//                    Toast.makeText(ctx, "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG).show();
                    Admin_Info.profileupdateid = itemPosition;
                    ctx.startActivity(new Intent(ctx, UserRecord.class));


                }

            });

            Toast.makeText(ctx, res, Toast.LENGTH_LONG).show();
        } else if (res.equals("Successfully updated")) {
            Toast.makeText(ctx, "Updated Successfully", Toast.LENGTH_LONG).show();
        }
       /* else if(res.equals("Successfully Deleted"))
        {
            Toast.makeText(ctx,"Record is Deleted Successfully",Toast.LENGTH_LONG).show();
        }*/
        else if (res.equals("Logged in")) {
            Intent i = new Intent(ctx, AdminControl.class);
            ctx.startActivity(i);
        }/* else if (res.equals("Deleted")) {
                Toast.makeText(ctx, "Record Deleted Successfuly", Toast.LENGTH_LONG).show();
                Intent i = new Intent(ctx, Admin_Info.class);

                ctx.startActivity(i);

            }*/ else {
            alertDialog.setMessage(res);
            alertDialog.show();


            //RegisterUserActivity registerUserActivity=new RegisterUserActivity();
            //registerUserActivity.Reg_User();
        }

    }
}
