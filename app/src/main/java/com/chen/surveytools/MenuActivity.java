package com.chen.surveytools;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.chen.GPS.GPSActivity;
import com.chen.level.LevelActivity;
import com.chen.member.MemberActivity;
import com.chen.ruler.CycleRulerActivity;
import com.chen.ruler.RuleActivity;


public class MenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }
    public void onClickFlashlamp (View view){
        Intent intent=new Intent(this,FlashlampActivity.class);
        startActivity(intent);
    }
    public void onClickCompass (View view){
        Intent intent=new Intent(this,CompassActivity.class);
        startActivity(intent);

    }public void onClickWatchActivity (View view){
        Intent intent=new Intent(this,WatchActivity.class);
        startActivity(intent);
    }
    public void onClickRuleActivity (View view){
        Intent intent=new Intent(this,RuleActivity.class);
        startActivity(intent);
    }
    public void onClickCycleRuleActivity (View view){
        Intent intent=new Intent(this,CycleRulerActivity.class);
        startActivity(intent);
    }
    public void onClickLevel (View view){
        Intent intent=new Intent(this,LevelActivity.class);
        startActivity(intent);
    }
    public void onClickGPS (View view){
        Intent intent=new Intent(this,GPSActivity.class);
        startActivity(intent);
    }
    public void onClickMember (View view){
        Intent intent=new Intent(this,MemberActivity.class);
        startActivity(intent);
    }
}
