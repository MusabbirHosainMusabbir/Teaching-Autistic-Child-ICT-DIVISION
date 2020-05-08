package com.teachingchild.teachingautisticchild.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.teachingchild.teachingautisticchild.R;

import me.biubiubiu.justifytext.library.JustifyTextView;

public class OutdoorActivities extends AppCompatActivity {

    ImageView image;
    JustifyTextView foodlist1,foodlist2,foodlist3,foodlist4,foodlist5,foodlist6,foodlist7,foodlist8,foodlist9,foodlist10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outdoor_activities);

        foodlist1 = findViewById(R.id.foodlist1description);
        foodlist2 = findViewById(R.id.foodlist2description);
        foodlist3 = findViewById(R.id.foodlist3description);
        foodlist4 = findViewById(R.id.foodlist4description4);
        foodlist5 = findViewById(R.id.foodlist4description5);
        foodlist6 = findViewById(R.id.foodlist4description6);
        foodlist7 = findViewById(R.id.foodlist4description7);
        foodlist8 = findViewById(R.id.foodlist4description8);
        foodlist9 = findViewById(R.id.foodlist4description9);
        foodlist10 = findViewById(R.id.foodlist4description10);
        image = findViewById(R.id.image);

        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/HKGrotesk-Regular.otf");
        foodlist1.setTypeface(tf);
        foodlist2.setTypeface(tf);
        foodlist3.setTypeface(tf);
        foodlist4.setTypeface(tf);
        foodlist5.setTypeface(tf);
        foodlist6.setTypeface(tf);
        foodlist7.setTypeface(tf);
        foodlist8.setTypeface(tf);
        foodlist9.setTypeface(tf);
        foodlist10.setTypeface(tf);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OutdoorActivities.this,Courses_Courses.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(OutdoorActivities.this,Courses_Courses.class);
            startActivity(intent);
            finish();
            // back was pressed
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
