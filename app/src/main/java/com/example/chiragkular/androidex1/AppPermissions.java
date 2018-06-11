package com.example.chiragkular.androidex1;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class AppPermissions {
    List<AppDetails> appList = new ArrayList<AppDetails>();
    List<String> normalApp = new ArrayList<String>();
    List<String> dangerousApp = new ArrayList<String>();
    List<String> signatureApp = new ArrayList<String>();
    public void getAllApps()
    {
        final PackageManager pm = MyConstants.getmContext().getPackageManager();
        final List<ApplicationInfo> installedApps = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        for ( ApplicationInfo app : installedApps ) {
            //Details:
            AppDetails apd =new AppDetails();
            apd.setName(app.packageName);
            //Log.d("packageName", "Package: " + app.packageName);
            //Log.d(TAG, "UID: " + app.uid);
            //Log.d(TAG, "Directory: " + app.sourceDir);

            //Permissions:
            StringBuffer permissions = new StringBuffer();

            try {
                PackageInfo packageInfo = pm.getPackageInfo(app.packageName, PackageManager.GET_PERMISSIONS);
                String[] requestedPermissions = packageInfo.requestedPermissions;
                if (requestedPermissions != null) {
                    int countNormal = 0, countDanger = 0, countSignature = 0;
                    for (int i = 0; i < requestedPermissions.length; i++) {
                        permissions.append(requestedPermissions[i] + "\n");
                        String a = requestedPermissions[i];
                        //Log.i("-Requested Permissions",a);
                        if (a.contains("android.permission.")) {
                            PermissionInfo pi = MyConstants.getmContext().getPackageManager().getPermissionInfo(a, PackageManager.GET_META_DATA);
                            if(pi!=null) {
                                //Log.i("--Permission Info",String.valueOf(pi.protectionLevel));
                                String protctionLevel;

                                switch (pi.protectionLevel) {
                                    case PermissionInfo.PROTECTION_NORMAL:
                                        protctionLevel = MyConstants.PROTECTION_NORMAL;
                                        apd.normalPermissionCount++;
                                        break;
                                    case PermissionInfo.PROTECTION_DANGEROUS:
                                        protctionLevel = MyConstants.PROTECTION_DANGEROUS;
                                        apd.dangerPermissionCount++;
                                        break;
                                    case PermissionInfo.PROTECTION_SIGNATURE:
                                        protctionLevel = MyConstants.PROTECTION_SIGNATURE;
                                        apd.signaturePermissionCount++;
                                        break;
                                    default:
                                        protctionLevel = MyConstants.PROTECTION_UNKNOWN;
                                        break;
                                        }
                                //Log.e("--Protection Level", pi.name + ": " + protctionLevel);

                            }

                            //list_permission.add(a+"        "+protctionLevel);
                        }
                        //Log.d(TAG, "Permissions: " + permissions);
                    }

                }
                String  applicationStatus =apd.getApplicationStatus();
                if(applicationStatus.equals(MyConstants.PROTECTION_NORMAL))
                {
                    apd.setStatus(MyConstants.PROTECTION_NORMAL);
                    apd.setScore(MyConstants.PROTECTION_NORMAL);
                }
                else if(applicationStatus.equals(MyConstants.PROTECTION_DANGEROUS))
                {
                    apd.setStatus(MyConstants.PROTECTION_DANGEROUS);
                    apd.setScore(MyConstants.PROTECTION_DANGEROUS);
                }
                else {
                    apd.setStatus(MyConstants.PROTECTION_SIGNATURE);
                    apd.setScore(MyConstants.PROTECTION_SIGNATURE);
                }
                appList.add(apd);
            }
            catch ( PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        /*for(int i = 0; i<appIter;i++)
        {
            Log.e("App:",AppStatus[i][i]);
            Log.e("App:",AppStatus[i][1]);

        }*/
    }
    public void appClassification()
    {
        AppDetails apd;String name,status;
        for(int i=0;i<appList.size();i++)
        {
            apd = appList.get(i);
            name = apd.getName();
            status = apd.getStatus();
            if(status.equals(MyConstants.PROTECTION_NORMAL))
            {
                this.normalApp.add(name);
            }
            else if(status.equals(MyConstants.PROTECTION_DANGEROUS))
            {
                this.dangerousApp.add(name);
            }
            else {
                this.signatureApp.add(name);
            }
        }
    }
    public int evaluateSystem()
    {
        AppDetails apd;
        int totalApp = appList.size();
        int totalScore = -1;
        for(int i=0;i<totalApp;i++)
        {
            apd = appList.get(i);
            totalScore+=apd.appScore;
        }
        if (totalScore<=totalApp && totalScore!=0)
            return MyConstants.MED_RISK;
        else if (totalScore>totalApp)
            return MyConstants.HIGH_RISK;
        else
            return MyConstants.LOW_RISK;
    }
    public void print()
    {
        AppDetails apd;
        for(int i=0;i<appList.size();i++)
        {
            apd = appList.get(i);
            Log.e("AppName: ",apd.appName+ "Score: "+apd.appScore);
        }
    }
}
