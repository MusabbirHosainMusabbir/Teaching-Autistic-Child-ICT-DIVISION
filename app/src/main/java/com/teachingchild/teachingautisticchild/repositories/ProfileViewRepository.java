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

public class ProfileViewRepository {

    JSONObject reqJsonObj;
    public ProfileViewRepository(Application application, JSONObject reqJsonObj) {
        this.reqJsonObj = reqJsonObj;
        Log.e("json req--->", "RegistrationRepository: "+reqJsonObj.length() );

    }

    MutableLiveData<ArrayList<HashMap<String,String>>> success;

    public LiveData<ArrayList<HashMap<String,String>>> getProfile(){

        Log.e("RESPO____>","BAL ");
        if (success == null) {
            success = new MutableLiveData<>();
        }

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"),reqJsonObj.toString());

        Call<ResponseBody> call = RetrofitInstance.getInstance().getApi().doProfile(requestBody);
        //Helper.showLoader(context,"");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                Log.e("repsonse",response.toString());
                Log.e("respo-->", "onResponse: "+response.body().toString());

                try {

                    ArrayList<HashMap<String,String>> dataList = new ArrayList<>();
                    JSONObject jsonObject=new JSONObject(response.body().string());
                    JSONObject user_info = jsonObject.getJSONObject("user_info");
                    Log.e("repsonselength", String.valueOf(user_info));



                    HashMap<String,String> responseMap = new HashMap<>();

                    responseMap.put("user_id",user_info.getString("user_id"));
                    responseMap.put("full_name",user_info.getString("full_name"));
                    responseMap.put("email",user_info.getString("email"));
                    responseMap.put("phone",user_info.getString("phone"));
                    responseMap.put("child_age",user_info.getString("child_age"));
                    responseMap.put("weight",user_info.getString("weight"));
                    responseMap.put("location",user_info.getString("location"));
                    responseMap.put("user_pic",user_info.getString("user_pic"));


                    dataList.add(responseMap);

                    Log.e("datalist", String.valueOf(dataList.size()));


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
