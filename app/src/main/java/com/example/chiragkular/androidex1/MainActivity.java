package com.example.chiragkular.androidex1;

import android.Manifest;
import android.app.DownloadManager;
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

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
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

//    public void sendMessage(View view){
//        Intent intent = new Intent(this, DisplayMessageActivity.class);
//        EditText editText = (EditText) findViewById(R.id.editText);
//        String message = editText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, message);
//        startActivity(intent);
//    }

    public void analyzeSystem(View view) throws IOException {

//        DeviceSecure ds = new DeviceSecure();
//        int deviceSecure_Score = ds.DeviceSecureAnalysis();
//        TextView score_deviceSecure = findViewById(R.id.txtScore_deviceSecure);
//        score_deviceSecure.setText(String.valueOf(deviceSecure_Score));
//
//        DeviceVersion dv= new DeviceVersion();
//        int deviceVersion_Score = dv.DeviceVersionAnalysis();
//        TextView score_deviceVersion = findViewById(R.id.txtScore_deviceVersion);
//        score_deviceVersion.setText(String.valueOf(deviceVersion_Score));
        getAllApps(getApplicationContext());
        //parseHTML("com.google.android.youtube");
        //parseHTML("com.igorkh.trustcheck.securitycheck");
        //new PlayStoreScraping().execute();
    }

    public void parseHTML2(String pkgName){
//        OkHttpClient client = new OkHttpClient();
//
//        DownloadManager.Request request = new DownloadManager.Request.Builder()
//                .url("https://api.apptweak.com/android/applications/com.google.android.youtube.json?country=us&language=en")
//                .get()
//                .addHeader("x-apptweak-key", "wXmJE3huo2WZtpA8-B1Sn2koG2c")
//                .addHeader("cache-control", "no-cache")
//                .addHeader("postman-token", "c65b9b60-ff7d-5e60-ba71-ffc05442a26f")
//                .build();
//
//        Response response = client.newCall(request).execute();
    }
//    public void parseHTML(String pkgName) throws IOException {
//
//        Document doc = Jsoup.connect("https://play.google.com/store/apps/details?id="+pkgName)
//                //.timeout(60000)
////                .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
////                .referrer("http://www.google.com")
//                .get();
//        Log.i("Doc ",doc.title());
////        Elements newsHeadlines = doc.select("div.pf5lIe");
////        for (Element headline : newsHeadlines) {
////            //Log.i("%s\n\t%s",newsHeadlines.get(0).attr("aria-label"));
////            Log.e("headline",headline.data());
////        }
//    }


    public void getAllApps(Context context){

        final PackageManager pm = getPackageManager();
        final List<ApplicationInfo> installedApps = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        for ( ApplicationInfo app : installedApps ) {
            //Details:
            //Log.d(TAG, "Package: " + app.packageName);
            Log.e("", app.packageName);
            //Log.d(TAG, "UID: " + app.uid);
            //Log.d(TAG, "Directory: " + app.sourceDir);

//            //Permissions:
//            StringBuffer permissions = new StringBuffer();
//
//            try {
//                PackageInfo packageInfo = pm.getPackageInfo(app.packageName, PackageManager.GET_PERMISSIONS);
//                String[] requestedPermissions = packageInfo.requestedPermissions;
//                if (requestedPermissions != null) {
//                    for (int i = 0; i < requestedPermissions.length; i++) {
//                        permissions.append(requestedPermissions[i] + "\n");
//                        String a = requestedPermissions[i];
//                        //Log.i("-Requested Permissions",a);
//                        if (a.contains("android.permission.")) {
//                            PermissionInfo pi = getPackageManager().getPermissionInfo(a, PackageManager.GET_META_DATA);
//                            Log.i("--Permission Info",String.valueOf(pi.protectionLevel));
//                            String protctionLevel;
//                            switch (pi.protectionLevel) {
//                                case PermissionInfo.PROTECTION_NORMAL:
//                                    protctionLevel = "normal";
//                                    break;
//                                case PermissionInfo.PROTECTION_DANGEROUS:
//                                    protctionLevel = "dangerous";
//                                    break;
//                                case PermissionInfo.PROTECTION_SIGNATURE:
//                                    protctionLevel = "signature";
//                                    break;
//                                default:
//                                    protctionLevel = "<unknown>";
//                                    break;
//                            }
//                            Log.e("--Protection Level", pi.name + ": " + protctionLevel);
//                            //list_permission.add(a+"        "+protctionLevel);
//                        }
//                        //Log.d(TAG, "Permissions: " + permissions);
//                    }
//                }
//            }
//            catch ( PackageManager.NameNotFoundException e) {
//                e.printStackTrace();
//            }
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
