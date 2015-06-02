package com.tweetmiw.app.tweetmiw;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Outline;
import android.os.Bundle;
import android.widget.ImageButton;

import com.tweetmiw.app.tweetmiw.fragments.Tweet_Fragments;

/**
 * Created by katherin on 02/06/2015.
 */
public class Timeline extends Activity implements ActionBar.TabListener {

    private Fragment[] fragments = new Fragment[]{

            new Tweet_Fragments(),
            new Tweet_Fragments(),
            new Tweet_Fragments()

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timeline);



        setTabs();

        FragmentManager manager = getFragmentManager();
        FragmentTransaction fragmentTransaction= manager.beginTransaction();

        for(Fragment fragment : fragments){
            fragmentTransaction.add(R.id.main_music,fragment).hide(fragment);
        }

        fragmentTransaction.show(fragments[0]).commit();

        int size = getResources().getDimensionPixelSize(R.dimen.fab_size);
     /*   Outline outline = new Outline();
        outline.setOval(0, 0, size, size);
      /*  ImageButton imageButton = (ImageButton) findViewById(R.id.fab_1);
        imageButton.setOutlin(outline);*/

    }

    private void setTabs(){
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        actionBar.addTab(actionBar.newTab().setText("Destacados").setTabListener(this));
        actionBar.addTab(actionBar.newTab().setText("Amigos").setTabListener(this));
        actionBar.addTab(actionBar.newTab().setText("Perfil").setTabListener(this));

    }


    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

        for(Fragment fragment : fragments){
            fragmentTransaction.hide(fragment);
        }

        fragmentTransaction.show(fragments[tab.getPosition()]);

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }
}
