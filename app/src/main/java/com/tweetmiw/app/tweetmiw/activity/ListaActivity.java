package com.tweetmiw.app.tweetmiw.activity;


import android.app.ListActivity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.tweetmiw.app.tweetmiw.R;
import com.tweetmiw.app.tweetmiw.adapters.UsersAdapter;
import com.tweetmiw.app.tweetmiw.entities.ProfileUser;
import com.tweetmiw.app.tweetmiw.utils.ConstantsUtils;
import com.tweetmiw.app.tweetmiw.utils.SessionManager;

import java.util.ArrayList;

import twitter4j.PagableResponseList;

/**
 * Created by katherin on 27/06/2015.
 */
public class ListaActivity extends ListActivity {
    private SessionManager sessionManager;
    private ArrayList<ProfileUser> usersList = new ArrayList<ProfileUser>();
    private String tipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sessionManager = new SessionManager(this);
        setContentView(R.layout.fragment_item_list);
        tipo = getIntent().getStringExtra("tipo");
        getLitsUsers();
        UsersAdapter adapter = new UsersAdapter(this, R.layout.users_row, usersList);

        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

    }

    public void getLitsUsers() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {

            twitter4j.Twitter twitter = sessionManager.getTwitter();

            PagableResponseList<twitter4j.User> users;
            String screename = twitter.getScreenName();
            if (ConstantsUtils.FOLLOWERS.equals(tipo)) {
                users = twitter.getFollowersList(screename, -1);
            } else  {
                users =  twitter.getFriendsList(screename, -1);
            }
            ProfileUser profileUser;
            for (twitter4j.User user : users) {
                profileUser = new ProfileUser();
                profileUser.setDescription(user.getDescription());
                profileUser.setName(user.getName());
                profileUser.setScreen_name(user.getScreenName());
                profileUser.setProfile_image_url(user.getProfileImageURL());
                usersList.add(profileUser);

                Log.v("Usuarios", user.getName() + " " + user.getScreenName());
            }
           /* do {
                followers = twitter.getFollowersList("screenName", cursor);
                for (User follower : followers) {
                    System.out.println(follower.getName() + " has " + follower.getFollowersCount() + " follower(s)");
                }
            } while ((cursor = followers.getNextCursor()) != 0);*/


        } catch ( twitter4j.TwitterException e) {
            //  Log.e()
            Log.e("ss", e.getMessage());
        }

    }
}


