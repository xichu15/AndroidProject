package com.example.best.the.androidproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
//SNIKI COMMIT
    Button baton;
    Button buttonToCurrentDayAcitivies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TU BYLAM ~Dudzilla
        baton = (Button) findViewById(R.id.settingsBtn);
        buttonToCurrentDayAcitivies = (Button) findViewById(R.id.buttonToCurrentDayActivites);

        baton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), Settings.class);
                startActivity(intent);
            }
        });

        buttonToCurrentDayAcitivies.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CurrentDayActivity.class);
                startActivity(intent);
            }
        });

    }
}
