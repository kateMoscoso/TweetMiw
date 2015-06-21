package com.tweetmiw.app.tweetmiw;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.StrictMode;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.tweetmiw.app.tweetmiw.adapters.ViewPagerAdapter;
import com.tweetmiw.app.tweetmiw.utils.ConstantsUtils;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.services.StatusesService;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;
import com.twitter.sdk.android.tweetui.CollectionTimeline;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.TweetUi;
import com.twitter.sdk.android.tweetui.TweetViewAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.FileHandler;

import io.fabric.sdk.android.Fabric;
import twitter4j.Status;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

public class InitialActivity extends AppCompatActivity {

    ViewPager pager;
    ViewPagerAdapter adapter;
    SlidingTabLayout tabs;
    ArrayList<String> dataset;
    CharSequence Titles[]={"Timeline","Followers","Mi Perfil"};
    int Numboftabs =3;
    private static Twitter twitter;
    TwitterAuthConfig authConfig;
 //   File myImageFile = new File("/path/to/");
   // Uri myImageUri = Uri.fromFile(myImageFile);
 private static final String URL = "https://api.twitter.com/1.1/statuses/user_timeline.json?screen_name=KaMl_smile&count=2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);
        Bundle extras = getIntent().getExtras();
        final String token = extras.getString("token");
        final String consumerSecret= extras.getString("secret");
        final TwitterAuthToken authToken =(TwitterAuthToken) extras.get("authToken");
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        authConfig =
                new TwitterAuthConfig(token,
                        consumerSecret);

       Fabric.with(this, new Twitter(authConfig));
     /*  me redirige a la pagina Fabric.with(this, new TweetComposer());
        TweetComposer.Builder builder = new TweetComposer.Builder(this)
                .text("Probando Fabric desde Android studio");

        builder.show();*/
        TwitterCore twitterCore = new TwitterCore(authConfig);
       TweetUi tweetUi = new TweetUi();
        Fabric.with(this, twitterCore, tweetUi);
        // Creating The Toolbar and setting it as the Toolbar for the activity
        TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient();
        ConfigurationBuilder builder = new ConfigurationBuilder();
        builder.setOAuthConsumerKey(authToken.token);
        builder.setOAuthConsumerSecret(authToken.secret);
        final UserTimeline userTimeline = new UserTimeline.Builder()
                .screenName("KaMl_smile")
                .build();
        final TweetTimelineListAdapter adaptertweet = new TweetTimelineListAdapter(this, userTimeline);

        TweetViewAdapter a1 = new TweetViewAdapter(this);
        System.out.println("DSD:" + userTimeline.toString() + "  "+  adaptertweet.getCount() + "  " );
        //AccessToken accessToken = twitter.getOAuthAccessToken(requestToken, verifier);
        //List<Tweet> tweetsList = adaptertweet.getTweets();
        final ProgressDialog progressDialog = ProgressDialog.show(this,"Espere por favor","estamos atendiendo su solocitud");

        //setListAdapter(adapter);

       Log.v("InitialActivity ","timeline "+ adaptertweet);
        //Log.v("InitialActivity ","timeline  sin metodo"+ adaptertweet .getItem(0));

// Can also use Twitter directly: Twitter.getApiClient()
        StatusesService statusesService = twitterApiClient.getStatusesService();
        statusesService.show(524971209851543553L, null, null, null, new Callback<Tweet>() {
            @Override
            public void success(Result<Tweet> result) {
                Result<Tweet> t  = result;

                Log.v("InitialActivity ","resultados response:  "+result.data.toString());
                Log.v("InitialActivity ","resultados response:  "+result.data.createdAt + " "+ result.data.text);
                Log.v("InitialActivity ","resultados tweets:  "+t.data.favorited);
                //Do something with result, which provides a Tweet inside of result.data
                //twitter4j.Twitter twitter = TwitterFactory.getSingleton();
               // twitter4j.Twitter twitter = new TwitterFactory().getInstance();

                try {
                    //twitter4j.Twitter twitter = TwitterFactory.getSingleton();
                    //twitter.setOAuthConsumer(ConstantsUtils.CONSUMER_KEY,ConstantsUtils.CONSUMER_SECRET);
                    //RequestToken requestToken = twitter.getOAuthRequestToken();


                    TwitterFactory factory = new TwitterFactory();
                    AccessToken accessToken = new AccessToken(token, consumerSecret);
                    twitter4j.Twitter twitter = factory.getInstance();
                  // twitter.setOAuthConsumer(ConstantsUtils.CONSUMER_KEY,ConstantsUtils.CONSUMER_SECRET);
                   // twitter.setOAuthAccessToken();

                    //twitter.setOAuthConsumerKe(ConstantsUtils.CONSUMER_KEY,ConstantsUtils.CONSUMER_SECRET);
                   /// twitter.setOAuthAccessToken(accessToken);
                   // twitter.
                    //User user = twitter.verifyCredentials();

                    Log.v("Initial",twitter.getScreenName());
                   /* List<Status> statuses = twitter.getHomeTimeline();
                    System.out.println("Showing home timeline.");
                    for (Status status : statuses) {
                        System.out.println(status.getUser().getName() + ":" +
                                status.getText());
                    }*/
                } catch (twitter4j.TwitterException e) {
                  //  Log.e()
                    Log.e("ss" ,e.getMessage());
                }

            }

            public void failure(TwitterException exception) {
                //Do something on failure
            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_my_toolbar);
        setSupportActionBar(toolbar);//modifico el action Bar pordefecto de l

        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        adapter =  new ViewPagerAdapter(getSupportFragmentManager(),Titles,Numboftabs);

        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        // Assiging the Sliding Tab Layout View
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.accent_color);
            }
        });

        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_initial, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(id == R.id.action_cerrar_sesion){
            Intent i = new Intent(this, MainActivity.class );
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
    public void login(View view) {
        Intent i = new Intent(this, MainActivity.class );
        startActivity(i);
    }
}