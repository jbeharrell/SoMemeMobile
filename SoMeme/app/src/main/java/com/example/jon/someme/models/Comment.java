package com.example.jon.someme.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Jon on 2/13/2015.
 */
public class Comment {
    private int id;
    private int memeId;
    private String timestamp;
    private String content;
    private OwnerUser owner;
    private Votes votes;
    private boolean isUsers;
    private CurrentUser currentUser;
    private ArrayList<Comment> children;

    public Comment(int id, int memeId, String timestamp, String content, OwnerUser owner, Votes votes, boolean isUsers, CurrentUser currentUser, ArrayList<Comment> children) {
        this.id = id;
        this.memeId = memeId;
        this.timestamp = timestamp;
        this.content = content;
        this.owner = owner;
        this.votes = votes;
        this.isUsers = isUsers;
        this.currentUser = currentUser;
        this.children = children;
    }

    public Comment(JSONObject json) throws JSONException {
        Log.i("jon", "Comment json: " + json.toString());

        id = json.getInt("id");
        memeId = json.getInt("meme_id");
        timestamp = json.getString("timestamp");
        content = json.getString("content");
        owner = new OwnerUser(json.getJSONObject("user"));
        votes = new Votes(json.getJSONObject("votes"));
        isUsers = json.getBoolean("isUsers");
        currentUser = new CurrentUser(json.getJSONObject("thisUser"));
        if(json.has("children")){
            JSONArray jsonChildren = json.getJSONArray("children");
            children = new ArrayList<Comment>();
            for (int i = 0; i < jsonChildren.length(); i++) {
                Comment comment = new Comment(jsonChildren.getJSONObject(i));
                children.add(comment);
            }
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMemeId() {
        return memeId;
    }

    public void setMemeId(int memeId) {
        this.memeId = memeId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public boolean isUsers() {
        return isUsers;
    }

    public void setUsers(boolean isUsers) {
        this.isUsers = isUsers;
    }

    public CurrentUser getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(CurrentUser currentUser) {
        this.currentUser = currentUser;
    }

    public ArrayList<Comment> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Comment> children) {
        this.children = children;
    }

    public JSONObject toJSON(){
        JSONObject json = new JSONObject();

        // TODO: create json logic

        return json;
    }
}
