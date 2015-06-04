package com.tweetmiw.app.tweetmiw;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Configurando que el Toolbar como ActionBar
        //Toolbar toolbar = (Toolbar) findViewById(R.id.activity_my_toolbar);
        //En este ejemplo, ocultamos el titulo de la aplicación, esto es opcional
       // setSupportActionBar(toolbar);

        //setToolbar();
    }

   /* public void setToolbar(){

        //Configurando que el Toolbar como ActionBar
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_my_toolbar);
        //En este ejemplo, ocultamos el titulo de la aplicación, esto es opcional
        //toolbar.setTitle("I am Pusheen");
        setSupportActionBar(toolbar);

    }*/

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
        Intent i = new Intent(this, Timeline.class );
        startActivity(i);
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
