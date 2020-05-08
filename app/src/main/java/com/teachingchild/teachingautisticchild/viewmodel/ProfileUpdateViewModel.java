package com.teachingchild.teachingautisticchild.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.teachingchild.teachingautisticchild.repositories.ProfileUpdateRepository;
import com.teachingchild.teachingautisticchild.repositories.UserDailyActivityRepository;

import org.json.JSONObject;

import java.util.HashMap;

public class ProfileUpdateViewModel extends AndroidViewModel {

    private ProfileUpdateRepository profileUpdateRepository;
    public ProfileUpdateViewModel(@NonNull Application application) {
        super(application);
    }
    public LiveData<HashMap<String,String>> getStatus(){
        return profileUpdateRepository.getSubmitStatus();
    }

    public void initialize(Application application, JSONObject reqJsonObj){
        Log.e("init--->", "initialize: "+reqJsonObj.toString() );
        profileUpdateRepository = new ProfileUpdateRepository(application,reqJsonObj);

    }
}
