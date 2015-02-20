package com.example.jon.someme.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.jon.someme.R;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the VideoActivity for the SoMeme application.
 * <p/>
 * This was a work in progress
 *
 * @author: Ian Mori
 * @since: 2015-02-12
 */
public class VideoActivity extends ActionBarActivity {

    //Creating initial objects and primitive variable
    private Button play;
    private VideoView vv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        play = (Button) findViewById(R.id.play);
        vv = (VideoView) findViewById(R.id.VideoView);

        //Get actual YouTube video url and play in a VideoView
        play.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String path = "rtsp://r3---sn-p5qlsu7r.c.youtube.com/CiILENy73wIaGQk7k1isHGoZHRMYDSANFEgGUgZ2aWRlb3MM/0/0/0/video.3gp";
                Uri uri = Uri.parse(path);
                vv.setVideoURI(uri);
                vv.start();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_nonuser, menu);
        return true;
    }
}