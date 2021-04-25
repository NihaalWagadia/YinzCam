package com.example.yinzcam.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.yinzcam.R;
import com.example.yinzcam.apicall.YinzCamApi;
import com.example.yinzcam.helper.SchedulerData;
import com.example.yinzcam.model.ScheduleApiResponse;
import com.example.yinzcam.model.gamesection.Game;
import com.example.yinzcam.model.gamesection.GameSection;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String BASE_URL = "http://files.yinzcam.com.s3.amazonaws.com/iOS/interviews/ScheduleExercise/";
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        callingApi();
    }

    private void callingApi() {
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
             final Retrofit retrofit = new Retrofit.Builder()
                     .baseUrl(BASE_URL)
                     .addConverterFactory(GsonConverterFactory.create())
                     .build();
             final YinzCamApi yinzCamApi = retrofit.create(YinzCamApi.class);
             Call<ScheduleApiResponse> call = yinzCamApi.getScheduleJson();
             call.enqueue(new Callback<ScheduleApiResponse>() {
                 @Override
                 public void onResponse(Call<ScheduleApiResponse> call, Response<ScheduleApiResponse> response) {
                     final ArrayList<SchedulerData> schedulerData = new ArrayList<>();
                     Log.d(TAG, "onResponse Server Response: " + response.toString());
                     Log.d(TAG, "onResponse received Information: " + response.body().toString());
                     ArrayList<Game> game = response.body().getGameSections().get(0).getGame();
                     for(int i=0; i< game.size(); i++){
                         if(game.get(i).getResult().length()>0){
                             Log.d(TAG, "onResponse \n" +
                                     "awayTeam:" + game.get(i).getOpponent().getName() + "\n" +
                                     "homeScore:" + game.get(i).getHomeScore() +"\n"+
                                     "awayScore:" +game.get(i).getAwayScore() +"\n"+
                                     "time:" +game.get(i).getDate().getText()+"\n"+
                                     "week:" +game.get(i).getWeek() +"\n"+
                                     "result:" +game.get(i).getResult() +"\n"+
                                     "state:" +game.get(i).getGameState() +"\n");

                         }
                         else {
                             Log.d(TAG, "No result");
                         }

                     }

                 }

                 @Override
                 public void onFailure(Call<ScheduleApiResponse> call, Throwable t) {
                     Log.d(TAG,"Something went wrong" + t.getMessage());

                 }
             });
            }

        },1500);
    }
}