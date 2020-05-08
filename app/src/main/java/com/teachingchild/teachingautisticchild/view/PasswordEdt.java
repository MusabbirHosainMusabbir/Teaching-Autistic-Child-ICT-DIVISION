package com.teachingchild.teachingautisticchild.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.teachingchild.teachingautisticchild.R;
import com.teachingchild.teachingautisticchild.Utils.Helper;
import com.teachingchild.teachingautisticchild.Utils.PreferenceMangement;
import com.teachingchild.teachingautisticchild.Utils.Utils;
import com.teachingchild.teachingautisticchild.viewmodel.RegistrationViewModel;

import org.json.JSONException;
import org.json.JSONObject;

public class PasswordEdt extends AppCompatActivity {

    Button buttoncontinue;
    TextView signin;
    ImageView backImg;
    Intent intent;
    String name,email,age,weight,password;
    EditText passwordEdt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_edt);
        Utils.fullScreenView(this,false);

        intent = getIntent();
        name = intent.getStringExtra("name");
        email = intent.getStringExtra("email");
        age = intent.getStringExtra("age");
        weight = intent.getStringExtra("weight");

        passwordEdt = findViewById(R.id.passwordEdt);
        buttoncontinue = findViewById(R.id.button);
        signin = findViewById(R.id.signin);
        backImg = findViewById(R.id.back_img);
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(PasswordEdt.this,AgeandWeightActivity.class);
                startActivity(intent);
                finish();
            }
        });

        buttoncontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PasswordEdt.this,LoginWithPhoneNumber.class);
                intent.putExtra("name",name);
                intent.putExtra("email",email);
                intent.putExtra("age",age);
                intent.putExtra("weight",weight);
                intent.putExtra("password",passwordEdt.getText().toString());
                startActivity(intent);
                finish();
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PasswordEdt.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(PasswordEdt.this,AgeandWeightActivity.class);
            startActivity(intent);
            finish();
            // back was pressed
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
