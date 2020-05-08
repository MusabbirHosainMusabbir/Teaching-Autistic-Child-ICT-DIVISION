package com.teachingchild.teachingautisticchild.repositories;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.teachingchild.teachingautisticchild.Utils.RetrofitInstance;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopicListRepository {

    JSONObject reqJsonObj;
    public TopicListRepository(Application application, JSONObject reqJsonObj) {
        this.reqJsonObj = reqJsonObj;
        Log.e("json req--->", "RegistrationRepository: "+reqJsonObj.length() );
    }

    MutableLiveData<ArrayList<HashMap<String,String>>> success;

    public LiveData<ArrayList<HashMap<String,String>>> getAllList(){

        Log.e("RESPO____>","BAL ");
        if (success == null) {
            success = new MutableLiveData<>();
        }

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"),reqJsonObj.toString());

        Call<ResponseBody> call = RetrofitInstance.getInstance().getApi().doDailyActivity(requestBody);
        //Helper.showLoader(context,"");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                Log.e("repsonse",response.toString());
                Log.e("respo-->", "onResponse: "+response.body().toString());

                try {

                    ArrayList<HashMap<String,String>> dataList = new ArrayList<>();
                    JSONObject jsonObject=new JSONObject(response.body().string());
                    JSONObject dailyactivityobj = jsonObject.getJSONObject("daily_activity");
                    for(int i=1; i<=dailyactivityobj.length(); i++){

                        HashMap<String,String> responseMap = new HashMap<>();
                        JSONObject jObjectTopic  = dailyactivityobj.getJSONObject(String.valueOf(i));
                        responseMap.put("topic_id",jObjectTopic.getString("topic_id"));
                        responseMap.put("topic_name",jObjectTopic.getString("topic_name"));
                        responseMap.put("topic_image",jObjectTopic.getString("topic_image"));
                        responseMap.put("total_sub_topic",jObjectTopic.getString("total_sub_topic"));
                        responseMap.put("user_sub_topic",jObjectTopic.getString("user_sub_topic"));

                        dataList.add(responseMap);
                    }

                    success.setValue(dataList);
                }
                catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("RESPO____>","BAL "+e.getMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

        return success;



    }

}

