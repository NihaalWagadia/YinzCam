package com.example.yinzcam.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yinzcam.R;
import com.example.yinzcam.helper.Match;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SchedulerAdapter extends RecyclerView.Adapter<SchedulerAdapter.MatchViewHolder> {

    private Context context;
    private List<Match> matchData;
    private String versus = "V";

    public SchedulerAdapter(Context context, List<Match> matchData){
        this.context = context;
        this.matchData = matchData;
    }

    @NonNull
    @Override
    public MatchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.schedulercard, null);
        return new MatchViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MatchViewHolder holder, int position) {

        final Match tempMatch = matchData.get(position);
        if(tempMatch.getAwayScore() != null){
            holder.awayTeamName.setText(tempMatch.getAwayTeamName());
            holder.homeScore.setText(tempMatch.getHomeScore());
            holder.awayScore.setText(tempMatch.getAwayScore());
            holder.timeStamp.setText(tempMatch.getTime());
            holder.week.setText(tempMatch.getWeek());
            holder.gameState.setText(tempMatch.getGameState());
            holder.versus.setText(versus);
            holder.homeTeamName.setText("PACKERS");
            Picasso.with(context).load(tempMatch.getImageUrl()).into(holder.awayTeamLogo);
            Picasso.with(context).load(tempMatch.getHomeImageUrl()).into(holder.homeTeamLogo);

        }
        else {
            holder.bye.setVisibility(View.VISIBLE);
        }


    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return matchData.size();
    }

    public void addItems(List<Match> matchLisrt) {
        matchData.addAll(matchLisrt);
        notifyDataSetChanged();
    }


    public class MatchViewHolder extends RecyclerView.ViewHolder {
        TextView awayTeamName, homeScore, awayScore, timeStamp, gameState, week, bye, versus, homeTeamName;
        RelativeLayout relativeLayout;
        ImageView awayTeamLogo, homeTeamLogo;


        public MatchViewHolder(@NonNull View itemView) {
            super(itemView);
            awayTeamName = itemView.findViewById(R.id.away_team_name);
            homeScore =  itemView.findViewById(R.id.home_team_score);
            awayScore = itemView.findViewById(R.id.away_team_score);
            timeStamp = itemView.findViewById(R.id.time_of_play);
            gameState = itemView.findViewById(R.id.state_of_play);
            week = itemView.findViewById(R.id.week);
            bye = itemView.findViewById(R.id.bye);
            awayTeamLogo = itemView.findViewById(R.id.away_team_logo);
            homeTeamLogo = itemView.findViewById(R.id.home_team_logo);
            homeTeamName = itemView.findViewById(R.id.home_team_name);
            relativeLayout = itemView.findViewById(R.id.relative_layout);
            versus = itemView.findViewById(R.id.versus);
        }
    }
}
