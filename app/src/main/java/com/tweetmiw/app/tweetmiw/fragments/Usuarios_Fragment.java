package com.tweetmiw.app.tweetmiw.fragments;

import android.app.Activity;
import android.net.Uri;
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
import com.tweetmiw.app.tweetmiw.adapters.Users_Adapter;
import com.tweetmiw.app.tweetmiw.entities.ProfileUser;
import com.tweetmiw.app.tweetmiw.entities.Tweet;
import com.tweetmiw.app.tweetmiw.entities.User;

import java.util.ArrayList;


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

        ArrayList<ProfileUser> userArrayList = new ArrayList<ProfileUser>();


        ProfileUser profileUser = new ProfileUser();
        profileUser.setName("Usuario");
        profileUser.setScreen_name("@screen_name");
        profileUser.setDescription("Descripcion del usuario");
        userArrayList.add(profileUser);


        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.my_recycler_view_user);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new Users_Adapter(userArrayList, R.layout.users_row));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());


    }

}
