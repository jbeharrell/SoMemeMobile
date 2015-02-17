package com.example.jon.someme.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jon.someme.R;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class RegisterActivity extends ActionBarActivity{

        // Progress Dialog
        private ProgressDialog pDialog;

        JSONParser jsonParser = new JSONParser();
        EditText username;
        EditText password;
        EditText email;
        EditText fname;
        EditText lname;
        EditText birthDate;
        Spinner gender;
        EditText country;
        Button register;
        Button login;

        // url to create new product
        private static String url_login = "http://192.168.2.11:80/finalapp/data/createAccount.php";

        // JSON Node names
        private static final String TAG_SUCCESS = "success";

        @Override
        public void onCreate(Bundle savedInstanceState) {
            //     super.onCreate(savedInstanceState);
            //       setContentView(R.layout.add_product);

            super.onCreate(savedInstanceState);
            // setting default screen to login.xml
            setContentView(R.layout.activity_register);

            register = (Button) findViewById(R.id.btnRegister);
            login = (Button)findViewById(R.id.btnLogin);
//        TextView registerScreen = (TextView) findViewById(R.id.link_to_register);

            // Listening to register new account link
            // Edit Text

            //use date for dob and, dropdown for countries, m/f/unspecified
            //validate email type possibly

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

                    if(AreDetailsValid) {
                        new VerifyAccountCreation().execute();
                    }else{
                        Toast.makeText(getApplicationContext(), "Sorry, details are not correct!", Toast.LENGTH_SHORT);
                    }
                }
            });
        }

    public boolean onSubmitClicked(View v) {
        String user = username.getText().toString();
        String pass = password.getText().toString();
        String fn = fname.getText().toString();
        String ln = lname.getText().toString();
        String em = email.getText().toString();
        String dob = birthDate.getText().toString();
        String cntry = country.getText().toString();

        if(TextUtils.isEmpty(user) || user.length() < 4 || user.length() > 12) {
            username.setError("Username must be between 4 and 11 characters in length.");
            return false;
        }

        if(TextUtils.isEmpty(pass) || pass.length() < 6) {
            password.setError("You must have at least 6 characters in your password.");
            return false;
        }

        if(TextUtils.isEmpty(fn) || fn.length() < 1) {
            fname.setError("First name cannot be blank!");
            return false;
        }

        if(TextUtils.isEmpty(ln) || ln.length() < 1) {
            lname.setError("Last name cannot be blank!");
            return false;
        }

        if(TextUtils.isEmpty(em) || em.length() < 1) {
            email.setError("Email must be in proper email format.");
            return false;
        }

        if(TextUtils.isEmpty(dob) || dob.length() < 10) {
            birthDate.setError("You must have fill in your date of birth.");
            return false;
        }

        if(TextUtils.isEmpty(cntry) || cntry.length() < 1) {
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
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String em = email.getText().toString();
                String fn = fname.getText().toString();
                String ln = lname.getText().toString();
                String dob = birthDate.getText().toString();
                String gen = gender.getSelectedItem().toString();
                String cntry = country.getText().toString();

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
                JSONObject json = jsonParser.makeHttpRequest(url_login,"POST", params);

                //check log cat fro response
                Log.d("Login", json.toString());
                //      check for success tag
                try {
                    int success = json.getInt(TAG_SUCCESS);
                    if (success == 1) {

                        //Toast.makeText(getApplicationContext(), "Success!", Toast.LENGTH_SHORT);
                        //successfully created product
                        Intent i = new Intent(getApplicationContext(), UserProfileActivity.class);
                        startActivity(i);
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
