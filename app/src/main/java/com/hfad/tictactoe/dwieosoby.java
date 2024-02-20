package com.hfad.tictactoe;

import android.content.Context;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class dwieosoby extends AppCompatActivity {

    public String[] pole = new String[10];
    public Button[] butto = new Button[10];
    public TextView[] info = new TextView[5];
    public String kto, kto_zaczynal = "X";
    public int punkty1 = 0, punkty2 = 0;
    public TextView[] linia = new TextView [9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dwieosoby);

        info[1] = findViewById(R.id.info);
        info[2] = findViewById(R.id.info1);
        info[3] = findViewById(R.id.info2);
        info[4] = findViewById(R.id.info3);


        butto[1] = findViewById(R.id.guzik);
        butto[2] = findViewById(R.id.guzik1);
        butto[3] = findViewById(R.id.guzik2);
        butto[4] = findViewById(R.id.guzik3);
        butto[5] = findViewById(R.id.guzik4);
        butto[6] = findViewById(R.id.guzik5);
        butto[7] = findViewById(R.id.guzik6);
        butto[8] = findViewById(R.id.guzik7);
        butto[9] = findViewById(R.id.guzik8);

        linia[1] = findViewById(R.id.lini1);
        linia[2] = findViewById(R.id.lini2);
        linia[3] = findViewById(R.id.lini3);
        linia[4] = findViewById(R.id.lini4);
        linia[5] = findViewById(R.id.lini5);
        linia[6] = findViewById(R.id.lini6);
        linia[7] = findViewById(R.id.lini7);
        linia[8] = findViewById(R.id.lini8);


        for(int j = 1; j<=9; j++){
            pole[j] = "n";
            butto[j].setTextSize(30);
        }

        info[1].setText("Punkty: " + punkty1);
        info[3].setText("Punkty: " + punkty2);

        info[2].setTextColor(getResources().getColor(R.color.niebieski));
        info[2].setText("  Twój ruch.");
        kto = "X";

    }

  public void ruch(View v) throws InterruptedException{
        Button but = findViewById(v.getId());

        if(kto=="X"){
            but.setClickable(false);
            but.setTextColor(getResources().getColor(R.color.niebieski));
            but.setText("X");
            info[4].setTextColor(getResources().getColor(R.color.czerwony));
            info[4].setText("Twój ruch.");
            info[2].setText("");
            kto = "O";
            if (v.getId() == R.id.guzik) {
                pole[1] = "X";
            } else if (v.getId() == R.id.guzik1) {
                pole[2] = "X";
            } else if (v.getId() == R.id.guzik2) {
                pole[3] = "X";
            } else if (v.getId() == R.id.guzik3) {
                pole[4] = "X";
            } else if (v.getId() == R.id.guzik4) {
                pole[5] = "X";
            } else if (v.getId() == R.id.guzik5) {
                pole[6] = "X";
            } else if (v.getId() == R.id.guzik6) {
                pole[7] = "X";
            } else if (v.getId() == R.id.guzik7) {
                pole[8] = "X";
            } else if (v.getId() == R.id.guzik8) {
                pole[9] = "X";
            }

        }else if(kto=="O"){
            but.setClickable(false);
            but.setTextColor(getResources().getColor(R.color.czerwony));
            but.setText("O");
            info[2].setTextColor(getResources().getColor(R.color.niebieski));
            info[2].setText("Twój ruch.");
            info[4].setText("");
            kto = "X";
            if (v.getId() == R.id.guzik) {
                pole[1] = "O";
            } else if (v.getId() == R.id.guzik1) {
                pole[2] = "O";
            } else if (v.getId() == R.id.guzik2) {
                pole[3] = "O";
            } else if (v.getId() == R.id.guzik3) {
                pole[4] = "O";
            } else if (v.getId() == R.id.guzik4) {
                pole[5] = "O";
            } else if (v.getId() == R.id.guzik5) {
                pole[6] = "O";
            } else if (v.getId() == R.id.guzik6) {
                pole[7] = "O";
            } else if (v.getId() == R.id.guzik7) {
                pole[8] = "O";
            } else if (v.getId() == R.id.guzik8) {
                pole[9] = "O";
            }
        }

        wygrana();

    }

    public void wygrana() throws InterruptedException{


        if ((pole[1] == pole[2] && pole[2] == pole[3] && pole[1] == "O") ||
                (pole[4] == pole[5] && pole[5] == pole[6] && pole[4] == "O") ||
                (pole[7] == pole[8] && pole[8] == pole[9] && pole[7] == "O") ||
                (pole[1] == pole[4] && pole[4] == pole[7] && pole[1] == "O") ||
                (pole[2] == pole[5] && pole[5] == pole[8] && pole[2] == "O") ||
                (pole[3] == pole[6] && pole[6] == pole[9] && pole[3] == "O") ||
                (pole[1] == pole[5] && pole[5] == pole[9] && pole[1] == "O") ||
                (pole[3] == pole[5] && pole[5] == pole[7] && pole[3] == "O")) {



            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                v.vibrate(VibrationEffect.createOneShot(25, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                v.vibrate(25);
            }

            rysuj_linie();

            for (int i = 1; i <= 9; i++) {
                butto[i].setClickable(false);
            }
            info[2].setText("");
            info[4].setText("+1");
            punkty2++;
            info[3].postDelayed(new Runnable() {
                @Override
                public void run() {
                    info[4].setText("");
                    info[3].setText("Punkty: " + punkty2);
                    for(int j = 1; j<=9; j++){
                        pole[j] = "n";
                        butto[j].setText("");
                        butto[j].setClickable(true);

                    }



                    if (kto_zaczynal=="X"){
                        info[4].setTextColor(getResources().getColor(R.color.czerwony));
                        info[4].setText("  Twój ruch.");
                        kto = "O";
                        kto_zaczynal = "O";

                    }else{
                        info[2].setTextColor(getResources().getColor(R.color.niebieski));
                        info[2].setText("  Twój ruch.");
                        kto = "X";
                        kto_zaczynal = "X";
                    }


                    wymaz_linie();
                }
            }, 1000);


        }



        else if ((pole[1] == pole[2] && pole[2] == pole[3] && pole[1] == "X") ||
                (pole[4] == pole[5] && pole[5] == pole[6] && pole[4] == "X") ||
                (pole[7] == pole[8] && pole[8] == pole[9] && pole[7] == "X") ||
                (pole[1] == pole[4] && pole[4] == pole[7] && pole[1] == "X") ||
                (pole[2] == pole[5] && pole[5] == pole[8] && pole[2] == "X") ||
                (pole[3] == pole[6] && pole[6] == pole[9] && pole[3] == "X") ||
                (pole[1] == pole[5] && pole[5] == pole[9] && pole[1] == "X") ||
                (pole[3] == pole[5] && pole[5] == pole[7] && pole[3] == "X")) {


            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                v.vibrate(VibrationEffect.createOneShot(25, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                v.vibrate(25);
            }

            rysuj_linie();

            for (int i = 1; i <= 9; i++) {
                butto[i].setClickable(false);
            }
            info[4].setText("");
            info[2].setText("+1");
            punkty1++;
            info[1].postDelayed(new Runnable() {
                @Override
                public void run() {
                    info[2].setText("");
                    info[1].setText("Punkty: " + punkty1);
                    for(int j = 1; j<=9; j++){
                        pole[j] = "n";
                        butto[j].setText("");
                        butto[j].setClickable(true);
                    }

                    if (kto_zaczynal!="X"){
                        info[4].setTextColor(getResources().getColor(R.color.czerwony));
                        info[4].setText("  Twój ruch.");
                        kto = "O";
                        kto_zaczynal = "O";

                    }else{
                        info[2].setTextColor(getResources().getColor(R.color.niebieski));
                        info[2].setText("  Twój ruch.");
                        kto = "X";
                        kto_zaczynal = "X";
                    }

                    wymaz_linie();
                }
            }, 1000);

        }else if(pole[1]!="n" && pole[2]!="n" && pole[3]!="n" && pole[4]!="n" && pole[5]!="n" && pole[6]!="n" && pole[7]!="n" && pole[8]!="n" && pole[9]!="n"){


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

            info[2].setText(Html.fromHtml(r+e+m+i+s));
            info[4].setText(Html.fromHtml(r+e+m+i+s));

            info[2].postDelayed(new Runnable() {
                @Override
                public void run() {
                    info[2].setText("");
                    info[4].setText("");

                    for(int j = 1; j<=9; j++){
                        pole[j] = "n";
                        butto[j].setText("");
                        butto[j].setClickable(true);

                    }

                    if (kto_zaczynal=="X"){
                        info[4].setTextColor(getResources().getColor(R.color.czerwony));
                        info[4].setText("  Twój ruch.");
                        kto = "O";
                        kto_zaczynal = "O";

                    }else{
                        info[2].setTextColor(getResources().getColor(R.color.niebieski));
                        info[2].setText("  Twój ruch.");
                        kto = "X";
                        kto_zaczynal = "X";
                    }

                    }

            }, 1000);
        }


    }


    public void rysuj_linie(){
        if (pole[1] == pole[2] && pole[2] == pole[3] && pole[1] != "n"){
               linia[1].setBackgroundResource(R.drawable.boarderss);
        }
        else if  (pole[4] == pole[5] && pole[5] == pole[6] && pole[4] != "n"){
            linia[2].setBackgroundResource(R.drawable.boarderss);
        }
        else if (pole[7] == pole[8] && pole[8] == pole[9] && pole[7] != "n"){
            linia[3].setBackgroundResource(R.drawable.boarderss);
        }
        else if (pole[1] == pole[4] && pole[4] == pole[7] && pole[1] != "n"){
            linia[6].setBackgroundResource(R.drawable.boarderss);
        }
        else if (pole[2] == pole[5] && pole[5] == pole[8] && pole[2] != "n"){
            linia[5].setBackgroundResource(R.drawable.boarderss);
        }
        else if (pole[3] == pole[6] && pole[6] == pole[9] && pole[3] != "n"){
            linia[4].setBackgroundResource(R.drawable.boarderss);
        }
        else if (pole[1] == pole[5] && pole[5] == pole[9] && pole[1] != "n"){
            linia[7].setBackgroundResource(R.drawable.boarderss);
        }
        else if (pole[3] == pole[5] && pole[5] == pole[7] && pole[3] != "n"){
            linia[8].setBackgroundResource(R.drawable.boarderss);
        }
    }

    public void wymaz_linie(){
        for(int j=1; j<=8; j++){
            linia[j].setBackgroundResource(0);
        }
    }


    public void resecik(View v){
        Vibrator v1 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v1.vibrate(VibrationEffect.createOneShot(25, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            v1.vibrate(25);
        }
                info[2].setText("");
                info[4].setText("");

                for(int j = 1; j<=9; j++){
                    pole[j] = "n";
                    butto[j].setText("");
                    butto[j].setClickable(true);

                }

        if (kto_zaczynal=="X"){
            info[4].setTextColor(getResources().getColor(R.color.czerwony));
            info[4].setText("Twój ruch.");
            kto = "O";
            kto_zaczynal = "O";

        }else{
            info[2].setTextColor(getResources().getColor(R.color.niebieski));
            info[2].setText("Twój ruch.");
            kto = "X";
            kto_zaczynal = "X";
        }
                wymaz_linie();

    }


}


