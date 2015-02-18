package com.example.jon.someme.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Jon on 2/13/2015.
 */
public class MemeViewData {
    private CurrentUser currentUser;
    private int id;
    private String title;
    private String content;
    private String sourceLink;
    private String timestamp;
    private int views;
    private int downloads;
    private int userViews;
    private OwnerUser owner;
    private Votes votes;
    private boolean isUsers;
    private ArrayList<Comment> comments;

    public MemeViewData(CurrentUser currentUser, int id, String title, String content, String sourceLink, String timestamp, int views, int downloads, int userViews, OwnerUser owner, Votes votes, boolean isUsers, ArrayList<Comment> comments) {
        this.currentUser = currentUser;
        this.id = id;
        this.title = title;
        this.content = content;
        this.sourceLink = sourceLink;
        this.timestamp = timestamp;
        this.views = views;
        this.downloads = downloads;
        this.userViews = userViews;
        this.owner = owner;
        this.votes = votes;
        this.isUsers = isUsers;
        this.comments = comments;
    }

    public MemeViewData(JSONObject json) throws JSONException {
        Log.i("jon", "MemeViewData json: " + json.toString());

        currentUser = new CurrentUser(json.getJSONObject("thisUser"));
        id = json.getInt("id");
        title = json.getString("title");
        content = json.getString("content");
        sourceLink = json.getString("source_link");
        timestamp = json.getString("timestamp");
        views = json.getInt("views");
        userViews = json.getInt("user_views");
        downloads = json.getInt("downloads");
        isUsers = json.getBoolean("isUsers");
        owner = new OwnerUser(json.getJSONObject("user"));
        votes = new Votes(json.getJSONObject("votes"));
        JSONArray jsonComments = json.getJSONArray("comments");
        comments = new ArrayList<Comment>();
        for (int i = 0; i < jsonComments.length(); i++) {
            Comment comment = new Comment(jsonComments.getJSONObject(i));
            comments.add(comment);
        }
    }

    public CurrentUser getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(CurrentUser currentUser) {
        this.currentUser = currentUser;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public int getDownloads() {
        return downloads;
    }

    public void setDownloads(int downloads) {
        this.downloads = downloads;
    }

    public int getUserViews() {
        return userViews;
    }

    public void setUserViews(int userViews) {
        this.userViews = userViews;
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

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public JSONObject toJSON(){
        JSONObject json = new JSONObject();

        // TODO: create json logic

        return json;
    }
}