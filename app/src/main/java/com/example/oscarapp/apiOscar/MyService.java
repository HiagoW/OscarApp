package com.example.oscarapp.apiOscar;

import java.util.List;

import model.Diretor;
import model.Filmes;
import retrofit2.Call;
import retrofit2.http.GET;

public interface MyService {

    @GET("/ufpr/diretor")
    Call<List<Diretor>> getDiretor();
}
