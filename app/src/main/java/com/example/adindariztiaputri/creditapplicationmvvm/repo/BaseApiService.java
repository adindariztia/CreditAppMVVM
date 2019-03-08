package com.example.adindariztiaputri.creditapplicationmvvm.repo;

import com.example.adindariztiaputri.creditapplicationmvvm.model.DataApplication;
import com.example.adindariztiaputri.creditapplicationmvvm.model.DataJson;
import com.example.adindariztiaputri.creditapplicationmvvm.model.OutputCreateApplication;
import com.example.adindariztiaputri.creditapplicationmvvm.model.ResponseKodepos;
import com.example.adindariztiaputri.creditapplicationmvvm.model.ResponseTempat;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface BaseApiService {

    @GET("/creditapplication/1.0/DaftarProvinsi")
    Call<ResponseTempat> getProvinsi();

    @GET("/creditapplication/1.0/DaftarKabupaten")
    Call<ResponseTempat> getKabupaten(@Query("provinsi_id") long id);
//    Call<ResponseTempat> getKabupaten();

    @GET("/creditapplication/1.0/DaftarKecamatan")
    Call<ResponseTempat> getKecamatan(@Query("kabupaten_id") long id);

    @GET("/creditapplication/1.0/DaftarKelurahan")
    Call<ResponseTempat> getKelurahan(@Query("kecamatan_id") long id);

    @GET("creditapplication/1.0/DaftarKodepos")
    Call<ResponseKodepos> getKodepos(@Query("kelurahan") String kelurahan);

    @Headers({
            "accept: application/json",
            "Content-type: application/json"
    })
    @POST("/creditapplication/1.0/CreateApplication")
    Call<OutputCreateApplication> sendApplication(@Body DataJson dataJson);

    @GET("/creditapplication/1.0/GetApplication")
    Call<DataApplication> showApplication(@Query("id") String id);
}
