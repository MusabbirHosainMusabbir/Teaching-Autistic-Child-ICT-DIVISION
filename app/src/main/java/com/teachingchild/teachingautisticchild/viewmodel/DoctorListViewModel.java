package com.teachingchild.teachingautisticchild.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.teachingchild.teachingautisticchild.repositories.DoctorListRepository;
import com.teachingchild.teachingautisticchild.repositories.FoodListRepository;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorListViewModel extends AndroidViewModel {

    private DoctorListRepository doctorListRepository;
    public DoctorListViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<ArrayList<HashMap<String,String>>> getTopics(){
        return doctorListRepository.getAllList();
    }

    public void initialize(Application application, JSONObject reqJsonObj){
        Log.e("init--->", "initialize: "+reqJsonObj.toString() );
        doctorListRepository = new DoctorListRepository(application,reqJsonObj);

    }
}
