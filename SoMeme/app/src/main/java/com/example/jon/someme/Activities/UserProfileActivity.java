package com.example.jon.someme.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.jon.someme.R;
import com.example.jon.someme.dataAccess.AsyncComment;
import com.example.jon.someme.dataAccess.AsyncUserProfileData;
import com.example.jon.someme.models.UserProfileData;

public class UserProfileActivity extends ActionBarActivity {
    UserProfileData data;
    Button btnFavorites;
    TextView username, joinDate, name, email, dob, gender, country;
    private int userID;
    String currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        userID = getIntent().getExtras().getInt("user_id");
        currentUser = getIntent().getExtras().getString("currentUser");
        new AsyncUserProfileData(this).execute(new String[]{userID+"", currentUser});
//        new AsyncUserProfileData(this).execute(userID);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user_profile, menu);
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

    public void setModel(UserProfileData data){
        this.data = data;


        btnFavorites = (Button)findViewById(R.id.btnFavorites);
        country = (TextView)findViewById(R.id.txtCountry);
        username = (TextView)findViewById(R.id.txtUsername);
        name = (TextView)findViewById(R.id.txtName);
        email = (TextView)findViewById(R.id.txtEmail);
        dob = (TextView)findViewById(R.id.txtDOB);
        joinDate = (TextView)findViewById(R.id.txtJoinDate);
        gender = (TextView)findViewById(R.id.txtGender);

        boolean gen = data.isGender();
        if (!gen)
            gender.setText("Male");
        else
            gender.setText("Female");

        country.setText(data.getCountry());
        username.setText(data.getUsername());
        name.setText(data.getFirstName());
        email.setText(data.getEmail());
        dob.setText(data.getDob());
        joinDate.setText(data.getDateJoined());

//        AsyncUserProfileData profileData = new AsyncUserProfileData(this);
//        //AsyncUserProfileData(UserProfileActivity activity)
//
//        profileData.
//        MemeListArrayAdapter adapter = new MemeListArrayAdapter(this, data.getMemes());
//        memeListView.setAdapter(adapter);


        btnFavorites.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // Switching to Register screen
                Intent i = new Intent(getApplicationContext(), FavoriteListActivity.class);
                i.putExtra("user_id", userID);
                startActivity(i);
            }
        });

        // TODO: refresh activity
    }
}
