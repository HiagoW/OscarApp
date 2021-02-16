package com.example.oscarapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oscarapp.apiOscar.RetrofitConfigWebService;

import model.Usuario;
import model.Voto;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuActivity extends AppCompatActivity {

    TextView textViewToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        textViewToken = findViewById(R.id.textViewToken);
//        textViewToken.setText("Token: " + MainActivity.user.getToken());
                textViewToken.setText("Token: 0");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menu_filme){
            Intent intent = new Intent(MenuActivity.this, VotoFilmeActivity.class);
            startActivity(intent);
        }else if(item.getItemId() == R.id.menu_diretor){
            Intent intent = new Intent(MenuActivity.this, VotoDiretorActivity.class);
            startActivity(intent);
        }else if(item.getItemId() == R.id.confirma_votos){
//            if(MainActivity.user.isVotou()){
//                Toast.makeText(this,"Já votou!",Toast.LENGTH_SHORT).show();
//            }else {
//                MainActivity.voto.setIdDiretor(1);
//                MainActivity.voto.setIdFilme(2);
//                Call<Voto> call = new RetrofitConfigWebService().webService().votar(MainActivity.voto);
//
//                ProgressDialog progressDialog = new ProgressDialog(this);
//                progressDialog.setMessage("Votando...");
//                progressDialog.show();
//
//                call.enqueue(new Callback<Voto>() {
//                    @Override
//                    public void onResponse(Call<Voto> call, Response<Voto> response) {
//                        if(response.isSuccessful()){
//                            progressDialog.dismiss();
//                            Toast.makeText(MenuActivity.this,"Voto computado com sucesso",Toast.LENGTH_SHORT).show();
//                            MainActivity.user.setVotou(true);
//                        }else{
//                            progressDialog.dismiss();
//                            Toast.makeText(MenuActivity.this,"Erro ao computar voto.",Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<Voto> call, Throwable t) {
//                        progressDialog.dismiss();
//                        Toast.makeText(MenuActivity.this,"Erro ao computar voto.",Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
        }
        return super.onOptionsItemSelected(item);
    }
}