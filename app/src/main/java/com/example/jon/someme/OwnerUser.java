package com.example.jon.someme;

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
