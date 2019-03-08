package com.example.adindariztiaputri.creditapplicationmvvm.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseKodepos {
    @SerializedName("kodepos")
    private List<ListKodepos> allKodePos;

    public List<ListKodepos> getAllKodePos(){
        return allKodePos;
    }
}
