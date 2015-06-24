package com.tweetmiw.app.tweetmiw.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tweetmiw.app.tweetmiw.R;
import com.tweetmiw.app.tweetmiw.adapters.Tweet_Adapter;
import com.tweetmiw.app.tweetmiw.adapters.Users_Adapter;
import com.tweetmiw.app.tweetmiw.entities.ProfileUser;
import com.tweetmiw.app.tweetmiw.entities.Tweet;
import com.tweetmiw.app.tweetmiw.entities.User;

import java.util.ArrayList;
import java.util.List;

import twitter4j.PagableResponseList;
import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.auth.AccessToken;


public class Usuarios_Fragment extends Fragment {
    public Usuarios_Fragment(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_usuarios, container, false);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        ArrayList<ProfileUser> userArrayList = new ArrayList<ProfileUser>();
        try {

            twitter4j.Twitter twitter = com.tweetmiw.app.tweetmiw.utils.Properties.getInstance().getTwitter();

            //List< Status> favoritesResources = twitter.getFavorites();
            int pageno = 1;
            Paging page = new Paging(pageno++, 10);
            PagableResponseList<twitter4j.User> users;
            String screename = twitter.getScreenName();
            users = twitter.getFollowersList(twitter.getScreenName(),-1);
            //List<Status> statuses;


            User usuario = new User();
            ProfileUser profileUser = new ProfileUser();
            for (twitter4j.User user : users) {
                profileUser.setDescription(user.getDescription());
                profileUser.setName(user.getName());
                profileUser.setScreen_name(user.getScreenName());
                profileUser.setProfile_image_url(user.getProfileImageURL());
                userArrayList.add(profileUser);
            }



        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.my_recycler_view_user);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new Users_Adapter(userArrayList, R.layout.users_row));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        } catch (twitter4j.TwitterException e) {
            //  Log.e()
            Log.e("ss", e.getMessage());
        }

    }

}
