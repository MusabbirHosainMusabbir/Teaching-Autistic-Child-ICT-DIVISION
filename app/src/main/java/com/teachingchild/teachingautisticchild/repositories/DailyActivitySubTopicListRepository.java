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

public class DailyActivitySubTopicListRepository {

    JSONObject reqJsonObj;
    public DailyActivitySubTopicListRepository(Application application, JSONObject reqJsonObj) {
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

        Call<ResponseBody> call = RetrofitInstance.getInstance().getApi().doDailyActvitiySubTopic(requestBody);
        //Helper.showLoader(context,"");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                Log.e("repsonse",response.toString());
                Log.e("respo-->", "onResponse: "+response.body().toString());

                try {

                    ArrayList<HashMap<String,String>> dataList = new ArrayList<>();
                    JSONObject jsonObject=new JSONObject(response.body().string());
                    JSONObject dailyactivityobj = jsonObject.getJSONObject("userActivite");
                    Log.e("repsonse", String.valueOf(dailyactivityobj.toString()));
                    Log.e("repsonselength", String.valueOf(dailyactivityobj.length()));

                    for(int i=1; i<=dailyactivityobj.length(); i++){

                         Log.e("da", String.valueOf(dailyactivityobj.getJSONObject(String.valueOf(i))));
                        Log.e("dailyaactivy", String.valueOf(dailyactivityobj.length()));
                        HashMap<String,String> responseMap = new HashMap<>();
                        JSONObject jObjectTopic  = dailyactivityobj.getJSONObject(String.valueOf(i));
                        responseMap.put("subtopic_id",jObjectTopic.getString("sub_topic_id"));
                        responseMap.put("subtopic_name",jObjectTopic.getString("sub_topic_name"));
                        responseMap.put("short_des",jObjectTopic.getString("short_des"));
                        responseMap.put("description",jObjectTopic.getString("description"));
                        responseMap.put("subtopic_image",jObjectTopic.getString("sub_topic_img"));
                        responseMap.put("status",jObjectTopic.getString("status"));

                        Log.e("statsuvalue", jObjectTopic.getString("status"));
                        //Log.e("subid", jObjectTopic.getString("subtopic_id"));
                        dataList.add(responseMap);

                        Log.e("datalist", String.valueOf(dataList.size()));
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

