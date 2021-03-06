package com.example.jon.someme.dataAccess;

import android.os.AsyncTask;
import android.util.Log;

import com.example.jon.someme.activities.FavoriteListActivity;
import com.example.jon.someme.models.FavoritesData;

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
import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the AsyncFavoritesData class for the SoMeme application.
 *
 * This will actually query the php page.
 *
 * @author: Ian Mori
 * @since: 2015-02-12
 */
public class AsyncFavoritesData extends AsyncTask<String, Void, FavoritesData> {
    //private final String url = URLS.updateFavorite;
    private final String url = "http://192.168.2.11:80/finalapp/data/getFavorites.php";
    private FavoriteListActivity activity;

    public AsyncFavoritesData(FavoriteListActivity activity){
        this.activity = activity;
    }

    protected FavoritesData doInBackground(String... args) {
        try {
            Log.d("asd", args[0]);
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(url);

            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("user_id", args[0]));

            post.setEntity(new UrlEncodedFormEntity(params));
            HttpResponse response = client.execute(post);
            HttpEntity entity = response.getEntity();

            JSONArray json = new JSONArray(EntityUtils.toString(entity));
            Log.i("jon", "main json: " + json.toString());

            FavoritesData data = new FavoritesData(json);
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

    protected void onPostExecute(FavoritesData data) {
            activity.setModel(data);
    }

}