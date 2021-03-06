package com.example.chiragkular.androidex1;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.view.animation.AccelerateInterpolator;

public class MyConstants {

    public final static String PLAYSTORE_JSON_FILE = "playStoreMetaData.json";
    public static final String RULES_TXT_FILE = "Rules.txt";

    public static final int LOW_RISK = 0;
    public static final int MED_RISK = 1;
    public static final int HIGH_RISK = 2;

    public static final String LOWRISK_ = "LOW RISK";
    public static final String MEDRISK = "MEDIUM RISK";
    public static final String HIGHRISK = "HIGH RISK";


    public static final String PROTECTION_NORMAL = "Normal";
    public static final String PROTECTION_DANGEROUS = "Dangerous";
    public static final String PROTECTION_SIGNATURE = "Signature";

    //public
    //Nougat N_MR1
    public static final int API_LEVEL_26 = 26;
    //Oreo O_MR1
    public static final int API_LEVEL_27 = 27;

    public static final int _6_MONTHS = 6;
    public static final int _12_MONTHS = 12;


    private static Context mContext;
    private static Activity mActivity;

    public MyConstants(Context context, Activity activity){
        mActivity = activity;
        mContext = context;
    }

    /**
     * Creates a common activity variable to be used across the project
     * @return activity
     */
    public static Activity getmActivity(){
        return mActivity;
    }
    /**
     * Creates a common context variable to be used across the project
     * @return context
     */
    public static Context getmContext(){
        return mContext;
    }

}
