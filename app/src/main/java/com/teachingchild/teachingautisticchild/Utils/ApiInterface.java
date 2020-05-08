package com.teachingchild.teachingautisticchild.Utils;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("user_registration.json")
    Call<ResponseBody> doRegistration(@Body RequestBody jsonObject);

    @POST("user_login.json")
    Call<ResponseBody> doLogin(@Body RequestBody jsonObject);


    @POST("user_verify.json")
    Call<ResponseBody> doVerify(@Body RequestBody jsonObject);

    @POST("resend_sms.json")
    Call<ResponseBody> doResend(@Body RequestBody jsonObject);

    @POST("daily_activity.json")
    Call<ResponseBody> doDailyActivity(@Body RequestBody jsonObject);

    @POST("get_sub_topic_list.json")
    Call<ResponseBody> doListSubActivity(@Body RequestBody jsonObject);

    @POST("get_sub_topic_details.json")
    Call<ResponseBody> doListDetails(@Body RequestBody jsonObject);

    @POST("user_daily_activitie.json")
    Call<ResponseBody> doUserActivity(@Body RequestBody jsonObject);

    @POST("user_activitie.json")
    Call<ResponseBody> doDailyActvitiySubTopic(@Body RequestBody jsonObject);

    @POST("food_list.json")
    Call<ResponseBody> doFoodListt(@Body RequestBody jsonObject);

    @POST("doctor_advice_list.json")
    Call<ResponseBody> doDoctorList(@Body RequestBody jsonObject);

    @POST("problem_solution_list.json")
    Call<ResponseBody> doProblemandSolution(@Body RequestBody jsonObject);

    @POST("doctor_advice_view.json")
    Call<ResponseBody> doDoctorAdviceView(@Body RequestBody jsonObject);

    @POST("problem_solution_view.json")
    Call<ResponseBody> doProblemandSolutionView(@Body RequestBody jsonObject);

    @POST("user_profile.json")
    Call<ResponseBody> doProfile(@Body RequestBody jsonObject);

    @POST("user_profile_update.json")
    Call<ResponseBody> doProfileUpdate(@Body RequestBody jsonObject);

    @POST("daily_elements.json")
    Call<ResponseBody> doDailyElements(@Body RequestBody jsonObject);

}
