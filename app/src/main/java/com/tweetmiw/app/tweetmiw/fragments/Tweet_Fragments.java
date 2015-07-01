package com.tweetmiw.app.tweetmiw.fragments;


import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tweetmiw.app.tweetmiw.R;
import com.tweetmiw.app.tweetmiw.adapters.Tweet_Adapter;
import com.tweetmiw.app.tweetmiw.entities.TwitterUser;
import com.tweetmiw.app.tweetmiw.entities.Tweet;
import com.tweetmiw.app.tweetmiw.utils.SessionManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import twitter4j.Paging;
import twitter4j.Status;

public class Tweet_Fragments extends Fragment {

    String f;
    private  static String TAG ="Tweet_Fragments";
    private SessionManager session;
    public Tweet_Fragments() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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
            TwitterUser twitterUser = new TwitterUser();
            twitterUser.setDescription(user.getDescription());
            twitterUser.setName(user.getName());
            twitterUser.setScreen_name(user.getScreenName());
            twitterUser.setProfile_image_url(user.getProfileImageURL());
            twitterUser.setFollowers_count( Integer.toString(user.getFollowersCount()));
            twitterUser.setFriends_count(Integer.toString(user.getFriendsCount()));
            //List< Status> favoritesResources = twitter.getFavorites();
            int pageno = 1;
            Paging page = new Paging(pageno++, 10);

            List<Status> statuses = twitter.getUserTimeline(userID);

            //twitter.getHomeTimeline()
            TwitterUser twitterUserAux;
            Tweet tweet;
            for (Status status : statuses) {
                twitterUserAux = new TwitterUser();
                twitterUserAux.setName(status.getUser().getName());
                twitterUserAux.setScreen_name(status.getUser().getScreenName());
                //twitterUserAux.setProfile_image_url(status.getUser().getProfileImageURL());
                tweet = new Tweet(status.getText(), twitterUserAux);
                Date date = status.getCreatedAt();
                tweet.setCreated_at(new SimpleDateFormat("dd/MM/yy - HH:mm").format(date));
                tweetArrayList.add(tweet);
            }
            RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.my_recycler_view_tweet);
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(new Tweet_Adapter(R.layout.tweet_row, R.layout.header,tweetArrayList, twitterUser));
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setItemAnimator(new DefaultItemAnimator());
        } catch (twitter4j.TwitterException e) {
            //  Log.e()
            Log.e(TAG, e.getMessage());
        }

    }


}
