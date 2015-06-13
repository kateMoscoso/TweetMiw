package com.tweetmiw.app.tweetmiw.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.tweetmiw.app.tweetmiw.R;

import com.tweetmiw.app.tweetmiw.entities.Tweet;

import java.util.ArrayList;

/**
 * Created by katherin on 11/06/2015.
 */
public class CustomAdapter extends ArrayAdapter<Tweet> {
    private ArrayList<Tweet> tweets;// dataset
    private  int itemLayout; // la vista, los row

    private boolean is_list;
    private ArrayList<Tweet> data;
    private LayoutInflater inflater;

    public CustomAdapter(Context context, ArrayList<Tweet> tweets, boolean is_list) {
        super(context, -1, tweets);
        this.tweets = tweets;
        this.is_list = is_list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();

        Tweet tweet = tweets.get(position);



        if (convertView == null) {
            convertView = inflater.inflate(R.layout.tweet_row, null);
            viewHolder.screenName = (TextView) convertView.findViewById(R.id.screenName);
            viewHolder.nombreUsuario = (TextView) convertView.findViewById(R.id.hora);
            viewHolder.mensajeTweet = (TextView) convertView.findViewById(R.id.tweets);

            convertView.setTag(viewHolder);
        } else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.screenName.setText(tweet.getUser().getProfile().getScreen_name());
        viewHolder.nombreUsuario.setText(tweet.getUser().getProfile().getName());
        viewHolder.mensajeTweet.setText(tweet.getMessage());
        return convertView;
    }

    private static class ViewHolder {
        public TextView nombreUsuario;
        public TextView screenName;
        public TextView mensajeTweet;

    }
}
