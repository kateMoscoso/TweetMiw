package com.tweetmiw.app.tweetmiw;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import io.fabric.sdk.android.Fabric;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;


public class MainActivity extends AppCompatActivity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "UnWcjSGaKLsofmQuIB53T97bC";
    private static final String TWITTER_SECRET = "YY3Rf2PUb8aFuN3Dwhfdm7ggN7SQ2Rsu9gaY8wpZhtt9AUvsh6";
    private TwitterLoginButton loginButton;
    private TwitterSession session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
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

        // Pass the activity result to the login button.
        loginButton.onActivityResult(requestCode, resultCode,
                data);
        Log.v("main","on activity result " + session.getUserName());
        TwitterAuthToken authToken = session.getAuthToken();
        String token = authToken.token;
        String secret = authToken.secret;
        Intent i = new Intent(this, InitialActivity.class );
        i.putExtra("token",token);
        i.putExtra("secret",secret);
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
        Intent i = new Intent(this, InitialActivity.class );
        startActivity(i);
    }
    public void lista(View view) {
        Intent i = new Intent(this, InitialActivity.class );
        startActivity(i);
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
