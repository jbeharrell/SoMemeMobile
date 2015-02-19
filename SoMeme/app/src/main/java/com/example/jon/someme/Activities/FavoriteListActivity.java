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
 * @author: Ian Mori
 * @since: 2015-02-12
 */
public class FavoriteListActivity extends ActionBarActivity {
    private FavoritesData data;
    private ListView favorites;
    private int currentUserID;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_meme_list);
//        memeListView = (ListView) findViewById(R.id.memeList);
//

//        // Adaptor
//    }


    ListView list;
    String[] web = {
            "Google Plus",
            "Twitter",
            "Windows",
            "Bing",
            "Itunes",
            "Wordpress",
            "Drupal"
    };
    ArrayList memes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_list);

        currentUserID = getIntent().getExtras().getInt("user_id");

Log.d("asd", currentUserID+"");
        new AsyncFavoritesData(this).execute(currentUserID+"");


//        memes = new ArrayList();
//        memes.add(new StringTest("http://i.imgur.com/Jzl5Xw7.png", "a"));
//        memes.add(new StringTest("http://i.imgur.com/7B6V5fg.png", "a"));
//        memes.add(new StringTest("http://i.imgur.com/FvNvgii.gif", "a"));
//        memes.add(new StringTest("http://i.imgur.com/nb4eyRe.jpg", "a"));
//        memes.add(new StringTest("http://i.imgur.com/yvzDW9A.jpg", "a"));


    }
    //new AsyncMemeListData(this).execute();
    // Adaptor

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

    public void setModel(final FavoritesData data) {
        this.data = data;
        favorites = (ListView) findViewById(R.id.favoriteList);
       // list = (ListView) findViewById(R.id.favoriteList);
        FavoritesArrayAdapter adapter = new FavoritesArrayAdapter(this, data.getMemes());
        favorites.setAdapter(adapter);

//        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                // Switching to Register screen
//                Intent i = new Intent(getApplicationContext(), FavoriteActivity.class);
//                //i.putExtra("key","value");
//                StringTest test = (StringTest) memes.get(position);
//                i.putExtra("url", test.getStr());
//                i.putExtra("title", test.getStr2());
//                startActivity(i);
////                    Toast.makeText(MemeListActivity.this, "You Clicked at " +web[+ position], Toast.LENGTH_SHORT).show();
//            }
//        });


        favorites.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent i = new Intent(getApplicationContext(), MemeViewActivity.class);
                i.putExtra("meme", data.getMemes().get((int)id).getId());
                i.putExtra("isFavorited", "true");
                i.putExtra("currentUserID", currentUserID);
                startActivity(i);
            }
        });
    }
}