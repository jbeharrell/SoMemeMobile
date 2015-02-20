package com.example.jon.someme.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.jon.someme.R;
import com.example.jon.someme.adapters.MemeListArrayAdapter;
import com.example.jon.someme.dataAccess.AsyncMemeListData;
import com.example.jon.someme.models.MemeListData;

/**
 * This is the MemeListActivity for the SoMeme application.
 *
 * This contains the content for the list of memes
 *
 * @author: Ian Mori
 * @since: 2015-02-13
 */
public class MemeListActivity extends ActionBarActivity {

    //Initial variables
    private MemeListData data;
    private ListView memeListView;
    private int currentUserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meme_list);
        //The user id passed from other activities or intents
        currentUserID = getIntent().getExtras().getInt("currentUserID");
        new AsyncMemeListData(this).execute();
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

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //Setting the memelist data
    public void setModel(final MemeListData data) {
        this.data = data;
        memeListView = (ListView) findViewById(R.id.memeList);
        MemeListArrayAdapter adapter = new MemeListArrayAdapter(this, data.getMemes());
        memeListView.setAdapter(adapter);

        //On click listener for the meme view list
        memeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent i = new Intent(getApplicationContext(), MemeViewActivity.class);
                i.putExtra("meme", data.getMemes().get((int) id).getId());
                i.putExtra("currentUserID", currentUserID);
                startActivity(i);
            }
        });
    }
}