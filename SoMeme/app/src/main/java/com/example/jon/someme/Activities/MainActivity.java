package com.example.jon.someme.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.jon.someme.R;


public class MainActivity extends ActionBarActivity {

    Button btnLogin;
    Button btnMemeList;
    Button btnProfile;
    Button btnRegister;
    Button btnMemeView;
    Button btnFavorites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogin = (Button) findViewById(R.id.link_to_login);
        btnRegister = (Button) findViewById(R.id.link_to_register);
        btnMemeList = (Button) findViewById(R.id.btnMemeList);
        btnProfile = (Button) findViewById(R.id.btnProfile);
        btnMemeView = (Button)findViewById(R.id.btnMemeView);
        btnFavorites=(Button)findViewById(R.id.btnFavorites);

        //Listening to register new account link
        btnLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // Switching to Register screen
                           Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                         startActivity(i);
            }
        });

        //Listening to register new account link
        btnMemeView.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // Switching to Register screen
                Intent i = new Intent(getApplicationContext(), MemeViewActivity.class);
                startActivity(i);
            }
        });

        //Listening to register new account link
        btnMemeList.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // Switching to Register screen
                Intent i = new Intent(getApplicationContext(), MemeListActivity.class);
                startActivity(i);
            }
        });

        //Listening to register new account link
        btnFavorites.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // Switching to Register screen
                Intent i = new Intent(getApplicationContext(), FavoriteActivity.class);
                startActivity(i);
            }
        });

        //Listening to register new account link
        btnProfile.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // Switching to Register screen
                Intent i = new Intent(getApplicationContext(), UserProfileActivity.class);
                startActivity(i);
            }
        });


        //Listening to register new account link
        btnRegister.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // Switching to Register screen
                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);
            }
        });
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




}
