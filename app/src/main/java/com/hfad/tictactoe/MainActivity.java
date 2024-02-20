package com.hfad.tictactoe;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.provider.Telephony;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.Intent.FLAG_ACTIVITY_NO_ANIMATION;


public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener{

    public String[] p = new String[10];
    public Button[] b = new Button[10];
    public TextView wygr;
    public Switch aSwitch;
    public int ja = 1, niespodzianka = 0;
    public TextView[] linia = new TextView [9];
    public ConstraintLayout cl;
    boolean laczek;
    private Drawable bacRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        boolean firstStart = prefs.getBoolean("firstStart", true);
        laczek = prefs.getBoolean("czy", false);
        laczek = false;

        if(firstStart){
            ShowStartDialog();
        }




        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        cl = findViewById(R.id.cl);
        cl.setMotionEventSplittingEnabled(false);


        for (int i = 1; i <= 9; i++) {
            p[i] = "n";
        }


        linia[1] = findViewById(R.id.li1);
        linia[2] = findViewById(R.id.li2);
        linia[3] = findViewById(R.id.li3);
        linia[4] = findViewById(R.id.li4);
        linia[5] = findViewById(R.id.li5);
        linia[6] = findViewById(R.id.li6);
        linia[7] = findViewById(R.id.li7);
        linia[8] = findViewById(R.id.li8);


        b[1] = findViewById(R.id.but1);
        b[2] = findViewById(R.id.but2);
        b[3] = findViewById(R.id.but3);
        b[4] = findViewById(R.id.but4);
        b[5] = findViewById(R.id.but5);
        b[6] = findViewById(R.id.but6);
        b[7] = findViewById(R.id.but7);
        b[8] = findViewById(R.id.but8);
        b[9] = findViewById(R.id.but9);

        bacRes = b[1].getBackground();

        wygr = findViewById(R.id.wygr);
        aSwitch = findViewById(R.id.sw);

        wygr.setText(Html.fromHtml(powodzenia()));
        aSwitch.setOnCheckedChangeListener(this);


        for(int j = 1; j<=9; j++){
            b[j].setTextSize(30);
        }





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
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    v.vibrate(VibrationEffect.createOneShot(25, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    v.vibrate(25);
                }
                Intent intent = new Intent(this, dwieosoby.class);
                startActivity(intent);
                return true;


                case R.id.informacja:

                Intent intent1 = new Intent(this, autro_main.class);
                startActivity(intent1);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b){
        if(aSwitch.isChecked()){
            ja=1;
            reset_do_switcha();
        }else{
            ja=0;
            reset_do_switcha();
        }
    }


    public void akcja(View v) throws InterruptedException {



        Button but = findViewById(v.getId());

        if(but.getId()==R.id.but5 && p[5] == "O"){
            niespodzianka++;
            if(niespodzianka==10){
                reset_niespodzianka();
                Toast toast = Toast.makeText(this, "Access granted.", Toast.LENGTH_LONG);
                toast.show();
                cl.setMotionEventSplittingEnabled(true);

            }
            if (p[1] == "X" && p[2] == "X" && p[3] == "X" &&
                    p[4] == "X" && p[5] == "O" && p[6] == "X" &&
                    p[7] == "X" && p[8] == "X" && p[9] == "X") {

                Intent intent = new Intent(this, scrollmain.class);
                intent.addFlags(FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);


            }


        }else if(cl.isMotionEventSplittingEnabled()) {
            but.setClickable(false);
            but.setTextColor(getResources().getColor(R.color.niebieski));
            if (!laczek){
                but.setText("X");
            }else{
                but.setBackground(getResources().getDrawable(R.drawable.pole_mikson));
            }
            if (v.getId() == R.id.but1) {
                p[1] = "X";
            } else if (v.getId() == R.id.but2) {
                p[2] = "X";
            } else if (v.getId() == R.id.but3) {
                p[3] = "X";
            } else if (v.getId() == R.id.but4) {
                p[4] = "X";
            } else if (v.getId() == R.id.but5) {
                p[5] = "X";
            } else if (v.getId() == R.id.but6) {
                p[6] = "X";
            } else if (v.getId() == R.id.but7) {
                p[7] = "X";
            } else if (v.getId() == R.id.but8) {
                p[8] = "X";
            } else if (v.getId() == R.id.but9) {
                p[9] = "X";
            }


        }else if(!cl.isMotionEventSplittingEnabled()) {
            but.setClickable(false);
            but.setTextColor(getResources().getColor(R.color.niebieski));
            if (!laczek){
                but.setText("X");
            }else{
                but.setBackground(getResources().getDrawable(R.drawable.pole_mikson));
            }

            if (v.getId() == R.id.but1) {
                p[1] = "X";
            } else if (v.getId() == R.id.but2) {
                p[2] = "X";
            } else if (v.getId() == R.id.but3) {
                p[3] = "X";
            } else if (v.getId() == R.id.but4) {
                p[4] = "X";
            } else if (v.getId() == R.id.but5) {
                p[5] = "X";
            } else if (v.getId() == R.id.but6) {
                p[6] = "X";
            } else if (v.getId() == R.id.but7) {
                p[7] = "X";
            } else if (v.getId() == R.id.but8) {
                p[8] = "X";
            } else if (v.getId() == R.id.but9) {
                p[9] = "X";
            }

            if (algorytm() < 10) {
                b[algorytm()].setTextColor(getResources().getColor(R.color.czerwony));
                b[algorytm()].setClickable(false);
                change();
                b[algorytm()].setText("O");
                p[algorytm()] = "O";
            } else if (p[5] == "n") {
                b[5].setTextColor(getResources().getColor(R.color.czerwony));
                b[5].setClickable(false);
                change();
                p[5] = "O";

            } else if (algorytm() == 10 && przypadek() != 0) {
                b[przypadek()].setTextColor(getResources().getColor(R.color.czerwony));
                b[przypadek()].setClickable(false);
                change();
                p[przypadek()] = "O";

            }
            sprawdz_wygr();

        }

        }

    private void change() {

                            if (!laczek){
                                b[algorytm()].setText("O");
                            }else{
                                b[algorytm()].setBackground(getResources().getDrawable(R.drawable.pole_mati));
                                Log.d("dupa", "String.valueOf(e)");
                            }





        }


    public int algorytm() {
        if (p[1] == p[2] && p[3] == "n" && p[1] == "O") return 3;
        else if (p[1] == p[3] && p[2] == "n" && p[1] == "O") return 2;
        else if (p[2] == p[3] && p[1] == "n" && p[2] == "O") return 1;

        else if (p[4] == p[5] && p[6] == "n" && p[4] == "O") return 6;
        else if (p[4] == p[6] && p[5] == "n" && p[4] == "O") return 5;
        else if (p[5] == p[6] && p[4] == "n" && p[5] == "O") return 4;

        else if (p[7] == p[8] && p[9] == "n" && p[7] == "O") return 9;
        else if (p[7] == p[9] && p[8] == "n" && p[7] == "O") return 8;
        else if (p[8] == p[9] && p[7] == "n" && p[8] == "O") return 7;

        else if (p[1] == p[4] && p[7] == "n" && p[1] == "O") return 7;
        else if (p[1] == p[7] && p[4] == "n" && p[1] == "O") return 4;
        else if (p[4] == p[7] && p[1] == "n" && p[4] == "O") return 1;

        else if (p[2] == p[5] && p[8] == "n" && p[2] == "O") return 8;
        else if (p[2] == p[8] && p[5] == "n" && p[2] == "O") return 5;
        else if (p[5] == p[8] && p[2] == "n" && p[5] == "O") return 2;

        else if (p[3] == p[6] && p[9] == "n" && p[3] == "O") return 9;
        else if (p[3] == p[9] && p[6] == "n" && p[3] == "O") return 6;
        else if (p[6] == p[9] && p[3] == "n" && p[6] == "O") return 3;

        else if (p[1] == p[5] && p[9] == "n" && p[1] == "O") return 9;
        else if (p[1] == p[9] && p[5] == "n" && p[1] == "O") return 5;
        else if (p[5] == p[9] && p[1] == "n" && p[5] == "O") return 1;

        else if (p[3] == p[5] && p[7] == "n" && p[3] == "O") return 7;
        else if (p[3] == p[7] && p[5] == "n" && p[3] == "O") return 5;
        else if (p[5] == p[7] && p[3] == "n" && p[5] == "O") return 3;


        else if (p[1] == p[2] && p[3] == "n" && p[1] == "X") return 3;
        else if (p[1] == p[3] && p[2] == "n" && p[1] == "X") return 2;
        else if (p[2] == p[3] && p[1] == "n" && p[2] == "X") return 1;

        else if (p[4] == p[5] && p[6] == "n" && p[4] == "X") return 6;
        else if (p[4] == p[6] && p[5] == "n" && p[4] == "X") return 5;
        else if (p[5] == p[6] && p[4] == "n" && p[5] == "X") return 4;

        else if (p[7] == p[8] && p[9] == "n" && p[7] == "X") return 9;
        else if (p[7] == p[9] && p[8] == "n" && p[7] == "X") return 8;
        else if (p[8] == p[9] && p[7] == "n" && p[8] == "X") return 7;

        else if (p[1] == p[4] && p[7] == "n" && p[1] == "X") return 7;
        else if (p[1] == p[7] && p[4] == "n" && p[1] == "X") return 4;
        else if (p[4] == p[7] && p[1] == "n" && p[4] == "X") return 1;

        else if (p[2] == p[5] && p[8] == "n" && p[2] == "X") return 8;
        else if (p[2] == p[8] && p[5] == "n" && p[2] == "X") return 5;
        else if (p[5] == p[8] && p[2] == "n" && p[5] == "X") return 2;

        else if (p[3] == p[6] && p[9] == "n" && p[3] == "X") return 9;
        else if (p[3] == p[9] && p[6] == "n" && p[3] == "X") return 6;
        else if (p[6] == p[9] && p[3] == "n" && p[6] == "X") return 3;

        else if (p[1] == p[5] && p[9] == "n" && p[1] == "X") return 9;
        else if (p[1] == p[9] && p[5] == "n" && p[1] == "X") return 5;
        else if (p[5] == p[9] && p[1] == "n" && p[5] == "X") return 1;

        else if (p[3] == p[5] && p[7] == "n" && p[3] == "X") return 7;
        else if (p[3] == p[7] && p[5] == "n" && p[3] == "X") return 5;
        else if (p[5] == p[7] && p[3] == "n" && p[5] == "X") return 3;

        else return 10;


    }


    public int przypadek() {

        //-----------PRZECIWNIK ZACZYNA OD ŚRODKA-----------


        //przypadek 1

        if (p[1] == "n" && p[2] == "n" && p[3] == "n" &&
                p[4] == "n" && p[5] == "X" && p[6] == "n" &&
                p[7] == "n" && p[8] == "n" && p[9] == "n") {

            return 1;
        }


        //przypadek 2

        else if (p[1] == "O" && p[2] == "n" && p[3] == "n" &&
                p[4] == "n" && p[5] == "X" && p[6] == "n" &&
                p[7] == "n" && p[8] == "n" && p[9] == "X") {

            return 7;
        }


        //przypadek 3

        else if (p[1] == "O" && p[2] == "n" && p[3] == "X" &&
                p[4] == "X" && p[5] == "X" && p[6] == "O" &&
                p[7] == "O" && p[8] == "n" && p[9] == "X") {

            return 2;
        }


        //przypadek 4

        else if (p[1] == "O" && p[2] == "X" && p[3] == "n" &&
                p[4] == "n" && p[5] == "X" && p[6] == "n" &&
                p[7] == "n" && p[8] == "O" && p[9] == "X") {

            return 7;
        }


        //przypadek 5

        else if (p[1] == "O" && p[2] == "n" && p[3] == "n" &&
                p[4] == "X" && p[5] == "X" && p[6] == "O" &&
                p[7] == "n" && p[8] == "n" && p[9] == "X") {

            return 3;
        }


        //przypadek 6

        else if (p[1] == "O" && p[2] == "X" && p[3] == "n" &&
                p[4] == "X" && p[5] == "X" && p[6] == "O" &&
                p[7] == "n" && p[8] == "O" && p[9] == "X") {

            return 3;
        }


        //-----------PRZECIWNIK ZACZYNA OD P[1]-----------


        //przypadek 7

        else if (p[1] == "X" && p[2] == "n" && p[3] == "n" &&
                p[4] == "n" && p[5] == "O" && p[6] == "X" &&
                p[7] == "n" && p[8] == "n" && p[9] == "n") {

            return 3;
        }


        //przypadek 8

        else if (p[1] == "X" && p[2] == "n" && p[3] == "n" &&
                p[4] == "n" && p[5] == "O" && p[6] == "n" &&
                p[7] == "n" && p[8] == "X" && p[9] == "n") {

            return 7;
        }


        //przypadek 9

        else if (p[1] == "n" && p[2] == "X" && p[3] == "n" &&
                p[4] == "n" && p[5] == "O" && p[6] == "n" &&
                p[7] == "n" && p[8] == "n" && p[9] == "X") {

            return 3;
        }

        //przypadek 10

        else if (p[1] == "n" && p[2] == "n" && p[3] == "n" &&
                p[4] == "n" && p[5] == "O" && p[6] == "X" &&
                p[7] == "n" && p[8] == "X" && p[9] == "n") {

            return 9;
        }


        //przypadek 11

        else if (p[1] == "n" && p[2] == "n" && p[3] == "n" &&
                p[4] == "X" && p[5] == "O" && p[6] == "n" &&
                p[7] == "n" && p[8] == "n" && p[9] == "X") {

            return 7;
        }

        //przypadek 12

        else if (p[1] == "n" && p[2] == "X" && p[3] == "n" &&
                p[4] == "n" && p[5] == "O" && p[6] == "n" &&
                p[7] == "X" && p[8] == "O" && p[9] == "X") {

            return 4;
        }


        //przypadek 13

        else if (p[1] == "n" && p[2] == "n" && p[3] == "X" &&
                p[4] == "X" && p[5] == "O" && p[6] == "O" &&
                p[7] == "n" && p[8] == "n" && p[9] == "X") {

            return 2;
        }

        //przypadek 14

        else if (p[1] == "n" && p[2] == "n" && p[3] == "X" &&
                p[4] == "n" && p[5] == "O" && p[6] == "n" &&
                p[7] == "n" && p[8] == "X" && p[9] == "n") {

            return 9;
        }

        //przypadek 15

        else if (p[1] == "n" && p[2] == "n" && p[3] == "n" &&
                p[4] == "n" && p[5] == "O" && p[6] == "X" &&
                p[7] == "X" && p[8] == "n" && p[9] == "n") {

            return 9;
        }

        //przypadek 16

        else if (p[1] == "n" && p[2] == "n" && p[3] == "n" &&
                p[4] == "n" && p[5] == "O" && p[6] == "X" &&
                p[7] == "n" && p[8] == "n" && p[9] == "n") {

            return 3;
        }

        //przypadek 17

        else if (p[1] == "n" && p[2] == "n" && p[3] == "n" &&
                p[4] == "n" && p[5] == "O" && p[6] == "n" &&
                p[7] == "n" && p[8] == "X" && p[9] == "n") {

            return 7;
        }


        //przypadek 18

        else if (p[1] == "O" && p[2] == "X" && p[3] == "n" &&
                p[4] == "n" && p[5] == "O" && p[6] == "n" &&
                p[7] == "n" && p[8] == "n" && p[9] == "X") {

            return 7;
        }//przypadek 19

        else if (p[1] == "n" && p[2] == "n" && p[3] == "X" &&
                p[4] == "n" && p[5] == "O" && p[6] == "n" &&
                p[7] == "X" && p[8] == "n" && p[9] == "n") {

            return 2;
        }//przypadek 20

        else if (p[1] == "X" && p[2] == "O" && p[3] == "n" &&
                p[4] == "n" && p[5] == "O" && p[6] == "n" &&
                p[7] == "n" && p[8] == "X" && p[9] == "n") {

            return 7;
        } else {
            return najlepszy_ruch();
        }

    }


    public int najlepszy_ruch() {

        if (p[1] == "n") {
            return 1;
        } else if (p[2] == "n") {
            return 2;
        } else if (p[3] == "n") {
            return 3;
        } else if (p[4] == "n") {
            return 4;
        } else if (p[5] == "n") {
            return 5;
        } else if (p[6] == "n") {
            return 6;
        } else if (p[7] == "n") {
            return 7;
        } else if (p[8] == "n") {
            return 8;
        } else if (p[9] == "n") {
            return 9;
        }else {
            return 0;
        }

    }


    public void reset(View v) {

        cl.setMotionEventSplittingEnabled(false);
        niespodzianka=0;

        Vibrator q = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            q.vibrate(VibrationEffect.createOneShot(25, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            q.vibrate(25);
        }



        for (int i = 1; i <= 9; i++) {
            p[i] = "n";
            b[i].setText("");
            b[i].setClickable(true);
            b[i].setBackground(bacRes);
            b[i].setBackground(bacRes);
        }


        wygr.setText(Html.fromHtml(powodzenia()));

        if(ja==0){
            b[5].setClickable(true);
            b[5].setTextColor(getResources().getColor(R.color.czerwony));
            if (!laczek){
                b[algorytm()].setText("O");
            }else{
                b[algorytm()].setBackground(getResources().getDrawable(R.drawable.pole_mati));
            }
            p[5] = "O";
        }

        wymaz_linie();

    }

    public void sprawdz_wygr() {

        if ((p[1] == p[2] && p[2] == p[3] && p[1] == "O") ||
                (p[4] == p[5] && p[5] == p[6] && p[4] == "O") ||
                (p[7] == p[8] && p[8] == p[9] && p[7] == "O") ||
                (p[1] == p[4] && p[4] == p[7] && p[1] == "O") ||
                (p[2] == p[5] && p[5] == p[8] && p[2] == "O") ||
                (p[3] == p[6] && p[6] == p[9] && p[3] == "O") ||
                (p[1] == p[5] && p[5] == p[9] && p[1] == "O") ||
                (p[3] == p[5] && p[5] == p[7] && p[3] == "O")) {


            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                v.vibrate(500);
            }

            rysuj_linie();
            for (int i = 1; i <= 9; i++) {
                b[i].setClickable(false);
            }
            String kol = "<b><font color='#fc0313'> O </font></b>";
            wygr.setText(Html.fromHtml("Wygrał gracz: " + kol));
        }



        else if ((p[1] == p[2] && p[2] == p[3] && p[1] == "X") ||
                (p[4] == p[5] && p[5] == p[6] && p[4] == "X") ||
                (p[7] == p[8] && p[8] == p[9] && p[7] == "X") ||
                (p[1] == p[4] && p[4] == p[7] && p[1] == "X") ||
                (p[2] == p[5] && p[5] == p[8] && p[2] == "X") ||
                (p[3] == p[6] && p[6] == p[9] && p[3] == "X") ||
                (p[1] == p[5] && p[5] == p[9] && p[1] == "X") ||
                (p[3] == p[5] && p[5] == p[7] && p[3] == "X")) {


            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                v.vibrate(500);
            }

            rysuj_linie();
            for (int i = 1; i <= 9; i++) {
                b[i].setClickable(false);
            }
            String kolo = "<b><font color='#000dff'> X </font></b>";
            wygr.setText(Html.fromHtml("Wygrał gracz: " + kolo));

        }else if(p[1]!="n" && p[2]!="n" && p[3]!="n" && p[4]!="n" && p[5]!="n" && p[6]!="n" && p[7]!="n" && p[8]!="n" && p[9]!="n"){

            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                v.vibrate(VibrationEffect.createOneShot(25, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                v.vibrate(25);
            }

            String r = "<b><font color='#fc0313'> R </font></b>";
            String e = "<b><font color='#000dff'> e </font></b>";
            String m = "<b><font color='#fc0313'> m </font></b>";
            String i = "<b><font color='#000dff'> i </font></b>";
            String s = "<b><font color='#fc0313'> s </font></b>";

            wygr.setText(Html.fromHtml(r+e+m+i+s));
        }

    }

    public void reset_do_switcha(){


        if (p[1]=="n" && p[2]=="n" && p[3]=="n" && p[4]=="n" && p[6]=="n" && p[7]=="n" && p[8]=="n" && p[9]=="n" && ja==1){
            b[5].setClickable(true);
            b[5].setText("");
            p[5] = "n";
            wymaz_linie();
        }else if (p[1]=="n" && p[2]=="n" && p[3]=="n" && p[4]=="n" && p[6]=="n" && p[7]=="n" && p[8]=="n" && p[9]=="n" && ja==0){
            b[5].setClickable(true);
            b[5].setTextColor(getResources().getColor(R.color.czerwony));
            if (!laczek){
                b[algorytm()].setText("O");
            }else{
                b[algorytm()].setBackground(getResources().getDrawable(R.drawable.pole_mati));
            }
            p[5] = "O";
            wymaz_linie();
        }
    }

    public String powodzenia(){
        String po = "<b><font color='#fc0313'> P </font></b>";
        String o = "<b><font color='#000dff'> O </font></b>";
        String w = "<b><font color='#fc0313'> W </font></b>";
        String d = "<b><font color='#fc0313'> D </font></b>";
        String z = "<b><font color='#000dff'> Z </font></b>";
        String e = "<b><font color='#fc0313'> E </font></b>";
        String n = "<b><font color='#000dff'> N </font></b>";
        String i = "<b><font color='#fc0313'> I </font></b>";
        String a = "<b><font color='#000dff'> A </font></b>";
        String wyk = "<b><font color='#fc0313'> !!! </font></b>";

        String powodz = po+o+w+o+d+z+e+n+i+a+wyk;
        return powodz;
    }


    public void rysuj_linie(){
        if (p[1] == p[2] && p[2] == p[3] && p[1] != "n"){
            linia[2].setBackgroundResource(R.drawable.boarderss);
        }
        else if  (p[4] == p[5] && p[5] == p[6] && p[4] != "n"){
            linia[1].setBackgroundResource(R.drawable.boarderss);
        }
        else if (p[7] == p[8] && p[8] == p[9] && p[7] != "n"){
            linia[3].setBackgroundResource(R.drawable.boarderss);
        }
        else if (p[1] == p[4] && p[4] == p[7] && p[1] != "n"){
            linia[6].setBackgroundResource(R.drawable.boarderss);
        }
        else if (p[2] == p[5] && p[5] == p[8] && p[2] != "n"){
            linia[4].setBackgroundResource(R.drawable.boarderss);
        }
        else if (p[3] == p[6] && p[6] == p[9] && p[3] != "n"){
            linia[5].setBackgroundResource(R.drawable.boarderss);
        }
        else if (p[1] == p[5] && p[5] == p[9] && p[1] != "n"){
            linia[7].setBackgroundResource(R.drawable.boarderss);
        }
        else if (p[3] == p[5] && p[5] == p[7] && p[3] != "n"){
            linia[8].setBackgroundResource(R.drawable.boarderss);
        }
    }

    public void wymaz_linie(){
        for(int j=1; j<=8; j++){
            linia[j].setBackgroundResource(0);
        }
    }

    public void reset_niespodzianka(){
        cl.setMotionEventSplittingEnabled(false);
        niespodzianka=0;

        Vibrator q = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            q.vibrate(VibrationEffect.createOneShot(25, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            q.vibrate(25);
        }

        for (int i = 1; i <= 9; i++) {
            p[i] = "n";
            b[i].setText("");
            b[i].setClickable(true);
            b[i].setBackground(bacRes);
        }


        wygr.setText(Html.fromHtml(powodzenia()));

            b[5].setClickable(true);
            b[5].setTextColor(getResources().getColor(R.color.czerwony));
        if (!laczek){
            b[algorytm()].setText("O");
        }else{
            b[algorytm()].setBackground(getResources().getDrawable(R.drawable.pole_mati));
        }
            p[5] = "O";


    }

    public void ShowStartDialog(){


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




        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firstStart", false);
        editor.apply();


    }

}