package com.example.weatherapp;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.weatherapp.City;
import com.example.weatherapp.MySingleton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class GetCitiesWithVolleyAPI {
    public ArrayList<String> fetchCities() {
        DoAsyncTask doAsyncTask = new DoAsyncTask();
        String url = "https://api.openaq.org/v1/cities?country=IN&limit=30";
        ArrayList<String> listOfCities;
        try {
            listOfCities = doAsyncTask.execute(url).get();
            Log.i("obtained result",String.valueOf(listOfCities));
            return listOfCities;
        }
        catch (Exception e) {

        }
        return null;
    }
    public class DoAsyncTask extends AsyncTask<String,Void, ArrayList<String>>{
        ArrayList<String> listOfCities=new ArrayList<>();
        @Override
        protected ArrayList<String> doInBackground(String... strings) {
            return null;
        }

    }
}
