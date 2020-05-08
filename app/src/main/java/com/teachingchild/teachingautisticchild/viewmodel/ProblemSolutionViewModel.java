package com.teachingchild.teachingautisticchild.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.teachingchild.teachingautisticchild.repositories.DoctorAdviceViewRepository;
import com.teachingchild.teachingautisticchild.repositories.ProblemSolutionViewRepository;
import com.teachingchild.teachingautisticchild.repositories.ProblemandSolutionListRepository;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ProblemSolutionViewModel extends AndroidViewModel {

    private ProblemSolutionViewRepository problemSolutionViewRepository;
    public ProblemSolutionViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<ArrayList<HashMap<String,String>>> getTopics(){
        return problemSolutionViewRepository.getViewProblemSolution();
    }

    public void initialize(Application application, JSONObject reqJsonObj){
        Log.e("init--->", "initialize: "+reqJsonObj.toString() );
        problemSolutionViewRepository = new ProblemSolutionViewRepository(application,reqJsonObj);

    }
}
