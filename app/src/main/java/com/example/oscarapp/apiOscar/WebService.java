package com.example.oscarapp.apiOscar;

import java.util.List;

import model.Filmes;
import model.Usuario;
import model.Voto;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface WebService {

    @POST("login")
    Call<Usuario> login(@Body Usuario data);

    @POST("voto/buscarVoto")
    Call<Voto> buscarVoto(@Body Usuario data);

    @POST("voto/votar")
    Call<Voto> votar(@Body Voto data);
}
