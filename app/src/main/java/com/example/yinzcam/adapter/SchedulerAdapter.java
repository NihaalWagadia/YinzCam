package com.example.yinzcam.adapter;

import android.content.Context;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yinzcam.R;
import com.example.yinzcam.helper.SchedulerData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SchedulerAdapter extends RecyclerView.Adapter<SchedulerAdapter.SchedulerViewHolder> {

    private Context context;
    private List<SchedulerData> schedulerData;

    public SchedulerAdapter(Context context, List<SchedulerData> schedulerData){
        this.context = context;
        this.schedulerData = schedulerData;
    }

    @NonNull
    @Override
    public SchedulerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.schedulercard, null);
        return new SchedulerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SchedulerViewHolder holder, int position) {

        final SchedulerData tempSchedulerData = schedulerData.get(position);
        if(tempSchedulerData.getAwayScore() != null){
            Log.d("Trial", tempSchedulerData.getTime());
            holder.awayTeamName.setText(tempSchedulerData.getAwayTeamName());
            holder.homeScore.setText(tempSchedulerData.getHomeScore());
            holder.awayScore.setText(tempSchedulerData.getAwayScore());
            holder.timeStamp.setText(tempSchedulerData.getTime());
            holder.week.setText(tempSchedulerData.getWeek());
            holder.gameState.setText(tempSchedulerData.getGameState());
            Picasso.with(context).load(tempSchedulerData.getImageUrl()).into(holder.awayTeamLogo);
            Picasso.with(context).load(tempSchedulerData.getHomeImageUrl()).into(holder.homeTeamLogo);

        }
        else {
            //holder.bye.setText();
            holder.bye.setVisibility(View.VISIBLE);
        }


    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return schedulerData.size();
    }

    public void addItems(List<SchedulerData> schedulerDataLisrt) {
        schedulerData.addAll(schedulerDataLisrt);
        notifyDataSetChanged();
    }


    public class SchedulerViewHolder extends RecyclerView.ViewHolder {
        TextView awayTeamName, homeScore, awayScore, timeStamp, gameState, week, bye;
        RelativeLayout relativeLayout;
        ImageView awayTeamLogo, homeTeamLogo;


        public SchedulerViewHolder(@NonNull View itemView) {
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
            relativeLayout = itemView.findViewById(R.id.relative_layout);
        }
    }
}
