package com.tweetmiw.app.tweetmiw.activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tweetmiw.app.tweetmiw.R;
import com.tweetmiw.app.tweetmiw.utils.SessionManager;

import java.io.File;

import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;

/**
 * Created by katherin on 26/06/2015.
 */
public class WriteTweetActivity  extends AppCompatActivity {
    SessionManager session;
    int RESULT_LOAD_IMAGE = 0;
    StatusUpdate statusUpdate;
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
                TextView tv = (TextView) findViewById(R.id.tweet);
                String mensaje = tv.getText().toString();
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                Twitter twitter = session.getTwitter();
                statusUpdate = new StatusUpdate(mensaje);

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
    public  void seleccionarImagen(View v){
        Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, RESULT_LOAD_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage,filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            statusUpdate.setMedia(new File(picturePath));
           // ImageView imageView = (ImageView) findViewById(R.id.imgView);
            //imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }
    }
}
