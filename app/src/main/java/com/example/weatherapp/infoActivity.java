package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

public class infoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        final TextView lattitude = (TextView) findViewById(R.id.lattitude);
        final TextView longitude = (TextView) findViewById(R.id.longitude);
        final TextView temperature = (TextView) findViewById(R.id.temperature);

        Intent intent=getIntent();
        final String requestedCityName=intent.getStringExtra("cityName");
        Toast.makeText(this.getApplicationContext(),requestedCityName,Toast.LENGTH_SHORT).show();
        String url = "https://api.openweathermap.org/data/2.5/weather?q="+requestedCityName+"&appid=ac7f8536f0280705e0a21ca710c76f56";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject coord = response.getJSONObject("coord");
                            lattitude.setText(String.valueOf(coord.get("lat")));
                            longitude.setText(String.valueOf(coord.get("lon")));
                            JSONObject temp = response.getJSONObject("main");
                            temperature.setText(String.valueOf(temp.get("temp")));
                            }
                        catch(Exception e){

                        }
                    }
                }, new Response.ErrorListener() {


                    @Override
                    public void onErrorResponse(VolleyError error) {
                        lattitude.setText("error man..."+error.getMessage()+"---->"+String.valueOf(requestedCityName));

                    }
                });

// Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }
}
