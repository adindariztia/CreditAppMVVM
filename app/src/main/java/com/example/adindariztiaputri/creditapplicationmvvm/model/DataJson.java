package com.example.adindariztiaputri.creditapplicationmvvm.model;

public class DataJson {
    final String name_, phone_, email_, date_of_birth_, address_, ref1_address_, zip_code_;

    public DataJson(String name_, String phone_, String email_, String date_of_birth_, String address_, String ref1_address_, String zip_code_){
        this.name_ = name_;
        this.phone_ = phone_;
        this.email_ = email_;
        this.date_of_birth_ = date_of_birth_;
        this.address_ = address_;
        this.ref1_address_ = ref1_address_;
        this.zip_code_= zip_code_;

    }

    public String getName_() {
        return name_;
    }

    public String getPhone_() {
        return phone_;
    }

    public String getEmail_() {
        return email_;
    }

    public String getDate_of_birth_() {
        return date_of_birth_;
    }

    public String getAddress_() {
        return address_;
    }

    public String getRef1_address_() {
        return ref1_address_;
    }

    public String getZip_code_() {
        return zip_code_;
    }
}
