package com.tweetmiw.app.tweetmiw.activity;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tweetmiw.app.tweetmiw.R;
import com.tweetmiw.app.tweetmiw.utils.SessionManager;
import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;

/**
 * Created by katherin on 26/06/2015.
 */
public class WriteTweetActivity  extends AppCompatActivity {
    SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet);
        session = new SessionManager(getApplication());
        Button publicar = (Button)findViewById(R.id.twittear);
        publicar.setOnClickListener(buttonClickListener);

    }

    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v){
            try {
                TextView tv = (TextView) findViewById(R.id.editText);
                String mensaje = tv.getText().toString();
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                Twitter twitter = session.getTwitter();
                StatusUpdate statusUpdate = new StatusUpdate(mensaje);
                Status status = twitter.updateStatus(statusUpdate);
                if(status.getCreatedAt().toString().length()>0){
                    Toast.makeText(getApplication(), getResources().getString(R.string.label_tweet_published),
                            Toast.LENGTH_LONG).show();
                    onBackPressed();
                }
                else{
                    Toast.makeText(getApplication(), getResources().getString(R.string.label_tweet_Nopublished),
                            Toast.LENGTH_LONG).show();
                }
            }
            catch (Exception e) {
                Log.e("error: ", Log.getStackTraceString(e));

            }
        }
    };
    public void cerrar(View v) {
        onBackPressed();
    }


}
