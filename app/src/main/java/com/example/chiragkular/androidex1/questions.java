package com.example.chiragkular.androidex1;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class questions extends AppCompatActivity {

    // Rule Names (RL)
    final String DEV_LOCK_RL = "DEV_LOCK";
    final String DEV_LOCK_TYPE_RL = "DEV_LOCK_TYPE";
    final String LOCK_TIMEOUT_RL = "LOCK_TIMEOUT";
    final String DEV_VER_RL = "DEV_VER";
    final String SEC_PATCH_RL = "SEC_PATCH";
    final String DEV_MODE_RL = "DEV_MODE";
    final String THIRD_PARTYAPP_RL = "THIRD_PARTYAPPS";
    final String DEV_LOC_RL = "DEV_LOCATION";

    final String DL_LT_SLT_RL = "DL_LT_SLT";
    final String DL_LT_RL = "DL_LT";
    final String DL_SLT_RL = "DL_SLT";
    final String DV_SP_RL = "DV_SP";
    final String DM_TP_RL = "DM_TP";

    // Condition Names (CN)
    final String DEV_LOCK_CN = "dev_lock";
    final String DEV_LOCK_TYPE_CN = "dev_LockType";
    final String LOCK_TIMEOUT_CN = "lock_timeout";
    final String DEV_VER_CN = "dev_ver";
    final String SEC_PATCH_CN = "sec_patch";
    final String DEV_MODE_CN = "dev_mode";
    final String THIRD_PARTYAPP_CN = "third_partyapps";
    final String DEV_LOC_CN = "dev_location";


    // Radio Groups (RG)
    RadioGroup dev_Lock_RG;
    RadioGroup lock_type_RG;
    RadioGroup lock_timeout_RG;

    RadioGroup devVer_RG;
    RadioGroup sec_patch_RG;

    RadioGroup devMode_RG;
    RadioGroup third_party_RG;

    RadioGroup devLoc_RG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        dev_Lock_RG=(RadioGroup)findViewById(R.id.devLock_RG);
        lock_type_RG=(RadioGroup)findViewById(R.id.lockType_RG);
        lock_timeout_RG=(RadioGroup)findViewById(R.id.LockTimeOut_RG);

        devVer_RG=(RadioGroup)findViewById(R.id.DevVer_RG);
        sec_patch_RG=(RadioGroup)findViewById(R.id.SecurityPatchRadio);

        devMode_RG=(RadioGroup)findViewById(R.id.DeveloperModeRadio);
        third_party_RG=(RadioGroup)findViewById(R.id.NumberOfThirdPartyApp);

        devLoc_RG=(RadioGroup)findViewById(R.id.DeviceLocation_RG);
    }

    public void evaluateButton(View view) {

        List<Rule> Rules = null;
        try {
            Rules = Rule.parseRules();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Rule> Facts = createFactsFromUserInput();
        print_PermAppCategoryList();
        int score = calculateScore(Rules, Facts);
        printOverallAppScore(score);

    }


    public void onRadioButtonClickedDevLock(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.deviceYes:
                if (checked) {
                    EnableRadioGroup(lock_type_RG,true);
                    EnableRadioGroup(lock_timeout_RG,true);
                }
                break;
            case R.id.deviceNo:
                if (checked) {
                    EnableRadioGroup(lock_type_RG, false);
                    EnableRadioGroup(lock_timeout_RG, false);
                }
                break;
        }

    }


    public void onRadioButtonDeviceVer(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.ver71:
                if (checked)
                    EnableRadioGroup(sec_patch_RG,true);
                break;
            case R.id.ver80:
                if (checked)
                    EnableRadioGroup(sec_patch_RG,true);
                break;
            case R.id.ver81:
                if (checked){
                    EnableRadioGroup(sec_patch_RG,false);
                }
                break;
        }

    }

    public void onRadioButtonDeveloperMode(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.devmodeOff:
                if (checked)
                    EnableRadioGroup(third_party_RG,false);
                    //Log.e("dev mode off "," Score:0");
                break;
            case R.id.devmodeOn:
                if (checked)
                    EnableRadioGroup(third_party_RG,true);
                    //Log.e("dev mode on "," Score:2");
                break;
        }
    }


    public int calculateScore(List<Rule> Rules, List<Rule> Facts){
        int total_score = 0;
        for(Rule fact:Facts) {
            for (Rule rule : Rules) {
                if (fact.rule_name.equals(rule.rule_name)){
                    int count = 0;
                    for(Rule.Condition fact_cond: fact.conditions) {
                        for (Rule.Condition rule_cond : rule.conditions) {
                            if (fact_cond.cond_name.equals(rule_cond.cond_name)
                                && fact_cond.cond_val.equals(rule_cond.cond_val)) {
                                count++;
                                Log.d("Rules",fact.rule_name+" "+rule_cond.cond_name+" "+rule_cond.cond_val+" "+String.valueOf(rule.action.score));
                                Log.d("Counter",String.valueOf(count));
                                //total_score += rule.action.score;

                            }
                        }
                    }
                    if(count==fact.conditions.size()){
                        //Log.d("Score",fact.rule_name+" "+rule_cond.cond_name+" "+rule_cond.cond_val+" "+String.valueOf(rule.action.score));
                        total_score += rule.action.score;
                        Log.d("Total Sc",String.valueOf(total_score));

                    }
                }
            }
        }

        Log.d("Score:",String.valueOf(total_score));
        return total_score;
    }


    public List<Rule> createFactsFromUserInput(){
        List<Rule> facts = new ArrayList<>();


        if(dev_Lock_RG.getCheckedRadioButtonId() != -1){
            int btnId = dev_Lock_RG.getCheckedRadioButtonId();
            String value1 = ((RadioButton)findViewById(btnId)).getText().toString();

            if(btnId == R.id.deviceYes){
                if(lock_timeout_RG.getCheckedRadioButtonId() != -1 &&
                        lock_type_RG.getCheckedRadioButtonId() != -1){
                    // Get DEV_Lock Checked value

                    int checkedRadioId2 = lock_type_RG.getCheckedRadioButtonId();
                    String value2 = ((RadioButton)findViewById(checkedRadioId2)).getText().toString();

                    int checkedRadioId3 = lock_timeout_RG.getCheckedRadioButtonId();
                    String value3 = ((RadioButton)findViewById(checkedRadioId3)).getText().toString();

                    Rule newFact = new Rule(DL_LT_SLT_RL);

                    Rule.Condition condition1 = newFact.new Condition(DEV_LOCK_CN, value1);
                    Rule.Condition condition2 = newFact.new Condition(DEV_LOCK_TYPE_CN, value2);
                    Rule.Condition condition3 = newFact.new Condition(LOCK_TIMEOUT_CN, value3);

                    newFact.conditions.add(condition1);
                    newFact.conditions.add(condition2);
                    newFact.conditions.add(condition3);
                    facts.add(newFact);
                }
                else if(lock_type_RG.getCheckedRadioButtonId() != -1){
                    int checkedRadioId2 = lock_type_RG.getCheckedRadioButtonId();
                    String value2 = ((RadioButton)findViewById(checkedRadioId2)).getText().toString();

                    Rule newFact = new Rule(DL_LT_RL);

                    Rule.Condition condition1 = newFact.new Condition(DEV_LOC_CN, value1);
                    Rule.Condition condition2 = newFact.new Condition(DEV_LOCK_TYPE_CN, value2);

                    newFact.conditions.add(condition1);
                    newFact.conditions.add(condition2);
                    facts.add(newFact);


                }
                else if(lock_timeout_RG.getCheckedRadioButtonId() != -1){
                    int checkedRadioId3 = lock_timeout_RG.getCheckedRadioButtonId();
                    String value3 = ((RadioButton)findViewById(checkedRadioId3)).getText().toString();

                    Rule newFact = new Rule(DL_SLT_RL);
                    Rule.Condition condition1 = newFact.new Condition(DEV_LOC_CN, value1);
                    Rule.Condition condition3 = newFact.new Condition(LOCK_TIMEOUT_CN, value3);
                    newFact.conditions.add(condition1);
                    newFact.conditions.add(condition3);
                    facts.add(newFact);

                }
            }
            else //(dev_Lock_RG.getCheckedRadioButtonId() == R.id.deviceNo)
                AddSingleNewFact(dev_Lock_RG, facts, DEV_LOCK_RL, DEV_LOCK_CN);
        }

//        if(lock_timeout_RG.getCheckedRadioButtonId() != -1){
//            AddSingleNewFact(lock_timeout_RG, facts, LOCK_TIMEOUT_RL, LOCK_TIMEOUT_CN);
//        }
//
//        if(lock_type_RG.getCheckedRadioButtonId() != -1){
//            AddSingleNewFact(lock_type_RG, facts, DEV_LOCK_TYPE_RL, DEV_LOCK_TYPE_CN);
//        }

        if(devVer_RG.getCheckedRadioButtonId() != -1){
            int btnId = devVer_RG.getCheckedRadioButtonId();
            String value1 = ((RadioButton)findViewById(btnId)).getText().toString();

            if(btnId == R.id.ver71 || btnId == R.id.ver80) {
                if(sec_patch_RG.getCheckedRadioButtonId() != -1) {
                    int checkedRadioId2 = sec_patch_RG.getCheckedRadioButtonId();
                    String value2 = ((RadioButton)findViewById(checkedRadioId2)).getText().toString();
                    Rule newFact = new Rule(DV_SP_RL);

                    Rule.Condition condition1 = newFact.new Condition(DEV_VER_CN, value1);
                    Rule.Condition condition2 = newFact.new Condition(SEC_PATCH_CN, value2);

                    newFact.conditions.add(condition1);
                    newFact.conditions.add(condition2);
                    facts.add(newFact);
                }
            }
            else
                AddSingleNewFact(devVer_RG, facts, DEV_VER_RL, DEV_VER_CN);
        }

//        if(sec_patch_RG.getCheckedRadioButtonId() != -1){
//            AddSingleNewFact(sec_patch_RG, facts, SEC_PATCH_RL, SEC_PATCH_CN);
//        }

        if(devMode_RG.getCheckedRadioButtonId() != -1){
            int btnId = devMode_RG.getCheckedRadioButtonId();
            String value1 = ((RadioButton)findViewById(btnId)).getText().toString();

            if(btnId == R.id.devmodeOn) {
                if(third_party_RG.getCheckedRadioButtonId() != -1) {
                    int checkedRadioId2 = third_party_RG.getCheckedRadioButtonId();
                    String value2 = ((RadioButton)findViewById(checkedRadioId2)).getText().toString();
                    Rule newFact = new Rule(DM_TP_RL);

                    Rule.Condition condition1 = newFact.new Condition(DEV_MODE_CN, value1);
                    Rule.Condition condition2 = newFact.new Condition(THIRD_PARTYAPP_CN, value2);

                    newFact.conditions.add(condition1);
                    newFact.conditions.add(condition2);
                    facts.add(newFact);
                }
            }
            else
                AddSingleNewFact(devMode_RG, facts, DEV_MODE_RL, DEV_MODE_CN);
        }

//        if(third_party_RG.getCheckedRadioButtonId() != -1){
//            AddSingleNewFact(third_party_RG, facts, THIRD_PARTYAPP_RL, THIRD_PARTYAPP_CN);
//        }

        if(devLoc_RG.getCheckedRadioButtonId() != -1){
            AddSingleNewFact(devLoc_RG, facts, DEV_LOC_RL, DEV_LOC_CN);
        }
        Log.d("facts",facts.toString());
        for(Rule fact:facts)
        Log.d("facts",fact.conditions.toString());
        return facts;
    }

    public void EnableRadioGroup(RadioGroup rg, boolean enableFlag){
        for (int i = 0; i < rg.getChildCount(); i++) {
            rg.getChildAt(i).setEnabled(enableFlag);
        }
    }

    public void AddSingleNewFact(RadioGroup rg, List<Rule> Facts, String rule_name, String cond_name){
        int checkedRadioId = rg.getCheckedRadioButtonId();
        String value = ((RadioButton)findViewById(checkedRadioId)).getText().toString();
        Rule newFact = new Rule(rule_name);
        Rule.Condition condition = newFact.new Condition(cond_name,value);
        newFact.conditions.add(condition);
        Facts.add(newFact);

    }

    public void resetAllQuestions(View view){
        dev_Lock_RG.clearCheck();
        EnableRadioGroup(dev_Lock_RG,true);
        lock_type_RG.clearCheck();
        EnableRadioGroup(lock_type_RG,true);
        lock_timeout_RG.clearCheck();
        EnableRadioGroup(lock_timeout_RG,true);
        devVer_RG.clearCheck();
        EnableRadioGroup(devVer_RG,true);
        sec_patch_RG.clearCheck();
        EnableRadioGroup(sec_patch_RG,true);
        devMode_RG.clearCheck();
        EnableRadioGroup(devMode_RG,true);
        third_party_RG.clearCheck();
        EnableRadioGroup(third_party_RG,true);
        devLoc_RG.clearCheck();
        EnableRadioGroup(devLoc_RG,true);
        TextView norm = findViewById(R.id.txt_normalApps);
        norm.setText("0");
        TextView dng = findViewById(R.id.txt_dangerApps);
        dng.setText("0");
        TextView sign = findViewById(R.id.txt_sigApps);
        sign.setText("0");
        TextView score_txt = findViewById(R.id.txt_SysScoreVal);
        score_txt.setText("0");
        TextView appScore_txt = findViewById(R.id.txt_AppScoreVal);
        appScore_txt.setText(String.valueOf("0"));
    }

    public void print_PermAppCategoryList()
    {
        AppPermissions ap = new AppPermissions();
        ap.getAllApps();
        ap.appClassification();
        /*
        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, ap.normalApp);

        ListView listView = (ListView) findViewById(R.id.listViewApp);
        listView.setAdapter(adapter);
        ArrayAdapter adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, ap.dangerousApp);

        ListView listView1 = (ListView) findViewById(R.id.dangerAppView);
        listView1.setAdapter(adapter1);
        */

        TextView norm = findViewById(R.id.txt_normalApps);
        norm.setText(String.valueOf(ap.normalApp.size()));
        TextView dng = findViewById(R.id.txt_dangerApps);
        dng.setText(String.valueOf(ap.dangerousApp.size()));
        TextView sign = findViewById(R.id.txt_sigApps);
        sign.setText(String.valueOf(ap.signatureApp.size()));

        // Print App Security Score
        String status = ap.evaluateSystem();
        TextView score_txt = findViewById(R.id.txt_AppScoreVal);
        if(status == MyConstants.LOWRISK_) {
            score_txt.setText(String.valueOf(status));
            score_txt.setTextColor(Color.GREEN);
        }
        else if(status == MyConstants.MEDRISK){
            score_txt.setText(String.valueOf(status));
            score_txt.setTextColor(Color.BLUE);

        }
        else if(status == MyConstants.HIGHRISK){
            score_txt.setText(String.valueOf(status));
            score_txt.setTextColor(Color.RED);
        }
        //return permAppScore;

    }
    public void printOverallAppScore(int score) {
        int total_score=score;
        String status = "";
        TextView score_txt = findViewById(R.id.txt_SysScoreVal);

        if(total_score<2) {
            status = MyConstants.LOWRISK_;
            score_txt.setText(String.valueOf(status));
            score_txt.setTextColor(Color.GREEN);
        }
        else if(total_score<5) {
            status = MyConstants.MEDRISK;
            score_txt.setText(String.valueOf(status));
            score_txt.setTextColor(Color.BLUE);

        }
        else if(total_score >= 5){
            status = MyConstants.HIGHRISK;
            score_txt.setText(String.valueOf(status));
            score_txt.setTextColor(Color.RED);
        }
        else{
            status = "ERROR. Reset and Re-evaluate";
        }

    }

}
