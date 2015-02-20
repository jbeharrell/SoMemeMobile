package com.example.jon.someme.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jon.someme.R;
import com.example.jon.someme.adapters.FavoritesArrayAdapter;
import com.example.jon.someme.adapters.MemeListArrayAdapter;
import com.example.jon.someme.dataAccess.AsyncFavoritesData;
import com.example.jon.someme.models.FavoritesData;
import com.example.jon.someme.models.MemeListData;

import java.util.ArrayList;

/**
 * This is the FavoriteActivity for the SoMeme application.
 *
 * This activity contains the content for the favorites list view
 *
 * @author: Ian Mori
 * @since: 2015-02-12
 */
public class FavoriteListActivity extends ActionBarActivity {
    private FavoritesData data;
    private ListView favorites;
    private int currentUserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_list);
        currentUserID = getIntent().getExtras().getInt("currentUserID");
        new AsyncFavoritesData(this).execute(currentUserID + "");
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
        super.onOptionsItemSelected(item);
        Intent i;

        //These items make up the overflow, this is very long and repetitive, but would be the same
        //on every activity
        switch (item.getItemId()) {
            case R.id.favorites:
                //If the user if logged in.
                if (currentUserID > 0) {
                    // Switching to Favorites screen
                    i = new Intent(getApplicationContext(), FavoriteListActivity.class);
                    i.putExtra("currentUserID", currentUserID);
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
                    i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                    Toast.makeText(getBaseContext(), "You have been logged out.", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.login:
                i = new Intent(getApplicationContext(), LoginActivity.class);
                i.putExtra("currentUserID", currentUserID);
                startActivity(i);
                break;
            case R.id.register:
                i = new Intent(getApplicationContext(), RegisterActivity.class);
                i.putExtra("currentUserID", currentUserID);
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

    //Gathering all the data for the listview
    public void setModel(final FavoritesData data) {
        this.data = data;
        favorites = (ListView) findViewById(R.id.favoriteList);
        FavoritesArrayAdapter adapter = new FavoritesArrayAdapter(this, data.getMemes());
        favorites.setAdapter(adapter);

        //Setting onclick listener for the listview
        favorites.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent i = new Intent(getApplicationContext(), MemeViewActivity.class);
                i.putExtra("meme", data.getMemes().get((int) id).getId());
                i.putExtra("isFavorited", "true");
                i.putExtra("currentUserID", currentUserID);
                startActivity(i);
            }
        });
    }
}