package com.example.oscarapp.apiOscar;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    private final Retrofit retrofit;
    private String url = "http://wecodecorp.com.br/";

    public RetrofitConfig(){
        this.retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public FilmesService getFilmesService(){
        return this.retrofit.create(FilmesService.class);
    }


    public DiretoresService getDiretoresService(){ return this.retrofit.create(DiretoresService.class);
    }

}
