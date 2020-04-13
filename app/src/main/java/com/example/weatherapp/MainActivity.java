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


//        String url = "https://api.openweathermap.org/data/2.5/weather?q=madurai,tamilnadu&appid=ac7f8536f0280705e0a21ca710c76f56";
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
//                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
//
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        textView.setText("Response: " + response.toString());
//                    }
//                }, new Response.ErrorListener() {
//
//
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        textView.setText("error man..."+error.getMessage());
//
//                    }
//                });
//
//// Access the RequestQueue through your singleton class.
//        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);




    }
    public  void getInfo(View view){
        EditText cityName=(EditText)findViewById(R.id.cityName);
        Intent intent=new Intent(this.getApplicationContext(),infoActivity.class);

        intent.putExtra("cityName",cityName.getText().toString());
        startActivity(intent);
    }
}
