package com.example.jon.someme.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.jon.someme.R;
import com.example.jon.someme.dataAccess.AsyncMemeListData;
import com.example.jon.someme.dataAccess.AsyncUserProfileData;
import com.example.jon.someme.models.MemeViewData;
import com.example.jon.someme.models.UserProfileData;

public class UserProfileActivity extends ActionBarActivity {
    UserProfileData data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        new AsyncUserProfileData(this).execute();
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
        // TODO: refresh activity
    }
}
