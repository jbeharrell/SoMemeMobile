package com.example.jon.someme.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.jon.someme.R;
import com.example.jon.someme.models.Comment;

import java.util.ArrayList;

/**
 * Created by Jon on 2/13/2015.
 */
public class CommentsArrayAdapter extends ArrayAdapter<Comment> {
    private Context context;
    private ArrayList<Comment> comments;

    public CommentsArrayAdapter(Context context, ArrayList<Comment> comments) {
        super(context, R.layout.list_item_comment, comments);
        this.context = context;
        this.comments = comments;
    }

    @Override
    public View getView(int position, View row, ViewGroup parent) {
        if(row == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.list_item_comment, parent, false);
        }



        return row;
    }
}