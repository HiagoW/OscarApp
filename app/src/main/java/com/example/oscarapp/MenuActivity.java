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
        textViewToken.setText("Token: " + MainActivity.user.getToken());
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
            if(MainActivity.voto.getIdFilme() == 0 ||
            MainActivity.voto.getIdDiretor() == 0){
                Toast.makeText(MenuActivity.this, "Você ainda não votou nas categorias.", Toast.LENGTH_SHORT).show();
            }else {
                Intent intent = new Intent(MenuActivity.this, ConfirmarVotoActivity.class);
                startActivity(intent);
            }
        }
        return super.onOptionsItemSelected(item);
    }
}