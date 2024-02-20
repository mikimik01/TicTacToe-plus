package com.hfad.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.Intent.FLAG_ACTIVITY_NO_ANIMATION;

public class scrollmain extends AppCompatActivity {

    public Button pros[] = new Button[10];
    public TextView wygryw, text10, text30;
    public ScrollView suwak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrollmain);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        text10 = findViewById(R.id.text10);
        text30 = findViewById(R.id.text30);
        String link = "<a href='miki01.cba.pl'> miki01.cba.pl </a>";
        String tekscik = "Byłbym wdzięczny gdybyś teraz napisał do mnie \n (Miki Kmieciak)\n na Facebooku i nie dzielił się z innymi w jaki sposób wygrałeś.\n Niech się jeszcze trochę pomęczą!";
        String tekscik2 = "Jeżeli masz chwilę wolnego czasu, \n to wpisz hasło:  \"Wygralem \" na stronie \""+link+"\"\n i zobacz co mogę Ci zaoferować! \n Miłego dnia! ;)";
        text10.setText(tekscik);
        text30.setText(Html.fromHtml(tekscik2, Html.FROM_HTML_MODE_COMPACT));

        suwak = findViewById(R.id.suwak);
        suwak.post(new Runnable() {
            public void run() {
                suwak.fullScroll(suwak.FOCUS_DOWN);
            }
        });

        wygryw = findViewById(R.id.wygryw);
        wygryw.setText(Html.fromHtml(powodzenia()));

        final Toast toast2 = Toast.makeText(this, "Wygrałeś!!!", Toast.LENGTH_LONG);
        Toast toast4 = Toast.makeText(this, "Gratulacje!!!", Toast.LENGTH_LONG);
        toast4.show();

        pros[1] = findViewById(R.id.pros1);
        pros[2] = findViewById(R.id.pros2);
        pros[3] = findViewById(R.id.pros3);
        pros[4] = findViewById(R.id.pros4);
        pros[5] = findViewById(R.id.pros5);
        pros[6] = findViewById(R.id.pros6);
        pros[7] = findViewById(R.id.pros7);
        pros[8] = findViewById(R.id.pros8);
        pros[9] = findViewById(R.id.pros9);

        for(int i = 1; i<=9; i++){
            pros[i].setTextSize(30);
            pros[i].setText("X");
            pros[i].setTextColor(getResources().getColor(R.color.niebieski));
            if(i!=5){
                pros[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        toast2.show();
                    }
                });
            }
        }

        pros[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suwak.post(new Runnable() {
                    public void run() {
                        suwak.fullScroll(suwak.FOCUS_DOWN);
                    }
                });
            }
        });

        pros[5].setText("O");
        pros[5].setTextColor(getResources().getColor(R.color.czerwony));

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.two:
                Toast toast = Toast.makeText(this, "Wygrałeś!!!", Toast.LENGTH_LONG);
                toast.show();
                return true;


            case R.id.informacja:

                Toast toast1 = Toast.makeText(this, "Wygrałeś!!!", Toast.LENGTH_LONG);
                toast1.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }


    public String powodzenia(){
        String g = "<b><font color='#fc0313'> G </font></b>";
        String r = "<b><font color='#000dff'> R </font></b>";
        String a = "<b><font color='#fc0313'> A </font></b>";
        String t = "<b><font color='#000dff'> T </font></b>";
        String u = "<b><font color='#fc0313'> U </font></b>";
        String l = "<b><font color='#000dff'> L </font></b>";
        String c = "<b><font color='#000dff'> C </font></b>";
        String j = "<b><font color='#fc0313'> J </font></b>";
        String e = "<b><font color='#000dff'> E </font></b>";
        String wyk = "<b><font color='#fc0313'> !!! </font></b>";

        String grat = g+r+a+t+u+l+a+c+j+e+wyk;
        return grat;
    }

    public void powrot(View v){
        Intent intent1 = new Intent(this, DelayedMessageService.class);
        intent1.putExtra(DelayedMessageService.EXTRA_MESSAGE, getResources().getString(R.string.nzn));
        startService(intent1);

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);



    }

}
