package com.tweetmiw.app.tweetmiw;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.widget.ToggleButton;

import com.tweetmiw.app.tweetmiw.R;
import com.tweetmiw.app.tweetmiw.entities.ProfileUser;
import com.tweetmiw.app.tweetmiw.entities.Tweet;
import com.tweetmiw.app.tweetmiw.entities.User;

/**
 * Created by katherin on 13/06/2015.
 */
public class TweetDetailActivity extends FragmentActivity {
    Tweet tweet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_tweet_detail);

        Intent intent = getIntent();
        User usuario = new User();
        ProfileUser profileUser = new ProfileUser();
        profileUser.setName("Usuario");
        profileUser.setScreen_name("@screen_name");
        usuario.setProfile(profileUser);
        tweet = new Tweet("esto es un tweet", usuario);
       // room = new Room(intent.getStringExtra(ROOM_NUMBER), intent.getStringExtra(ROOM_TYPE));
      //  ToggleButton toggle_recommendation = (ToggleButton)findViewById(R.id.toggle_recommendation);
       // toggle_recommendation.setChecked(true);
/*
        int resource = -1;
        if (room.getRoomType().equals(Room.STANDARD_ROOM)) {
            resource = R.drawable.hotel1;
        } else {
            resource = R.drawable.hotel2;
        }

        ImageView img_header = (ImageView)findViewById(R.id.img_header);
        img_header.setImageResource(resource);
        setTitle(room.getRoomNumber());*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_initial, menu);
        return true;
    }

}
