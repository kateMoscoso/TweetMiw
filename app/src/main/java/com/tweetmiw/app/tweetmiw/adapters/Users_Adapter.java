package com.tweetmiw.app.tweetmiw.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tweetmiw.app.tweetmiw.R;
import com.tweetmiw.app.tweetmiw.entities.TwitterUser;
import com.tweetmiw.app.tweetmiw.utils.SessionManager;
import com.twitter.sdk.android.tweetui.UserTimeline;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import twitter4j.Twitter;

public class Users_Adapter extends RecyclerView.Adapter<Users_Adapter.ViewHolder> {

    private ArrayList<TwitterUser> users;// dataset
    private int itemLayout; // la vista, los row
    private SessionManager sessionManager;

    public Users_Adapter(ArrayList<TwitterUser> users, int itemLayout) {
        this.users = users;
        this.itemLayout = itemLayout;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).
                inflate(itemLayout, viewGroup, false); // flase no heredo
        sessionManager = new SessionManager(viewGroup.getContext());
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        final TwitterUser user = users.get(i);


        viewHolder.screenName.setText(user.getScreen_name());
        viewHolder.nombreUsuario.setText(user.getName());
        viewHolder.descripcion.setText(user.getDescription());
        URL url = null;
        try {
            url = new URL(user.getProfile_image_url());
            HttpURLConnection conn = null;
            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            viewHolder.avatar.setImageBitmap(bmp);

        } catch (IOException e) {
            Log.v("", "error cargando foto");
            // e.printStackTrace();
        }
        final Twitter twitter = sessionManager.getTwitter();
        viewHolder.follower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Log.v("", "" + user.getId_user());

                    twitter.createFriendship(user.getId_user());
                    viewHolder.follower.setImageResource(R.mipmap.ic_follow_activo_36dp);
                    //  finalViewHolder.retweet.setColorFilter(R.color.accent_color);
                    //   Toast.makeText()
                } catch (Exception e) {
                    Log.v("Error", "e.getMessage");
                }
            }

        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }


    /**
     * Clase que define cada elemento
     */
    public class ViewHolder extends RecyclerView.ViewHolder {


        public TextView nombreUsuario;
        public TextView screenName;
        public TextView descripcion;
        public ImageView avatar;
        public ImageView follower;
        private AdapterView.OnItemClickListener onItemClickListener;

        public ViewHolder(View itemView) {
            super(itemView);
            //itemView.setOnClickListener(this);

            screenName = (TextView) itemView.findViewById(R.id.screenName);
            nombreUsuario = (TextView) itemView.findViewById(R.id.name);
            descripcion = (TextView) itemView.findViewById(R.id.descripcion);
            avatar = (ImageView) itemView.findViewById(R.id.avatar_usuario);
            follower = (ImageView) itemView.findViewById(R.id.follow);

        }
    }
}


        /*@Override
        public boolean onLongClick(View v) {
            //do something


            Toast.makeText(v.getContext(), "Aqui definimos el on long click", Toast.LENGTH_SHORT).show();
            return true;
        }
        @Override
        public void onClick(View view) {
            int position  = ViewHolder.super.getAdapterPosition();
            String screename = users.get(position).getScreen_name();
            UserTimeline userTimeline = new UserTimeline.Builder().screenName(screename).build();
            Toast.makeText(view.getContext(), "Aqui definimos el onclick nuevo " + position, Toast.LENGTH_SHORT).show();
        }
    }*/
