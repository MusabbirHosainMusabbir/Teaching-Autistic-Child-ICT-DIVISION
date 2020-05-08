package com.teachingchild.teachingautisticchild.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;

import com.teachingchild.teachingautisticchild.R;
import com.teachingchild.teachingautisticchild.Utils.Utils;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Utils.fullScreenView(this,false);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

//                if (!PreferenceMangement.getPreference(SplashActivity.this,"user_id").equals("0")){
//                    startActivity(new Intent(SplashActivity.this,LoginActivity.class));
//                    finish();
//                }
                //else {
                    startActivity(new Intent(SplashActivity.this,EmailActivity.class));
                    finish();
                //}



            }
        },4000);


    }
}
