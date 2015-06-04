package com.tweetmiw.app.tweetmiw.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tweetmiw.app.tweetmiw.R;
import com.tweetmiw.app.tweetmiw.adapters.Tweet_Adapter;
import com.tweetmiw.app.tweetmiw.entities.ProfileUser;
import com.tweetmiw.app.tweetmiw.entities.Tweet;
import com.tweetmiw.app.tweetmiw.entities.User;

import java.util.ArrayList;

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

        User usuario = new User();
        ProfileUser profileUser = new ProfileUser();
        profileUser.setName("Usuario");
        profileUser.setScreen_name("@screen_name");
        usuario.setProfile(profileUser);
        Tweet tweet = new Tweet("esto es un tweet", usuario);
        tweetArrayList.add(tweet);

        Tweet tweet2 = new Tweet("esto es otro tweet", usuario);
        tweetArrayList.add(tweet2);

        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.my_recycler_view_profile);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new Tweet_Adapter(tweetArrayList, R.layout.tweet_row));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());


    }
}
