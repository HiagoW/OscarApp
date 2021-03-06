package com.example.oscarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.oscarapp.apiOscar.RetrofitConfigWebService;

import java.math.BigInteger;
import java.security.MessageDigest;

import model.Usuario;
import model.Voto;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText editTextUsuario, editTextSenha;
    public static Usuario user = new Usuario();
    public static Voto voto = new Voto();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextUsuario = findViewById(R.id.editTextUsuario);
        editTextSenha = findViewById(R.id.editTextSenha);
    }

    public void login(View view){
        user = new Usuario();
        voto = new Voto();
        String usuario = editTextUsuario.getText().toString();
        String senha = editTextSenha.getText().toString();
        if(usuario.length() == 0 || senha.length() == 0){
            Toast.makeText(this,"Digite usuário e senha.",Toast.LENGTH_SHORT).show();
        }else{
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(senha.getBytes(),0,senha.length());
                String senhamd5 = new BigInteger(1,md.digest()).toString(16);
                user = new Usuario();
                user.setLogin(usuario);
                user.setSenha(senhamd5);

                ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setMessage("Login...");
                progressDialog.show();

                Call<Usuario> call = new RetrofitConfigWebService().webService().login(user);

                call.enqueue(new Callback<Usuario>() {
                    @Override
                    public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                        if(response.isSuccessful()){
                            user = response.body();
                            voto.setUsuario(user);
                            progressDialog.dismiss();
                            if(user.isVotou()){
                                ProgressDialog progressDialog2 = new ProgressDialog(MainActivity.this);
                                progressDialog2.setMessage("Buscando voto...");
                                progressDialog2.show();
                                Call<Voto> call2 = new RetrofitConfigWebService().webService().buscarVoto(user);
                                call2.enqueue(new Callback<Voto>(){

                                    @Override
                                    public void onResponse(Call<Voto> call, Response<Voto> response) {
                                        if(response.isSuccessful()){
                                            voto = response.body();
                                            progressDialog2.dismiss();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Voto> call, Throwable t) {
                                        progressDialog.dismiss();
                                        Toast.makeText(MainActivity.this, "Erro no buscar voto.", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                            Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                            startActivity(intent);
                        }else if(response.code() == 401){
                            progressDialog.dismiss();
                            Toast.makeText(MainActivity.this, "Usuário ou senha incorretos.", Toast.LENGTH_SHORT).show();
                        }else{
                            progressDialog.dismiss();
                            Toast.makeText(MainActivity.this, "Erro no login.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Usuario> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, "Erro no login.", Toast.LENGTH_SHORT).show();
                    }
                });
            }catch (Exception e){
                Toast.makeText(this,"Erro no processo de login.",Toast.LENGTH_SHORT).show();
            }
        }
    }
}