package com.teachingchild.teachingautisticchild.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
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
import android.widget.Toast;

import com.rilixtech.widget.countrycodepicker.CountryCodePicker;
import com.teachingchild.teachingautisticchild.R;
import com.teachingchild.teachingautisticchild.Utils.Helper;
import com.teachingchild.teachingautisticchild.Utils.PreferenceMangement;
import com.teachingchild.teachingautisticchild.Utils.Utils;
import com.teachingchild.teachingautisticchild.viewmodel.RegistrationViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class LoginWithPhoneNumber extends AppCompatActivity {

    Button buttoncontinue;
    EditText edtPhoneNumber;
    JSONObject reqJsonObj;
    RegistrationViewModel registrationViewModel;
    Intent intent;
    String name,email,age,weight,password;
    ImageView backImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_with_phone_number);
        Utils.fullScreenView(this,false);

        intent = getIntent();
        name = intent.getStringExtra("name");
        email = intent.getStringExtra("email");
        age = intent.getStringExtra("age");
        weight = intent.getStringExtra("weight");
        password = intent.getStringExtra("password");
        backImg = findViewById(R.id.back_img);
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(LoginWithPhoneNumber.this,PasswordEdt.class);
                startActivity(intent);
                finish();
            }
        });


        edtPhoneNumber = findViewById(R.id.phoneEdt);
        buttoncontinue = findViewById(R.id.button);
        buttoncontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.showLoader(LoginWithPhoneNumber.this,"");

                reqJsonObj = new JSONObject();

                try {
                    reqJsonObj.put("name",name.trim());
                    reqJsonObj.put("email",email.trim());
                    reqJsonObj.put("age",age.trim());
                    reqJsonObj.put("weight",weight.trim());
                    reqJsonObj.put("password",password);
                    reqJsonObj.put("phone",edtPhoneNumber.getText().toString());

                    Log.e("requestjson", String.valueOf(reqJsonObj));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                registrationViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RegistrationViewModel.class);
                registrationViewModel.initialize(getApplication(),reqJsonObj);
                registrationViewModel.getLength().observe(LoginWithPhoneNumber.this, new Observer<HashMap<String, String>>() {
                    @Override
                    public void onChanged(HashMap<String, String> stringStringHashMap) {
                        Helper.cancelLoader();
                        if (stringStringHashMap.get("status").equalsIgnoreCase("1")){
                            String otpcode = stringStringHashMap.get("otp_code");
                            String user_id = stringStringHashMap.get("user_id");
                            Log.e("otp",otpcode);
                            PreferenceMangement.savePreference(LoginWithPhoneNumber.this,"user_id",user_id);
                            //Toast.makeText(LoginWithPhoneNumber.this,"Successfully registered..", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(LoginWithPhoneNumber.this,VerfifyNumber.class);
                            intent.putExtra("otp_code",otpcode);
                            PreferenceMangement.savePreference(LoginWithPhoneNumber.this,"otp_code",otpcode);
                            intent.putExtra("user_id",user_id);
                            intent.putExtra("status",stringStringHashMap.get("status"));
                            PreferenceMangement.savePreference(LoginWithPhoneNumber.this,"status",stringStringHashMap.get("status"));
                            startActivity(intent);
                            finish();
                            //startActivity(new Intent(LoginWithPhoneNumber.this,HomeActivity.class));
                        }else if(stringStringHashMap.get("status").equalsIgnoreCase("0")){
                            String message = stringStringHashMap.get("message");
                            Toast.makeText(LoginWithPhoneNumber.this,"Phone Number"+message, Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(LoginWithPhoneNumber.this,"Registration failed..", Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            intent = new Intent(LoginWithPhoneNumber.this,PasswordEdt.class);
            startActivity(intent);
            finish();
            // back was pressed
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
