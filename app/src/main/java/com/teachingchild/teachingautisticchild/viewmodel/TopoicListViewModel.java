package com.teachingchild.teachingautisticchild.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.teachingchild.teachingautisticchild.repositories.TopicListRepository;


import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class TopoicListViewModel extends AndroidViewModel {

    private TopicListRepository topicListRepository;
    public TopoicListViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<ArrayList<HashMap<String,String>>> getTopics(){
        return topicListRepository.getAllList();
    }

    public void initialize(Application application, JSONObject reqJsonObj){
        Log.e("init--->", "initialize: "+reqJsonObj.toString() );
        topicListRepository = new TopicListRepository(application,reqJsonObj);

    }
}
