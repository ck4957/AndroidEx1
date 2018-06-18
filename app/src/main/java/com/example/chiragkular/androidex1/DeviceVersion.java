package com.example.chiragkular.androidex1;

import android.os.Build;
import android.util.Log;
import android.widget.TextView;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import static android.os.Build.VERSION.SECURITY_PATCH;
import static com.example.chiragkular.androidex1.MyConstants.*;

/**
 * This class reads the current OS version and Security Patch date
 * and claculates a score based on this two values
 */
public class DeviceVersion {

    public static int DEVICE_VERSION_SCORE = -1;

    int currentVersion;

    /**
     * Read the Current OS version of the system where app is installed
     * @return
     */
    public int getCurrentOSVersion(){
        int version = Build.VERSION.SDK_INT;
        String versionRelease = Build.VERSION.RELEASE;
//        TextView currentOSVersion = MyConstants.getmActivity().findViewById(R.id.txtVal_currentOS);
//        currentOSVersion.setText(versionRelease);
        return version;
    }

    /**
     * Gets the security patch of the system and evaluates the score based on the values
     * @return
     */
    public int checkSecurityPatchDuration()
    {
        LocalDate today = LocalDate.now();
        LocalDate patchDate = LocalDate.parse(SECURITY_PATCH, DateTimeFormatter.ISO_LOCAL_DATE);
        long diff = ChronoUnit.MONTHS.between(patchDate.withDayOfMonth(1), today.withDayOfMonth(1));

        if(diff <=_6_MONTHS)
            DEVICE_VERSION_SCORE = LOW_RISK;
        else if(diff <= _12_MONTHS)
            DEVICE_VERSION_SCORE = MED_RISK;
        else
            DEVICE_VERSION_SCORE = HIGH_RISK;

        return DEVICE_VERSION_SCORE;



    }

    /**
     * Calculating score based on OS Version
     * @return
     */
    public int DeviceVersionAnalysis(){
        currentVersion = getCurrentOSVersion();

        if(currentVersion >= API_LEVEL_27)
            DEVICE_VERSION_SCORE = LOW_RISK;
        else if(currentVersion >= API_LEVEL_26)
            DEVICE_VERSION_SCORE = MED_RISK;
        else
            DEVICE_VERSION_SCORE = HIGH_RISK;

        return DEVICE_VERSION_SCORE;
    }

}
