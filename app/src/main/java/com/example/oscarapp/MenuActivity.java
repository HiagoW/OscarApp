package com.example.oscarapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
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
        }
        return super.onOptionsItemSelected(item);
    }
}