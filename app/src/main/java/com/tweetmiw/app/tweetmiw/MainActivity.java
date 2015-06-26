package com.tweetmiw.app.tweetmiw;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.tweetmiw.app.tweetmiw.utils.ConstantsUtils;
import com.tweetmiw.app.tweetmiw.utils.Properties;
import com.tweetmiw.app.tweetmiw.utils.SessionManager;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import io.fabric.sdk.android.Fabric;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;

public class MainActivity extends AppCompatActivity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private TwitterLoginButton loginButton;
    private TwitterSession session;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sessionManager = new SessionManager(getApplicationContext());
        if (sessionManager.isLoggedIn()) {
            lanzarLogeado();
        }

        TwitterAuthConfig authConfig = new TwitterAuthConfig(ConstantsUtils.CONSUMER_KEY, ConstantsUtils.CONSUMER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.activity_main);
        loginButton = (TwitterLoginButton)
                findViewById(R.id.login_button);
        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                session = Twitter.getSessionManager().getActiveSession();

            }

            @Override
            public void failure(TwitterException exception) {
                // Do something on failure
                Toast.makeText(getApplication(), getResources().getString(R.string.label_login_noAuth),
                        Toast.LENGTH_LONG).show();
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result to the login button.
        loginButton.onActivityResult(requestCode, resultCode,
                data);

        try {


            TwitterAuthToken authToken = session.getAuthToken();
            Properties.getInstance().setToken(authToken.token);
            Properties.getInstance().setSecret(authToken.secret);
            sessionManager.createLoginSession(authToken.token, authToken.secret);
            lanzarLogeado();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void lanzarLogeado() {
        Intent i = new Intent(this, InitialActivity.class);
        startActivity(i);
    }


    public void lista(View view) {

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
