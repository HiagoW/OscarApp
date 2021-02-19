package com.example.oscarapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.oscarapp.apiOscar.RetrofitConfig;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import adapter.ListaFilmeAdapter;
import model.Filmes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class VotoFilmeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListaFilmeAdapter listaFilmeAdapter;
    public static List<Filmes> filmesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voto_filme);

        //PARA TESTE
        MainActivity.voto.setIdFilme(2);

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Carregando Filmes...");
        progressDialog.show();

        Call<List<Filmes>> call =
                new RetrofitConfig().getFilmesService().getFilmes();

        call.enqueue(new Callback<List<Filmes>>() {
            @Override
            public void onResponse(Call<List<Filmes>> call, Response<List<Filmes>> response) {
                if(response.isSuccessful()){
                    filmesList = response.body();

                    recyclerView = findViewById(R.id.recyclerViewLista);

                    listaFilmeAdapter = new ListaFilmeAdapter(filmesList);

                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
                    recyclerView.setAdapter(listaFilmeAdapter);

                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<List<Filmes>> call, Throwable t) {

            }
        });



    }
}