package com.example.chiragkular.androidex1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class listApp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_app);
        Intent intent = getIntent();

        appListPrint();
    }
    public void appListPrint()
    {
        AppPermissions ap = new AppPermissions();
        ap.getAllApps();ap.appClassification();
//        ArrayAdapter adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1, ap.normalApp);
//
//        ListView listView = (ListView) findViewById(R.id.listViewApp);
//        listView.setAdapter(adapter);
//        ArrayAdapter adapter1 = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1, ap.dangerousApp);
//
//        ListView listView1 = (ListView) findViewById(R.id.dangerAppView);
//        listView1.setAdapter(adapter1);
        TextView norm = findViewById(R.id.normTxt);
        norm.setText(String.valueOf(ap.normalApp.size()));
        TextView dng = findViewById(R.id.dngTxt);
        dng.setText(String.valueOf(ap.dangerousApp.size()));
        TextView sign = findViewById(R.id.signTxt);
        sign.setText(String.valueOf(ap.signatureApp.size()));
    }

    public void appScore(View view) {
        AppPermissions ap = new AppPermissions();
        ap.getAllApps();
        int sc =ap.evaluateSystem();
        String scoreApp = "Application score: "+String.valueOf(sc);

        TextView score = findViewById(R.id.appScoreTxt);
        score.setText(scoreApp);
    }
}
