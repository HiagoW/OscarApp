package com.example.oscarapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

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

import java.util.ArrayList;
import java.util.List;

public class VotoDiretorActivity extends AppCompatActivity {

    RadioGroup rg;
    public static List<Diretor> diretoresList = new ArrayList<>();

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
                    diretoresList = response.body();
                    rg = findViewById(R.id.radioGroup);
                    for(Diretor d: diretoresList){
                        RadioButton rb = new RadioButton(VotoDiretorActivity.this);
                        rb.setText(d.getNome());
                        rb.setId(d.getId());
                        rg.addView(rb);
                    }
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<List<Diretor>> call, Throwable t) {

            }
        });

        rg.setOnCheckedChangeListener(new rg.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                idDiretor = checkedId;
            }
        });
    }
}