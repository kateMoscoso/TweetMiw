package com.tweetmiw.app.tweetmiw.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import com.tweetmiw.app.tweetmiw.R;
import com.tweetmiw.app.tweetmiw.activity.TweetDetailActivity;
import com.tweetmiw.app.tweetmiw.adapters.CustomAdapter;
import com.tweetmiw.app.tweetmiw.entities.ProfileUser;
import com.tweetmiw.app.tweetmiw.entities.Tweet;
import com.tweetmiw.app.tweetmiw.entities.User;
import com.tweetmiw.app.tweetmiw.utils.SessionManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class TweetListFragment extends ListFragment {
   private int fragNum;
    // Session Manager Class
    private SessionManager session;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private View layoutView;
    public ArrayList<Tweet> tweets = new ArrayList<Tweet>();
    private RecyclerView mRecyclerView;

    /**
     *
     * @param val
     * @return
     */
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
        actualizarLista();
    }
    private void actualizarLista(){
        tweets.clear();
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            twitter4j.Twitter twitter = session.getTwitter();
            List<twitter4j.Status> statuses = twitter.getHomeTimeline();
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
                user.setProfile(profileUser);

                tweet = new Tweet(status.getText(), user);
                Date date = status.getCreatedAt();
                tweet.setCreated_at(new SimpleDateFormat("dd/MM/yy - HH:mm").format(date));
                tweets.add(i, tweet);
                i++;
            }

        } catch (Exception e) {
            Log.e("error: ", Log.getStackTraceString(e));

        }
    }
    private void setupAdapter() {
        actualizarLista();
        CustomAdapter adapter = new CustomAdapter(getActivity(), 0, tweets);
        setListAdapter(adapter);
    }


    /**
     * The Fragment's UI is a simple text view showing its instance number and
     * an associated list.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        layoutView = inflater.inflate(R.layout.fragment_pager_list,
                container, false);
        //= (SwipeRefreshLayout) getActivity().findViewById(R.id.timeline_swipe_refresh_layout);
         mSwipeRefreshLayout = (SwipeRefreshLayout)  layoutView.findViewById(R.id.timeline_swipe_refresh_layout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.accent_color, R.color.primary_dark_color, R.color.primary_color);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mSwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        new android.os.Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Log.v("","onRefresh");
                                setupAdapter();
                                mSwipeRefreshLayout.setRefreshing(false);
                            }
                        }, 2500);
                    }
                });
       // mSwipeRefreshLayout.setProgressViewEndTarget( new SwipeRefreshLayout.OnDragListener());
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
        Tweet t = tweets.get(position);
        Intent intent = new Intent();
        intent.setClass(getActivity(), TweetDetailActivity.class);
        intent.putExtra("name", t.getUser().getProfile().getName());
        intent.putExtra("screenName", t.getUser().getProfile().getScreen_name());
        intent.putExtra("position", position);
        startActivity(intent);
        /*Usuarios_Fragment nextFrag= new Usuarios_Fragment();
        this.getFragmentManager().beginTransaction()
                .replace(R.id.my_recycler_view_tweet, nextFrag,"22")
                .addToBackStack(null)
                .commit();*/
    }

}
