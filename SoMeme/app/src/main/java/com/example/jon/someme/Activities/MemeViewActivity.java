package com.example.jon.someme.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jon.someme.R;
import com.example.jon.someme.dataAccess.AsyncMemeViewData;
import com.example.jon.someme.models.MemeViewData;

import java.io.InputStream;

public class MemeViewActivity extends ActionBarActivity {
    private MemeViewData data;
    private int currentMemeId;

    private TextView title;
    private ImageView imageView;
    private Button like;
    private Button dislike;
    private Button favorite;
    private Button download;
    private Button share;
    private EditText editText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meme_view);
        currentMemeId = getIntent().getExtras().getInt("meme");

        // show The Image
//        new DownloadImageTask((ImageView) findViewById(R.id.imageView))
//                .execute(url);
        share = (Button) findViewById(R.id.share);


//Listening to register new account link
        share.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://twitter.com/home?status=Check out this meme I made at Someme.me!\""));
                startActivity(browserIntent);
                // Switching to Register screen
                //Intent i = new Intent(getApplicationContext(), MemeViewActivity.class);
                //startActivity(i);
            }
        });

        new AsyncMemeViewData(this).execute(currentMemeId+"");
    }



    public void onClick(View v) {
//        startActivity(new Intent(this, IndexActivity.class));
  //      finish();

    }

private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;

    public DownloadImageTask(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
        bmImage.setImageBitmap(result);
    }
}



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_meme_view, menu);
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

    public void setModel(MemeViewData data){
        this.data = data;

        title = (TextView) findViewById(R.id.title);
        //imageView = (ImageView) findViewById(R.id.imageView);
        like = (Button) findViewById(R.id.like);
        dislike = (Button) findViewById(R.id.dislike);
        favorite = (Button) findViewById(R.id.favorite);
        download = (Button) findViewById(R.id.download);
        share = (Button) findViewById(R.id.share);
        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);

        title.setText(data.getTitle());
        //TODO populate data

        //TODO add onClick listeners to buttons to call proper Async classes (Need to make Async classes to send like/comment information to the server)

        // TODO populate comments with CommentsArrayAdaptor
    }
}
