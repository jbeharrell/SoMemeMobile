package com.example.jon.someme.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Jon on 2/13/2015.
 */
public class MemeListData {

    private ArrayList<ListMeme> memes;

    public MemeListData(ArrayList<ListMeme> memes) {
        this.memes = memes;
    }

    public MemeListData(JSONArray json) throws JSONException {
        Log.i("jon", "memeListData json: "+json.toString());
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

        // TODO: create json logic

        return json;
    }
}
