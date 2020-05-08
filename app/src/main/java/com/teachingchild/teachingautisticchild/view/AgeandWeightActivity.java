package com.teachingchild.teachingautisticchild.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.teachingchild.teachingautisticchild.R;
import com.teachingchild.teachingautisticchild.Utils.PreferenceMangement;
import com.teachingchild.teachingautisticchild.Utils.Utils;

public class AgeandWeightActivity extends AppCompatActivity {

    Button buttoncontinue;
    ImageView backImg;
    Intent intent;
    String email,name;
    EditText ageEdt,weightEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ageand_weight);
        Utils.fullScreenView(this,false);

        intent = getIntent();
        name = intent.getStringExtra("name");
        email = intent.getStringExtra("email");


        ageEdt = findViewById(R.id.ageEdt);
        weightEdt = findViewById(R.id.weightEdt);

        backImg = findViewById(R.id.back_img);
        buttoncontinue = findViewById(R.id.button);

        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(AgeandWeightActivity.this,EmailActivity.class);
                startActivity(intent);
                finish();
            }
        });


        buttoncontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PreferenceMangement.savePreference(AgeandWeightActivity.this,"age",ageEdt.getText().toString());
                PreferenceMangement.savePreference(AgeandWeightActivity.this,"weight",weightEdt.getText().toString());

                Intent intent = new Intent(AgeandWeightActivity.this,PasswordEdt.class);
                intent.putExtra("name",name);
                intent.putExtra("email",email);
                intent.putExtra("age",ageEdt.getText().toString());
                intent.putExtra("weight",weightEdt.getText().toString());
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(AgeandWeightActivity.this,EmailActivity.class);
            startActivity(intent);
            finish();
            // back was pressed
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
