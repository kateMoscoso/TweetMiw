package com.tweetmiw.app.tweetmiw;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.tweetmiw.app.tweetmiw.adapters.ViewPagerAdapter;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.services.StatusesService;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;
import com.twitter.sdk.android.tweetui.TweetUi;

import java.io.File;
import java.util.logging.FileHandler;

import io.fabric.sdk.android.Fabric;

public class InitialActivity extends AppCompatActivity {

    ViewPager pager;
    ViewPagerAdapter adapter;
    SlidingTabLayout tabs;
    CharSequence Titles[]={"Timeline","Followers","Mi Perfil"};
    int Numboftabs =3;
 //   File myImageFile = new File("/path/to/");
   // Uri myImageUri = Uri.fromFile(myImageFile);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);
        Bundle extras = getIntent().getExtras();
        String consumerKey = extras.getString("token");
        String consumerSecret= extras.getString("secret");
        TwitterAuthConfig authConfig =
                new TwitterAuthConfig(consumerKey,
                        consumerSecret);

       Fabric.with(this, new Twitter(authConfig));
     /*  me redirige a la página Fabric.with(this, new TweetComposer());
        TweetComposer.Builder builder = new TweetComposer.Builder(this)
                .text("Probando Fabric desde Android studio");

        builder.show();*/

        Fabric.with(this, new TwitterCore(authConfig), new TweetUi());

        // Creating The Toolbar and setting it as the Toolbar for the activity
        TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient();
// Can also use Twitter directly: Twitter.getApiClient()
        StatusesService statusesService = twitterApiClient.getStatusesService();
        statusesService.show(524971209851543553L, null, null, null, new Callback<Tweet>() {
            @Override
            public void success(Result<Tweet> result) {

                Log.v("InitialActivity ","resultados:  "+result.data.entities.toString());
                //Do something with result, which provides a Tweet inside of result.data
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