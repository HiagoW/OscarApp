package com.example.oscarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetalheFilmeActivity extends AppCompatActivity {

    int idFilme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_filme);

        TextView nome = findViewById(R.id.textViewNome2);
        TextView genero = findViewById(R.id.textViewGenero2);
        ImageView foto = findViewById(R.id.imageViewFotoG);
        Button votar = findViewById(R.id.votar);

        Intent it = getIntent();

        if (it != null) {
            Bundle params = it.getExtras();
            if (params != null) {
                String nomeFilme = params.getString("nomeFilme");
                String generoFilme = params.getString("generoFilme");
                String fotoFilme = params.getString("fotoFilme");
                idFilme = params.getInt("idFilme");

                nome.setText(nomeFilme);
                genero.setText(generoFilme);
                Picasso.get().load(fotoFilme).into(foto);

            }
        }

        if(MainActivity.user.isVotou()){
            votar.setVisibility(View.GONE);
        }
    }

    public void buttonVotar(View view){
        MainActivity.voto.setIdFilme(idFilme);

        VotoFilmeActivity.vfa.finish();

        Intent it = new Intent(DetalheFilmeActivity.super.getBaseContext(), VotoFilmeActivity.class);

        startActivity(it);

        finish();

    }
}