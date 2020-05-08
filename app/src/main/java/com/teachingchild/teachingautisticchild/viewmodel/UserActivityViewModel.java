package com.teachingchild.teachingautisticchild.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.teachingchild.teachingautisticchild.repositories.LoginRepository;
import com.teachingchild.teachingautisticchild.repositories.UserDailyActivityRepository;

import org.json.JSONObject;

import java.util.HashMap;

public class UserActivityViewModel extends AndroidViewModel {

    private UserDailyActivityRepository userDailyActivityRepository;
    public UserActivityViewModel(@NonNull Application application) {
        super(application);
    }
    public LiveData<HashMap<String,String>> getStatus(){
        return userDailyActivityRepository.getSubmitStatus();
    }

    public void initialize(Application application, JSONObject reqJsonObj){
        Log.e("init--->", "initialize: "+reqJsonObj.toString() );
        userDailyActivityRepository = new UserDailyActivityRepository(application,reqJsonObj);

    }
}
