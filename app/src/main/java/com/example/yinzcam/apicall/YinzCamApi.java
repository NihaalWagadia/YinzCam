package com.example.yinzcam.apicall;

import com.example.yinzcam.model.ScheduleApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface YinzCamApi {

    String BASE_URL = "http://files.yinzcam.com.s3.amazonaws.com/iOS/interviews/ScheduleExercise/";

    @Headers("Content-Type: application/json")
    @GET("schedule.json")
    Call<ScheduleApiResponse> getScheduleJson();
}
