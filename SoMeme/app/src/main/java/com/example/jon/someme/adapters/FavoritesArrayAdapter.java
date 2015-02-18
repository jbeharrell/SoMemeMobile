package com.example.jon.someme.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jon.someme.R;
import com.example.jon.someme.models.StringTest;

import java.util.ArrayList;

/**
 * Created by Jon on 2/13/2015.
 */
public class FavoritesArrayAdapter extends ArrayAdapter<StringTest> {
    private Context context;
    private ArrayList<StringTest> memes;

    public FavoritesArrayAdapter(Context context, ArrayList<StringTest> memes) {
        super(context, R.layout.list_item_favorite, memes);
        this.context = context;
        this.memes = memes;
    }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = new LinearLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater vi = (LayoutInflater)getContext().getSystemService(inflater);
            convertView = vi.inflate(R.layout.list_item_favorite, parent, false);


            // Product object
            StringTest name = getItem(position);
            //
           TextView txtTitle = (TextView) convertView.findViewById(R.id.txt);
            txtTitle.setText(name.getStr2());

            // show image
            ImageView img = (ImageView)convertView.findViewById(R.id.img);

            // download image
            ImageDownloader imageDownloader = new ImageDownloader();
            imageDownloader.download(name.getStr(), img);

            return convertView;
        }
    }


//    @Override
//    public View getView(int position, View row, ViewGroup parent) {
//        if(row == null) {
//            LayoutInflater inflater = (LayoutInflater) context
//                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            row = inflater.inflate(R.layout.list_item_meme, parent, false);
//        }
//
//        TextView votePosView = (TextView) row.findViewById(R.id.vote_pos);
//        TextView voteNegView = (TextView) row.findViewById(R.id.vote_neg);
//        //ImageView imageView = (ImageView) row.findViewById(R.id.image);
//        TextView usernameView = (TextView) row.findViewById(R.id.username);
//        TextView timestampView = (TextView) row.findViewById(R.id.timestamp);
//
//        votePosView.setText(memes.get(position).getVotes().getPositive());
//        voteNegView.setText(memes.get(position).getVotes().getNegative());
//        //imageView.setImageURI(memes.get(position).getSourceLink());
//        usernameView.setText(memes.get(position).getOwner().getUsername());
//        timestampView.setText(memes.get(position).getTimestamp());
//
//        return row;
//    }
//}