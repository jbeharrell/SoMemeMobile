package com.example.jon.someme.dataAccess;

import android.os.AsyncTask;
import android.util.Log;

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
public class AsyncMemeViewData extends AsyncTask<Void, Void, JSONObject> {
    private final String url = Constants.memeView;

    protected JSONObject doInBackground(Void... v) {
        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(url);

            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("user", "1"));
            params.add(new BasicNameValuePair("meme", "1"));
            params.add(new BasicNameValuePair("isView", "true"));

            post.setEntity(new UrlEncodedFormEntity(params));
            HttpResponse response = client.execute(post);
            HttpEntity entity = response.getEntity();

            String returnValue = EntityUtils.toString(entity);
            Log.i("jon", returnValue);

            return new JSONObject(returnValue);

        } catch (ClientProtocolException e){
            e.printStackTrace();
            cancel(true);
        } catch (IOException e) {
            e.printStackTrace();
            cancel(true);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new JSONObject();

    }

    protected void onPostExecute(JSONObject json) {
        try {
            Log.i("jon", "main json: " + json.toString());
            MemeViewData memeViewData = new MemeViewData(json);

            //  TODO: Render view

        }catch (JSONException e){
            Log.e("json", e.toString());
        }
    }

}