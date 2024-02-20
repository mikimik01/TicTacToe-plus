package com.hfad.tictactoe;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class Dodatki extends AppCompatActivity {

    private EditText haslo;

    private Switch aSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodatki);

        aSwitch = findViewById(R.id.przelacznik);
        haslo = findViewById(R.id.haslo);

        final SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        boolean czy = prefs.getBoolean("czy", false);
        aSwitch.setChecked(czy);

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putBoolean("czy", true);
                    editor.apply();
                }else{
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putBoolean("czy", false);
                    editor.apply();
                }
            }
        });

    }

    public void login(View view) throws Exception {

        String h = haslo.getText().toString().trim();

        if (h.equals("")){
            Toast.makeText(this, "Wpisz hasło", Toast.LENGTH_SHORT).show();
        }else if (h.equals("PafnucyMikson1")){
            aSwitch.setVisibility(View.VISIBLE);
        }
    }
}
