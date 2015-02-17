package com.example.jon.someme.models;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Jon on 2/13/2015.
 */
public class OwnerUser {

    private int id;
    private String username;

    public OwnerUser(int id, String username) {
        this.id = id;
        this.username = username;
    }

    public OwnerUser(JSONObject json) throws JSONException {
        Log.i("jon", "owneruser json: "+json.toString());
        id = json.getInt("id");
        username = json.getString("username");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public JSONObject toJSON(){
        JSONObject json = new JSONObject();

        // TODO: create json logic

        return json;
    }
}
