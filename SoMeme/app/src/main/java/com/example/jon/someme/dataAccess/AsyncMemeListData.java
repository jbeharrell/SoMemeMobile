package com.example.jon.someme.dataAccess;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by Jon on 2/13/2015.
 */
public class AsyncMemeListData extends AsyncTask<Void, Void, JSONArray> {
    private final String url = "http://localhost/SoMeme/data/memeListData.php";

    protected JSONArray doInBackground(Void... v) {
        try {

            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost();
            post.setHeader("localhost", url);
            HttpResponse response = client.execute(post);
            HttpEntity entity = response.getEntity();

            return new JSONArray(EntityUtils.toString(entity));

        } catch (ClientProtocolException e){
            cancel(true);
        } catch (IOException e) {
            cancel(true);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new JSONArray();

    }

    protected void onPostExecute(JSONArray json) {

        try {
            Log.i("jon", ""+json.getJSONObject(0).getInt("id"));
        }catch (JSONException e) {
            e.printStackTrace();
        }
    }

}