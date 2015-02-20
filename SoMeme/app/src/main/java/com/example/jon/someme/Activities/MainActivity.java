package com.example.jon.someme.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.jon.someme.R;

/**
 * This is the MainActivity for the SoMeme application.
 *
 * @author: Ian Mori
 * @since: 2015-02-13
 */
public class MainActivity extends ActionBarActivity {

    //Creating initial objects and id variable
    private Button btnLogin, btnLogout, btnMemeList, btnProfile, btnRegister, btnFavorites, btnPlay;
    private int currentUserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogin = (Button) findViewById(R.id.link_to_login);
        btnRegister = (Button) findViewById(R.id.link_to_register);
        btnMemeList = (Button) findViewById(R.id.btnMemeList);
        btnProfile = (Button) findViewById(R.id.btnProfile);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        btnFavorites = (Button) findViewById(R.id.btnFavorites);
        btnPlay = (Button) findViewById(R.id.btnPlay);

        //Listening for the user logging in
        btnLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (currentUserID > 0) {
                    Toast.makeText(getBaseContext(), "You must logout before signing in again.", Toast.LENGTH_SHORT).show();
                } else {
                    // Switching to Register screen
                    Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                    // i.putExtra("currentUserID", currentUserID);
                    startActivity(i);
                }
            }
        });

        //Listening for logout
        btnLogout.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (currentUserID > 0) {
                    String where = "1=1";
                    int i = getContentResolver().delete(LoginProvider.CONTENT_URI, where, null);
                    currentUserID = 0;
                    Toast.makeText(getBaseContext(), "You have been logged out.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Listening for the memelist
        btnMemeList.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MemeListActivity.class);
                i.putExtra("currentUserID", currentUserID);
                startActivity(i);
            }
        });

        //Listening to register new account link
        btnFavorites.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                if (currentUserID > 0) {
                    // Switching to Favorites screen
                    Intent i = new Intent(getApplicationContext(), FavoriteListActivity.class);
                    i.putExtra("currentUserID", currentUserID);
                    Log.d("user id ", currentUserID + "");
                    startActivity(i);

                } else {
                    Toast.makeText(getBaseContext(), "You must be logged in first to view favorites.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Listening for profile click
        btnProfile.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (currentUserID > 0) {
                    Intent i = new Intent(getApplicationContext(), UserProfileActivity.class);
                    i.putExtra("currentUserID", currentUserID);
                    i.putExtra("profileUserID", currentUserID);
                    startActivity(i);
                } else {
                    Toast.makeText(getBaseContext(), "You must be logged in first to view your profile.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Listening to register new account
        btnRegister.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);
            }
        });

        //Listening for play button
        btnPlay.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), VideoActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Creating a cursor object, this is used to query the database with the LoginProvider content uri
        String projection[] = {LoginProvider.user_id};
        Cursor cur = getContentResolver().query(LoginProvider.CONTENT_URI, projection, null, null, LoginProvider.id + " DESC");

        //Move to the first record of the descending sorted list
        cur.moveToFirst();
        if (cur.getCount() >= 1) {
            //Getting the user id, this will be used throughout to manage content and pages
            String id = cur.getString(cur.getColumnIndex("user_id"));
            currentUserID = Integer.parseInt(id);
            getMenuInflater().inflate(R.menu.menu_main, menu);
            cur.close();
        } else {
            currentUserID = 0;
            getMenuInflater().inflate(R.menu.menu_main_nonuser, menu);
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        Intent i;

        //This switch will go through the overflow items, this is used twice throughout the project
        //but could be used on every activity
        switch (item.getItemId()) {
            case R.id.favorites:
                if (currentUserID > 0) {
                    i = new Intent(getApplicationContext(), FavoriteListActivity.class);
                    i.putExtra("currentUserID", currentUserID);
                    Log.d("user id ", currentUserID + "");
                    startActivity(i);
                } else {
                    Toast.makeText(getBaseContext(), "You must be logged in first to view favorites.", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.profile:
                if (currentUserID > 0) {
                    i = new Intent(getApplicationContext(), UserProfileActivity.class);
                    i.putExtra("currentUserID", currentUserID);
                    i.putExtra("profileUserID", currentUserID);
                    startActivity(i);
                } else {
                    Toast.makeText(getBaseContext(), "You must be logged in first to view your profile.", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.logout:
                if (currentUserID > 0) {
                    String where = "1=1";
                    int x = getContentResolver().delete(LoginProvider.CONTENT_URI, where, null);
                    currentUserID = 0;
                    Toast.makeText(getBaseContext(), "You have been logged out.", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.login:
                i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
                break;
            case R.id.register:
                i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);
                break;
            case R.id.viewMemes:
                i = new Intent(getApplicationContext(), MemeListActivity.class);
                i.putExtra("currentUserID", currentUserID);
                startActivity(i);
                break;
        }

        return true;
    }
}