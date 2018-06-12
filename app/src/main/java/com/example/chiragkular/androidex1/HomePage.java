package com.example.chiragkular.androidex1;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        MyConstants mc = new MyConstants(HomePage.this, this);
        Intent intent = getIntent();

    }

    /*public void homeTransit()
    {
        TextView displayMsg = findViewById(R.id.textDisplayMsg);
        displayMsg.setText("To know your system security score HIT Evaluate");

    }*/
    public void analyzeSystem(View view) throws Settings.SettingNotFoundException {

        DeviceSecure ds = new DeviceSecure();
        int deviceSecure_Score = ds.DeviceSecureAnalysis();
        TextView score_deviceSecure = findViewById(R.id.txtScore_deviceSecure);
        score_deviceSecure.setText(String.valueOf(deviceSecure_Score));

        DeviceVersion dv= new DeviceVersion();
        int deviceVersion_Score = dv.DeviceVersionAnalysis();
        TextView score_deviceVersion = findViewById(R.id.txtScore_deviceVersion);
        score_deviceVersion.setText(String.valueOf(deviceVersion_Score));
        //getAllApps(getApplicationContext());
        AppPermissions perm = new AppPermissions();
        perm.getAllApps();
        perm.appClassification();
        //perm.print();
        //Log.e("SystemEvaluation",String.valueOf(perm.evaluateSystem()));

    }

    public void TransitToListApp(View view) {
        Intent intent = new Intent(this, listApp.class);
        startActivity(intent);
    }
}
