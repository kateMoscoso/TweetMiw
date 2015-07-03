package com.tweetmiw.app.tweetmiw.fragments;

import android.app.ProgressDialog;
import android.os.AsyncTask;
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
import com.tweetmiw.app.tweetmiw.adapters.Users_Adapter;
import com.tweetmiw.app.tweetmiw.entities.TwitterUser;
import com.tweetmiw.app.tweetmiw.utils.SessionManager;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import twitter4j.PagableResponseList;


public class Usuarios_Fragment extends Fragment {
    private WeakReference<FollowersSearchTask> asyncTaskWeakRef;
    ArrayList<TwitterUser> followers = new ArrayList<TwitterUser>();
    private RecyclerView recyclerView;
    private Users_Adapter adapter;
    public Usuarios_Fragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setRetainInstance(true);
        startNewAsyncTask();
        return inflater.inflate(R.layout.fragment_usuarios, container, false);

    }
    private void startNewAsyncTask() {
        final FollowersSearchTask asyncTask = new FollowersSearchTask(this);

        this.asyncTaskWeakRef = new WeakReference<FollowersSearchTask>(asyncTask);

        asyncTask.execute();

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
       recyclerView = (RecyclerView) getActivity().findViewById(R.id.my_recycler_view_user);
        recyclerView.setHasFixedSize(true);
        //recyclerView.setAdapter(new Users_Adapter(followers, R.layout.users_row));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());


    }

    private class FollowersSearchTask extends AsyncTask<Object, Void, ArrayList<TwitterUser>> {
        SessionManager session;
        private WeakReference<Usuarios_Fragment> fragmentWeakRef;

        private FollowersSearchTask (Usuarios_Fragment fragment) {
            this.fragmentWeakRef = new WeakReference<Usuarios_Fragment>(fragment);
        }
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            session = new SessionManager(getActivity());
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage(getResources().getString(R.string.user_loading));
            progressDialog.show();
        }

        @Override
        protected ArrayList<TwitterUser> doInBackground(Object... param) {


            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            try {

                twitter4j.Twitter twitter = session.getTwitter();

                PagableResponseList<twitter4j.User> users;
                String screename = twitter.getScreenName();
                users = twitter.getFollowersList(screename,-1);

                TwitterUser twitterUser = new TwitterUser();
                for (twitter4j.User user : users) {
                    twitterUser = new TwitterUser();
                    twitterUser.setDescription(user.getDescription());
                    twitterUser.setName(user.getName());
                    twitterUser.setScreen_name(user.getScreenName());
                    twitterUser.setProfile_image_url(user.getProfileImageURL());
                    twitterUser.setIsFollower(true);
                    

                    twitterUser.setId_user(user.getId());
                    followers.add(twitterUser);
                    Log.v("Usuarios",user.getName() + " " + user.getScreenName() + " " + user.isFollowRequestSent());
                }
           /* do {
                followers = twitter.getFollowersList("screenName", cursor);
                for (User follower : followers) {
                    System.out.println(follower.getName() + " has " + follower.getFollowersCount() + " follower(s)");
                }
            } while ((cursor = followers.getNextCursor()) != 0);*/



            } catch (twitter4j.TwitterException e) {
                //  Log.e()
                Log.e("ss", e.getMessage());
            }
            return followers;
        }


        protected void onPostExecute(ArrayList<TwitterUser> followers){
            progressDialog.dismiss();
            super.onPostExecute(followers);


            if (followers.isEmpty()) {
                //Toast.makeText(getActivity(), getResources().getString(R.string.label_tweets_not_found),Toast.LENGTH_SHORT).show();

            } else {
             //   Toast.makeText(getActivity(), getResources().getString(R.string.label_tweets_downloaded), Toast.LENGTH_SHORT).show();
                recyclerView.setAdapter(new Users_Adapter(followers, R.layout.users_row));

            }
        }


    }

}
