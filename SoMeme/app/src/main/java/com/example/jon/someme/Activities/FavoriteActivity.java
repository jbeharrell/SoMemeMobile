package com.example.jon.someme.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.jon.someme.R;
import com.example.jon.someme.adapters.FavoritesArrayAdapter;
import com.example.jon.someme.adapters.MemeListArrayAdapter;
import com.example.jon.someme.models.MemeListData;
import com.example.jon.someme.models.StringTest;

import java.util.ArrayList;


public class FavoriteActivity extends ActionBarActivity {
    private MemeListData data;
    private ListView memeListView;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_meme_list);
//        memeListView = (ListView) findViewById(R.id.memeList);
//
//        new AsyncMemeListData().execute();
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
        } ;
        ArrayList memes;


                //imageId("http://i.imgur.com/Jzl5Xw7.png");
//    = {
//                "http://i.imgur.com/Jzl5Xw7.png",
//                "http://i.imgur.com/Jzl5Xw7.png",
//                "http://i.imgur.com/Jzl5Xw7.png",
//                "http://i.imgur.com/Jzl5Xw7.png",
//                "http://i.imgur.com/Jzl5Xw7.png"
//        };





        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_favorite_list);
            list=(ListView)findViewById(R.id.favoriteList);

            memes = new ArrayList();
            memes.add(new StringTest("http://i.imgur.com/Jzl5Xw7.png","a"));
            memes.add(new StringTest("http://i.imgur.com/7B6V5fg.png","a"));
            memes.add(new StringTest("http://i.imgur.com/FvNvgii.gif","a"));
            memes.add(new StringTest("http://i.imgur.com/nb4eyRe.jpg","a"));
            memes.add(new StringTest("http://i.imgur.com/yvzDW9A.jpg","a"));

            FavoritesArrayAdapter adapter = new
                    FavoritesArrayAdapter(this, memes);

           // System.out.println(memes);


            list.setAdapter(adapter);

            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {

                            // Switching to Register screen
                            Intent i = new Intent(getApplicationContext(), FavoriteActivity.class);
                            //i.putExtra("key","value");
                            StringTest test = (StringTest)memes.get(position);
                            i.putExtra("url",test.getStr());
                            i.putExtra("title", test.getStr2());
                            startActivity(i);



//                    Toast.makeText(MemeListActivity.this, "You Clicked at " +web[+ position], Toast.LENGTH_SHORT).show();
                }
            });
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

    public void setModel(MemeListData data){
        this.data = data;
        // TODO: refresh activity
    }
}