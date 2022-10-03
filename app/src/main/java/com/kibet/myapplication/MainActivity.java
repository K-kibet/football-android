package com.kibet.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    private  static final  String JSON_URL = "https://apiv2.allsportsapi.com/football/?met=Livescore&APIkey=8859625bd594b254542534e009ae3e9ce12cf3662ac436b139fd475f843819fa";
    //the tutorial list where we will store all the tutorial objects after parsing json
    List<Prediction> predictionList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        listView = findViewById(R.id.predictions);
        predictionList = new ArrayList<>();
        
        //this method will fetch and parse the data
        loadPredictionList();

        //this method will create new activity when list item is clicked

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent predictionIntent = new Intent(getApplicationContext(), activity_prediction.class);
                Prediction extras = predictionList.get(i);
                String matchId = extras.getMatchId();
                predictionIntent.putExtra("matchID", matchId);
                startActivity(predictionIntent);
            }
        });
    }

    private void loadPredictionList() {
        final ContentLoadingProgressBar progressBar =  findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        //creating a string request to send request to the url
        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressBar.setVisibility(View.INVISIBLE);
                        try {
                            //getting the whole json object from the response
                            JSONObject obj = new JSONObject(response);

                            JSONArray dataArray = obj.getJSONArray("result");
                            System.out.println(dataArray);

                            //looping through all the elements of the json array
                            for (int i = 0; i < dataArray.length(); i++) {
                                //getting the json object of the particular index inside the array
                                JSONObject predictionsObject = dataArray.getJSONObject(i);

                                //creating a tutorial object and giving them the values from json object
                                Prediction prediction = new Prediction(predictionsObject.getString("country_name"),
                                        predictionsObject.getString("league_name"), predictionsObject.getString("league_logo"),
                                        predictionsObject.getString("event_home_team"), predictionsObject.getString("event_away_team"),
                                        predictionsObject.getString("event_final_result"), predictionsObject.getString("event_key"));

                                //adding the prediction to predictionlist
                                predictionList.add(prediction);

                                //creating custom adapter object
                                PredictionsAdapter adapter = new PredictionsAdapter(getApplicationContext(),R.layout.item_prediction,predictionList);

                                //adding the adapter to listview
                                listView.setAdapter(adapter);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //adding the string request to request queue
        requestQueue.add(stringRequest);

    }
}