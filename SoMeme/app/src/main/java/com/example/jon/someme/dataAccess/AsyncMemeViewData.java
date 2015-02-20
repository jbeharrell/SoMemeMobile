package com.example.jon.someme.dataAccess;

import android.os.AsyncTask;
import android.util.Log;

import com.example.jon.someme.activities.MemeViewActivity;
import com.example.jon.someme.models.MemeViewData;

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
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jon on 2/13/2015.
 */
public class AsyncMemeViewData extends AsyncTask<String, Void, MemeViewData> {
    //private final String url = URLS.memeView;
    private final String url = "http://192.168.2.11:80/finalapp/data/memeViewData.php";
    private MemeViewActivity activity;

    public AsyncMemeViewData(MemeViewActivity activity){
        this.activity = activity;
    }

    protected MemeViewData doInBackground(String... args) {
        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(url);

            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("user", args[0]));
            params.add(new BasicNameValuePair("meme", args[1]));
            params.add(new BasicNameValuePair("isView", "true"));

            post.setEntity(new UrlEncodedFormEntity(params));
            HttpResponse response = client.execute(post);
            HttpEntity entity = response.getEntity();

            JSONObject json = new JSONObject(EntityUtils.toString(entity));
            Log.i("jon", "main json: " + json.toString());
            MemeViewData data = new MemeViewData(json);

            return data;

        } catch (ClientProtocolException e){
            e.printStackTrace();
            cancel(true);
        } catch (IOException e) {
            e.printStackTrace();
            cancel(true);
        } catch (JSONException e) {
            Log.e("json", e.toString());
        }
        return null;
    }

    protected void onPostExecute(MemeViewData data) {
        activity.setModel(data);
    }
}