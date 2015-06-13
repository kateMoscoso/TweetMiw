package com.tweetmiw.app.tweetmiw.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.tweetmiw.app.tweetmiw.InitialActivity;
import com.tweetmiw.app.tweetmiw.MainActivity;
import com.tweetmiw.app.tweetmiw.R;
import com.tweetmiw.app.tweetmiw.entities.ProfileUser;
import com.tweetmiw.app.tweetmiw.entities.Tweet;
import com.tweetmiw.app.tweetmiw.fragments.Profile_Fragments;

import java.util.ArrayList;

/**
 * Created by katherin on 04/06/2015.
 */
public class Users_Adapter extends RecyclerView.Adapter<Users_Adapter.ViewHolder>{

    private ArrayList<ProfileUser> users;// dataset
    private  int itemLayout; // la vista, los row

    public  Users_Adapter( ArrayList<ProfileUser>  users, int itemLayout){
        this.users = users;
        this.itemLayout = itemLayout;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).
                inflate(itemLayout, viewGroup, false); // flase no heredo
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        ProfileUser user = users.get(i);


        viewHolder.screenName.setText(user.getScreen_name());
        viewHolder.nombreUsuario.setText(user.getName());
        viewHolder.descripcion.setText(user.getDescription());

    }

    @Override
    public int getItemCount() {
        return users.size();
    }




    /**
     * Clase que define cada elemento
     */
    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{


        public TextView nombreUsuario;
        public TextView screenName;
        public TextView descripcion;
        private AdapterView.OnItemClickListener onItemClickListener;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            screenName = (TextView) itemView.findViewById(R.id.screenName);
            nombreUsuario = (TextView) itemView.findViewById(R.id.name);
            descripcion = (TextView) itemView.findViewById(R.id.descripcion);


        }


        @Override
        public boolean onLongClick(View v) {
            //do something


            Toast.makeText(v.getContext(), "Aqui definimos el on long click", Toast.LENGTH_SHORT).show();
            return true;
        }
        @Override
        public void onClick(View view) {
          // Intent i = new Intent(ViewHolder.this, MainActivity.class);
         //   Intent i = Users_Adapter.this.getIntent(v.getContext(), mCrime);
         //   startActivity(i);
            int position  = ViewHolder.super.getAdapterPosition();




            Toast.makeText(view.getContext(), "Aqui definimos el onclick nuevo " + position, Toast.LENGTH_SHORT).show();
        }
    }
}
