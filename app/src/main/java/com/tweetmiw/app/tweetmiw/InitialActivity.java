package com.tweetmiw.app.tweetmiw;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Outline;
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
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.tweetmiw.app.tweetmiw.activity.WriteTweetActivity;
import com.tweetmiw.app.tweetmiw.adapters.ViewPagerAdapter;
import com.tweetmiw.app.tweetmiw.utils.ConstantsUtils;
import com.tweetmiw.app.tweetmiw.utils.SessionManager;
import com.twitter.sdk.android.Twitter;

import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;

import java.util.ArrayList;

import io.fabric.sdk.android.Fabric;


public class InitialActivity extends AppCompatActivity {

    ViewPager pager;
    ViewPagerAdapter adapter;
    SlidingTabLayout tabs;
    ArrayList<String> dataset;
    CharSequence Titles[]={"Timeline","Followers","Mi Perfil"};
    int Numboftabs =3;
    private static Twitter twitter;
    TwitterAuthConfig authConfig;
    // Session Manager Class
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_initial);
        session = new SessionManager(getApplicationContext());
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

        if(id == R.id.action_cerrar_sesion){
            session.logoutUser();
            Intent i = new Intent(this, MainActivity.class );
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
    public void lanzarEscribirTweet(View view){
        /*TwitterAuthConfig authConfig =  new TwitterAuthConfig("consumerKey", "consumerSecret");
        Fabric.with(this, new TwitterCore(authConfig), new TweetComposer());
        TweetComposer.Builder builder = new TweetComposer.Builder(this);
        builder.show();*/
        Intent i = new Intent(this, WriteTweetActivity.class);
        startActivity(i);
    }


    /**
     *
     * @param view
     */
    public void showFollowersList (View view){
        twitter4j.Twitter twitter = session.getTwitter();
        
        Toast.makeText(view.getContext(), "Mostrar Followers", Toast.LENGTH_SHORT).show();
    }

    /**
     *
     * @param view
     */
    public void showFollowingList (View view){
        Toast.makeText(view.getContext(), "Mostrar Following", Toast.LENGTH_SHORT).show();
    }

}