package com.tweetmiw.app.tweetmiw.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tweetmiw.app.tweetmiw.R;

import com.tweetmiw.app.tweetmiw.entities.Tweet;
import com.tweetmiw.app.tweetmiw.holders.ViewHolderTweet;
import com.tweetmiw.app.tweetmiw.utils.SessionManager;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class TimelineAdapter extends ArrayAdapter<Tweet> {
    private Context context;
    private ArrayList<Tweet> tweets;// dataset
    private  int itemLayout; // la vista, los row

    private boolean is_list;
    private ArrayList<Tweet> data;
    private LayoutInflater inflater;
    private SessionManager sessionManager;

    public TimelineAdapter(Context context, int viewResourceId, ArrayList<Tweet> tweets) {
        super(context, viewResourceId, tweets);
        this.context = context;
        this.tweets = tweets;
        inflater = LayoutInflater.from(context);
        sessionManager = new SessionManager(getContext());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderTweet viewHolder = new ViewHolderTweet(parent);

        final Tweet tweet = tweets.get(position);
        final twitter4j.Twitter twitter = sessionManager.getTwitter();


        if (convertView == null) {
          // convertView = inflater.inflate(R.layout.tweet_row, null);
            convertView = LayoutInflater.from(context).inflate(R.layout.tweet_row, parent, false);
            viewHolder.nombreUsuario = (TextView) convertView.findViewById(R.id.username);
            viewHolder.screenName = (TextView) convertView.findViewById(R.id.screenName);
            viewHolder.mensajeTweet = (TextView) convertView.findViewById(R.id.tweets);
            viewHolder.hora = (TextView) convertView.findViewById(R.id.hora);
            viewHolder.retweet = (ImageView)convertView.findViewById(R.id.retweet);
            viewHolder.favorite = (ImageView)convertView.findViewById(R.id.favorito);

            convertView.setTag(viewHolder);
        } else {
            viewHolder= (ViewHolderTweet) convertView.getTag();
        }
        viewHolder= (ViewHolderTweet) convertView.getTag();
        URL url ;
        try {
            Log.v("Customdapter", tweet.getTwitterUser().getProfile_image_url());
            url = new URL(tweet.getTwitterUser().getProfile_image_url());
           // Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            //viewHolder.avatar.setImageBitmap(bmp);

        } catch (IOException e) {
            e.printStackTrace();
        }
        viewHolder.nombreUsuario.setText(tweet.getTwitterUser().getName());
        viewHolder.screenName.setText(tweet.getTwitterUser().getScreen_name());
        viewHolder.mensajeTweet.setText(tweet.getMessage());
        viewHolder.hora.setText(tweet.getCreated_at());
        final ViewHolderTweet finalViewHolder = viewHolder;
        final ViewHolderTweet finalViewHolder1 = viewHolder;
        viewHolder.retweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Log.v("",""+tweet.getId());

                    twitter.retweetStatus(tweet.getId());
                    finalViewHolder1.retweet.setImageResource(R.mipmap.ic_swap_horiz_activo_24dp);
                  //  finalViewHolder.retweet.setColorFilter(R.color.accent_color);
                 //   Toast.makeText()
                } catch (Exception e){
                    Log.v("Error", "e.getMessage");
                }
            }

        });
        final ViewHolderTweet finalViewHolder2 = viewHolder;
        viewHolder.favorite.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            Log.v("",""+tweet.getId());

                            twitter.createFavorite(tweet.getId());
                            finalViewHolder2.favorite.setImageResource(R.mipmap.ic_star_rate_activo_24dp);
                            //  finalViewHolder.retweet.setColorFilter(R.color.accent_color);
                            //   Toast.makeText()
                        } catch (Exception e){
                            Log.v("Error", "e.getMessage");
                        }
                    }

                });

        return convertView;
    }


}
