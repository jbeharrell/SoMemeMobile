package com.example.jon.someme.activities;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jon.someme.R;
import com.example.jon.someme.dataAccess.URLS;

import org.json.JSONException;

/**
 * This is the LoginActivity for the SoMeme application.
 * <p/>
 * This is the activity that will handle the user login
 *
 * @author: Ian Mori
 * @since: 2015-02-12
 */
public class LoginActivity extends ActionBarActivity {

    //Initial variables and objects
    private ProgressDialog pDialog;
    private JSONParser jsonParser = new JSONParser();
    private EditText username;
    private EditText password;
    private Button login;
    private Button register;
    private boolean isLoginSuccessful;

    //url to login
    //This will need to be changed to the local machine IP
    private static String url = "http://192.168.2.11:80/finalapp/data/authenticate.php";
    //final private static String url  = URLS.authenticate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = (Button) findViewById(R.id.btnLogin);
        register = (Button) findViewById(R.id.btnRegister);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        //Setting onclick listener for the register button
        register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //Will start the register activity
                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);
            }
        });

        //Setting onclick listener for the login button
        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                //Check if the user input was valid
                boolean AreDetailsValid = onSubmitClicked(view);
                if (AreDetailsValid) {
                    new VerifyLogin().execute();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_nonuser, menu);
        return true;
    }

    /**
     * This method will check the details entered by the user
     */
    public boolean onSubmitClicked(View v) {
        String user = username.getText().toString();
        String pass = password.getText().toString();

        if (TextUtils.isEmpty(user) || user.length() < 1) {
            username.setError("Please enter your username.");
            return false;
        }

        if (TextUtils.isEmpty(pass) || pass.length() < 1) {
            password.setError("Please enter your password.");
            return false;
        }

        return true;
    }

    /**
     * Background Async Task to verify the login
     */
    class VerifyLogin extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(LoginActivity.this);
            pDialog.setMessage("Verifying details...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Creating data to send to the server
         */
        protected String doInBackground(String... args) {
            String user = username.getText().toString().trim();
            String pass = password.getText().toString().trim();

            //Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("username", user));
            params.add(new BasicNameValuePair("password", pass));
            params.add(new BasicNameValuePair("mobile", "true"));

            //Getting the JSON Object
            //Sending POST parameters to the PHP page
            JSONObject json = jsonParser.makeHttpRequest(url, "POST", params);

            //Log response
            Log.d("Login", json.toString());

            //Check if the login was successful from the return value of the request
            try {
                int success = json.getInt(URLS.TAG_SUCCESS);
                if (success == 1) {
                    //User is logged in, storing the username to the local device db
                    //The LoginProvider content provider will allow the usernames to be accessed from another application
                    isLoginSuccessful = true;
                    ContentValues values = new ContentValues();
                    values.put(LoginProvider.user_id, json.getString("id"));
                    Uri uri = getContentResolver().insert(LoginProvider.CONTENT_URI, values);
                    Intent i = new Intent(getApplicationContext(), UserProfileActivity.class);
                    i.putExtra("currentUserID", json.getInt("id"));
                    i.putExtra("profileUserID", json.getInt("id"));
                    startActivity(i);
                } else {
                    isLoginSuccessful = false;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog.
         */
        protected void onPostExecute(String file_url) {
            pDialog.dismiss();

            //Show an error message if there was an issue logging in.
            if (isLoginSuccessful == false) {
                Toast.makeText(getApplicationContext(), "There was an error logging in.", Toast.LENGTH_LONG).show();
            }
        }
    }
}