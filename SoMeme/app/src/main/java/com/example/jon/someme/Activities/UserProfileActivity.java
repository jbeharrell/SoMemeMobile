package com.example.jon.someme.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.jon.someme.R;
import com.example.jon.someme.dataAccess.AsyncComment;
import com.example.jon.someme.dataAccess.AsyncUserProfileData;
import com.example.jon.someme.models.UserProfileData;

/**
 * This is the UserProfileActivity for the SoMeme application.
 *
 * This page will provdide the necessary data for the user profile view
 *
 * @author: Ian Mori
 * @since: 2015-02-12
 */
public class UserProfileActivity extends ActionBarActivity {
    private UserProfileData data;
    private Button btnFavorites;
    private TextView username, joinDate, name, email, dob, gender, country;
    private int currentUserID;
    private int profileUserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        currentUserID = getIntent().getExtras().getInt("currentUserID");
        profileUserID = getIntent().getExtras().getInt("profileUserID");
        Log.d("userprofile id", profileUserID + "");
        new AsyncUserProfileData(this).execute(new String[]{currentUserID + "", profileUserID + ""});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (currentUserID > 0)
            getMenuInflater().inflate(R.menu.menu_main, menu);
        else
            getMenuInflater().inflate(R.menu.menu_main_nonuser, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setModel(UserProfileData data) {
        this.data = data;

        btnFavorites = (Button) findViewById(R.id.btnFavorites);
        country = (TextView) findViewById(R.id.txtCountry);
        username = (TextView) findViewById(R.id.txtUsername);
        name = (TextView) findViewById(R.id.txtName);
        email = (TextView) findViewById(R.id.txtEmail);
        dob = (TextView) findViewById(R.id.txtDOB);
        joinDate = (TextView) findViewById(R.id.txtJoinDate);
        gender = (TextView) findViewById(R.id.txtGender);

        String gen = data.getGender();
        if (gen.startsWith("M"))
            gender.setText("Male");
        else
            gender.setText("Female");

        country.setText(data.getCountry());
        username.setText(data.getUsername());
        name.setText(data.getFirstName());
        email.setText(data.getEmail());
        dob.setText(data.getDob());
        joinDate.setText(data.getDateJoined());

        //Listener for favorites button
        btnFavorites.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), FavoriteListActivity.class);
                i.putExtra("currentUserID", currentUserID);
                startActivity(i);
            }
        });
    }
}
