package com.example.oscarapp.apiOscar;

import java.util.List;

import model.Diretor;
import retrofit2.Call;
import retrofit2.http.GET;

public interface DiretoresService {
    @GET("/ufpr/diretor")
    Call<List<Diretor>> getDiretores();
}
