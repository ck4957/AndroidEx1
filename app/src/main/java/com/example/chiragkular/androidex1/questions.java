package com.example.chiragkular.androidex1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

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

    }
}
