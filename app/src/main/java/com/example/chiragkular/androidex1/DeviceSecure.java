package com.example.chiragkular.androidex1;
import android.app.KeyguardManager;
import android.content.Context;
import android.provider.Settings;
import android.util.Log;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;


public class DeviceSecure {

    public static int DEVICE_SECURE_SCORE = -1;

    /**
     * Check if device is locked by reading system values
     * @return boolean value
     */
    public boolean IsDeviceLocked(){
        KeyguardManager km = (KeyguardManager) MyConstants.getmContext().getSystemService(Context.KEYGUARD_SERVICE);
        boolean deviceSecureFlag = km.isDeviceSecure();
//        TextView deviceSecure = MyConstants.getmActivity().findViewById(R.id.txtVal_deviceSecure);
//        deviceSecure.setText(String.valueOf(deviceSecureFlag));
        return deviceSecureFlag;
    }

    /**
     * Gets the screen off time out in milliseconds
     * @return
     */
    public long getTimeout() {
        int timeOutinMS = Settings.System.getInt(MyConstants.getmContext().getContentResolver(), Settings.System.SCREEN_OFF_TIMEOUT,0);
        long timeOutinSec = TimeUnit.MILLISECONDS.toSeconds(timeOutinMS);
        Log.e("System TimeOut Time (ms)", String.valueOf(timeOutinSec));
        return timeOutinSec;
    }


    /**
     * Calculates the score based on device lock and screen timeout values
     * @return
     */
    public int DeviceSecureAnalysis(){
        long timeOut = -1;
        if(IsDeviceLocked()){
            // DEVICE is locked
            DEVICE_SECURE_SCORE = MyConstants.MED_RISK;

            // Now check for Screen Timeout
            try {
                timeOut = getTimeout();
            } catch (Exception e) {
                Log.e("ERROR in get Screen Off TimeOut ","Exception",e);
            }
            if(timeOut <= 15)
                DEVICE_SECURE_SCORE = MyConstants.LOW_RISK;
            else if(timeOut <=30)
                DEVICE_SECURE_SCORE = MyConstants.MED_RISK;
            else if(timeOut > 30)
                DEVICE_SECURE_SCORE = MyConstants.HIGH_RISK;
        }
        else{
            // Device is not Locked
            DEVICE_SECURE_SCORE = MyConstants.HIGH_RISK;
        }
        return  DEVICE_SECURE_SCORE;
    }
}
