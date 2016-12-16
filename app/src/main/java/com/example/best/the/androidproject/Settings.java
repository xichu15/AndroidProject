package com.example.best.the.androidproject;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class Settings extends Activity {
    private static final String PREFERENCES_NAME = "myPreferences";
    private static final String PREFERENCES_TEXT_FIELD = "textField";
    private static final String PREFERENCES_INT_FIELD = "intField";
    private static int period = 0;

    private EditText etToSave;
    private Button btnSave;
    private Switch serviceSwitch;
    private CleaningService cService;
    private boolean mBound = false;

    private SharedPreferences preferences;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            CleaningService.LocalBinder binder = (CleaningService.LocalBinder) service;
            cService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ustawienia);
        preferences = getSharedPreferences(PREFERENCES_NAME, Activity.MODE_PRIVATE);
        etToSave = (EditText) findViewById(R.id.EdtTxt);
        btnSave = (Button) findViewById(R.id.SaveBtn);
        serviceSwitch = (Switch) findViewById(R.id.serviceSwitch);

        serviceSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(!mBound){
                        bindService(new Intent(getApplicationContext(), CleaningService.class), mConnection, Context.BIND_AUTO_CREATE);
                        mBound = true;
                    }
                } else{
                    if(mBound){
                        unbindService(mConnection);
                        mBound = false;
                    }
                }
            }
        });

        initButtonOnClick();
        restoreData();
    }

    private void initButtonOnClick() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveData();
                showToast("Zapis");
            }
        });
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void saveData() {
        SharedPreferences.Editor preferencesEditor = preferences.edit();
        String editTextData = etToSave.getText().toString();
        preferencesEditor.putString(PREFERENCES_TEXT_FIELD, editTextData);
        preferencesEditor.putInt(PREFERENCES_INT_FIELD, Integer.parseInt(editTextData));
        preferencesEditor.commit();
    }

    private void restoreData() {
        String textFromPreferences = preferences.getString(PREFERENCES_TEXT_FIELD, "");
        int periodFromPreferences = preferences.getInt(PREFERENCES_INT_FIELD, 0);
        etToSave.setText(textFromPreferences);
        setPeriod(periodFromPreferences);
    }

    public void getCounter(View view){
        if(mBound){
            cService.getCounter();
        }
    }

    public static int getPeriod() {
        return period;
    }

    public static void setPeriod(int period) {
        Settings.period = period;
    }
}