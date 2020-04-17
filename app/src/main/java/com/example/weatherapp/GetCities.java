package com.example.weatherapp;

import android.os.AsyncTask;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class GetCities {

    public ArrayList<String> getListOfCities() {
    ArrayList<String> cityName=new ArrayList<>();

        String response = "";
       GetCityFromApi getCityFromApi=new GetCityFromApi();
        JSONObject jsonObject=null;
        JSONObject temp=null;
        try {
            response = getCityFromApi.execute("https://api.openaq.org/v1/cities?country=IN&limit=30").get();

            jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("results");
            for(int i=2;i<jsonArray.length();i++) {
                boolean isExists=false;
                for(int j=0;j<i;j++) {
                    if (jsonArray.getJSONObject(i).getString("city").indexOf(jsonArray.getJSONObject(j).getString("city")) != -1) {
                        isExists = true;

                    }}
                if(!isExists)
                cityName.add(jsonArray.getJSONObject(i).getString("city"));

            }
            return cityName;
        }
        catch (Exception e)
        {

        }
        return null;
    }
    public class GetCityFromApi extends AsyncTask<String, Void, String> {


            @Override
        protected String doInBackground(String... address) {
            try {
                URL url = new URL(address[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream is = connection.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                int data = isr.read();
                String content = "";
                char ch;
                while(data != -1){
                    ch = (char) data;
                    content = content + ch;
                    data  = isr.read();
                }
                return content;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}