package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
                                                }
    public  void getInfo(View view){
        EditText cityName=(EditText)findViewById(R.id.cityName);
        Intent intent=new Intent(this.getApplicationContext(),infoActivity.class);

        intent.putExtra("cityName",cityName.getText().toString());
        startActivity(intent);
    }
    public void viewListOfCities(View view){
        Intent intent=new Intent(this.getApplicationContext(),cityListActivity.class);
        startActivity(intent);
    }
}
