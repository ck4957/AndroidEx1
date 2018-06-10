package com.example.chiragkular.androidex1;

import android.os.Build;
import android.util.Log;
import android.widget.TextView;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import static android.os.Build.VERSION.SECURITY_PATCH;

public class DeviceVersion {

    public static int DEVICE_VERSION_SCORE = -1;

    int currentVersion;

    public int getCurrentOSVersion(){
        int version = Build.VERSION.SDK_INT;
        String versionRelease = Build.VERSION.RELEASE;
        TextView currentOSVersion = MyConstants.getmActivity().findViewById(R.id.txtVal_currentOS);
        currentOSVersion.setText(versionRelease);
        return version;
    }
    public int checkSecurityPatchDuration()
    {
        LocalDate today = LocalDate.now();
        LocalDate patchDate = LocalDate.parse(SECURITY_PATCH, DateTimeFormatter.ISO_LOCAL_DATE);
        //LocalDate date2 = LocalDate.parse(todayDate, DateTimeFormatter.ISO_LOCAL_DATE);
        long diff = ChronoUnit.MONTHS.between(patchDate.withDayOfMonth(1), today.withDayOfMonth(1));

        if(diff <= MyConstants._6_MONTHS)
            DEVICE_VERSION_SCORE = MyConstants.LOW_RISK;
        else if(diff <= MyConstants._12_MONTHS)
            DEVICE_VERSION_SCORE = MyConstants.MED_RISK;
        else
            DEVICE_VERSION_SCORE = MyConstants.HIGH_RISK;

        return DEVICE_VERSION_SCORE;



    }

    public int DeviceVersionAnalysis(){
        currentVersion = getCurrentOSVersion();

        if(currentVersion >= MyConstants.API_LEVEL_27)
            DEVICE_VERSION_SCORE = MyConstants.LOW_RISK;
        else if(currentVersion >= MyConstants.API_LEVEL_26)
            DEVICE_VERSION_SCORE = MyConstants.MED_RISK;
        else
            DEVICE_VERSION_SCORE = MyConstants.HIGH_RISK;

        return DEVICE_VERSION_SCORE;
    }

}
