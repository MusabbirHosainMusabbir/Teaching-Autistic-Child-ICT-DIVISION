package com.teachingchild.teachingautisticchild.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.teachingchild.teachingautisticchild.viewmodel.OTPViewModel;
import com.teachingchild.teachingautisticchild.viewmodel.RegistrationViewModel;
import com.teachingchild.teachingautisticchild.viewmodel.ResendViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class VerfifyNumber extends AppCompatActivity {

    EditText firstdigit,seconddigit,thirddigit,fourthdigit;
    Button buttoncontinue;
    String otpString;
    Intent intent;
    String otp_code,user_id,status;
    JSONObject reqJsonObj;
    OTPViewModel otpViewModel;
    ResendViewModel resendModel;
    TextView verifyTxt;
    ImageView backImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verfify_number);
        Utils.fullScreenView(this,false);

        user_id = PreferenceMangement.getPreference(VerfifyNumber.this,"user_id");
        otp_code = PreferenceMangement.getPreference(VerfifyNumber.this,"otp_code");
        status = PreferenceMangement.getPreference(VerfifyNumber.this,"status");

        Log.e("otpstring",otp_code);
        verifyTxt = findViewById(R.id.verifyTxt);
        firstdigit = findViewById(R.id.firstdigit);
        seconddigit = findViewById(R.id.seconddigit);
        thirddigit = findViewById(R.id.thirddigit);
        fourthdigit = findViewById(R.id.fourthdigit);
        buttoncontinue = findViewById(R.id.button);
        backImg = findViewById(R.id.back_img);
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(VerfifyNumber.this,LoginWithPhoneNumber.class);
                startActivity(intent);
                finish();
            }
        });


        verifyTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doResend();
            }
        });


//        firstdigit.addTextChangedListener(new GenericTextWatcher(firstdigit));
//        seconddigit.addTextChangedListener(new GenericTextWatcher(seconddigit));
//        thirddigit.addTextChangedListener(new GenericTextWatcher(thirddigit));
//        fourthdigit.addTextChangedListener(new GenericTextWatcher(fourthdigit));

        firstdigit.setText("1");
        firstdigit.requestFocus();
        firstdigit.setBackgroundResource(R.drawable.focusrectangle);

        seconddigit.setText("2");
        seconddigit.requestFocus();
        seconddigit.setBackgroundResource(R.drawable.focusrectangle);

        thirddigit.setText("3");
        thirddigit.requestFocus();
        thirddigit.setBackgroundResource(R.drawable.focusrectangle);

        fourthdigit.setText("4");
        fourthdigit.requestFocus();
        fourthdigit.setBackgroundResource(R.drawable.focusrectangle);


//        firstdigit.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                seconddigit.setText("2");
//                seconddigit.requestFocus();
//                seconddigit.setBackgroundResource(R.drawable.focusrectangle);
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
////                if(s.length()==1)
////                {
////                    seconddigit.setText("2");
////                    seconddigit.requestFocus();
////                    seconddigit.setBackgroundResource(R.drawable.focusrectangle);
////                }
////                else if(s.length()==0)
////                {
////                    seconddigit.setText("2");
////                    seconddigit.clearFocus();
////                }
//            }
//        });
//
//        seconddigit.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
////                if(s.length()==1)
////                {
////                    thirddigit.setText("3");
////                    thirddigit.requestFocus();
////                    thirddigit.setBackgroundResource(R.drawable.focusrectangle);
////                }
////                else if(s.length()==0)
////                {
////                    thirddigit.setText("3");
////                    thirddigit.requestFocus();
////                }
//            }
//        });
//
//        thirddigit.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
////                if(s.length()==1)
////                {
////                    fourthdigit.setText("4");
////                    fourthdigit.requestFocus();
////                    fourthdigit.setBackgroundResource(R.drawable.focusrectangle);
////                }
////                else if(s.length()==0)
////                {
////                    fourthdigit.setText("4");
////                    fourthdigit.requestFocus();
////                }
//            }
//        });

        buttoncontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("otpcode",PreferenceMangement.getPreference(VerfifyNumber.this,"otp_code"));
                String firstnum = firstdigit.getText().toString();
                String secondnum = seconddigit.getText().toString();
                String thirdnum = thirddigit.getText().toString();
                String fourthnum = fourthdigit.getText().toString();

                String otpString = firstnum+secondnum+thirdnum+fourthnum;
                if(otpString.equalsIgnoreCase(PreferenceMangement.getPreference(VerfifyNumber.this,"otp_code"))){
                     doVerify();
                }else{
                    Toast.makeText(VerfifyNumber.this, "Please enter correct code..", Toast.LENGTH_SHORT).show();
                }
