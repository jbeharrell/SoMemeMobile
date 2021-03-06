package com.example.jon.someme.dataAccess;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jon on 2/13/2015.
 */
public class AsyncVote extends AsyncTask<String, Void, String> {
    private final String url = URLS.vote;
    private Activity activity;

    public AsyncVote(Activity activity){
        this.activity = activity;
    }
    public AsyncVote(){}

    protected String doInBackground(String... args) {
        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(url);

            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("user", "1"));
            params.add(new BasicNameValuePair("type", args[0]));
            params.add(new BasicNameValuePair("content", args[1]));
            params.add(new BasicNameValuePair("vote", args[2]));

            post.setEntity(new UrlEncodedFormEntity(params));
            HttpResponse response = client.execute(post);
            HttpEntity entity = response.getEntity();

            String responseString = EntityUtils.toString(entity);
            Log.i("jon", "Vote response: "+responseString);
            return responseString;

        } catch (ClientProtocolException e){
            e.printStackTrace();
            cancel(true);
        } catch (IOException e) {
            e.printStackTrace();
            cancel(true);
        }
        return null;
    }

    protected void onPostExecute(String response) {
    }
}