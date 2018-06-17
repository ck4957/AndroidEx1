package com.example.chiragkular.androidex1;

import android.util.Log;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;




public class Rule
{
    public String rule_name;
    public List<Condition> conditions;
    public Action action;

    public Rule(){
        this.rule_name = null;
        this.conditions = new ArrayList<>();
        this.action = new Action();
    }

    class Condition
    {
        public String cond_name;
        public String cond_val;
        public Condition(){
            this.cond_val = null;
            this.cond_name = null;
        }
    }

    class Action
    {
        public int score;
        public Action(){
            this.score = 0;
        }
    }

    public static List<String> ReadRules() throws IOException {
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

    public static List<Rule> parseRules() throws IOException {
        List<String> lines = ReadRules();
        List<Rule> rules = new ArrayList<>();

        for (String line: lines) {
            if(line.startsWith("rule")){
                Rule newRule = new Rule();
                //Get Rule Name
                int secondSpace = StringUtils.ordinalIndexOf(line," ",2);
                newRule.rule_name = line.substring(line.indexOf(" ")+1,secondSpace);
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

//        for(Rule rule:rules){
//            Log.d("rule",rule.rule_name+" "
//                    +String.valueOf(rule.action.score));
//            for(Rule.Condition cond:rule.conditions){
//                Log.d("Cond",cond.cond_name+" "
//                        +cond.cond_val+" ");
//            }
//        }
        return rules;

    }


}