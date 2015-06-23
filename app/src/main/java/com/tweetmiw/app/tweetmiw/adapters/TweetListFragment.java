package com.tweetmiw.app.tweetmiw.adapters;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.tweetmiw.app.tweetmiw.R;
import com.tweetmiw.app.tweetmiw.TweetDetailActivity;
import com.tweetmiw.app.tweetmiw.entities.ProfileUser;
import com.tweetmiw.app.tweetmiw.entities.Tweet;
import com.tweetmiw.app.tweetmiw.entities.User;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import twitter4j.Status;

/**
 * Created by katherin on 11/06/2015.
 */
public class TweetListFragment extends ListFragment {
    int fragNum;
    public ArrayList<Tweet> tweets = new ArrayList<Tweet>();
   // String arr[] = { "This is", "a Truiton", "Demo", "App", "For", "Showing",
     //       "FragmentStatePagerAdapter", "and ViewPager", "Implementation" };

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
        fragNum = getArguments() != null ? getArguments().getInt("val") : 1;

       /* User usuario = new User();
        ProfileUser profileUser = new ProfileUser();
        profileUser.setName("Usuario");
        profileUser.setScreen_name("@screen_name");
        usuario.setProfile(profileUser);
        Tweet tweet = new Tweet("esto es un tweet", usuario);
        tweetArrayList.add(tweet);
        Tweet tweet2 = new Tweet("esto es otro tweet", usuario);
        tweetArrayList.add(tweet2);
        Tweet tweet3 = new Tweet("esto es otro tweet", usuario);
        tweetArrayList.add(tweet3);
        Tweet tweet4 = new Tweet("esto es otro tweet", usuario);
        tweetArrayList.add(tweet4);
        Tweet tweet5 = new Tweet("esto es otro tweet", usuario);
        tweetArrayList.add(tweet5);*/
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
        new TweetsSearchTask().execute();
       /* View tv = layoutView.findViewById(R.id.text);
        ((TextView) tv).setText("Truiton Fragment #" + fragNum);*/


        // Supply val input as an argument.

        return layoutView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        CustomAdapter adapter = new CustomAdapter(getActivity(), 0,  tweets);
        setListAdapter(adapter);

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Log.i("Truiton FragmentList", "Item clicked: " + id);
        Tweet clicked_tweet = (Tweet) l.getItemAtPosition(position);
        Intent intent = new Intent (getActivity(), TweetDetailActivity.class);
      //  intent.putExtra(TweetDetailActivity.ROOM_TYPE, clicked_tweet.getUser().);
       // intent.putExtra(TweetDetailActivity.ROOM_NUMBER, clicked_tweet.getRoomNumber());

        startActivity(intent);
    }
    private class TweetsSearchTask extends AsyncTask<Object, Void, ArrayList<Tweet>> {

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage(getResources().getString(R.string.user_loading));
            progressDialog.show();
        }

        @Override
        protected ArrayList<Tweet> doInBackground(Object... param) {

            ArrayList<Tweet> tweets = new ArrayList<Tweet>();

            try {

                twitter4j.Twitter twitter = com.tweetmiw.app.tweetmiw.utils.Properties.getInstance().getTwitter();
                List<twitter4j.Status> statuses = twitter.getHomeTimeline();
                // String timeline = TwitterUtils.getTimelineForSearchTerm(ConstantsUtils.MEJORANDROID_TERM);
                Tweet tweet;
                User user = new User();
                ProfileUser profileUser = new ProfileUser();
                int i = 0;
                for (twitter4j.Status status : statuses) {
                    profileUser.setName(status.getUser().getName());
                    profileUser.setScreen_name(status.getUser().getScreenName());
                    user.setProfile(profileUser);

                    tweet = new Tweet(status.getText(), user);
                    tweets.add(i, tweet);
                    i++;
                }

            } catch (Exception e) {
                Log.e("error: ", Log.getStackTraceString(e));

            }
            return tweets;
        }

        @Override
        protected void onPostExecute(ArrayList<Tweet> tweets){
            progressDialog.dismiss();

            if (tweets.isEmpty()) {
                Toast.makeText(getActivity(), getResources().getString(R.string.label_tweets_not_found),
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), getResources().getString(R.string.label_tweets_downloaded),
                        Toast.LENGTH_SHORT).show();
            }
        }


    }
}
