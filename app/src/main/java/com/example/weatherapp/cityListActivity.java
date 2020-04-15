package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class cityListActivity extends AppCompatActivity {
    static JSONObject storedResponse = new JSONObject();

    public void getCitiesFromAPI() {
        String url = "https://api.openaq.org/v1/cities?country=IN&limit=10";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                       try{
                           ArrayList<City> listOfCities=new ArrayList<>();
                           JSONArray jsonArray = response.getJSONArray("results");
                           for(int i=2;i<jsonArray.length();i++) {
                               City city = new City();
                               city.setCity(jsonArray.getJSONObject(i).getString("city"));
                               city.setName(jsonArray.getJSONObject(i).getString("name"));
                               listOfCities.add(city);
                             }
                           listCities(listOfCities);
                       }
                       catch(Exception e){

                       }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }
    public void listCities(final ArrayList<City> listOfCities){
        ListView listView = (ListView) findViewById(R.id.listOfCities);

        ArrayList<String> cityName=new ArrayList<>();
        ArrayList<String> temperatures=new ArrayList<>();
        boolean isExists;
        for(int i=0;i<listOfCities.size();i++){
            isExists=false;
            for(int j=0;j<i;j++) {
                if (listOfCities.get(i).getName().indexOf(listOfCities.get(j).getName()) != -1) {
                    isExists = true;
                    listOfCities.remove(i);
                }
            }
                if(!isExists)
                    cityName.add(listOfCities.get(i).getName());
            }
        for(int i=0;i<cityName.size();i++){

            String url = "https://api.openweathermap.org/data/2.5/weather?q="+cityName.get(i)+"&appid=ac7f8536f0280705e0a21ca710c76f56";
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            try {


                                JSONObject temp = response.getJSONObject("main");
                               listOfCities.get(0).setTemp(Integer.parseInt(temp.getString("temp")));
                            }
                            catch(Exception e){

                            }
                        }
                    }, new Response.ErrorListener() {
                         @Override
                        public void onErrorResponse(VolleyError error) {}
                    });

// Access the RequestQueue through your singleton class.
            MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.listofcities, R.id.city, cityName);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);
        getCitiesFromAPI();
//        Toast.makeText(this.getApplicationContext(), "conntent-->" + String.valueOf(cityListActivity.storedResponse), Toast.LENGTH_LONG).show();

    }

}
