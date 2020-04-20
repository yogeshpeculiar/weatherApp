package com.example.weatherapp;

import java.util.ArrayList;

public class City {
    String name;
    String city;
    double temp;
//    ArrayList<com.example.weatherapp.City> listOfCities=new ArrayList<>();
//    public void addCity(com.example.weatherapp.City city){
//        listOfCities.add(city);
//    }
//    public ArrayList<com.example.weatherapp.City> getListOfCities() {
//        return listOfCities;
//    }

    public City() {
    }

//    public void setListOfCities(ArrayList<com.example.weatherapp.City> listOfCities) {
//        this.listOfCities = listOfCities;
//    }

    public City(String name, String city, double temp) {
        this.name = name;
        this.city = city;
        this.temp = temp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

}
