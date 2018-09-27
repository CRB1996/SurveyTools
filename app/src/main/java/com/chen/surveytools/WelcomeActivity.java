package com.chen.surveytools;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //设置为无标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置为全屏模式
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);

        //获取组件
        RelativeLayout welcome = (RelativeLayout)findViewById(R.id.welcome);

        //背景透明度变化3秒内从0.3变到1.0
        AlphaAnimation aa = new AlphaAnimation(0.3f, 1.0f);
        aa.setDuration(3000);
        welcome.startAnimation(aa);

        Timer timer=new Timer();
        TimerTask timerTask=new TimerTask() {
            @Override
            public void run() {
                Intent intent1=new Intent(WelcomeActivity.this,MenuActivity.class);
                startActivity(intent1);
                WelcomeActivity.this.finish();
            }
        };
        timer.schedule(timerTask,1000*3);
    }
}
