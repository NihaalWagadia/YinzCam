package com.example.yinzcam.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.ParseException;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.yinzcam.R;
import com.example.yinzcam.adapter.SchedulerAdapter;
import com.example.yinzcam.apicall.YinzCamApi;
import com.example.yinzcam.model.Match;
import com.example.yinzcam.model.ScheduleApiResponse;
import com.example.yinzcam.model.responseobjects.Game;
import com.example.yinzcam.model.responseobjects.TeamInfo;

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
    private String homeImageUrl = "";
    private static final String startUrl = "http://yc-app-resources.s3.amazonaws.com/nfl/logos/nfl_";
    private static final String endUrl = "_light.png";
    String awayTeamName, homeScore, awayScore, timeStamp, gameState, week, imageUrl, numeric, day, homeTeamName;
    Date date;
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
                        final ArrayList<Match> matchArrayList = new ArrayList<>();
                        Log.d(TAG, "onResponse Server Response: " + response.toString());
                        Log.d(TAG, "onResponse received Information: " + response.body().toString());
                        ArrayList<Game> game = response.body().getGameSections().get(0).getGame();
                        TeamInfo teamInfos = response.body().getTeam();
                        for(int j=0; j<1; j++){
                            for (int i = game.size()-1; i >= 0; i--) {
                                Match match = null;
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
                                    imageUrl = startUrl+game.get(i).getOpponent().getTriCode().toLowerCase()+endUrl;
                                    homeImageUrl = startUrl+teamInfos.getHomeTriCode().toLowerCase()+endUrl;
                                    numeric = game.get(i).getDate().getNumeric();
                                    TimeZone tz = TimeZone.getTimeZone("UTC");
                                    homeTeamName = teamInfos.getHomeName();
                                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                                    df.setTimeZone(tz);
                                    try {
                                        date = df.parse(numeric);
                                    } catch (ParseException | java.text.ParseException e) {
                                        e.printStackTrace();
                                    }
                                    day = date.toString();
                                    String[] detailed_time = day.split(" ");
                                    day = detailed_time[0]+", "+detailed_time[1]+" "+detailed_time[2];
                                    match = new Match(awayTeamName, homeScore, awayScore, day, week, gameState, imageUrl, homeImageUrl, homeTeamName);


                                } else {
                                    match = new Match(null, null, null, null, null, null, null, null, null);
                                }
                                matchArrayList.add(match);

                            }
                        }

                        adapter = new SchedulerAdapter(getApplicationContext(), new ArrayList<Match>());
                        recyclerView.setAdapter(adapter);
                        adapter.addItems(matchArrayList);


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