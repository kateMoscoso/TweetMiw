package com.tweetmiw.app.tweetmiw.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tweetmiw.app.tweetmiw.R;

public class ViewHolderTweet  extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView nombreUsuario;
    public TextView screenName;
    public TextView mensajeTweet;
    public TextView hora;
    public ImageView avatar;


    public ViewHolderTweet(View itemView) {
        super(itemView);


        nombreUsuario = (TextView) itemView.findViewById(R.id.username);
        screenName = (TextView) itemView.findViewById(R.id.screenName);
        mensajeTweet = (TextView) itemView.findViewById(R.id.tweets);
        hora = (TextView) itemView.findViewById(R.id.hora);
        avatar = (ImageView) itemView.findViewById(R.id.avatar_usuario);


    }

    @Override
    public void onClick(View view) {

        int position = ViewHolderTweet.super.getAdapterPosition();


        Toast.makeText(view.getContext(), "Aqui definimos el onclick nuevo " + position, Toast.LENGTH_SHORT).show();
    }
}
