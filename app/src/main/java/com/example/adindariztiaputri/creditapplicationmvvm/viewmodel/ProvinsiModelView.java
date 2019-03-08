package com.example.adindariztiaputri.creditapplicationmvvm.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import com.example.adindariztiaputri.creditapplicationmvvm.model.DataApplication;
import com.example.adindariztiaputri.creditapplicationmvvm.model.DataJson;
import com.example.adindariztiaputri.creditapplicationmvvm.model.ListKodepos;
import com.example.adindariztiaputri.creditapplicationmvvm.model.ListTempat;
import com.example.adindariztiaputri.creditapplicationmvvm.model.OutputCreateApplication;
import com.example.adindariztiaputri.creditapplicationmvvm.model.ResponseKodepos;
import com.example.adindariztiaputri.creditapplicationmvvm.model.ResponseTempat;
import com.example.adindariztiaputri.creditapplicationmvvm.repo.BaseApiService;
import com.example.adindariztiaputri.creditapplicationmvvm.repo.UtilsApi;
import com.example.adindariztiaputri.creditapplicationmvvm.view.MainActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProvinsiModelView extends ViewModel {
    BaseApiService mApiService;
    private List<ListTempat> arrayProvinsi, arrayKabupaten, arrayKecamatan, arrayKelurahan;
    private List<ListKodepos> arrayKodepos;
    DataJson dataJson;
//    debug
    private MutableLiveData<String> output;
    private MutableLiveData<DataJson> dataApplication;
    private MutableLiveData<List<ListTempat>> listProvinsi, listKabupaten, listKecamatan, listKelurahan;
    private MutableLiveData<List<ListKodepos>> listKodepos;
    public static final String LOG_TAG = "myLog";

//buat debug
//    public LiveData<String> getCoba(){
//        if (coba == null){
//            coba = new MutableLiveData<>();
//
//            loadKabupaten(8);
//        }
//        return coba;
//    }


    //    livedata untuk provinsi
    public LiveData<List<ListTempat>> getProvinsi(){
        if (listProvinsi == null){
            listProvinsi = new MutableLiveData<>();

            loadProvinsi();
        }

        return listProvinsi;
    }

//    livedata untuk kabupaten
    public LiveData<List<ListTempat>> getKabupaten(long idProvinsi){
        listKabupaten = new MutableLiveData<>();

        loadKabupaten(idProvinsi);

        return listKabupaten;
    }

    public LiveData<List<ListTempat>> getKecamatan(long idKabupaten){
        listKecamatan = new MutableLiveData<>();

        loadKecamatan(idKabupaten);

        return listKecamatan;
    }

    public LiveData<List<ListTempat>> getKelurahan(long idKecamatan){
        listKelurahan = new MutableLiveData<>();

        loadKelurahan(idKecamatan);

        return listKelurahan;
    }

    public LiveData<List<ListKodepos>> getKodepos(String kelurahan){
        listKodepos = new MutableLiveData<>();

        loadKodepos(kelurahan);

        return listKodepos;
    }

    public  LiveData<String> sendApplication(DataJson dataJson){
        output = new MutableLiveData<>();

        loadSendApplication(dataJson);

        return output;
    }

    public LiveData<DataJson> showApplication(String id){
        dataApplication = new MutableLiveData<>();

        getDataApplication(id);

        return dataApplication;
    }



    private void loadProvinsi(){
        mApiService = UtilsApi.getApiService();
        Call<ResponseTempat> call = mApiService.getProvinsi();
        call.enqueue(new Callback<ResponseTempat>() {
            @Override
            public void onResponse(Call<ResponseTempat> call, Response<ResponseTempat> response) {

                arrayProvinsi = new ArrayList<>();
                List<ListTempat> listTempats = response.body().getAlltempat();
                for (ListTempat lt : listTempats){
                    arrayProvinsi.add(lt);
                }
                listProvinsi.setValue(arrayProvinsi);
            }

            @Override
            public void onFailure(Call<ResponseTempat> call, Throwable t) {

            }
        });

    }

    private void loadKabupaten(long idProvinsi){
        mApiService = UtilsApi.getApiService();

        Call<ResponseTempat> call = mApiService.getKabupaten(idProvinsi);
        call.enqueue(new Callback<ResponseTempat>() {
            @Override
            public void onResponse(Call<ResponseTempat> call, Response<ResponseTempat> response) {
                arrayKabupaten = new ArrayList<>();
                List<ListTempat> listTempats = response.body().getAlltempat();
                for (ListTempat lt : listTempats){
                    arrayKabupaten.add(lt);
                }
                listKabupaten.setValue(arrayKabupaten);
            }

            @Override
            public void onFailure(Call<ResponseTempat> call, Throwable t) {

            }
        });
    }

    private void loadKecamatan(long idKabupaten){
        mApiService = UtilsApi.getApiService();

        Call<ResponseTempat> call = mApiService.getKecamatan(idKabupaten);
        call.enqueue(new Callback<ResponseTempat>() {
            @Override
            public void onResponse(Call<ResponseTempat> call, Response<ResponseTempat> response) {
                arrayKecamatan = new ArrayList<>();
                List<ListTempat> listTempats = response.body().getAlltempat();
                for (ListTempat lt : listTempats){
                    arrayKecamatan.add(lt);
                }
                listKecamatan.setValue(arrayKecamatan);
            }

            @Override
            public void onFailure(Call<ResponseTempat> call, Throwable t) {

            }
        });
    }

    private void loadKelurahan(long idKecamatan){
        mApiService = UtilsApi.getApiService();

        Call<ResponseTempat> call = mApiService.getKelurahan(idKecamatan);
        call.enqueue(new Callback<ResponseTempat>() {
            @Override
            public void onResponse(Call<ResponseTempat> call, Response<ResponseTempat> response) {
                arrayKelurahan = new ArrayList<>();
                List<ListTempat> listTempats = response.body().getAlltempat();
                for (ListTempat lt : listTempats){
                    arrayKelurahan.add(lt);
                }
                listKelurahan.setValue(arrayKelurahan);
            }

            @Override
            public void onFailure(Call<ResponseTempat> call, Throwable t) {

            }
        });
    }

    private void loadKodepos(String kelurahan){
        mApiService = UtilsApi.getApiService();

        Call<ResponseKodepos> call = mApiService.getKodepos(kelurahan);
        call.enqueue(new Callback<ResponseKodepos>() {
            @Override
            public void onResponse(Call<ResponseKodepos> call, Response<ResponseKodepos> response) {
                arrayKodepos = new ArrayList<>();
                List<ListKodepos> listKode = response.body().getAllKodePos();
                for (ListKodepos lt : listKode){
                    arrayKodepos.add(lt);
                }
                listKodepos.setValue(arrayKodepos);
            }

            @Override
            public void onFailure(Call<ResponseKodepos> call, Throwable t) {

            }
        });
    }

    private void loadSendApplication(DataJson dataJson){
        mApiService = UtilsApi.getApiService();
        Call<OutputCreateApplication> call = mApiService.sendApplication(dataJson);
        call.enqueue(new Callback<OutputCreateApplication>() {
            @Override
            public void onResponse(Call<OutputCreateApplication> call, Response<OutputCreateApplication> response) {
                String id = response.body().getOutput();
                output.setValue(id);
            }

            @Override
            public void onFailure(Call<OutputCreateApplication> call, Throwable t) {

            }
        });
    }

    private void getDataApplication(String id){
        mApiService = UtilsApi.getApiService();
        Call<DataApplication> call = mApiService.showApplication(id);
        call.enqueue(new Callback<DataApplication>() {
            @Override
            public void onResponse(Call<DataApplication> call, Response<DataApplication> response) {
                String name = response.body().getName();
                String phone = response.body().getPhone();
                String email = response.body().getEmail();
                String date = response.body().getDateOfBirth();
                String address = response.body().getAddress();
                String city = response.body().getRef1Address();
                String zipCode = response.body().getZipCode();
                DataJson showData = new DataJson(name, phone, email, date, address, city, zipCode);

                dataApplication.setValue(showData);
            }

            @Override
            public void onFailure(Call<DataApplication> call, Throwable t) {

            }
        });
    }


    //                if (response.isSuccessful()){
//                    coba.setValue("berhasil");
//                } else {
//                    coba.setValue("gagal" + response.code());
//                }
//                coba.setValue("error: " + response.code());
}
