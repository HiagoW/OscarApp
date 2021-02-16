package com.example.oscarapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.LinearLayout;

import adapter.ListaFilmeAdapter;
import model.Diretor;
import model.Filmes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oscarapp.apiOscar.RetrofitConfig;

import java.util.List;

public class VotoDiretorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voto_diretor);

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Carregando Diretores...");
        progressDialog.show();

        Call<List<Diretor>> call =
                new RetrofitConfig().getDiretoresService().getDiretores();

        call.enqueue(new Callback<List<Diretor>>() {


            @Override
            public void onResponse(Call<List<Diretor>> call, Response<List<Diretor>> response) {
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
            public void onFailure(Call<List<Diretor>> call, Throwable t) {

            }
        });
    }
}