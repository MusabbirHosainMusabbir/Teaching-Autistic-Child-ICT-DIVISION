package com.teachingchild.teachingautisticchild.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.teachingchild.teachingautisticchild.repositories.DailyActivitySubTopicListRepository;
import com.teachingchild.teachingautisticchild.repositories.DoctorAdviceViewRepository;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorAdviewViewModel extends AndroidViewModel {

    private DoctorAdviceViewRepository doctorAdviceViewRepository;
    public DoctorAdviewViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<ArrayList<HashMap<String,String>>> getTopics(){
        return doctorAdviceViewRepository.getViewDoctorAdvice();
    }

    public void initialize(Application application, JSONObject reqJsonObj){
        Log.e("init--->", "initialize: "+reqJsonObj.toString() );
        doctorAdviceViewRepository = new DoctorAdviceViewRepository(application,reqJsonObj);

    }
}
