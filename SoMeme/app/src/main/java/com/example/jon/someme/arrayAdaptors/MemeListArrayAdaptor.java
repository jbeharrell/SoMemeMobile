package com.example.jon.someme.arrayAdaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.jon.someme.models.ListMeme;
import com.example.jon.someme.R;

import java.util.ArrayList;

/**
 * Created by Jon on 2/13/2015.
 */
public class MemeListArrayAdaptor extends ArrayAdapter<ListMeme> {
    private Context context;
    private ArrayList<ListMeme> memes;

    public MemeListArrayAdaptor(Context context, ArrayList<ListMeme> memes) {
        super(context, R.layout.meme_list_item, memes);
        this.context = context;
        this.memes = memes;
    }

    @Override
    public View getView(int position, View row, ViewGroup parent) {
        if(row == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.meme_list_item, parent, false);
        }

        TextView votePosView = (TextView) row.findViewById(R.id.vote_pos);
        TextView voteNegView = (TextView) row.findViewById(R.id.vote_neg);
        //ImageView imageView = (ImageView) row.findViewById(R.id.image);
        TextView usernameView = (TextView) row.findViewById(R.id.username);
        TextView timestampView = (TextView) row.findViewById(R.id.timestamp);

        votePosView.setText(memes.get(position).getVotes().getPositive());
        voteNegView.setText(memes.get(position).getVotes().getNegative());
        //imageView.setImageURI(memes.get(position).getSourceLink());
        usernameView.setText(memes.get(position).getOwner().getUsername());
        timestampView.setText(memes.get(position).getTimestamp());

        return row;
    }
}