package com.hfad.tictactoe;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

public class autro_main extends AppCompatActivity {

    private TextView autor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autro_main);
        Toolbar toolbar = findViewById(R.id.toolbarek);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        autor = findViewById(R.id.autorek);

        String raz = "<b><font color='#C21024'>Autor: </font></b>";
        String dwa = "<br><br><b><font color='#C21024'>Instagram: </font></b>";

        autor.setText(Html.fromHtml( raz + "Mikołaj Kmieciak" + dwa + "kmiecivk"));
    }


    public void wykonaj(View v){


        new AlertDialog.Builder(this)
                .setTitle("WSKAZÓWKA:")
                .setMessage("Myśl nieszablonowo. \nSpróbuj zrobić tak, aby \"Kółko\" nie miało możliwości ruchu.")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();



        new AlertDialog.Builder(this)
                .setTitle("WAŻNE INFORMACJE")
                .setMessage("Tę grę da się wygrać. \nJeśli to zrobisz, napisz do mnie!")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();




    }

    public void dodatki(View view) {

        Intent intent = new Intent(this, Dodatki.class);
        startActivity(intent);

    }
}
