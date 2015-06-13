package com.tweetmiw.app.tweetmiw.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.tweetmiw.app.tweetmiw.R;
import com.tweetmiw.app.tweetmiw.entities.Tweet;
import com.tweetmiw.app.tweetmiw.fragments.Profile_Fragments;

import java.util.ArrayList;

/**
 * Created by katherin on 02/06/2015.
 */
public class Tweet_Adapter  extends RecyclerView.Adapter<Tweet_Adapter.ViewHolder>{

    private ArrayList<Tweet> tweets;// dataset
    private  int itemLayout; // la vista, los row

    public  Tweet_Adapter( ArrayList<Tweet>  tweets, int itemLayout){
        this.tweets = tweets;
        this.itemLayout = itemLayout;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).
                inflate(itemLayout, viewGroup, false); // false no heredo obtengo el contexto del fragment
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        Tweet tweet = tweets.get(i);
        viewHolder.screenName.setText(tweet.getUser().getProfile().getScreen_name());
        viewHolder.nombreUsuario.setText(tweet.getUser().getProfile().getName());
        viewHolder.mensajeTweet.setText(tweet.getMessage());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do somethingvi

               // setContentView(R.layout.actvity_tweet_detail);
                Toast.makeText(v.getContext(), "Aqui definimos el onclick" , Toast.LENGTH_SHORT).show();
            }
        });
        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //do something
                Toast.makeText(v.getContext(), "Aqui definimos el on long click", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }

    /**
     * Clase que define cada elemento
     */
    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView nombreUsuario;
        public TextView screenName;
        public TextView mensajeTweet;

        public ViewHolder(View itemView) {
            super(itemView);

            screenName = (TextView) itemView.findViewById(R.id.screenName);
            nombreUsuario = (TextView) itemView.findViewById(R.id.hora);
            mensajeTweet = (TextView) itemView.findViewById(R.id.tweets);


        }
        @Override
        public void onClick(View view) {
            // Intent i = new Intent(ViewHolder.this, MainActivity.class);
            //   Intent i = Users_Adapter.this.getIntent(v.getContext(), mCrime);
            //   startActivity(i);
            int position  = ViewHolder.super.getAdapterPosition();


            Toast.makeText(view.getContext(), "Aqui definimos el onclick nuevo " + position, Toast.LENGTH_SHORT).show();
        }
    }
}
