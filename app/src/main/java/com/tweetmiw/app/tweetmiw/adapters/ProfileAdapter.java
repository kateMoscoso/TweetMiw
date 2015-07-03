package com.tweetmiw.app.tweetmiw.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.tweetmiw.app.tweetmiw.entities.TwitterUser;
import com.tweetmiw.app.tweetmiw.entities.Tweet;
import com.tweetmiw.app.tweetmiw.holders.ViewHolderTweet;
import com.tweetmiw.app.tweetmiw.holders.ViewHolderUser;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class ProfileAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int HEADER = 0;
    private static final int OTHER = 1;
    private ArrayList<Tweet> tweets;// dataset
    private int itemLayout; // la vista, los row
    private int header; //usuario
    private TwitterUser user;
    private static String TAG="Tweet_Aapter";

    public ProfileAdapter(int itemLayout, int header, ArrayList<Tweet> tweets, TwitterUser user) {
        this.tweets = tweets;
        this.itemLayout = itemLayout;
        this.header = header;
        this.user = user;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == HEADER) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(header, viewGroup, false);
            return new ViewHolderUser(v);
        } else if (i == OTHER) {
            View v = LayoutInflater.from(viewGroup.getContext()).
                    inflate(itemLayout, viewGroup, false); // false no heredo obtengo el contexto del fragment
            return new ViewHolderTweet(v);
        } else
            throw new RuntimeException("Could not inflate layout");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

       // Log.v("Tweet_Adapter", tweet.getUser().getProfile().getProfile_image_url());
        URL url ;
        if (viewHolder instanceof ViewHolderTweet) {
            Tweet tweet = tweets.get(position-1);

          /*  try {
                url = new URL(tweet.getTwitterUser().getProfile_image_url());
                Log.v("Tweet_Adapter", tweet.getTwitterUser().getProfile_image_url());
                Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                ((ViewHolderTweet) viewHolder).avatar.setImageBitmap(bmp);

            } catch (IOException e) {
                Log.e(tag, e.toString());
            }*/
            ((ViewHolderTweet) viewHolder).nombreUsuario.setText(tweet.getTwitterUser().getName());
            ((ViewHolderTweet) viewHolder).screenName.setText(tweet.getTwitterUser().getScreen_name());
            ((ViewHolderTweet) viewHolder).mensajeTweet.setText(tweet.getMessage());
            ((ViewHolderTweet) viewHolder).hora.setText(tweet.getCreated_at());

        }
        if (viewHolder instanceof ViewHolderUser) {
           // BitmapManager.getInstance().loadBitmap(user.getProfile().getProfile_image_url(), ((ViewHolderUser) viewHolder).avatar);
            try {
                url = new URL(user.getProfile_image_url());
                Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                ((ViewHolderUser) viewHolder).avatar.setImageBitmap(bmp);

            } catch (IOException e) {
                Log.e(TAG, e.toString());
            }
            ((ViewHolderUser) viewHolder).nombreUsuario.setText(user.getName());
            ((ViewHolderUser) viewHolder).screenName.setText(user.getScreen_name());
            ((ViewHolderUser) viewHolder).descripcion.setText(user.getDescription());

            ((ViewHolderUser) viewHolder).seguidores.setText(user.getFollowers_count());
            ((ViewHolderUser) viewHolder).siguiendo.setText(user.getFriends_count());
        }


        /*viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do somethingvi

                // setContentView(R.layout.activity_tweet_detail);
                Toast.makeText(v.getContext(), "Aqui definimos el onclick", Toast.LENGTH_SHORT).show();
            }
        });
        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //do something
                Toast.makeText(v.getContext(), "Aqui definimos el on long click", Toast.LENGTH_SHORT).show();
                return true;
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return tweets.size()+1;
    }
    @Override
    public int getItemViewType(int position) {
       // Log.v("getItemViewType position: " , ""+ position);
        if (position == HEADER)
            return HEADER;
        else
            return OTHER;
    }



}
