package com.example.chiragkular.androidex1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class questions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
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

    public void onRadioButtonDeviceVer(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.ver1:
                if (checked)
                    Log.e("version: Yes"," Score:2");
                break;
            case R.id.ver2:
                if (checked)
                    Log.e("version: No"," Score:1");
                break;
            case R.id.ver3:
                if (checked)
                    Log.e("version: No"," Score:0");
                break;
        }

    }

    public void onRadioButtonDeviceLocation(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.locYes:
                if (checked)
                    Log.e("location On: "," Score:0");
                break;
            case R.id.locNo:
                if (checked)
                    Log.e("location Off: "," Score:2");
                break;
        }

    }

    public void onRadioButtonDeviceTimeout(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.timeout1:
                if (checked)
                    Log.e("timeout>15 sec"," Score:0");
                break;
            case R.id.timeout2:
                if (checked)
                    Log.e("timeout >30 sec"," Score:1");
                break;
            case R.id.timeout3:
                if (checked)
                    Log.e("timeout > 60sec"," Score:2");
                break;
        }
    }

    public void onRadioButtonSecurityPatch(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.sec3Month:
                if (checked)
                    Log.e("sec less than 3 "," Score:0");
                break;
            case R.id.sec6Month:
                if (checked)
                    Log.e("sec less than 6"," Score:1");
                break;
            case R.id.sec12Month:
                if (checked)
                    Log.e("sec less than 12"," Score:2");
                break;
        }
    }

    public void onRadioButtonThirdPartyApp(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.thirdParty3:
                if (checked)
                    Log.e("apps > 3 "," Score:0");
                break;
            case R.id.thirdParty5:
                if (checked)
                    Log.e("apps > 5 "," Score:1");
                break;
            case R.id.thirdParty7:
                if (checked)
                    Log.e("apps > 7 "," Score:2");
                break;
        }

    }

    public void onRadioButtonDeveloperMode(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.devmodeOff:
                if (checked)
                    Log.e("dev mode off "," Score:0");
                break;
            case R.id.devmodeOn:
                if (checked)
                    Log.e("dev mode on "," Score:2");
                break;
        }
    }

    public void buttonForAppScreen(View view) {
        Intent intent = new Intent(this, listApp.class);
        startActivity(intent);


        List<Rule> Rules = null;
        try {
            Rules = Rule.parseRules();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Rule> Facts = createFactsFromUserInput();
        calculateScore(Rules, Facts);


    }

    public void calculateScore(List<Rule> Rules, List<Rule> Facts){
        int total_score = 0;
        for(Rule fact:Facts) {
            for (Rule rule : Rules) {
                if (fact.rule_name.equals(rule.rule_name)){
                    for(Rule.Condition fact_cond: fact.conditions) {
                        for (Rule.Condition rule_cond : rule.conditions) {
                            if (fact_cond.cond_name.equals(rule_cond.cond_name)
                                && fact_cond.cond_val.equals(rule_cond.cond_val))
                                total_score += rule.action.score;

                        }
                    }
                }
            }
        }
        Log.d("Score:",String.valueOf(total_score));

    }

    public List<Rule> createFactsFromUserInput(){
        List<Rule> facts = new ArrayList<>();

        RadioGroup rg1=(RadioGroup)findViewById(R.id.devLock_RG);
        RadioGroup rg2=(RadioGroup)findViewById(R.id.DevVer_RG);
        RadioGroup rg3=(RadioGroup)findViewById(R.id.DeviceLocation_RG);
        RadioGroup rg4=(RadioGroup)findViewById(R.id.LockTimeOut_RG);
        RadioGroup rg5=(RadioGroup)findViewById(R.id.SecurityPatchRadio);
        RadioGroup rg6=(RadioGroup)findViewById(R.id.NumberOfThirdPartyApp);
        RadioGroup rg7=(RadioGroup)findViewById(R.id.DeveloperModeRadio);

        if(rg1.getCheckedRadioButtonId() != -1){

            int checkedRadioId = rg1.getCheckedRadioButtonId();
            String value = ((RadioButton)findViewById(checkedRadioId)).getText().toString();

            Rule newFact = new Rule();
            newFact.rule_name = "DEV_LOCK";
            Rule.Condition condition = newFact.new Condition();
            condition.cond_name = "dev_lock";
            condition.cond_val = value;
            newFact.conditions.add(condition);
            facts.add(newFact);
        }

        if(rg2.getCheckedRadioButtonId() != -1){

            int checkedRadioId = rg2.getCheckedRadioButtonId();
            String value = ((RadioButton)findViewById(checkedRadioId)).getText().toString();

            Rule newFact = new Rule();
            newFact.rule_name = "DEV_VER";
            Rule.Condition condition = newFact.new Condition();
            condition.cond_name = "dev_ver";
            condition.cond_val = value;
            newFact.conditions.add(condition);
            facts.add(newFact);
        }

        if(rg3.getCheckedRadioButtonId() != -1){

            int checkedRadioId = rg3.getCheckedRadioButtonId();
            String value = ((RadioButton)findViewById(checkedRadioId)).getText().toString();

            Rule newFact = new Rule();
            newFact.rule_name = "DEV_LOCATION";
            Rule.Condition condition = newFact.new Condition();
            condition.cond_name = "dev_location";
            condition.cond_val = value;
            newFact.conditions.add(condition);
            facts.add(newFact);
        }

        if(rg4.getCheckedRadioButtonId() != -1){

            int checkedRadioId = rg4.getCheckedRadioButtonId();
            String value = ((RadioButton)findViewById(checkedRadioId)).getText().toString();

            Rule newFact = new Rule();
            newFact.rule_name = "LOCK_TIMEOUT";
            Rule.Condition condition = newFact.new Condition();
            condition.cond_name = "lock_timeout";
            condition.cond_val = value;
            newFact.conditions.add(condition);
            facts.add(newFact);
        }

        if(rg5.getCheckedRadioButtonId() != -1){

            int checkedRadioId = rg5.getCheckedRadioButtonId();
            String value = ((RadioButton)findViewById(checkedRadioId)).getText().toString();

            Rule newFact = new Rule();
            newFact.rule_name = "SEC_PATCH";
            Rule.Condition condition = newFact.new Condition();
            condition.cond_name = "sec_patch";
            condition.cond_val = value;
            newFact.conditions.add(condition);
            facts.add(newFact);
        }


        if(rg6.getCheckedRadioButtonId() != -1){

            int checkedRadioId = rg6.getCheckedRadioButtonId();
            String value = ((RadioButton)findViewById(checkedRadioId)).getText().toString();

            Rule newFact = new Rule();
            newFact.rule_name = "THIRD_PARTYAPPS";
            Rule.Condition condition = newFact.new Condition();
            condition.cond_name = "third_partyapps";
            condition.cond_val = value;
            newFact.conditions.add(condition);
            facts.add(newFact);
        }

        if(rg7.getCheckedRadioButtonId() != -1){

            int checkedRadioId = rg7.getCheckedRadioButtonId();
            String value = ((RadioButton)findViewById(checkedRadioId)).getText().toString();

            Rule newFact = new Rule();
            newFact.rule_name = "DEV_MODE";
            Rule.Condition condition = newFact.new Condition();
            condition.cond_name = "dev_mode";
            condition.cond_val = value;
            newFact.conditions.add(condition);
            facts.add(newFact);
        }
        return facts;
    }

    public void resetAllQuestions(View view){
        RadioGroup rg1=(RadioGroup)findViewById(R.id.devLock_RG);
        RadioGroup rg2=(RadioGroup)findViewById(R.id.DevVer_RG);
        RadioGroup rg3=(RadioGroup)findViewById(R.id.DeviceLocation_RG);
        RadioGroup rg4=(RadioGroup)findViewById(R.id.LockTimeOut_RG);
        RadioGroup rg5=(RadioGroup)findViewById(R.id.SecurityPatchRadio);
        RadioGroup rg6=(RadioGroup)findViewById(R.id.NumberOfThirdPartyApp);
        RadioGroup rg7=(RadioGroup)findViewById(R.id.DeveloperModeRadio);
        rg1.clearCheck();
        rg2.clearCheck();
        rg3.clearCheck();
        rg4.clearCheck();
        rg5.clearCheck();
        rg6.clearCheck();
        rg7.clearCheck();
    }
}
