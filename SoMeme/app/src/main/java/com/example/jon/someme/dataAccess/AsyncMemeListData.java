package com.example.jon.someme.dataAccess;

import android.os.AsyncTask;
import android.util.Log;
import com.example.jon.someme.activities.MemeListActivity;
import com.example.jon.someme.models.MemeListData;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by Jon on 2/13/2015.
 */
public class AsyncMemeListData extends AsyncTask<Void, Void, MemeListData> {
    private final String url = URLS.memeList;
    private MemeListActivity activity;

    public AsyncMemeListData(MemeListActivity activity){
        this.activity = activity;
    }

    protected MemeListData doInBackground(Void... v) {
        try {

            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(url);
            HttpResponse response = client.execute(post);
            HttpEntity entity = response.getEntity();
            JSONArray json = new JSONArray(EntityUtils.toString(entity));

            Log.i("jon", "main json: "+json.toString());
            MemeListData data = new MemeListData(json);

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

    protected void onPostExecute(MemeListData data) {
            activity.setModel(data);
    }

}