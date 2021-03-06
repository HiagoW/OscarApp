package com.example.oscarapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

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
    public static Activity vfa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        vfa = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voto_filme);

        //PARA TESTE
        //MainActivity.voto.setIdFilme(2);

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

                    recyclerView.addOnItemTouchListener(new MyRecycleViewClickListener(VotoFilmeActivity.super.getBaseContext(), new MyRecycleViewClickListener.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            Intent it = new Intent(VotoFilmeActivity.this, DetalheFilmeActivity.class);

                            Bundle params = new Bundle();
                            params.putString("nomeFilme", filmesList.get(position).getNome());
                            params.putString("generoFilme", filmesList.get(position).getGenero());
                            params.putString("fotoFilme", filmesList.get(position).getFoto());
                            params.putInt("idFilme", filmesList.get(position).getId());

                            it.putExtras(params);

                            startActivity(it);
                        }
                    }));
                }
            }

            @Override
            public void onFailure(Call<List<Filmes>> call, Throwable t) {
                Toast.makeText(VotoFilmeActivity.this,"Erro ao buscar filmes.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}