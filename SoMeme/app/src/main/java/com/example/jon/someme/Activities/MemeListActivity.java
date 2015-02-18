package com.example.jon.someme.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.jon.someme.R;
import com.example.jon.someme.dataAccess.AsyncMemeListData;
import com.example.jon.someme.models.MemeListData;


public class MemeListActivity extends ActionBarActivity {
    private MemeListData data;
    private ListView memeListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meme_list);
        memeListView = (ListView) findViewById(R.id.memeList);

        new AsyncMemeListData(this).execute();
        // Adaptor
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

    public void setModel(MemeListData data){
        this.data = data;
        // TODO: refresh activity
    }
}
