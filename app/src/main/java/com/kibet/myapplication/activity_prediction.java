package com.kibet.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ContentLoadingProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class activity_prediction extends AppCompatActivity {

    ImageView leagueImage;
    TextView leagueName;

    TextView homeTeamTv;
    ImageView homeTeamImage;
    TextView homeTeamCountryTv;

    TextView finalResultTV;
    TextView halfTimeResultTV;

    TextView awayTeamTv;
    ImageView awayTeamImage;
    TextView awayTeamCountryTv;

    TextView homeTeamHeadTv;
    TextView homeHeadNameTv;

    TextView drawHeadTv;

    TextView awayTeamHeadTv;
    TextView awayHeadNameTv;

    TextView ov25Tv;
    TextView un25Tv;

    TextView ov35Tv;
    TextView un35Tv;

    TextView ov15Tv;
    TextView un15Tv;



    String JSON_URL ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prediction);


        leagueImage = findViewById(R.id.leagueImage);
        leagueName = findViewById(R.id.leagueName);

        homeTeamTv = findViewById(R.id.homeTeamTv);
        homeTeamImage = findViewById(R.id.homeTeamImage);
        homeTeamCountryTv = findViewById(R.id.homeTeamCountryTv);

        finalResultTV = findViewById(R.id.finalResultTV);
        halfTimeResultTV = findViewById(R.id.halfTimeResultTV);

        awayTeamTv = findViewById(R.id.awayTeamTv);
        awayTeamImage = findViewById(R.id.awayTeamImage);
        awayTeamCountryTv = findViewById(R.id.awayTeamCountryTv);

        homeTeamHeadTv = findViewById(R.id.homeTeamHeadTv);
        homeHeadNameTv = findViewById(R.id.homeHeadNameTv);

        drawHeadTv = findViewById(R.id.drawHeadTv);

        awayTeamHeadTv = findViewById(R.id.awayTeamHeadTv);
        awayHeadNameTv = findViewById(R.id.awayHeadNameTv);

        ov25Tv = findViewById(R.id.ov25Tv);
        un25Tv = findViewById(R.id.un25Tv);

        ov35Tv = findViewById(R.id.ov35Tv);
        un35Tv = findViewById(R.id.un35Tv);

        ov15Tv = findViewById(R.id.ov15Tv);
        un15Tv = findViewById(R.id.un15Tv);

        Intent intent = getIntent();
        String matchId = intent.getStringExtra("matchID");
        JSON_URL = "https://apiv2.allsportsapi.com/football/?met=Probabilities&matchId=" + matchId + "&APIkey=8859625bd594b254542534e009ae3e9ce12cf3662ac436b139fd475f843819fa";
        getData();
    }

    private void getData() {
        final ContentLoadingProgressBar progressBar = findViewById(R.id.progressBarPrediction);
        progressBar.setVisibility(View.VISIBLE);

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //progressBar.setVisibility(View.INVISIBLE);
                        try {
                            JSONObject data = new JSONObject(response);
                            JSONArray result = data.getJSONArray("result");
                            JSONObject dataArray = result.getJSONObject(0);
                            //Picasso.get().load(dataArray.getString("")).resize(50, 50).centerCrop().into(leagueImage);
                            leagueName.setText(dataArray.getString("league_name"));

                            homeTeamTv.setText(dataArray.getString("event_home_team"));
                            //homeTeamImage.setText(dataArray.getString("country_name"));
                            homeTeamCountryTv.setText(dataArray.getString("country_name"));

                            finalResultTV.setText(dataArray.getString("event_final_result"));
                            halfTimeResultTV.setText(dataArray.getString("event_halftime_result"));

                            awayTeamTv.setText(dataArray.getString("event_away_team"));
                            //awayTeamImage.setText(dataArray.getString("country_name"));
                            awayTeamCountryTv.setText(dataArray.getString("country_name"));

                            homeTeamHeadTv.setText(dataArray.getString("event_HW").split("\\.")[0].concat("%"));
                            homeHeadNameTv.setText(dataArray.getString("event_home_team"));

                            drawHeadTv.setText(dataArray.getString("event_D").split("\\.")[0].concat("%"));

                            awayTeamHeadTv.setText(dataArray.getString("event_AW").split("\\.")[0].concat("%"));
                            awayHeadNameTv.setText(dataArray.getString("event_away_team"));

                            ov25Tv.setText(dataArray.getString("event_O").split("\\.")[0].concat("%"));
                            un25Tv.setText(dataArray.getString("event_U").split("\\.")[0].concat("%"));

                            ov35Tv.setText(dataArray.getString("event_O_3").split("\\.")[0].concat("%"));
                            un35Tv.setText(dataArray.getString("event_U_3").split("\\.")[0].concat("%"));

                            ov15Tv.setText(dataArray.getString("event_O").split("\\.")[0].concat("%"));
                            un15Tv.setText(dataArray.getString("event_U").split("\\.")[0].concat("%"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast toast = Toast.makeText(activity_prediction.this, "No data found for the match", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();
                            finish();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(activity_prediction.this, "Unable to make request", Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}