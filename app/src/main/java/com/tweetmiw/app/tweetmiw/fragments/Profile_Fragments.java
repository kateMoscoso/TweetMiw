package com.tweetmiw.app.tweetmiw.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.tweetmiw.app.tweetmiw.R;
import com.tweetmiw.app.tweetmiw.adapters.Tweet_Adapter;
import com.tweetmiw.app.tweetmiw.entities.ProfileUser;
import com.tweetmiw.app.tweetmiw.entities.Tweet;
import com.tweetmiw.app.tweetmiw.entities.User;
import com.tweetmiw.app.tweetmiw.utils.ConstantsUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import twitter4j.Status;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Created by katherin on 04/06/2015.
 */
public class Profile_Fragments extends Fragment {

    String f;

    public Profile_Fragments() {
        // Required empty public constructor
    }

   /* public AmigosFragment(String f) {
        this.f = f;
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayList<Tweet> tweetArrayList = new ArrayList<Tweet>();
         twitter4j.User user = null;

        try {


            Properties prop = new Properties();
            ConfigurationBuilder builder = new ConfigurationBuilder();
            builder.setOAuthConsumerKey(ConstantsUtils.CONSUMER_KEY);
            builder.setOAuthConsumerSecret(ConstantsUtils.CONSUMER_SECRET);
            String token = prop.getProperty("oauth.accessToken");
            String secret = prop.getProperty("oauth.accessTokenSecret");
            Log.v("USer", token + " " + secret);
            AccessToken accessToken = new AccessToken(token, secret);
            twitter4j.Twitter twitter = new TwitterFactory(builder.build()).getInstance(accessToken);
            long userID = accessToken.getUserId();
            user = twitter.showUser(userID);
            String username = user.getName();

            List< Status> favoritesResources = twitter.getFavorites();
           // List<Status> statuses = twitter.getHomeTimeline();
            System.out.println("Showing home timeline.");
            for (Status status : favoritesResources) {
                System.out.println(status.getUser().getName() + ":" +
                        status.getText());
            }
            System.exit(0);
        } catch (twitter4j.TwitterException e) {
            //  Log.e()
            Log.e("ss", e.getMessage());
        }
        User usuario = new User();
        ProfileUser profileUser = new ProfileUser();
        if(user !=null) {
            profileUser.setName(user.getName());
        }
        profileUser.setScreen_name("@screen_name");
        usuario.setProfile(profileUser);
        Tweet tweet = new Tweet("esto es un tweet", usuario);
        tweetArrayList.add(tweet);

        Tweet tweet2 = new Tweet("esto es otro tweet", usuario);
        tweetArrayList.add(tweet2);

        Tweet tweet3 = new Tweet("esto es otro tweet", usuario);
        tweetArrayList.add(tweet3);
        Tweet tweet4 = new Tweet("esto es otro tweet", usuario);
        tweetArrayList.add(tweet4);
        Tweet tweet5 = new Tweet("esto es otro tweet", usuario);
        tweetArrayList.add(tweet5);

        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.my_recycler_view_tweet);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new Tweet_Adapter(R.layout.tweet_row, R.layout.header,tweetArrayList, usuario));
       // recyclerView.setAdapter(new Tweet_Adapter(tweetArrayList, R.layout.tweet_row));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());


    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        return super.onContextItemSelected(item);
    }

}
