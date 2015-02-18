package com.example.jon.someme.models;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Jon on 2/13/2015.
 */
public class Votes {
    private int positive;
    private int negative;

    public Votes(int positive, int negative){
        this.positive = positive;
        this.negative = negative;
    }

    public Votes(JSONObject json) throws JSONException {
        Log.i("jon", "Votes json: "+json.toString());
        positive = json.getInt("pos");
        negative = json.getInt("neg");
    }

    public int getPositive() {
        return positive;
    }

    public void setPositive(int positive) {
        this.positive = positive;
    }

    public int getNegative() {
        return negative;
    }

    public void setNegative(int negative) {
        this.negative = negative;
    }

    public void vote(OwnerUser user, boolean isPositive){

        if(true){ // check if current user has voted
            if(isPositive){
                negative--;
            }else{
                positive--;
            }
        }
        if(isPositive){
            positive++;
        }else{
            negative++;
        }
    }

    public JSONObject toJSON(){
        JSONObject json = new JSONObject();

        return json;
    }
}
