package com.kibet.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.squareup.picasso.Picasso;

import java.util.List;

public class PredictionsAdapter extends ArrayAdapter<Prediction> {
    private List<Prediction> predictionList;
    private Bitmap bitmap;
    private Context context;

    public PredictionsAdapter(Context context, int item_prediction, List<Prediction> predictionList) {
        super(context,R.layout.item_prediction ,predictionList);
        this.predictionList = predictionList;
        this.context = context;
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder;
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(R.layout.item_prediction, null, true);
        holder = new ViewHolder();
        //getting view
        holder.imageCountryFlag = convertView.findViewById(R.id.countryFlagTV);
        holder.textCountryName = convertView.findViewById(R.id.countryTV);
        holder.textLeagueName = convertView.findViewById(R.id.leagueTV);
        holder.textHomeTeam = convertView.findViewById(R.id.teamOneTV);
        holder.textAwayTeam = convertView.findViewById(R.id.teamTwoTV);
        holder.textFinalResults = convertView.findViewById(R.id.resultsTV);
        convertView.setTag(holder);

        //Getting the tutorial for the specified position
        Prediction prediction = predictionList.get(position);
        String imageUrl = prediction.getImageUrl();
        String countryName = prediction.getCountryName();
        String leagueName = prediction.getLeagueName();
        String homeTeam = prediction.getHomeTeam();
        String awayTeam = prediction.getAwayTeam();
        String predictionResult = prediction.getPredictionResult();


        //holder.imageCountryFlag.setImageURI(Uri.parse(imageUrl));
        Picasso.get().load(imageUrl).resize(50, 50).centerCrop().into(holder.imageCountryFlag);
        holder.textCountryName.setText(countryName);
        holder.textLeagueName.setText(leagueName.split("-")[0]);
        holder.textHomeTeam.setText(homeTeam);
        holder.textFinalResults.setText(predictionResult);
        holder.textAwayTeam.setText(awayTeam);
        return convertView;
    }

    static class ViewHolder {
        ImageView imageCountryFlag;
        TextView textCountryName;
        TextView textLeagueName;
        TextView textHomeTeam;
        TextView textFinalResults;
        TextView textAwayTeam;

    }
}




