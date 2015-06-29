package com.tweetmiw.app.tweetmiw.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.tweetmiw.app.tweetmiw.R;

import com.tweetmiw.app.tweetmiw.entities.Tweet;
import com.tweetmiw.app.tweetmiw.holders.ViewHolderTweet;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

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
        ViewHolderTweet viewHolder = new ViewHolderTweet(parent);

        Tweet tweet = tweets.get(position);



        if (convertView == null) {
          // convertView = inflater.inflate(R.layout.tweet_row, null);
            convertView = LayoutInflater.from(context).inflate(R.layout.tweet_row, parent, false);
            viewHolder.nombreUsuario = (TextView) convertView.findViewById(R.id.username);
            viewHolder.screenName = (TextView) convertView.findViewById(R.id.screenName);
            viewHolder.mensajeTweet = (TextView) convertView.findViewById(R.id.tweets);
            viewHolder.hora = (TextView) convertView.findViewById(R.id.hora);

            convertView.setTag(viewHolder);
        } else {
            viewHolder= (ViewHolderTweet) convertView.getTag();
        }
        viewHolder= (ViewHolderTweet) convertView.getTag();
        URL url ;
        try {
            Log.v("Customdapter",tweet.getUser().getProfile().getProfile_image_url() );
            url = new URL(tweet.getUser().getProfile().getProfile_image_url());
           // Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            //viewHolder.avatar.setImageBitmap(bmp);

        } catch (IOException e) {
            e.printStackTrace();
        }
        viewHolder.nombreUsuario.setText(tweet.getUser().getProfile().getName());
        viewHolder.screenName.setText(tweet.getUser().getProfile().getScreen_name());
        viewHolder.mensajeTweet.setText(tweet.getMessage());
        viewHolder.hora.setText(tweet.getCreated_at());
        return convertView;
    }


}
