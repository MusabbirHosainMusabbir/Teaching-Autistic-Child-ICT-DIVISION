package com.teachingchild.teachingautisticchild.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.teachingchild.teachingautisticchild.repositories.RegistrationRepository;
import org.json.JSONObject;

import java.util.HashMap;

public class RegistrationViewModel extends AndroidViewModel {

    private RegistrationRepository registrationRepository;
    public RegistrationViewModel(@NonNull Application application) {
        super(application);
    }
    public LiveData<HashMap<String,String>> getLength(){
        return registrationRepository.getRegistrationStatus();
    }

    public void initialize(Application application, JSONObject reqJsonObj){
        Log.e("init--->", "initialize: "+reqJsonObj.toString() );
        registrationRepository = new RegistrationRepository(application,reqJsonObj);

    }
}
