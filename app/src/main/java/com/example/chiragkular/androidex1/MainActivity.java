package com.example.chiragkular.androidex1;

import android.Manifest;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CALENDAR = 0;
    public static final String EXTRA_MESSAGE = "com.example.chiragkular.MESSAGE";
    private static final String TAG = "MyActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        readOSVersion(getApplicationContext());
//        getAllApps(getApplicationContext());
        MyConstants mc = new MyConstants(MainActivity.this, this);
        setContentView(R.layout.activity_main);

    }
    public void startButton(View view)
    {
        Intent intent = new Intent(this, questions.class);
        startActivity(intent);
    }


    public void analyzeSystem(View view) throws Settings.SettingNotFoundException {

        DeviceSecure ds = new DeviceSecure();
        int deviceSecure_Score = ds.DeviceSecureAnalysis();
//        TextView score_deviceSecure = findViewById(R.id.txtScore_deviceSecure);
//        score_deviceSecure.setText(String.valueOf(deviceSecure_Score));

        DeviceVersion dv= new DeviceVersion();
        int deviceVersion_Score = dv.DeviceVersionAnalysis();
//        TextView score_deviceVersion = findViewById(R.id.txtScore_deviceVersion);
//        score_deviceVersion.setText(String.valueOf(deviceVersion_Score));

    }


    public void OtherSecurity(Context context)
    {
        int devOption = Settings.Secure.getInt(this.getContentResolver(), Settings.Global.DEVELOPMENT_SETTINGS_ENABLED , 0);
        try {
            int loc = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }

    }


}
