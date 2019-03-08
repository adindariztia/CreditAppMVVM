package com.example.adindariztiaputri.creditapplicationmvvm.model;

import com.google.gson.annotations.SerializedName;

public class ListTempat {

    @SerializedName("id")
    private int id;

    @SerializedName("nama")
    private String nama;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    @Override
    public String toString(){
        return nama;
    }

    public boolean equals(Object obj){
        if (obj instanceof ListTempat){
            ListTempat lt = (ListTempat)obj;
            if (lt.getNama().equals(nama) && lt.getId()==id)
                return true;
        }

        return false;
    }
}

