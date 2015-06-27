package com.tweetmiw.app.tweetmiw.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tweetmiw.app.tweetmiw.R;
import com.tweetmiw.app.tweetmiw.entities.ProfileUser;

import com.tweetmiw.app.tweetmiw.holders.ViewHolderUser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class UsersAdapter extends ArrayAdapter<ProfileUser> {
    private Context context;
    private int itemLayout; // la vista, los row

    private boolean is_list;
    private ArrayList<ProfileUser> users;
    private LayoutInflater inflater;

    public UsersAdapter(Context context, int viewResourceId, ArrayList<ProfileUser> users) {
        super(context, viewResourceId, users);
        this.context = context;
        this.users = users;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.users_row, parent, false);
            viewHolder = new ViewHolder(convertView);



            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ProfileUser user = users.get(position);


        viewHolder.nombreUsuario.setText(user.getName());
        viewHolder.screenName.setText(user.getScreen_name());
        viewHolder.descripcion.setText(user.getDescription());
        URL url = null;
        try {
            url = new URL(user.getProfile_image_url());
            HttpURLConnection conn = null;
            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            viewHolder.avatar.setImageBitmap(bmp);

        } catch (IOException e) {
            e.printStackTrace();
        }

        convertView.setTag(viewHolder);


        return convertView;
    }

    /**
     * Clase que define cada elemento
     */
    public class ViewHolder extends RecyclerView.ViewHolder {


        public TextView nombreUsuario;
        public TextView screenName;
        public TextView descripcion;
        public ImageView avatar;
        private AdapterView.OnItemClickListener onItemClickListener;

        public ViewHolder(View itemView) {
            super(itemView);

            screenName = (TextView) itemView.findViewById(R.id.screenName);
            nombreUsuario = (TextView) itemView.findViewById(R.id.name);
            descripcion = (TextView) itemView.findViewById(R.id.descripcion);
            avatar = (ImageView) itemView.findViewById(R.id.avatar_usuario);
            //hora = (TextView) itemView.findViewById(R.id.)


        }

    }
}
