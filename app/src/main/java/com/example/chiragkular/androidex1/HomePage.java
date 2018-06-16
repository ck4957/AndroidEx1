package com.example.chiragkular.androidex1;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.jsoup.helper.StringUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        MyConstants mc = new MyConstants(HomePage.this, this);
        Intent intent = getIntent();

    }

    /*public void homeTransit()
    {
        TextView displayMsg = findViewById(R.id.textDisplayMsg);
        displayMsg.setText("To know your system security score HIT Evaluate");

    }*/
    public void analyzeSystem(View view) throws Settings.SettingNotFoundException, IOException, JSONException {

        DeviceSecure ds = new DeviceSecure();
        int deviceSecure_Score = ds.DeviceSecureAnalysis();
        TextView score_deviceSecure = findViewById(R.id.txtScore_deviceSecure);
        score_deviceSecure.setText(String.valueOf(deviceSecure_Score));

        DeviceVersion dv= new DeviceVersion();
        int deviceVersion_Score = dv.DeviceVersionAnalysis();
        TextView score_deviceVersion = findViewById(R.id.txtScore_deviceVersion);
        score_deviceVersion.setText(String.valueOf(deviceVersion_Score));
        //getAllApps(getApplicationContext());
//        AppPermissions perm = new AppPermissions();
//        perm.getAllApps();
//        perm.print();
//        Log.e("SystemEvaluation",String.valueOf(perm.evaluateSystem()));
//
//        PlayStoreRating psr = new PlayStoreRating();
//        psr.AppRatingsAnalysis();
        List<String> lines = new ArrayList<>();
        lines = ReadRules();
        parseRules(lines);

    }

    public void TransitToListApp(View view) {
        Intent intent = new Intent(this, listApp.class);
        startActivity(intent);
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.deviceYes:
                if (checked)
                    Log.e("lock: Yes"," Score:0");
                    break;
            case R.id.deviceNo:
                if (checked)
                    Log.e("lock: No"," Score:2");
                    break;
        }

    }

    public List<String> ReadRules() throws IOException {
//        File externalDir = Environment.getExternalStorageDirectory();
//        Log.d("External File Dir",externalDir.toString());
//// Get /storage/emulated/0/Music folder.
//        File DCIM = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
//        Log.d("DCIM",DCIM.toString());
        String str = "";
        List<String> lines = new ArrayList<>();
        StringBuffer sf = new StringBuffer();
        InputStream is = MyConstants.getmActivity().getAssets().open("Rules.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        while ((str = br.readLine()) != null) {
            lines.add(str);
        }
        Log.d("TAG",sf.toString());
        return lines;
    }

    public void parseRules(List<String> lines){
        List<Rule> rules = new ArrayList<>();

        for (String line: lines) {
            if(line.startsWith("rule")){
                Rule newRule = new Rule();
                //Get Rule Name
                int secondSpace = StringUtils.ordinalIndexOf(line," ",2);
                newRule.rule_name = line.substring(line.indexOf(" "),secondSpace);
                //newRule.conditions.add();
                //List<Rule.Condition> cond_list = new ArrayList<>();
                String conditions = StringUtils.substringBetween(line,"if "," then");

                if(conditions.contains("&")){
                    String[] temp_condn_list = conditions.split("&");

                    for (String condition:temp_condn_list) {
                        Rule.Condition eachCondition = newRule.new Condition();
                        eachCondition.cond_name =  condition.split("=")[0].trim();
                        eachCondition.cond_val = condition.split("=")[1].trim();
                        newRule.conditions.add(eachCondition);
                    }
                }
                else{
                    //only one condtion
                    Rule.Condition eachCondition = newRule.new Condition();
                    eachCondition.cond_name =  conditions.split("=")[0].trim();
                    eachCondition.cond_val = conditions.split("=")[1].trim();
                    //cond_obj.add(eachCondition);
                    newRule.conditions.add(eachCondition);

                }
                //Get the action score
                newRule.action.score = Integer.parseInt(StringUtils.substringAfterLast(line,"=").trim());
                rules.add(newRule);
            }
        }

        for(Rule rule:rules){
            Log.d("rule",rule.rule_name+" "
                    +String.valueOf(rule.action.score));
            for(Rule.Condition cond:rule.conditions){
                Log.d("Cond",cond.cond_name+" "
                        +cond.cond_val+" ");
            }
        }
    }
}
