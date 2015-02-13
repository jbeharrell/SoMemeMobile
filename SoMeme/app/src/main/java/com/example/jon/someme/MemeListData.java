package com.example.jon.someme;

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
