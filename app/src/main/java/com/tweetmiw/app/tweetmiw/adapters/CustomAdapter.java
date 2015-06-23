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
    private Context context;
    private ArrayList<Tweet> tweets;// dataset
    private  int itemLayout; // la vista, los row

    private boolean is_list;
    private ArrayList<Tweet> data;
    private LayoutInflater inflater;

    public CustomAdapter(Context context, int viewResourceId, ArrayList<Tweet> tweets) {
        super(context, viewResourceId, tweets);
        this.context = context;
        this.tweets = tweets;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();

        Tweet tweet = tweets.get(position);



        if (convertView == null) {
          // convertView = inflater.inflate(R.layout.tweet_row, null);
            convertView = LayoutInflater.from(context).inflate(R.layout.tweet_row, parent, false);
            viewHolder.nombreUsuario = (TextView) convertView.findViewById(R.id.username);
            viewHolder.screenName = (TextView) convertView.findViewById(R.id.screen);
            viewHolder.mensajeTweet = (TextView) convertView.findViewById(R.id.tweets);
            viewHolder.mensajeTweet = (TextView) convertView.findViewById(R.id.hora);

            convertView.setTag(viewHolder);
        } else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder= (ViewHolder) convertView.getTag();
        viewHolder.nombreUsuario.setText(tweet.getUser().getProfile().getName());
        viewHolder.screenName.setText(tweet.getUser().getProfile().getScreen_name());
        viewHolder.mensajeTweet.setText(tweet.getMessage());
        viewHolder.hora.setText(tweet.getCreated_at());
        return convertView;
    }

    private static class ViewHolder {
        public TextView nombreUsuario;
        public TextView screenName;
        public TextView mensajeTweet;
        public TextView hora;

    }
}
