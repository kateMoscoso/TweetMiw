package com.tweetmiw.app.tweetmiw;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.tweetmiw.app.tweetmiw.utils.ConstantsUtils;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import io.fabric.sdk.android.Fabric;
import twitter4j.Status;
import twitter4j.TwitterFactory;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;

import org.json.JSONArray;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;


public class MainActivity extends AppCompatActivity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
   // private static final String TWITTER_KEY = "UnWcjSGaKLsofmQuIB53T97bC";
    //private static final String TWITTER_SECRET = "YY3Rf2PUb8aFuN3Dwhfdm7ggN7SQ2Rsu9gaY8wpZhtt9AUvsh6";
    private TwitterLoginButton loginButton;
    private TwitterSession session;
    private static final String TOKEN_URL = "https://api.twitter.com/oauth2/token";
    Properties prop = new Properties();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prop.setProperty("oauth.consumerKey", ConstantsUtils.CONSUMER_KEY);
        prop.setProperty("oauth.consumerSecret", ConstantsUtils.CONSUMER_SECRET);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(ConstantsUtils.CONSUMER_KEY, ConstantsUtils.CONSUMER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.activity_main);
        loginButton = (TwitterLoginButton)
                findViewById(R.id.login_button);


        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                // Do something with result, which provides a
                // TwitterSession for making API calls
                session = Twitter.getSessionManager().getActiveSession();

                Log.v("main","login succes " + session.getUserName());



            }

            @Override
            public void failure(TwitterException exception) {
                // Do something on failure
                Log.v("main","login fail");
            }
        });

        //Configurando que el Toolbar como ActionBar
        //Toolbar toolbar = (Toolbar) findViewById(R.id.activity_my_toolbar);
        //En este ejemplo, ocultamos el titulo de la aplicación, esto es opcional
       // setSupportActionBar(toolbar);

        //setToolbar();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        File file = new File("twitter4j.properties");
        Properties prop = new Properties();
        InputStream is = null;
        OutputStream os = null;
        // Pass the activity result to the login button.
        loginButton.onActivityResult(requestCode, resultCode,
                data);

        TwitterAuthToken authToken = session.getAuthToken();
        String token = authToken.token;
        String secret = authToken.secret;
        final UserTimeline userTimeline = new UserTimeline.Builder()
                .screenName("KaMl_smile")
                .build();
        final TweetTimelineListAdapter adaptertweet = new TweetTimelineListAdapter(this, userTimeline);
        System.out.println("DSD:" + adaptertweet.getCount());
        Log.v("main","on activity result " + session.getUserName());
        Log.v("main","on activity result " + session.getUserId());
        Log.v("main","on activity result " + session.getId());
        Log.v("main","on activity result " + session.getUserId());
        prop.setProperty("oauth.accessToken", authToken.token);
        prop.setProperty("oauth.accessTokenSecret", authToken.secret);
        try {
            os = new FileOutputStream("twitter4j.properties");
            prop.store(os, "twitter4j.properties");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Intent i = new Intent(this, InitialActivity.class );
        i.putExtra("token",token);
        i.putExtra("secret",secret);
        i.putExtra("authToken",session.getAuthToken());
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

        return super.onOptionsItemSelected(item);
    }
    public void login(View view) {
        Intent i = new Intent(this, SegundaActividad.class );
        startActivity(i);
    }
    public void lista(View view) {
        //Intent i = new Intent(this, InitialActivity.class );
        //startActivity(i);
        final UserTimeline userTimeline = new UserTimeline.Builder()
                .screenName("fabric")
                .build();
        final TweetTimelineListAdapter adaptertweet = new TweetTimelineListAdapter(this, userTimeline);

       // setListAdapte(adapter);
    }



    public void onClick(View v){
        Toast.makeText(this, "Hola, estoy escuchando", Toast.LENGTH_LONG).show();
    }


    public void alertDialog(View view) {
        AlertDialog.Builder aviso = new AlertDialog.Builder(this);
        aviso.setMessage("Esta aplicación es un cliente de Twitter, por lo que se necesita estar registrado en Twitter para poder utilizarla. Esta aplicación no guarda ni tu usuario ni tu contraseña,\n" +
                "simplemente se autentica a través del prpio Twitter. Si todavía no tienes una cuenta de Twitter, haz click en el botón 'Regístrate en Twitter' y create una.");
        aviso.setTitle("¡Bienvenido!");
        aviso.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        aviso.create();
        aviso.show();
    }
}
