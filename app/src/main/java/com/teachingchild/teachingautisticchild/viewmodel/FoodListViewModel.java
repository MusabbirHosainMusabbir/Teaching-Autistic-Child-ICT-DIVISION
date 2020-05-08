package com.teachingchild.teachingautisticchild.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.teachingchild.teachingautisticchild.repositories.FoodListRepository;
import com.teachingchild.teachingautisticchild.repositories.TopicListRepository;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class FoodListViewModel extends AndroidViewModel {

    private FoodListRepository foodListRepository;
    public FoodListViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<ArrayList<HashMap<String,String>>> getTopics(){
        return foodListRepository.getAllList();
    }

    public void initialize(Application application, JSONObject reqJsonObj){
        Log.e("init--->", "initialize: "+reqJsonObj.toString() );
        foodListRepository = new FoodListRepository(application,reqJsonObj);

    }
}
