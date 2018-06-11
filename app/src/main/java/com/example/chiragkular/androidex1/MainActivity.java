package com.example.chiragkular.androidex1;

import android.Manifest;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
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


public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CALENDAR = 0;
    public static final String EXTRA_MESSAGE = "com.example.chiragkular.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        readOSVersion(getApplicationContext());
//        getAllApps(getApplicationContext());
        MyConstants mc = new MyConstants(MainActivity.this, this);
        setContentView(R.layout.activity_main);

    }
    public void transitButton(View view)
    {
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);
    }

//    public void sendMessage(View view){
//        Intent intent = new Intent(this, DisplayMessageActivity.class);
//        EditText editText = (EditText) findViewById(R.id.editText);
//        String message = editText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, message);
//        startActivity(intent);
//    }

//    public void analyzeSystem(View view) throws Settings.SettingNotFoundException {
//
//        DeviceSecure ds = new DeviceSecure();
//        int deviceSecure_Score = ds.DeviceSecureAnalysis();
//        TextView score_deviceSecure = findViewById(R.id.txtScore_deviceSecure);
//        score_deviceSecure.setText(String.valueOf(deviceSecure_Score));
//
//        DeviceVersion dv= new DeviceVersion();
//        int deviceVersion_Score = dv.DeviceVersionAnalysis();
//        TextView score_deviceVersion = findViewById(R.id.txtScore_deviceVersion);
//        score_deviceVersion.setText(String.valueOf(deviceVersion_Score));
//        //getAllApps(getApplicationContext());
//        AppPermissions perm = new AppPermissions();
//        perm.getAllApps();
//        perm.print();
//        Log.e("SystemEvaluation",String.valueOf(perm.evaluateSystem()));
//
//    }




    public void OtherSecurity(Context context)
    {
        int devOption = Settings.Secure.getInt(this.getContentResolver(), Settings.Global.DEVELOPMENT_SETTINGS_ENABLED , 0);
        try {
            int loc = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }

    }

    public String printLoops(Object[] someList){
        for (Object item: someList){
            Log.e("All Items",item.toString());
        }
        return "------------------------";
    }

    public String printLoops(int[] someList){
        for (int item: someList){
            Log.e("All Items", String.valueOf(item));
        }
        return "------------------------";
    }

    public void readOSVersion(Context context){
        KeyguardManager km = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
        boolean deviceSecure = km.isDeviceSecure();
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        int version = Build.VERSION.SDK_INT;
        String versionRelease = Build.VERSION.RELEASE;


        Log.e("MyActivity", "manufacturer " + manufacturer
                + " \n model " + model
                + " \n version " + version
                + " \n versionRelease " + versionRelease
                + "\n Device Secure "+ deviceSecure
        );
    }

    public void checkPerm(View view){
        // Check if Calendar permission is already available
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALENDAR)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission Available

        }
        else{
            // Permission not Granted
            // Explain why permission is needed
            if(shouldShowRequestPermissionRationale(Manifest.permission.READ_CALENDAR)){
                Toast.makeText(this,"Calendar permission is needed to read birthday events.",
                        Toast.LENGTH_SHORT).show();
            }

            // Request the necessary Permission
            requestPermissions(new String[]{ Manifest.permission.READ_CALENDAR }, REQUEST_CALENDAR);
        }

    }
}
