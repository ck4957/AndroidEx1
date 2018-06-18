package com.example.chiragkular.androidex1;


import static com.example.chiragkular.androidex1.MyConstants.*;

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
            return PROTECTION_NORMAL;
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
        if(status == PROTECTION_NORMAL)
        {
            this.appScore = LOW_RISK;
        }
        else if(status == PROTECTION_DANGEROUS)
        {
            this.appScore = MED_RISK;
        }
        else {
            this.appScore = HIGH_RISK;
        }
    }


}
