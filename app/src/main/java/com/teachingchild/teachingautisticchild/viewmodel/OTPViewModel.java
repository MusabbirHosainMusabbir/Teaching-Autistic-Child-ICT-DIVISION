package com.teachingchild.teachingautisticchild.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.teachingchild.teachingautisticchild.repositories.LoginRepository;
import com.teachingchild.teachingautisticchild.repositories.OTPRepository;

import org.json.JSONObject;

import java.util.HashMap;

public class OTPViewModel extends AndroidViewModel {

    private OTPRepository otpRepository;
    public OTPViewModel(@NonNull Application application) {
        super(application);
    }
    public LiveData<HashMap<String,String>> getStatus(){
        return otpRepository.getOTPStatus();
    }

    public void initialize(Application application, JSONObject reqJsonObj){
        Log.e("init--->", "initialize: "+reqJsonObj.toString() );
        otpRepository = new OTPRepository(application,reqJsonObj);

    }
}
