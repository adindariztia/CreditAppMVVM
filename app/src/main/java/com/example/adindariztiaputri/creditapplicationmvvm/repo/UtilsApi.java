package com.example.adindariztiaputri.creditapplicationmvvm.repo;

public class UtilsApi {
    public static final String BASE_URL_API = "https://api-gateway.nextapi.navcore.com";

    public static BaseApiService getApiService(){
        return RetrofitClient.getClient(BASE_URL_API).create(BaseApiService.class);
    }
}
