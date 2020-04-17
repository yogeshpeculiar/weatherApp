package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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


public class cityList extends AppCompatActivity {
    static JSONObject storedResponse = new JSONObject();

    public void getCitiesFromAPI() {
        String url = "https://api.openaq.org/v1/cities?country=IN&limit=20";
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

    public void listCities( ArrayList<City> listOfCities){
        ListView listView = (ListView) findViewById(R.id.listOfCities);
        GetTemp getTemp=new GetTemp();
        ArrayList<String> cityName=new ArrayList<>();
        ArrayList<String> temperatures=new ArrayList<>();
        boolean isExists;
        for(int i=0;i<listOfCities.size();i++){
            isExists=false;
            for(int j=0;j<i;j++) {
                if (listOfCities.get(i).getName().indexOf(listOfCities.get(j).getName()) != -1) {
                    isExists = true;
                    listOfCities.remove(i);
                }}
                if(!isExists) {
                    String temp=String.valueOf(getTemp.getTempFromCityName(listOfCities.get(i).getName()));

                    if(temp!="null")
                       cityName.add(listOfCities.get(i).getName()+"    "+temp);

                }
        }


        Toast.makeText(this.getApplicationContext(),String.valueOf("temp"+String.valueOf(getTemp.getTempFromCityName(listOfCities.get(4).getName()))),Toast.LENGTH_LONG).show();






        Log.i("temperature--->",String.valueOf(listOfCities.get(0).getTemp()));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.listofcities, R.id.city, cityName);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);

                showMoreInfo(selectedItem);
            }
        });
    }
public void showMoreInfo(String selectedString){
        Intent intent=new Intent(this.getApplicationContext(),showDetailsOfTheCity.class);
        intent.putExtra("selectedCity",selectedString);
    startActivity(intent);
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);
        getCitiesFromAPI();



    }

}
