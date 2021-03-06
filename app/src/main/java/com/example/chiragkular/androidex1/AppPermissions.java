package com.example.chiragkular.androidex1;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static com.example.chiragkular.androidex1.MyConstants.*;

public class AppPermissions {
    List<AppDetails> appList = new ArrayList<AppDetails>();
    List<String> normalApp = new ArrayList<String>();
    List<String> dangerousApp = new ArrayList<String>();
    List<String> signatureApp = new ArrayList<String>();

    /**
     * Method creates a list of all the application names and their permission
     * and set its protection level and corresponding score
     */
    public void getAllApps()
    {
        final PackageManager pkgMngr = MyConstants.getmContext().getPackageManager();
        final List<ApplicationInfo> appsList = pkgMngr.getInstalledApplications(PackageManager.GET_META_DATA);

        for (ApplicationInfo appInfo : appsList) {
            AppDetails appDetails = new AppDetails();
            appDetails.setAppName(appInfo.packageName);
            //Permissions:
            StringBuffer allAppsPermissions = new StringBuffer();
            try {
                PackageInfo pkgInfo = pkgMngr.getPackageInfo(appInfo.packageName, PackageManager.GET_PERMISSIONS);
                String[] requestedPermissions = pkgInfo.requestedPermissions;
                if (requestedPermissions != null) {
                    for (int i = 0; i < requestedPermissions.length; i++) {
                        allAppsPermissions.append(requestedPermissions[i] + "\n");
                        String singlePermission = requestedPermissions[i];
                        if (singlePermission.contains("android.permission.")) {
                            PermissionInfo permInfo = MyConstants.getmContext().getPackageManager().getPermissionInfo(singlePermission, PackageManager.GET_META_DATA);
                            if(permInfo!=null) {
                                switch (permInfo.protectionLevel)
                                {
                                    case PermissionInfo.PROTECTION_NORMAL:
                                        appDetails.normalPermissionCount++;
                                        break;
                                    case PermissionInfo.PROTECTION_DANGEROUS:
                                        appDetails.dangerPermissionCount++;
                                        break;
                                    case PermissionInfo.PROTECTION_SIGNATURE:
                                        appDetails.signaturePermissionCount++;
                                        break;
                                    default:
                                        break;
                                }
                            }
                        }
                    }
                }
                String applicationStatus = appDetails.getApplicationStatus();
                if(applicationStatus.equals(PROTECTION_NORMAL))
                {
                    appDetails.setStatus(PROTECTION_NORMAL);
                    appDetails.setScore(PROTECTION_NORMAL);
                }
                else if(applicationStatus.equals(PROTECTION_DANGEROUS))
                {
                    appDetails.setStatus(PROTECTION_DANGEROUS);
                    appDetails.setScore(PROTECTION_DANGEROUS);
                }
                else {
                    appDetails.setStatus(PROTECTION_SIGNATURE);
                    appDetails.setScore(PROTECTION_SIGNATURE);
                }
                appList.add(appDetails);
            }
            catch(PackageManager.NameNotFoundException e) {
                //e.printStackTrace();
            }
        }

    }

    /**
     * Method classify each application into categories: normal, dangerous and Signature App
     */
    public void appClassification()
    {
        AppDetails apd;
        String name,status;
        for(int i=0;i<appList.size();i++)
        {
            apd = appList.get(i);
            name = apd.getAppName();
            status = apd.getStatus();
            if(status.equals(PROTECTION_NORMAL))
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

    /**
     * Method aggregates the score of each App
     * and evaluates the total score as low, medium or high risk
     * @return
     */
    public String evaluateSystem()
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
            return MyConstants.MEDRISK;
        else if (totalScore>totalApp)
            return MyConstants.HIGHRISK;
        else
            return MyConstants.LOWRISK_;
    }

    /**
     * Log the App name and its corresponding score
     */
    public void print()
    {
        AppDetails apd;
        for(int i=0;i<normalApp.size();i++)
        {
            //apd = appList.get(i);
            //Log.e("AppName: ",apd.appName+ "Score: "+apd.appScore);
            Log.e("App",normalApp.get(i));
        }
    }
}
