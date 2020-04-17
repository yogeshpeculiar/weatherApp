package com.example.weatherapp;

import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.ArrayList;

public interface ResponseListener {
    public void processRequest(String temp);
}
