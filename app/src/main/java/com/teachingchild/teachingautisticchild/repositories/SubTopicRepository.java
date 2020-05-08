package com.teachingchild.teachingautisticchild.repositories;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.teachingchild.teachingautisticchild.Utils.RetrofitInstance;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubTopicRepository {

    JSONObject reqJsonObj;
    public SubTopicRepository(Application application, JSONObject reqJsonObj) {
        this.reqJsonObj = reqJsonObj;
        Log.e("json req--->", "RegistrationRepository: "+reqJsonObj.length() );

    }

    MutableLiveData<HashMap<String,String>> success;

    public LiveData<HashMap<String,String>> getDetails(){

        Log.e("RESPO____>","BAL ");
        if (success == null) {
            success = new MutableLiveData<>();
        }

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"),reqJsonObj.toString());

        Call<ResponseBody> call = RetrofitInstance.getInstance().getApi().doListDetails(requestBody);
        //Helper.showLoader(context,"");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                Log.e("respo-->", "onResponse: "+response.body().toString().length() );

                // length.setValue(response.body().length());
                try {
                    JSONObject jsonObject=new JSONObject(response.body().string());
                    //Log.e("RESPO____>",""+jsonObject.getJSONObject("sub_tipic_details").getString("status"));

                    HashMap<String,String> responseMap = new HashMap<>();
                    responseMap.put("sub_topic_id",jsonObject.getJSONObject("sub_tipic_details").getString("sub_topic_id"));
                    responseMap.put("sub_topic_name",jsonObject.getJSONObject("sub_tipic_details").getString("sub_topic_name"));
                    responseMap.put("short_des",jsonObject.getJSONObject("sub_tipic_details").getString("short_des"));
                    responseMap.put("description",jsonObject.getJSONObject("sub_tipic_details").getString("description"));
                    responseMap.put("sub_topic_img",jsonObject.getJSONObject("sub_tipic_details").getString("sub_topic_img"));

                    success.setValue(responseMap);
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