;            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            intent = new Intent(VerfifyNumber.this,LoginWithPhoneNumber.class);
            startActivity(intent);
            finish();
            // back was pressed
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void doResend() {

        Log.e("status",status);

        Helper.showLoader(VerfifyNumber.this,"");

        reqJsonObj = new JSONObject();

        try {
            reqJsonObj.put("user_id",user_id);
            reqJsonObj.put("status",status);
            Log.e("requestjson", String.valueOf(reqJsonObj));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        resendModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(ResendViewModel.class);
        resendModel.initialize(getApplication(),reqJsonObj);
        resendModel.getStatus().observe(VerfifyNumber.this, new Observer<HashMap<String, String>>() {
            @Override
            public void onChanged(HashMap<String, String> stringStringHashMap) {
                Helper.cancelLoader();
                if (stringStringHashMap.get("status").equals("1")){
                    String message = stringStringHashMap.get("message");
                    otp_code = stringStringHashMap.get("otp_code");
                    status = stringStringHashMap.get("status");

                    PreferenceMangement.savePreference(VerfifyNumber.this,"otp_code",otp_code);
                    PreferenceMangement.savePreference(VerfifyNumber.this,"status",status);

                    Log.e("otpcode",PreferenceMangement.getPreference(VerfifyNumber.this,"otp_code"));
                }
                else {
                    Toast.makeText(VerfifyNumber.this,"Failed..", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void doVerify() {
        Helper.showLoader(VerfifyNumber.this,"");

        reqJsonObj = new JSONObject();

        try {
            reqJsonObj.put("user_id",user_id);
            reqJsonObj.put("status",status);


            Log.e("requestjson", String.valueOf(reqJsonObj));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        otpViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(OTPViewModel.class);
        otpViewModel.initialize(getApplication(),reqJsonObj);
        otpViewModel.getStatus().observe(VerfifyNumber.this, new Observer<HashMap<String, String>>() {
            @Override
            public void onChanged(HashMap<String, String> stringStringHashMap) {
                Helper.cancelLoader();
                if (stringStringHashMap.get("status").equals("1")){
                    String message = stringStringHashMap.get("message");

                    Toast.makeText(VerfifyNumber.this,""+message, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(VerfifyNumber.this,HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(VerfifyNumber.this,"Verification failed..", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

//    public class GenericTextWatcher implements TextWatcher
//    {
//        private View view;
//        private GenericTextWatcher(View view)
//        {
//            this.view = view;
//        }
//
//        @Override
//        public void afterTextChanged(Editable editable) {
//            // TODO Auto-generated method stub
//            String text = editable.toString();
//            switch(view.getId())
//            {
//
//                case R.id.firstdigit:
//                    if(text.length()==1)
//                        et2.requestFocus();
//                    break;
//                case R.id.seconddigit:
//                    if(text.length()==1)
//                        et3.requestFocus();
//                    else if(text.length()==0)
//                        et1.requestFocus();
//                    break;
//                case R.id.thirddigit:
//                    if(text.length()==1)
//                        et4.requestFocus();
//                    else if(text.length()==0)
//                        et2.requestFocus();
//                    break;
//                case R.id.four:
//                    if(text.length()==0)
//                        et3.requestFocus();
//                    break;
//            }
//        }
//
//        @Override
//        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
//            // TODO Auto-generated method stub
//        }
//
//        @Override
//        public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
//            // TODO Auto-generated method stub
//        }
//    }
}
