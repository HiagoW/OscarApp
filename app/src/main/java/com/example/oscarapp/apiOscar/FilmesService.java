package com.example.oscarapp.apiOscar;

import java.util.List;

import model.Filmes;
import retrofit2.Call;
import retrofit2.http.GET;

public interface FilmesService {

    @GET("/ufpr/filme")
    Call<List<Filmes>> getFilmes();
}
