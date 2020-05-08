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

public class ProblemSolutionViewRepository {

    JSONObject reqJsonObj;
    public ProblemSolutionViewRepository(Application application, JSONObject reqJsonObj) {
        this.reqJsonObj = reqJsonObj;
        Log.e("json req--->", "RegistrationRepository: "+reqJsonObj.length() );

    }

    MutableLiveData<ArrayList<HashMap<String,String>>> success;

    public LiveData<ArrayList<HashMap<String,String>>> getViewProblemSolution(){

        Log.e("RESPO____>","BAL ");
        if (success == null) {
            success = new MutableLiveData<>();
        }

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"),reqJsonObj.toString());

        Call<ResponseBody> call = RetrofitInstance.getInstance().getApi().doProblemandSolutionView(requestBody);
        //Helper.showLoader(context,"");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                Log.e("repsonse",response.toString());
                Log.e("respo-->", "onResponse: "+response.body().toString());

                try {

                    ArrayList<HashMap<String,String>> dataList = new ArrayList<>();
                    JSONObject jsonObject=new JSONObject(response.body().string());
                    JSONObject advice_info = jsonObject.getJSONObject("ps_info");
                    Log.e("repsonselength", String.valueOf(advice_info));



                        HashMap<String,String> responseMap = new HashMap<>();

                        responseMap.put("ps_id",advice_info.getString("ps_id"));
                        responseMap.put("ps_name",advice_info.getString("ps_name"));
                        responseMap.put("ps_description",advice_info.getString("ps_description"));


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
