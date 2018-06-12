package com.example.chiragkular.androidex1;

import android.util.Log;
import android.view.DragAndDropPermissions;

public class AppDetails
{
    int normalPermissionCount, dangerPermissionCount, signaturePermissionCount, appScore;
    String appName, appStatus;
    public AppDetails()
    {
        normalPermissionCount= 0;
        dangerPermissionCount =0;
        signaturePermissionCount =0;
        appName = "";
        appStatus = "";
        appScore=-1;
    }
    public String getApplicationStatus()
    {
        if (Math.max(Math.max(normalPermissionCount,dangerPermissionCount),signaturePermissionCount) == normalPermissionCount)
        {
            return MyConstants.PROTECTION_NORMAL;
        }
        else if (Math.max(Math.max(normalPermissionCount,dangerPermissionCount),signaturePermissionCount) == dangerPermissionCount)
        {
            return MyConstants.PROTECTION_DANGEROUS;
        }
        else
        {
            return MyConstants.PROTECTION_SIGNATURE;
        }
    }
    public void setStatus(String status)
    {
        this.appStatus = status;
    }
    public void setAppName(String name)
    {
        this.appName = name;
    }
    public String getAppName()
    {
        return this.appName;
    }
    public String getStatus()
    {
        return this.appStatus;
    }
    public void setScore(String status)
    {
        if(status == MyConstants.PROTECTION_NORMAL)
        {
            this.appScore = MyConstants.LOW_RISK;
        }
        else if(status == MyConstants.PROTECTION_DANGEROUS)
        {
            this.appScore = MyConstants.MED_RISK;
        }
        else {
            this.appScore = MyConstants.HIGH_RISK;
        }
    }


}
