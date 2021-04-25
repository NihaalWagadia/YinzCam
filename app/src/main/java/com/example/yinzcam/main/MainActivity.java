package com.example.yinzcam.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.ParseException;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import com.example.yinzcam.R;
import com.example.yinzcam.adapter.SchedulerAdapter;
import com.example.yinzcam.apicall.YinzCamApi;
import com.example.yinzcam.helper.SchedulerData;
import com.example.yinzcam.model.ScheduleApiResponse;
import com.example.yinzcam.model.gamesection.Game;
import com.example.yinzcam.model.gamesection.GameSection;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String BASE_URL = "http://files.yinzcam.com.s3.amazonaws.com/iOS/interviews/ScheduleExercise/";
    private static final String TAG = "MainActivity";
    String awayTeamName, homeScore, awayScore, timeStamp, gameState, week, imageUrl,homeImageUrl;
    Date val;
    SchedulerAdapter adapter;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        callingApi();
    }

    private void callingApi() {
        new Handler().postDelayed(new Runnable() {
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
                        final ArrayList<SchedulerData> schedulerDataArrayList = new ArrayList<>();
                        Log.d(TAG, "onResponse Server Response: " + response.toString());
                        Log.d(TAG, "onResponse received Information: " + response.body().toString());
                        ArrayList<Game> game = response.body().getGameSections().get(0).getGame();
                        for (int i = 0; i < game.size(); i++) {
                            if (game.get(i).getResult().length() > 0) {
                                if(game.get(i).isHome()){
                                    homeScore = game.get(i).getHomeScore();
                                    awayScore = game.get(i).getAwayScore();
                                }
                                else {
                                    homeScore = game.get(i).getAwayScore();
                                    awayScore = game.get(i).getHomeScore();
                                }
                                Log.d(TAG, "onResponse \n" +
                                        "awayTeam:" + game.get(i).getOpponent().getName() + "\n" +
                                        "homeScore:" + game.get(i).getHomeScore() + "\n" +
                                        "awayScore:" + game.get(i).getAwayScore() + "\n" +
                                        "time:" + game.get(i).getDate().getText() + "\n" +
                                        "week:" + game.get(i).getWeek() + "\n" +
                                        "result:" + game.get(i).getResult() + "\n" +
                                        "state:" + game.get(i).getGameState() + "\n");

                                awayTeamName = game.get(i).getOpponent().getName();
                                timeStamp = game.get(i).getDate().getText();
                                week = game.get(i).getWeek();
                                gameState = game.get(i).getGameState();
                                imageUrl = "http://yc-app-resources.s3.amazonaws.com/nfl/logos/nfl_"+game.get(i).getOpponent().getTriCode().toLowerCase()+"_light.png";
                                homeImageUrl = "http://yc-app-resources.s3.amazonaws.com/nfl/logos/nfl_gb_light.png";


                                SchedulerData schedulerData = new SchedulerData(awayTeamName, homeScore, awayScore, timeStamp, week, gameState, imageUrl, homeImageUrl);
                                schedulerDataArrayList.add(schedulerData);


                            } else {
                                Log.d("Dummy", "No result");
                                SchedulerData schedulerData = new SchedulerData(null, null, null, null, null, null, null, null);
                                schedulerDataArrayList.add(schedulerData);
                            }

                        }
                        adapter = new SchedulerAdapter(getApplicationContext(), new ArrayList<SchedulerData>());
                        recyclerView.setAdapter(adapter);
                        adapter.addItems(schedulerDataArrayList);


                    }

                    @Override
                    public void onFailure(Call<ScheduleApiResponse> call, Throwable t) {
                        Log.d("ERROR TAG", "Something went wrong" + t.getMessage());

                    }
                });
            }

        }, 1500);
    }
}