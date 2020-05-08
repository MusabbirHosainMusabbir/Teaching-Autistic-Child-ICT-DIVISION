package com.teachingchild.teachingautisticchild.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.teachingchild.teachingautisticchild.R;
import com.teachingchild.teachingautisticchild.Utils.Helper;
import com.teachingchild.teachingautisticchild.Utils.PreferenceMangement;
import com.teachingchild.teachingautisticchild.Utils.Utils;
import com.teachingchild.teachingautisticchild.viewmodel.LoginViewModel;
import com.teachingchild.teachingautisticchild.viewmodel.ProfileviewViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    ImageView backImg;
    Intent intent;
    Button buttoncontinue;
    LoginViewModel loginViewModel;
    EditText emailEdt,passwordEdt;
    ProfileviewViewModel profileviewViewModel;
    String full_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Utils.fullScreenView(this,false);

        emailEdt = findViewById(R.id.emailEdt);
        passwordEdt = findViewById(R.id.passwordEdt);

        buttoncontinue = findViewById(R.id.button);
        backImg = findViewById(R.id.back_img);
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               intent = new Intent(LoginActivity.this,EmailActivity.class);
               startActivity(intent);
               finish();
            }
        });

        buttoncontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.showLoader(LoginActivity.this,"");


                JSONObject reqJsonObj = new JSONObject();

                try {
                    reqJsonObj.put("email",emailEdt.getText().toString().trim());
                    reqJsonObj.put("password",passwordEdt.getText().toString().trim());

                    Log.e("reqjson", String.valueOf(reqJsonObj));

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                loginViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(LoginViewModel.class);
                loginViewModel.initialize(getApplication(),reqJsonObj);
                loginViewModel.getStatus().observe(LoginActivity.this, new Observer<HashMap<String, String>>() {
                    @Override
                    public void onChanged(HashMap<String, String> stringStringHashMap) {
                        Helper.cancelLoader();
                        if (stringStringHashMap.get("status").equals("1")){

                            PreferenceMangement.savePreference(LoginActivity.this,"user_id",stringStringHashMap.get("user_id"));

                            getProfileInfo(stringStringHashMap.get("user_id"));
                            //Log.e("namevalue",name);
                            //PreferenceMangement.savePreference(LoginActivity.this, "name", name);
                        }
                        else {
                            Toast.makeText(LoginActivity.this, "Login failed..", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private String getProfileInfo(String user_id) {
        Log.e("userid",user_id);
        //Helper.showLoader(LoginActivity.this,"");
        JSONObject reqJsonObj = new JSONObject();

        try {
            reqJsonObj.put("user_id",user_id);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        profileviewViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(ProfileviewViewModel.class);
        profileviewViewModel.initialize(getApplication(),reqJsonObj);
        profileviewViewModel.getProfile().observe(LoginActivity.this, new Observer<ArrayList<HashMap<String, String>>>() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onChanged(ArrayList<HashMap<String, String>> hashMaps) {
                //Helper.cancelLoader();
                for (int x= 0; x<hashMaps.size();x++) {
                    full_name = hashMaps.get(x).get("full_name");
                    PreferenceMangement.savePreference(LoginActivity.this, "name", full_name);
                    Log.e("fullnamelogin",full_name);

                    Toast.makeText(LoginActivity.this, "Successfully login..", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                    finish();
                }
            }
        });

        return full_name;
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(LoginActivity.this,EmailActivity.class);
            startActivity(intent);
            finish();
            // back was pressed
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
