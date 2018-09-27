package com.chen.surveytools;


import android.app.Activity;
import android.content.Context;
import android.hardware.Camera;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.Window;
import android.widget.Button;


public class FlashlampActivity extends Activity{

    private boolean status = false;
    private CameraManager cm;
    private Camera camera;
    android.hardware.Camera.Parameters parameters;
    FlashlampActivity instance;
    private Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置为无标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_flashlamp);

        instance = this;
        final Button btn_open=(Button)findViewById(R.id.btn_open);
        btn_open.setText("关");
        cm = (CameraManager)getSystemService(Context.CAMERA_SERVICE);
        camera=Camera.open();
        btn_open.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(!status){
                    btn_open.setText("开");
                    status = true;
                    vibrator = (Vibrator) view.getContext().getSystemService(Context.VIBRATOR_SERVICE);
                    vibrator.vibrate(10);
                    new Thread(new TurnOnLight()).start();
                }
                else{
                    vibrator.vibrate(10);
                    parameters=camera.getParameters();
                    instance.parameters.setFlashMode("off");
                    camera.setParameters(instance.parameters);
                    btn_open.setText("关");
                    status=false;
                }
            }
        });
    }
    @Override
    protected void onDestroy(){
         super.onDestroy();
         camera.stopPreview();
         camera.release();
         camera = null;
    }
    private class TurnOnLight implements Runnable{

        @Override
        public void run() {
            //打开闪光灯
            parameters=camera.getParameters();
            parameters.setFlashMode("torch");//打开
            camera.setParameters(parameters);
        }
    }

}
