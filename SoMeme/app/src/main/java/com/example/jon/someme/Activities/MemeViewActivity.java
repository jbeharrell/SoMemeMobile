package com.example.jon.someme.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jon.someme.R;
import com.example.jon.someme.adapters.CommentsArrayAdapter;
import com.example.jon.someme.dataAccess.AsyncComment;
import com.example.jon.someme.dataAccess.AsyncMemeViewData;
import com.example.jon.someme.dataAccess.AsyncVote;
import com.example.jon.someme.dataAccess.URLS;
import com.example.jon.someme.models.MemeViewData;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the MemeViewActivity for the SoMeme application.
 * <p/>
 * This has the content for the individual meme views
 *
 * @author: Ian Mori
 * @since: 2015-02-12
 */
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
    private EditText commentText;
    private Button submitComment;
    private LinearLayout comments;
    private boolean isUpdateSuccessful;
    private JSONParser jsonParser = new JSONParser();
    private String isFavorited;
    private int currentUserID;

    //url to login
    //This will need to be changed to the local machine IP
    private static String url = "http://192.168.2.11:80/finalapp/data/updateFavorite.php";
    //    final private static String url  = URLS.updateFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meme_view);
        //Gathering passed "extras"
        currentMemeId = getIntent().getExtras().getInt("meme");
        isFavorited = getIntent().getExtras().getString("isFavorited");
        currentUserID = getIntent().getExtras().getInt("currentUserID");
        new AsyncMemeViewData(this).execute(new String[]{currentUserID + "", currentMemeId + ""});
    }

    //The following was modified by Ryan on 2/18/2015
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

        public DownloadImageTask() {

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
            imageView.setImageBitmap(result);
        }
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

    //Setting the data for the pages.
    public void setModel(final MemeViewData data) {
        this.data = data;

        title = (TextView) findViewById(R.id.title);
        imageView = (ImageView) findViewById(R.id.imageView);
        like = (Button) findViewById(R.id.like);
        dislike = (Button) findViewById(R.id.dislike);
        favorite = (Button) findViewById(R.id.favorite);
        download = (Button) findViewById(R.id.download);
        share = (Button) findViewById(R.id.share);
        commentText = (EditText) findViewById(R.id.commentText);
        submitComment = (Button) findViewById(R.id.submitComment);
        comments = (LinearLayout) findViewById(R.id.comments);

        title.setText(data.getTitle());

        //Listening for the Twitter share button
        share.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://twitter.com/home?status=Check out this meme I made at Someme.me!\""));
                startActivity(browserIntent);
            }
        });

        //Listener for download button
        download.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //Downloads the current picture to the local storage of the device
                //Not stored in the most accessible place
                imageView.buildDrawingCache();
                Bitmap bmap = imageView.getDrawingCache();
                saveToInternalSorage(bmap);
            }
        });

        //Listening for the favorite button
        favorite.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (currentUserID > 0)
                    new UpdateFavorite().execute();
                else
                    Toast.makeText(getBaseContext(), "You must be logged in first to view favorite a meme.", Toast.LENGTH_SHORT).show();
            }
        });

        new DownloadImageTask().execute(data.getSourceLink());
        final Activity activity = this;
        submitComment.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (commentText.getText().toString().trim().length() > 0) {
                    //If the user submits a comment, we try and store this in the MySQL db
                    if (currentUserID > 0) {
                        new AsyncComment(activity).execute(new String[]{currentUserID + "", currentMemeId + "", commentText.getText().toString().trim()});
                        commentText.setText("");
                        finish();
                        startActivity(getIntent());
                    } else {
                        Toast.makeText(getBaseContext(), "You must be logged in to comment.", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getBaseContext(), "Comments cannot be blank.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Listening for the like button
        like.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new AsyncVote(activity).execute(new String[]{"meme", currentMemeId + "", "1"});
            }
        });

        //Listening for the dislike button
        dislike.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new AsyncVote(activity).execute(new String[]{"meme", currentMemeId + "", "0"});
            }
        });

        //Adding comments to the page
        CommentsArrayAdapter adapter = new CommentsArrayAdapter(this, data.getComments());
        final int adapterCount = adapter.getCount();
        for (int i = 0; i < adapterCount; i++) {
            View item = adapter.getView(i, null, null);
            comments.addView(item);
        }
    }

    /**
     * Background Async Task for updating a favorite
     */
    class UpdateFavorite extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        /**
         * Creating data to send to the server
         */
        protected String doInBackground(String... args) {

            String memeID = Integer.toString(data.getId());

            //Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("meme", memeID));
            params.add(new BasicNameValuePair("user", currentUserID + ""));
            params.add(new BasicNameValuePair("current", isFavorited));

            //Getting the JSON Object
            //Sending POST parameters to the PHP page
            JSONObject json = jsonParser.makeHttpRequest(url, "POST", params);

            //Log response
            Log.d("Login", json.toString());

            //Check if the login was successful from the return value of the request
            try {
                int success = json.getInt(URLS.TAG_SUCCESS);
                if (success == 1)
                    isUpdateSuccessful = true;
                else
                    isUpdateSuccessful = false;

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog.
         */
        protected void onPostExecute(String file_url) {

            //Show an error message if there was an issue updating favorites.
            if (isUpdateSuccessful == false) {
                Toast.makeText(getApplicationContext(), "There was an error updating your favorites.", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "Your favorites were updated successfully.", Toast.LENGTH_LONG).show();
            }
        }
    }

    //Method for saving photo to internal storage
    private String saveToInternalSorage(Bitmap bitmapImage) {
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        //Path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("Memes", Context.MODE_PRIVATE);
        //Create imageDir
        File mypath = new File(directory, data.getTitle() + ".jpg");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            //Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return directory.getAbsolutePath();
    }
}
