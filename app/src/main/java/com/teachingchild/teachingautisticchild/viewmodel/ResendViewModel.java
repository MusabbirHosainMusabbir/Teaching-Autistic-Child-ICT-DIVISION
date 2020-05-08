package com.teachingchild.teachingautisticchild.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.teachingchild.teachingautisticchild.repositories.OTPRepository;
import com.teachingchild.teachingautisticchild.repositories.ResendRepository;

import org.json.JSONObject;

import java.util.HashMap;

public class ResendViewModel extends AndroidViewModel {

    private ResendRepository resendRepository;
    public ResendViewModel(@NonNull Application application) {
        super(application);
    }
    public LiveData<HashMap<String,String>> getStatus(){
        return resendRepository.getOTPStatus();
    }

    public void initialize(Application application, JSONObject reqJsonObj){
        Log.e("init--->", "initialize: "+reqJsonObj.toString() );
        resendRepository = new ResendRepository(application,reqJsonObj);

    }
}
