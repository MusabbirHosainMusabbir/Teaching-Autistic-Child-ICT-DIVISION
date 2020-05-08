package com.teachingchild.teachingautisticchild.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.teachingchild.teachingautisticchild.repositories.DoctorListRepository;
import com.teachingchild.teachingautisticchild.repositories.ProblemandSolutionListRepository;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ProblemandSolutionListViewModel extends AndroidViewModel {

    private ProblemandSolutionListRepository problemandSolutionListRepository;
    public ProblemandSolutionListViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<ArrayList<HashMap<String,String>>> getTopics(){
        return problemandSolutionListRepository.getAllList();
    }

    public void initialize(Application application, JSONObject reqJsonObj){
        Log.e("init--->", "initialize: "+reqJsonObj.toString() );
        problemandSolutionListRepository = new ProblemandSolutionListRepository(application,reqJsonObj);

    }
}
