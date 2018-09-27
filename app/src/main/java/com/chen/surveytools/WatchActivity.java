package com.chen.surveytools;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class WatchActivity extends Activity {

    private int seconds = 0;
    private boolean running = false;
    private boolean wasRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("计时器");
        setContentView(R.layout.activity_watch);
        if(savedInstanceState != null){
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");

        }
        runTimer();
    }

    @Override
    protected void onPause(){
        super.onPause();
        wasRunning = running;
        running = false;
    }

    @Override
    protected void onResume(){
        super.onResume();
        if(wasRunning){
            running = true;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle saveInstanceState) {
        saveInstanceState.putInt("seconds", seconds);
        saveInstanceState.putBoolean("running", running);
        saveInstanceState.putBoolean("wasRunning", wasRunning);

    }

    public void onClickStart(View view){
        running = true ;
        Toast toast = Toast.makeText(this,"开始计时！",Toast.LENGTH_SHORT);
        toast.show();
    }

    public void onClickStop(View view){
        running = false;
        Toast toast = Toast.makeText(this,"暂停计时！",Toast.LENGTH_SHORT);
        toast.show();
    }

    public void onClickReset(View view){
        running = false;
        seconds = 0;
        Toast toast = Toast.makeText(this,"重置成功！",Toast.LENGTH_SHORT);
        toast.show();
    }

    private void runTimer(){
        final TextView timeView = (TextView) findViewById(R.id.time_view);

        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;
                String time = String.format("%d:%02d:%02d",hours,minutes,secs);
                timeView.setText(time);
                if(running){
                    seconds++;
                }
                handler.postDelayed(this,1000);
            }
        });
    }

}
