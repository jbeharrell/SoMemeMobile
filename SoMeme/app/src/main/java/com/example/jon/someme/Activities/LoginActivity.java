package com.example.jon.someme.activities;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jon.someme.R;
import org.json.JSONException;

public class LoginActivity extends ActionBarActivity {

    // Progress Dialog
    private ProgressDialog pDialog;

    JSONParser jsonParser = new JSONParser();
    EditText username;
    EditText password;
    Button login;
    Button register;

    // url to create new product
    private static String url_login = "http://192.168.2.11:80/finalapp/data/authenticate.php";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";

    @Override
    public void onCreate(Bundle savedInstanceState) {
   //     super.onCreate(savedInstanceState);
 //       setContentView(R.layout.add_product);

        super.onCreate(savedInstanceState);
        // setting default screen to login.xml
        setContentView(R.layout.activity_login);

        login = (Button) findViewById(R.id.btnLogin);
        register = (Button)findViewById(R.id.btnRegister);
//        TextView registerScreen = (TextView) findViewById(R.id.link_to_register);

        // Listening to register new account link
        // Edit Text


        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        //inputDesc = (TextView) findViewById(R.id.inputDesc);

        // Create button
//        Button btnCreateProduct = (Button) findViewById(R.id.login);
        register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // creating new product in background thread
                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);
            }
        });

        // button click event
        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // creating new product in background thread
                new VerifyLogin().execute();
            }
        });
    }

    /**
     * Background Async Task to Create new product
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
         * Creating product
         */
        protected String doInBackground(String... args) {
            String user = username.getText().toString();
            String pass = password.getText().toString();
//            String description = inputDesc.getText().toString();

            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("username", user));
            params.add(new BasicNameValuePair("password", pass));
            params.add(new BasicNameValuePair("mobile", "true"));

            // getting JSON Object
            // Note that create product url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url_login,"POST", params);

            //check log cat fro response
            Log.d("Login", json.toString());
      //      check for success tag
            try {
                int success = json.getInt(TAG_SUCCESS);
               if (success == 1) {

                   //Toast.makeText(getApplicationContext(), "Success!", Toast.LENGTH_SHORT);
                    //successfully created product
  //                  Intent i = new Intent(getApplicationContext(), UserProfileActivity.class);
//                    startActivity(i);
                    //closing this screen
// sending pid to next activity
                   //i.putExtra(TAG_PID, pid);

                   // starting new activity and expecting some response back
                   //startActivityForResult(in, 100);
                } else {
                    //failed to create product
                }
           } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * *
         */
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            pDialog.dismiss();
        }
    }
}