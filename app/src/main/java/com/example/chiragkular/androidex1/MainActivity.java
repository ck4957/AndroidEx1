package com.example.chiragkular.androidex1;

import android.Manifest;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CALENDAR = 0;
    public static final String EXTRA_MESSAGE = "com.example.chiragkular.MESSAGE";
    private static Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        readOSVersion(getApplicationContext());
//        getAllApps(getApplicationContext());
        setContentView(R.layout.activity_main);
    }

    public void sendMessage(View view){
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void analyzeSystem(View view){
        getCurrentOSVersion();
        getAllApps(getApplicationContext());

    }

    public void getCurrentOSVersion(){
        int version = Build.VERSION.SDK_INT;
        String versionRelease = Build.VERSION.RELEASE;
        TextView currentOSVersion = findViewById(R.id.txtVal_currentOS);
        currentOSVersion.setText(versionRelease);
    }

    public void readCalendar(){

    }

    public void getAllApps(Context context){
        PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> applist = packageManager.getInstalledPackages(PackageManager.GET_PERMISSIONS);
        for (PackageInfo app:applist) {
            if(app.packageName == "com.google.android.youtube")
            Log.e("Apps","App Name:"+app.packageName
                +"\n App Info "+ app.applicationInfo
                    +"\n App Permission "+ app.permissions
                    +"\n App RequestedPermissions "+ printLoops(app.requestedPermissions)
                    +"\n App RequestedPermissionsFlags "+ printLoops(app.requestedPermissionsFlags));
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
            readCalendar();
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
