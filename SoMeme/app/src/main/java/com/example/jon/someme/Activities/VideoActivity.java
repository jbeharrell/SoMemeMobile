package com.example.jon.someme.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.util.Log;
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

public class VideoActivity extends ActionBarActivity {

    // Progress Dialog
//    private ProgressDialog pDialog;
//
//    JSONParser jsonParser = new JSONParser();
//    EditText username;
//    EditText password;
//    Button login;
    Button play;
    VideoView vv;
    // url to create new product
   // private static String url_login = "http://192.168.2.11:80/finalapp/data/authenticate.php";

    // JSON Node names
    //private static final String TAG_SUCCESS = "success";

    @Override
    public void onCreate(Bundle savedInstanceState) {
   //     super.onCreate(savedInstanceState);
 //       setContentView(R.layout.add_product);

        super.onCreate(savedInstanceState);
        // setting default screen to login.xml
        setContentView(R.layout.activity_video);

        play = (Button) findViewById(R.id.play);
        vv=(VideoView)findViewById(R.id.VideoView);
        //register = (Button)findViewById(R.id.btnRegister);
//        TextView registerScreen = (TextView) findViewById(R.id.link_to_register);

        // Listening to register new account link
        // Edit Text


        //username = (EditText) findViewById(R.id.username);
        //password = (EditText) findViewById(R.id.password);
        //inputDesc = (TextView) findViewById(R.id.inputDesc);

        // Create button
//        Button btnCreateProduct = (Button) findViewById(R.id.login);
        play.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // creating new product in background thread
                String path = "rtsp://r3---sn-p5qlsu7r.c.youtube.com/CiILENy73wIaGQk7k1isHGoZHRMYDSANFEgGUgZ2aWRlb3MM/0/0/0/video.3gp";
                String path1 = "http://commonsware.com/misc/test2.3gp";



                Uri uri = Uri.parse(path);

                //VideoView video = (VideoView) findViewById(R.id.VideoView);
                vv.setVideoURI(uri);
                vv.start();
            }
        });

        // button click event

    }
    }
