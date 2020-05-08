package com.teachingchild.teachingautisticchild.Utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class PrimaryTools extends AppCompatActivity {

    int cloud_x,cloud2_x,cloud3_x;
    private Timer timer = new Timer();
    private Handler handler = new Handler();

    public void preSets(int cloud_x, int cloud2_x, int cloud3_x){

        this.cloud_x = cloud_x;
        this.cloud2_x = cloud2_x;
        this.cloud3_x = cloud3_x;

    }




    public void moveClouds(final FrameLayout frameLayoutFL, final ImageView cloudA, final ImageView cloudB, final ImageView cloudC){
        timer.schedule(new TimerTask() {
            FrameLayout frameLayout = frameLayoutFL;
            ImageView cloud1 = cloudA;
            ImageView cloud2 = cloudB;
            ImageView cloud3 = cloudC;




            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {


                            cloud_x += 12;
                            if (cloud_x > frameLayout.getWidth()-60){
                                cloud_x = (int) frameLayout.getX()-20;
                            }

                            cloud1.setX(cloud_x);

                            cloud2_x += 8;
                            if (cloud2_x > frameLayout.getWidth()-60){
                                cloud2_x = (int) frameLayout.getX() - 12;
                            }

                            cloud2.setX(cloud2_x);

                            cloud3_x += 10;
                            if (cloud3_x > frameLayout.getWidth()-60){
                                cloud3_x = (int) frameLayout.getX() -25;
                            }

                            cloud3.setX(cloud3_x);


                    }
                });
            }
        },0,300);
    }

    public void hideNav(Activity activity){
        activity.getWindow().getDecorView()
                .setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_FULLSCREEN |
                                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                );
    }

    public void vibration(Context ctx){
        Vibrator v = (Vibrator) getSystemService(ctx.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            //deprecated in API 26
            v.vibrate(500);
        }
    }



}
