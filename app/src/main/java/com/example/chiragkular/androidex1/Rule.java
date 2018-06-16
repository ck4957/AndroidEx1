package com.example.chiragkular.androidex1;

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


}