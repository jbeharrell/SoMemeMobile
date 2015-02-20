package com.example.jon.someme.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Jon on 2/13/2015.
 */
public class FavoritesData {

    private ArrayList<ListMeme> memes;

    public FavoritesData(ArrayList<ListMeme> memes) {
        this.memes = memes;
    }

    public FavoritesData(JSONArray json) throws JSONException {
        Log.i("jon", "favoritesListData json: "+json.toString());
        memes = new ArrayList<ListMeme>();
        for (int i = 0; i < json.length(); i++) {
            ListMeme meme = new ListMeme(json.getJSONObject(i));
            memes.add(meme);
        }
    }

    public ArrayList<ListMeme> getMemes() {
        return memes;
    }

    public void setMemes(ArrayList<ListMeme> memes) {
        this.memes = memes;
    }

    public JSONObject toJSON(){
        JSONObject json = new JSONObject();

        return json;
    }
}