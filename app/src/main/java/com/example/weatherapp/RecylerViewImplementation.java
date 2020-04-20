package com.example.weatherapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MotionEvent;

import java.util.ArrayList;

public class RecylerViewImplementation extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyler_view_implementation);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        GetCities getCities = new GetCities();
        ArrayList<String> cities = getCities.getListOfCities();   //getting the list of cities..
        GetTemp getTemp = new GetTemp();                          //getting the temperature
        for (int i = 0; i < cities.size(); i++) {
            Double temp = getTemp.getTempFromCityName(cities.get(i));
            if (temp == null) {                               //removing the city from the list if its temp cannot be found
                cities.remove(i);
                i--;
            } else
                cities.set(i, cities.get(i) + "     " + String.valueOf(temp));
        }
        mAdapter = new MyAdapter(this,cities);
        recyclerView.setAdapter(mAdapter);


    }

}
