package com.example.oscarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.math.BigInteger;
import java.security.MessageDigest;

public class MainActivity extends AppCompatActivity {

    EditText editTextUsuario, editTextSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextUsuario = findViewById(R.id.editTextUsuario);
        editTextSenha = findViewById(R.id.editTextSenha);
    }

    public void login(View view){
        String usuario = editTextUsuario.getText().toString();
        String senha = editTextSenha.getText().toString();
        if(usuario.length() == 0 || senha.length() == 0){
            Toast.makeText(this,"Digite usuário e senha.",Toast.LENGTH_SHORT).show();
        }else{
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(senha.getBytes(),0,senha.length());
                String senhamd5 = new BigInteger(1,md.digest()).toString(16);

            }catch (Exception e){
                Toast.makeText(this,"Erro no processo de login.",Toast.LENGTH_SHORT).show();
            }
        }
        Intent intent = new Intent(MainActivity.this, MenuActivity.class);
        startActivity(intent);
    }
}