package com.tweetmiw.app.tweetmiw.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tweetmiw.app.tweetmiw.R;

/**
 * Created by katherin on 23/06/2015.
 */
public class ViewHolderUser extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView nombreUsuario;
    public TextView screenName;
    public TextView descripcion;
    public TextView hora;
    public ImageView avatar;


    public ViewHolderUser(View itemView) {
        super(itemView);


        nombreUsuario = (TextView) itemView.findViewById(R.id.username);
        screenName = (TextView) itemView.findViewById(R.id.screenName);
        descripcion = (TextView) itemView.findViewById(R.id.descripcion);
        hora = (TextView) itemView.findViewById(R.id.hora);
        avatar = (ImageView) itemView.findViewById(R.id.avatar_usuario);


    }

    @Override
    public void onClick(View view) {

        int position = ViewHolderUser.super.getAdapterPosition();


        Toast.makeText(view.getContext(), "Aqui definimos el onclick nuevo " + position, Toast.LENGTH_SHORT).show();
    }
}

