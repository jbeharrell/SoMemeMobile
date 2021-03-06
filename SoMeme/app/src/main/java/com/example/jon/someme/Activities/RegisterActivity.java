package com.example.jon.someme.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jon.someme.R;
import com.example.jon.someme.dataAccess.URLS;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class RegisterActivity extends ActionBarActivity {

    //Initial variables and objects
    private ProgressDialog pDialog;
    private JSONParser jsonParser = new JSONParser();
    private EditText username, password, email, fname, lname, birthDate, country;
    private Spinner gender;
    private Button register, login;
    private boolean isRegisterSuccessful;
    //private int currentUserID;

    //URL to create new account
    private static String url = "http://192.168.2.11:80/finalapp/data/createAccount.php";
    //final private static String url  = URLS.register;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //  currentUserID = getIntent().getExtras().getInt("currentUserID");

        register = (Button) findViewById(R.id.btnRegister);
        login = (Button) findViewById(R.id.btnLogin);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        email = (EditText) findViewById(R.id.email);
        fname = (EditText) findViewById(R.id.fname);
        lname = (EditText) findViewById(R.id.lname);
        birthDate = (EditText) findViewById(R.id.dob);
        gender = (Spinner) findViewById(R.id.gender);
        country = (EditText) findViewById(R.id.country);
        //inputDesc = (TextView) findViewById(R.id.inputDesc);

        // Create button
//        Button btnCreateProduct = (Button) findViewById(R.id.login);


//            username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//                @Override
//                public void onFocusChange(View v, boolean hasFocus) {
//
//                    if (hasFocus) {
//                        if (username.getText().toString().trim().length() < 3) {
//                            username.setError("Failed");
//                        } else {
//                            username.setError(null);
//                        }}}});

        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // creating new product in background thread
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
//                i.putExtra("currentUserID", currentUserID);
                startActivity(i);
            }
        });

        // button click event
        register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // creating new product in background thread
                //validate before sending
                boolean AreDetailsValid = onSubmitClicked(view);

                if (AreDetailsValid) {
                    new VerifyAccountCreation().execute();
                } else {
                    Toast.makeText(getApplicationContext(), "Sorry, details are not correct!", Toast.LENGTH_SHORT);
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_nonuser, menu);
        return true;
    }

    public boolean onSubmitClicked(View v) {
        String user = username.getText().toString().trim();
        String pass = password.getText().toString().trim();
        String fn = fname.getText().toString().trim();
        String ln = lname.getText().toString().trim();
        String em = email.getText().toString().trim();
        String dob = birthDate.getText().toString().trim();
        String cntry = country.getText().toString().trim();

        if (TextUtils.isEmpty(user) || user.length() < 4 || user.length() > 12) {
            username.setError("Username must be between 4 and 11 characters in length.");
            return false;
        }

        if (TextUtils.isEmpty(pass) || pass.length() < 6) {
            password.setError("You must have at least 6 characters in your password.");
            return false;
        }

        if (TextUtils.isEmpty(fn) || fn.length() < 1) {
            fname.setError("First name cannot be blank!");
            return false;
        }

        if (TextUtils.isEmpty(ln) || ln.length() < 1) {
            lname.setError("Last name cannot be blank!");
            return false;
        }

        if (TextUtils.isEmpty(em) || em.length() < 1) {
            email.setError("Email must be in proper email format.");
            return false;
        }

        if (TextUtils.isEmpty(dob) || dob.length() < 10) {
            birthDate.setError("You must have fill in your date of birth.");
            return false;
        }

        if (TextUtils.isEmpty(cntry) || cntry.length() < 1) {
            country.setError("You must choose a country.");
            return false;
        }

        return true;
    }

    /**
     * Background Async Task to Create new account
     */
    class VerifyAccountCreation extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(RegisterActivity.this);
            pDialog.setMessage("Creating account...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
//            String text = spinner.getSelectedItem().toString();

        /**
         * Creating account
         */
        protected String doInBackground(String... args) {
            String user = username.getText().toString().trim();
            String pass = password.getText().toString().trim();
            String em = email.getText().toString().trim();
            String fn = fname.getText().toString().trim();
            String ln = lname.getText().toString().trim();
            String dob = birthDate.getText().toString().trim();


            String gen = String.valueOf(gender.getSelectedItem());


            String cntry = country.getText().toString().trim();


            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("username", user));
            params.add(new BasicNameValuePair("password", pass));
            params.add(new BasicNameValuePair("email", em));
            params.add(new BasicNameValuePair("fname", fn));
            params.add(new BasicNameValuePair("lname", ln));
            params.add(new BasicNameValuePair("dob", dob));
            params.add(new BasicNameValuePair("gender", gen));
            params.add(new BasicNameValuePair("country", cntry));
            params.add(new BasicNameValuePair("mobile", "true"));

            // getting JSON Object
            // Note that create product url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url, "POST", params);

            //check log cat fro response
            Log.d("Login", json.toString());
            //      check for success tag
            try {
                int success = json.getInt(URLS.TAG_SUCCESS);
                if (success == 1) {

                    isRegisterSuccessful = true;
                    //Toast.makeText(getApplicationContext(), "Success!", Toast.LENGTH_SHORT);
                    //successfully created product
                    Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                    // i.putExtra("currentUserID", currentUserID);
                    startActivity(i);
                    //closing this screen
// sending pid to next activity
                    //i.putExtra(TAG_PID, pid);

                    // starting new activity and expecting some response back
                    //startActivityForResult(in, 100);
                } else {
                    isRegisterSuccessful = false;
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

            if (isRegisterSuccessful == false) {
                Toast.makeText(getApplicationContext(), "There was an error registering the account.", Toast.LENGTH_LONG).show();
            }
        }
    }
}
