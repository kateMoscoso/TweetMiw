package com.tweetmiw.app.tweetmiw.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.tweetmiw.app.tweetmiw.fragments.Profile_Fragments;
import com.tweetmiw.app.tweetmiw.fragments.Tweet_Fragments;
import com.tweetmiw.app.tweetmiw.fragments.Usuarios_Fragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created


    // Build a Constructor and assign the passed Values to appropriate values in the class
    public ViewPagerAdapter(FragmentManager fm,CharSequence mTitles[], int mNumbOfTabsumb) {
        super(fm);

        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;

    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {

        if(position == 0) // if the position is 0 we are returning the First tab
        {

          //  Tab1 tab1 = new Tab1();
            return TweetListFragment.init(position);
        }
        if(position == 1) // if the position is 0 we are returning the First tab
        {
            Usuarios_Fragment uf = new Usuarios_Fragment();
            //  Tab1 tab1 = new Tab1();
            return uf;
        }
        if(position == 2) // if the position is 0 we are returning the First tab
        {
            Tweet_Fragments tf = new Tweet_Fragments();
            Profile_Fragments pf = new Profile_Fragments();
            //  Tab1 tab1 = new Tab1();
         //   return pf;
         //   ArrayListFragment ar = new ArrayListFragment();
            // Tab2 tab2 = new Tab2();
            return tf;
        }
        else             // As we are having 2 tabs if the position is now 0 it must be 1 so we are returning second tab
        {
            ArrayListFragment ar = new ArrayListFragment();
           // Tab2 tab2 = new Tab2();
            return TweetListFragment.init(position);
        }

    }

    // This method return the titles for the Tabs in the Tab Strip

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    // This method return the Number of tabs for the tabs Strip

    @Override
    public int getCount() {
        return NumbOfTabs;
    }
}