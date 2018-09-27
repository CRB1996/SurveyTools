package com.chen.ruler;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;

import com.chen.surveytools.R;


public class RuleActivity extends Activity {

    private RulerView rulerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //设置为无标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置为全屏模式
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //设置为横屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        setContentView(rulerView = new RulerView(this));
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onRestoreInstanceState(savedInstanceState);
        if (rulerView != null) {
            rulerView.setLineX(savedInstanceState.getFloat("lineX"));
            rulerView.setKedu(savedInstanceState.getInt("kedu"));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // TODO Auto-generated method stub
        if (rulerView != null) {
            outState.putFloat("lineX", rulerView.getLineX());
            outState.putInt("kedu", rulerView.getKedu());
        }
        super.onSaveInstanceState(outState);
    }
}
