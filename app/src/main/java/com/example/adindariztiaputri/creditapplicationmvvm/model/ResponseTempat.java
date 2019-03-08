package com.example.adindariztiaputri.creditapplicationmvvm.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseTempat {

    @SerializedName("tempats")
    private List<ListTempat> alltempat;

    public List<ListTempat> getAlltempat(){
        return alltempat;
    }
}
