package com.teachingchild.teachingautisticchild.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.teachingchild.teachingautisticchild.repositories.LoginRepository;


import org.json.JSONObject;

import java.util.HashMap;

public class LoginViewModel extends AndroidViewModel {

    private LoginRepository loginRepository;
    public LoginViewModel(@NonNull Application application) {
        super(application);
    }
    public LiveData<HashMap<String,String>> getStatus(){
        return loginRepository.getLoginStatus();
    }

    public void initialize(Application application, JSONObject reqJsonObj){
        Log.e("init--->", "initialize: "+reqJsonObj.toString() );
        loginRepository = new LoginRepository(application,reqJsonObj);

    }
}
