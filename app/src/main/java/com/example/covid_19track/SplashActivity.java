package com.example.covid_19track;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;



import static java.lang.Thread.sleep;

public class SplashActivity extends AppCompatActivity   {

    private int SLEEP_TIMER=5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

//        LogoLauncher logoLauncher = new LogoLauncher();
//        logoLauncher.start();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                SplashActivity.this.finish();
            }
        },3000);




    }


    private class LogoLauncher extends Thread{
        public void run(){
            try{
                sleep(1000*SLEEP_TIMER);

            }catch (InterruptedException e){
                e.printStackTrace();
            }
            startActivity(new Intent(SplashActivity.this,MainActivity.class));

            SplashActivity.this.finish();
        }
    }



}
