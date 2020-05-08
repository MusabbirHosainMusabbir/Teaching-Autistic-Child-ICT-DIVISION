package com.teachingchild.teachingautisticchild.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.teachingchild.teachingautisticchild.repositories.DailyActivitySubTopicListRepository;
import com.teachingchild.teachingautisticchild.repositories.SubTopicListRepository;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class DailyActivitySubTopicListViewModel extends AndroidViewModel {

    private DailyActivitySubTopicListRepository subtopicListRepository;
    public DailyActivitySubTopicListViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<ArrayList<HashMap<String,String>>> getTopics(){
        return subtopicListRepository.getAllList();
    }

    public void initialize(Application application, JSONObject reqJsonObj){
        Log.e("init--->", "initialize: "+reqJsonObj.toString() );
        subtopicListRepository = new DailyActivitySubTopicListRepository(application,reqJsonObj);

    }
}
