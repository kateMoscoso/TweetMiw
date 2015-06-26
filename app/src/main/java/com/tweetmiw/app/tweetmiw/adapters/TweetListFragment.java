package com.tweetmiw.app.tweetmiw.adapters;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import com.tweetmiw.app.tweetmiw.R;
import com.tweetmiw.app.tweetmiw.activity.TweetDetailActivity;
import com.tweetmiw.app.tweetmiw.entities.ProfileUser;
import com.tweetmiw.app.tweetmiw.entities.Tweet;
import com.tweetmiw.app.tweetmiw.entities.User;
import com.tweetmiw.app.tweetmiw.utils.SessionManager;

import java.util.ArrayList;
import java.util.List;

public class TweetListFragment extends ListFragment {
    int fragNum;
    // Session Manager Class
    SessionManager session;
    public ArrayList<Tweet> tweets = new ArrayList<Tweet>();

    public static TweetListFragment init(int val) {
        TweetListFragment truitonList = new TweetListFragment();
        Bundle args = new Bundle();
        args.putInt("val", val);
        truitonList.setArguments(args);

        return truitonList;
    }


    /**
     * Retrieving this instance's number from its arguments.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        session = new SessionManager(getActivity());
        fragNum = getArguments() != null ? getArguments().getInt("val") : 1;

        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            twitter4j.Twitter twitter = session.getTwitter();
            List<twitter4j.Status> statuses = twitter.getHomeTimeline();
            // String timeline = TwitterUtils.getTimelineForSearchTerm(ConstantsUtils.MEJORANDROID_TERM);
            Tweet tweet;
            User user;
            ProfileUser profileUser;
            int i = 0;
            for (twitter4j.Status status : statuses) {
                user = new User();
                profileUser = new ProfileUser();
                profileUser.setName(status.getUser().getName());
                profileUser.setScreen_name(status.getUser().getScreenName());
                profileUser.setProfile_image_url(status.getUser().getProfileImageURL());
                profileUser.setCreatedAt(status.getCreatedAt().toString());
                user.setProfile(profileUser);

                tweet = new Tweet(status.getText(), user);
                tweets.add(i, tweet);
                i++;
            }

        } catch (Exception e) {
            Log.e("error: ", Log.getStackTraceString(e));

        }
    }

    public boolean pullToRefreshEnabled() {
        return true;
    }

    /**
     * The Fragment's UI is a simple text view showing its instance number and
     * an associated list.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layoutView = inflater.inflate(R.layout.fragment_pager_list,
                container, false);
       /* layoutView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        } );*/
        return layoutView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        CustomAdapter adapter = new CustomAdapter(getActivity(), 0, tweets);
        setListAdapter(adapter);

    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), TweetDetailActivity.class);
        intent.putExtra("position", position);
        startActivity(intent);
      /* TweetDetailFragment nextFrag = new TweetDetailFragment();
        this.getFragmentManager().beginTransaction()
                .replace(R.id.tweets, nextFrag, null)
                .addToBackStack(null)
                .commit();

        // Set the item as checked to be highlighted when in two-pane layout
        getListView().setItemChecked(position, true);
/*

        Tweet clicked_tweet = (Tweet) l.getItemAtPosition(position);
       Intent intent = new Intent (getActivity(), TweetDetailActivity.class);
       Tweet tweetIntent =  tweets.get(position);

        intent.putExtra("tweet", tweetIntent);
        startActivity(intent);*/
    }

}
