package com.example.jon.someme.models;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Jon on 2/13/2015.
 */
public class ListMeme {
    private int id;
    private String title;
    private String sourceLink;
    private String timestamp;
    private int views;
    private OwnerUser owner;
    private Votes votes;

    public ListMeme(int id, String title, String source_link, String timestamp, int views, OwnerUser owner, Votes votes) {
        this.id = id;
        this.title = title;
        this.sourceLink = source_link;
        this.timestamp = timestamp;
        this.views = views;
        this.owner = owner;
        this.votes = votes;
    }

    public ListMeme(JSONObject json) throws JSONException {
        Log.i("jon", "listMeme json: "+json.toString());
        id = json.getInt("id");
        title = json.getString("title");
        sourceLink = json.getString("source_link");
        timestamp = json.getString("timestamp");
        views = json.getInt("views");
        owner = new OwnerUser(json.getJSONObject("user"));
        votes = new Votes(json.getJSONObject("votes"));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSourceLink() {
        return sourceLink;
    }

    public void setSourceLink(String sourceLink) {
        this.sourceLink = sourceLink;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public OwnerUser getOwner() {
        return owner;
    }

    public void setOwner(OwnerUser owner) {
        this.owner = owner;
    }

    public Votes getVotes() {
        return votes;
    }

    public void setVotes(Votes votes) {
        this.votes = votes;
    }

    public JSONObject toJSON(){
        JSONObject json = new JSONObject();

        return json;
    }
}
