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

    public Rule(String name){
        this.rule_name = name;
        this.conditions = new ArrayList<>();
        this.action = new Action();
    }

    class Condition
    {
        public String cond_name;
        public String cond_val;
        public Condition(String cond_name, String cond_val){
            this.cond_name = cond_name;
            this.cond_val = cond_val;
        }

        public String toString(){
            return "Cond Name: "+cond_name+" Cond Val: "+cond_val;
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

        try {
            for (String line : lines) {
                if (line.startsWith("rule")) {
                    //Get Rule Name
                    int secondSpace = StringUtils.ordinalIndexOf(line, " ", 2);
                    String rule_name = line.substring(line.indexOf(" ") + 1, secondSpace);
                    Rule newRule = new Rule(rule_name);

                    //List<Rule.Condition> cond_list = new ArrayList<>();
                    String conditions = StringUtils.substringBetween(line, "if ", " then");

                    if (conditions.contains("&&")) {
                        // Multiple Conditions
                        String[] temp_condn_list = conditions.split("&&");

                        for (String condition : temp_condn_list) {
                            String cond_name =condition.split("=")[0].trim();
                            String cond_val = condition.split("=")[1].trim();
                            Rule.Condition eachCondition = newRule.new Condition(cond_name,cond_val);
                            newRule.conditions.add(eachCondition);
                        }
                    } else {
                        //only one condtion
                        String cond_name =conditions.split("=")[0].trim();
                        String cond_val = conditions.split("=")[1].trim();
                        Rule.Condition eachCondition = newRule.new Condition(cond_name,cond_val);

                        newRule.conditions.add(eachCondition);

                    }
                    //Get the action score
                    newRule.action.score = Integer.parseInt(StringUtils.substringAfterLast(line, "=").trim());
                    rules.add(newRule);
                }
            }
        }
        catch (Exception e){
            Log.e("EXCEPTION","Error in Parsing Rules",e);
        }
        return rules;

    }

    public String toString(){
        return "RuleName: "+rule_name;
    }


}