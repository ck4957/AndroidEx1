package com.example.chiragkular.androidex1;


import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayStoreRating {

    public Map getAppRatings() throws IOException, JSONException {
        InputStream is = MyConstants.getmActivity().getAssets().open(MyConstants.PLAYSTORE_JSON_FILE);
        Map<String, Float> AppRatings = new HashMap<String, Float>();
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();
        //Log.d("--->",MyConstants.getmContext().getAssets().list(path.getPath());
        JSONArray jsonData = new JSONArray(new String(buffer, "UTF-8"));
        for(int i=0; i<jsonData.length();i++){
            JSONObject obj = jsonData.getJSONObject(i);
            JSONObject ratingsObj = obj.getJSONObject("ratings");
            AppRatings.put(obj.getString("application_id"), BigDecimal.valueOf(ratingsObj.getDouble("average")).floatValue());
        }
        return AppRatings;
    }

    public int getRatingScore(float val){
        int score=-1;
        if (val >= 4.0)
            score = MyConstants.LOW_RISK;
        else if(val >2.5 && val<4.0)
            score = MyConstants.MED_RISK;
        else
            score = MyConstants.HIGH_RISK;
        return score;

    }
    public void AppRatingsAnalysis() throws IOException, JSONException {
        Map<String, Float> AppRatings = getAppRatings();
        for (Map.Entry<String,Float> entry: AppRatings.entrySet()) {
            String appName = entry.getKey();
            Float appRating = entry.getValue();
            Log.d("Ratings","App: "+appName+", Rating: "+appRating+", Rating Risk: "+getRatingScore(appRating));
        }
    }



}
