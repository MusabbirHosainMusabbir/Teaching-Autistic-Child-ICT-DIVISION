package com.teachingchild.teachingautisticchild.repositories;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.teachingchild.teachingautisticchild.Utils.Helper;
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

public class DailyElementsListRepository {

    JSONObject reqJsonObj;
    public DailyElementsListRepository(Application application, JSONObject reqJsonObj) {
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

        Call<ResponseBody> call = RetrofitInstance.getInstance().getApi().doDailyElements(requestBody);
        //Helper.showLoader(context,"");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                Log.e("repsonse",response.toString());
                Log.e("respo-->", "onResponse: "+response.body().toString());

                try {

                    ArrayList<HashMap<String,String>> dataList = new ArrayList<>();
                    JSONObject jsonObject=new JSONObject(response.body().string());
                    JSONObject daily_element_list = jsonObject.getJSONObject("daily_element_list");

                    Log.e("dailyelementlist",daily_element_list.toString());
                    for(int i=1; i<=daily_element_list.length(); i++){

                        HashMap<String,String> responseMap = new HashMap<>();
                        JSONObject jObjectTopic  = daily_element_list.getJSONObject(String.valueOf(i));
                        responseMap.put("element_id",jObjectTopic.getString("element_id"));
                        responseMap.put("element_name",jObjectTopic.getString("element_name"));
                        responseMap.put("element_description",jObjectTopic.getString("element_description"));

                        dataList.add(responseMap);
                    }

                    success.setValue(dataList);
                }
                catch (JSONException e) {
                    e.printStackTrace();
                    Helper.cancelLoader();
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

