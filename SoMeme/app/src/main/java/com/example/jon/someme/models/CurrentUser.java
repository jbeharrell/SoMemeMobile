package com.example.jon.someme.models;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Jon on 2/13/2015.
 */
public class CurrentUser {
    private boolean hasVoted;
    private boolean vote;
    private boolean favorited;

    public CurrentUser(boolean hasVoted, boolean vote, boolean favorited) {
        this.hasVoted = hasVoted;
        this.vote = vote;
        this.favorited = favorited;
    }

    public CurrentUser(boolean favorited){
        hasVoted = false;
        this.favorited = favorited;
    }

    public CurrentUser(boolean vote, boolean favorited){
        hasVoted = true;
        this.vote = vote;
        this.favorited = favorited;
    }

    public CurrentUser(JSONObject json) throws JSONException {
        Log.i("jon", "CurrentUser json: " + json.toString());

        if(json.get("vote").equals(null)){
            hasVoted = false;
        }else{
            hasVoted = true;
            if(json.getInt("vote") == 1) {
                vote = true;
            }else{
                vote = false;
            }
        }
        if(json.has("favorite")){ // Belongs to meme
            favorited = json.getBoolean("favorite");
        }
    }

    public boolean isHasVoted() {
        return hasVoted;
    }

    public void setHasVoted(boolean hasVoted) {
        this.hasVoted = hasVoted;
    }

    public boolean isVote() {
        return vote;
    }

    public void setVote(boolean vote) {
        this.vote = vote;
    }

    public boolean isFavorited() {
        return favorited;
    }

    public void setFavorited(boolean favorited) {
        this.favorited = favorited;
    }

    public JSONObject toJSON(){
        JSONObject json = new JSONObject();

        return json;
    }
}
