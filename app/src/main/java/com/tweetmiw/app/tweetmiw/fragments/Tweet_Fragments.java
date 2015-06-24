package com.tweetmiw.app.tweetmiw.fragments;

//import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tweetmiw.app.tweetmiw.R;
import com.tweetmiw.app.tweetmiw.adapters.Tweet_Adapter;
import com.tweetmiw.app.tweetmiw.entities.ProfileUser;
import com.tweetmiw.app.tweetmiw.entities.Tweet;
import com.tweetmiw.app.tweetmiw.entities.User;
import com.tweetmiw.app.tweetmiw.utils.ConstantsUtils;
import com.tweetmiw.app.tweetmiw.utils.SessionManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Created by katherin on 02/06/2015.
 */
public class Tweet_Fragments extends Fragment {

    String f;
    private SessionManager session;
    private static SharedPreferences mSharedPreferences;

    public Tweet_Fragments() {
        // Required empty public constructor
    }

    /*public Tweet_Fragments(String token,String secret) {
        this.f = f;
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        session = new SessionManager(getActivity());
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        ArrayList<Tweet> tweetArrayList = new ArrayList<Tweet>();
        twitter4j.User user = null;
        try {


            twitter4j.Twitter twitter = session.getTwitter();
            long userID = twitter.getId();
            user = twitter.showUser(userID);
            User usuario = new User();
            ProfileUser profileUser = new ProfileUser();
            profileUser.setDescription(user.getDescription());
            profileUser.setName(user.getName());
            profileUser.setScreen_name(user.getScreenName());
            profileUser.setProfile_image_url(user.getProfileImageURL());
            usuario.setProfile(profileUser);
            //List< Status> favoritesResources = twitter.getFavorites();
            int pageno = 1;
            Paging page = new Paging(pageno++, 10);

            List<Status> statuses = twitter.getUserTimeline(userID);

            //twitter.getHomeTimeline()
            User usuarioAux = new User();
            ProfileUser profileUserAux = new ProfileUser();
            Tweet tweet;
            for (Status status : statuses) {
                profileUserAux.setName(status.getUser().getName());
                profileUserAux.setScreen_name(status.getUser().getScreenName());
                profileUser.setProfile_image_url(status.getUser().getProfileImageURL());
                Log.v("Tweet_fragments",status.getUser().getProfileImageURL());
                usuarioAux.setProfile(profileUserAux);
                tweet = new Tweet(status.getText(), usuarioAux);
                tweet.setCreated_at(""+status.getCreatedAt().getTime());
                tweetArrayList.add(tweet);
            }


            RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.my_recycler_view_tweet);
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(new Tweet_Adapter(R.layout.tweet_row, R.layout.header,tweetArrayList, usuario));
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setItemAnimator(new DefaultItemAnimator());
           // recyclerView.notifySubtreeAccessibilityStateChanged();
           // System.exit(0);
        } catch (twitter4j.TwitterException e) {
            //  Log.e()
            Log.e("ss", e.getMessage());
        }

    }

}
