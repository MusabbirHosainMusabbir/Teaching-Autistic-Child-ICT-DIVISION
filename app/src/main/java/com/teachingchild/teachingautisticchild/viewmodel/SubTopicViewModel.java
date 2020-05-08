package com.teachingchild.teachingautisticchild.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.teachingchild.teachingautisticchild.repositories.LoginRepository;
import com.teachingchild.teachingautisticchild.repositories.SubTopicListRepository;
import com.teachingchild.teachingautisticchild.repositories.SubTopicRepository;

import org.json.JSONObject;

import java.util.HashMap;

public class SubTopicViewModel extends AndroidViewModel {

    private SubTopicRepository subTopicRepository;
    public SubTopicViewModel(@NonNull Application application) {
        super(application);
    }
    public LiveData<HashMap<String,String>> getStatus(){
        return subTopicRepository.getDetails();
    }

    public void initialize(Application application, JSONObject reqJsonObj){
        Log.e("init--->", "initialize: "+reqJsonObj.toString() );
        subTopicRepository = new SubTopicRepository(application,reqJsonObj);

    }
}
