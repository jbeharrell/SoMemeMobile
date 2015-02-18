package com.example.jon.someme.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Jon on 2/13/2015.
 */
public class UserProfileData {
    private int id;
    private String username;
    private String dateJoined;
    private boolean isUsers;
    private ArrayList<ListMeme> memes;

    private String email;
    private String firstName;
    private String lastName;
    private String dob;
    private boolean gender;
    private String country;

    public UserProfileData(int id, String username, String dateJoined, boolean isUsers, ArrayList<ListMeme> memes) {
        this.id = id;
        this.username = username;
        this.dateJoined = dateJoined;
        this.isUsers = isUsers;
        this.memes = memes;
    }

    public UserProfileData(int id, String username, String dateJoined, String email, String firstName, String lastName, String dob, boolean gender, String country, boolean isUsers, ArrayList<ListMeme> memes) {
        this.id = id;
        this.username = username;
        this.dateJoined = dateJoined;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.gender = gender;
        this.country = country;
        this.isUsers = isUsers;
        this.memes = memes;
    }

    public UserProfileData(JSONObject json) throws JSONException{

        id = json.getInt("id");
        username = json.getString("username");
        dateJoined = json.getString("date_joined");
        isUsers = json.getBoolean("isUsers");
        JSONArray jsonMemes = json.getJSONArray("memes");
        memes = new ArrayList<ListMeme>();
        for (int i = 0; i < jsonMemes.length(); i++) {
            ListMeme meme = new ListMeme(jsonMemes.getJSONObject(i));
            memes.add(meme);
        }

        if(isUsers){
            if(!json.get("email").equals(null))
                email = json.getString("email");
            if(!json.get("first_name").equals(null))
                firstName = json.getString("first_name");
            if(!json.get("last_name").equals(null))
                lastName = json.getString("last_name");
            if(!json.get("dob").equals(null))
                dob = json.getString("dob");
            if(!json.get("gender").equals(null))
                gender = json.getBoolean("gender");
            if(!json.get("country").equals(null))
                country = json.getString("country");
        }
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

    public String getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(String dateJoined) {
        this.dateJoined = dateJoined;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isUsers() {
        return isUsers;
    }

    public void setUsers(boolean isUsers) {
        this.isUsers = isUsers;
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
