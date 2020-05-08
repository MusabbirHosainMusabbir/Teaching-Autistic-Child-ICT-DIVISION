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

public class RegistrationRepository {

    JSONObject reqJsonObj;
    public RegistrationRepository(Application application, JSONObject reqJsonObj) {
        this.reqJsonObj = reqJsonObj;
        Log.e("json req--->", "RegistrationRepository: "+reqJsonObj.length() );

    }

    MutableLiveData<HashMap<String,String>> success;

    public LiveData<HashMap<String,String>> getRegistrationStatus(){

        Log.e("RESPO____>","BAL ");
        if (success == null) {
            success = new MutableLiveData<>();
        }

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"),reqJsonObj.toString());

        Call<ResponseBody> call = RetrofitInstance.getInstance().getApi().doRegistration(requestBody);
        //Helper.showLoader(context,"");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                Log.e("respst",response.body().toString());
               Log.e("respo-->", "onResponse: "+response.body().toString().length() );

               // length.setValue(response.body().length());
                try {
                    JSONObject jsonObject=new JSONObject(response.body().string());
                    Log.e("RESPO____>",""+jsonObject.getJSONObject("response").getString("status"));

                    if(jsonObject.getJSONObject("response").getString("status").equalsIgnoreCase("1")){
                        HashMap<String,String> responseMap = new HashMap<>();
                        responseMap.put("user_id",jsonObject.getJSONObject("response").getString("user_id"));
                        responseMap.put("status",jsonObject.getJSONObject("response").getString("status"));
                        responseMap.put("otp_code",jsonObject.getJSONObject("response").getString("otp_code"));
                        responseMap.put("message",jsonObject.getJSONObject("response").getString("message"));

                        success.setValue(responseMap);
                    }else if(jsonObject.getJSONObject("response").getString("status").equalsIgnoreCase("0")){
                        HashMap<String,String> responseMap = new HashMap<>();
                        responseMap.put("status",jsonObject.getJSONObject("response").getString("status"));
                        responseMap.put("message",jsonObject.getJSONObject("response").getString("message"));
                        success.setValue(responseMap);
                    }

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
