package com.example.chiragkular.androidex1;


import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static com.example.chiragkular.androidex1.MyConstants.*;

public class PlayStoreRating {

    public Map getAppRatings() throws IOException, JSONException {
        InputStream is = getmActivity().getAssets().open(PLAYSTORE_JSON_FILE);
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
            score = LOW_RISK;
        else if(val >2.5 && val<4.0)
            score = MED_RISK;
        else
            score = HIGH_RISK;
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
