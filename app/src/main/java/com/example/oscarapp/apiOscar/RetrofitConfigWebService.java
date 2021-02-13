package com.example.oscarapp.apiOscar;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfigWebService {

    private final Retrofit retrofit;
    private String url = "http://192.168.15.12:8080/oscarapp/";

    public RetrofitConfigWebService(){
        this.retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public WebService webService(){
        return this.retrofit.create(WebService.class);
    }

}
