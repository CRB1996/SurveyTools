package com.chen.surveytools;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class CompassActivity extends Activity implements SensorEventListener {

    private TextView tv1,tv2,tv3;
    private ImageView img;
    private SensorManager sensorManager;
    private Sensor sensor;
    private float startAngle = 0;
    private DefinedRotateAnimation definedRotateAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("指南针");
        setContentView(R.layout.activity_compass);
        init();
    }

    public void init(){
        tv1 = (TextView)findViewById(R.id.tv1);
        tv2 = (TextView)findViewById(R.id.tv2);
        tv3 = (TextView)findViewById(R.id.tv3);
        img = (ImageView)findViewById(R.id.img);
        //获取传感器管理者，通过系统获取
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        //获取方向传感器
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);

        definedRotateAnimation = new DefinedRotateAnimation();
        definedRotateAnimation.setDuration(200);
        definedRotateAnimation.setFillAfter(true);

        tv1.bringToFront();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //第三个参数：传感器刷新的更新速度
        sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float presentAngle = event.values[0];
        if(startAngle == (-presentAngle)){
            return ;
        }
        if((presentAngle>=340&&presentAngle<=360)||(presentAngle>=0&&presentAngle<=20)){
            tv3.setText("北");
        }else if(presentAngle>20&&presentAngle<70){
            tv3.setText("东北");
        }else if(presentAngle>=70&&presentAngle<=110){
            tv3.setText("东");
        }else if(presentAngle>110&&presentAngle<160){
            tv3.setText("东南");
        }else if(presentAngle>=160&&presentAngle<=200){
            tv3.setText("南");
        }else if(presentAngle>200&presentAngle<250){
            tv3.setText("西南");
        }else if(presentAngle>=250&&presentAngle<=290){
            tv3.setText("西");
        }else {
            tv3.setText("西北");
        }
        tv2.setText((int)presentAngle + "°");
        presentAngle = (-presentAngle);
        definedRotateAnimation.setStartAngle(startAngle);
        definedRotateAnimation.setEndAngle(presentAngle);
        img.startAnimation(definedRotateAnimation);
        startAngle = presentAngle;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
