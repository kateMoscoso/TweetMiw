package com.tweetmiw.app.tweetmiw;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.tweetmiw.app.tweetmiw.activity.ListaActivity;
import com.tweetmiw.app.tweetmiw.activity.WriteTweetActivity;
import com.tweetmiw.app.tweetmiw.adapters.ViewPagerAdapter;
import com.tweetmiw.app.tweetmiw.utils.SessionManager;
import com.twitter.sdk.android.Twitter;
import com.tweetmiw.app.tweetmiw.utils.ConstantsUtils;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import java.util.ArrayList;


public class InitialActivity extends AppCompatActivity  {

    private ViewPager pager;
    private ViewPagerAdapter adapter;
    private SlidingTabLayout tabs;
    private ArrayList<String> dataset;

    private CharSequence Titles[] = new CharSequence[3];
    private int Numboftabs = 3;
    private static Twitter twitter;
    private TwitterAuthConfig authConfig;
    private SessionManager session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_initial);
        session = new SessionManager(getApplicationContext());
        Titles[0] = getString(R.string.tab1);
        Titles[1] = getString(R.string.tab2);
        Titles[2] = getString(R.string.tab3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_my_toolbar);
        setSupportActionBar(toolbar);//modifico el action Bar pordefecto de l

        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), Titles, Numboftabs);

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

        if (id == R.id.action_cerrar_sesion) {
            session.logoutUser();
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    public void lanzarEscribirTweet(View view) {
        Intent i = new Intent(this, WriteTweetActivity.class);
        startActivity(i);
    }


    /**
     * @param view
     */
    public void showFollowersList(View view) {
        Intent intent = new Intent();
        intent.setClass(this, ListaActivity.class);
        intent.putExtra("tipo", ConstantsUtils.FOLLOWERS);
        startActivity(intent);
    }

    /**
     * @param view
     */
    public void showFollowingList(View view) {

        Intent intent = new Intent();
        intent.setClass(this, ListaActivity.class);
        intent.putExtra("tipo", ConstantsUtils
                .FOLLOWING);
        startActivity(intent);
    }

}