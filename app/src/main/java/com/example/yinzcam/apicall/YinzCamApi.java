package com.example.yinzcam.apicall;

import com.example.yinzcam.model.ScheduleApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface YinzCamApi {

    @Headers("Content-Type: application/json")
    @GET("schedule.json")
    Call<ScheduleApiResponse> getScheduleJson();
}
