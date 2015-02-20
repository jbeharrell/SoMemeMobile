package com.example.jon.someme.activities;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.jon.someme.R;


public class MainActivity extends ActionBarActivity {

    private Button btnLogin,btnLogout,btnMemeList,btnProfile,btnRegister,btnFavorites,btnPlay;
    private boolean isLoggedIn;
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

        //Listening to register new account link
        btnLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // Switching to Register screen
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
            }
        });

        //Listening to register new account link
//        btnMemeView.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View v) {
//                // Switching to Register screen
//                Intent i = new Intent(getApplicationContext(), MemeViewActivity.class);
//                i.putExtra("currentUserID", currentUserID);
//                startActivity(i);
//            }
//        });

        //Listening to register new account link
        btnMemeList.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // Switching to Register screen
                Intent i = new Intent(getApplicationContext(), MemeListActivity.class);
                i.putExtra("currentUserID", currentUserID);
                startActivity(i);
            }
        });

        //Listening to register new account link
        btnFavorites.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // Switching to Register screen
                Intent i = new Intent(getApplicationContext(), FavoriteListActivity.class);
                i.putExtra("currentUserID", currentUserID);

                Log.d("user id ", currentUserID+"");

                startActivity(i);
            }
        });

        //Listening to register new account link
        btnProfile.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // Switching to Register screen
                Intent i = new Intent(getApplicationContext(), UserProfileActivity.class);
                i.putExtra("currentUserID", currentUserID);
                i.putExtra("profileUserID", currentUserID);
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

        //  Listening to register new account link
        btnPlay.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // Switching to Register screen
                Intent i = new Intent(getApplicationContext(), VideoActivity.class);
                startActivity(i);
            }
        });


        //get the user id if there is one and set it in the intent

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.


//        Uri uri = getContentResolver().insert(LoginProvider.CONTENT_URI,);
String projection[] = {LoginProvider.user_id};

       // ContentValues values = new ContentValues();
//        values.put(LoginProvider.user_id, json.getString("id"));
        Cursor cur = getContentResolver().query(LoginProvider.CONTENT_URI,projection,null,null,LoginProvider.id + " DESC");

        //gold
        cur.moveToFirst();
        if(cur.getCount() >= 1){
            String id = cur.getString(cur.getColumnIndex("user_id"));
            currentUserID = Integer.parseInt(id);

            Log.d("user id", currentUserID+"");

            getMenuInflater().inflate(R.menu.menu_main, menu);
            cur.close();

        }else{
            currentUserID = 0;
            getMenuInflater().inflate(R.menu.menu_main_nonuser, menu);
        }
        //check the db, see if there is a record

        //check the user type here, if they are logged, give them the menu main
        //if the user is not logged in, give them the alternate menu

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        super.onOptionsItemSelected(item);
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }


        super.onOptionsItemSelected(item);

        Intent i;
        switch (item.getItemId()) {

            case R.id.favorites:
                i = new Intent(getApplicationContext(), FavoriteListActivity.class);
                startActivity(i);
                //Toast.makeText(getBaseContext(), "You selected favorites", Toast.LENGTH_SHORT).show();
                break;

            case R.id.profile:
                i = new Intent(getApplicationContext(), UserProfileActivity.class);
                startActivity(i);
                //Toast.makeText(getBaseContext(), "You selected profile", Toast.LENGTH_SHORT).show();
                break;

            case R.id.logout:
                //i = new Intent(getApplicationContext(), UserActivity.class);
                //startActivity(i);
                Toast.makeText(getBaseContext(), "You selected logout", Toast.LENGTH_SHORT).show();
                break;
            case R.id.login:
                //i = new Intent(getApplicationContext(), UserActivity.class);
                //startActivity(i);
                i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
                //Toast.makeText(getBaseContext(), "You selected login", Toast.LENGTH_SHORT).show();
                break;
            case R.id.register:
                //i = new Intent(getApplicationContext(), UserActivity.class);
                //startActivity(i);
//                Toast.makeText(getBaseContext(), "You selected register", Toast.LENGTH_SHORT).show();
                i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);
                break;
            case R.id.viewMemes:
                //i = new Intent(getApplicationContext(), UserActivity.class);
                //startActivity(i);
                //Toast.makeText(getBaseContext(), "You selected view memes", Toast.LENGTH_SHORT).show();
                i = new Intent(getApplicationContext(), MemeListActivity.class);
                startActivity(i);
                break;
        }

        return true;
    }
}