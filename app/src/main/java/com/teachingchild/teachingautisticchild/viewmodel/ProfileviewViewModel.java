package com.teachingchild.teachingautisticchild.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.teachingchild.teachingautisticchild.repositories.DoctorAdviceViewRepository;
import com.teachingchild.teachingautisticchild.repositories.ProfileViewRepository;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ProfileviewViewModel extends AndroidViewModel {

    private ProfileViewRepository profileViewRepository;
    public ProfileviewViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<ArrayList<HashMap<String,String>>> getProfile(){
        return profileViewRepository.getProfile();
    }

    public void initialize(Application application, JSONObject reqJsonObj){
        Log.e("init--->", "initialize: "+reqJsonObj.toString() );
        profileViewRepository = new ProfileViewRepository(application,reqJsonObj);

    }
}
