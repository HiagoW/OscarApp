package com.example.oscarapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oscarapp.apiOscar.RetrofitConfig;
import com.example.oscarapp.apiOscar.RetrofitConfigWebService;

import java.util.List;

import adapter.ListaFilmeAdapter;
import model.Diretor;
import model.Filmes;
import model.Voto;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfirmarVotoActivity extends AppCompatActivity {

    TextView textViewTitulo, textViewFilme, textviewDiretor, textViewToken2;
    EditText editTextToken;
    Button button;
    private AlertDialog alerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_voto);
        textViewTitulo = findViewById(R.id.textViewTitulo);
        textViewFilme = findViewById(R.id.textViewFilme);
        textviewDiretor = findViewById(R.id.textViewDiretor);
        textViewToken2 = findViewById(R.id.textViewToken2);
        editTextToken = findViewById(R.id.editTextToken);
        button = findViewById(R.id.buttonConfirmarVoto);

        if(VotoFilmeActivity.filmesList.size() == 0) {

            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Carregando Filmes...");
            progressDialog.show();

            Call<List<Filmes>> call =
                    new RetrofitConfig().getFilmesService().getFilmes();

            call.enqueue(new Callback<List<Filmes>>() {
                @Override
                public void onResponse(Call<List<Filmes>> call, Response<List<Filmes>> response) {
                    if (response.isSuccessful()) {
                        VotoFilmeActivity.filmesList = response.body();
                        progressDialog.dismiss();
                        mostraVotadoFilme();
                    }
                }

                @Override
                public void onFailure(Call<List<Filmes>> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(ConfirmarVotoActivity.this,"Erro ao buscar filmes.", Toast.LENGTH_SHORT).show();
                }
            });
        }

        if(VotoDiretorActivity.diretoresList.size() == 0){

            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Carregando Diretores...");
            progressDialog.show();

            Call<List<Diretor>> call =
                    new RetrofitConfig().getDiretoresService().getDiretores();

            call.enqueue(new Callback<List<Diretor>>() {


                @Override
                public void onResponse(Call<List<Diretor>> call, Response<List<Diretor>> response) {
                    if(response.isSuccessful()){
                        VotoDiretorActivity.diretoresList = response.body();
                        progressDialog.dismiss();
                        mostraVotadoDiretor();
                    }
                }

                @Override
                public void onFailure(Call<List<Diretor>> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(ConfirmarVotoActivity.this,"Erro ao buscar diretores.", Toast.LENGTH_SHORT).show();
                }
            });
        }

        mostraVotadoDiretor();
        mostraVotadoFilme();

        if(MainActivity.user.isVotou()){
            jaVotou();
        }

    }

    private void mostraVotadoDiretor(){
        for(Diretor diretor : VotoDiretorActivity.diretoresList){
            if(diretor.getId() == MainActivity.voto.getIdDiretor()){
                textviewDiretor.setText("Seu voto para diretor foi: " + diretor.getNome());
                break;
            }
        }
    }

    private void mostraVotadoFilme(){
        for(Filmes filme : VotoFilmeActivity.filmesList){
            if(filme.getId() == MainActivity.voto.getIdFilme()){
                textViewFilme.setText("Seu voto para filme foi: " + filme.getNome());
                break;
            }
        }
    }

    private void jaVotou(){
        button.setVisibility(View.GONE);
        editTextToken.setVisibility(View.GONE);
        textViewToken2.setText("Você já votou!");
    }

    private void criaAlertDialog(String text){
        //Cria o gerador do AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //define o titulo
        builder.setTitle("Mensagem");
        //define a mensagem
        builder.setMessage(text);
        //define um botão como positivo
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {

            }
        });

        //define um botão como negativo.
        builder.setNegativeButton("Fechar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {

            }
        });

        //cria o AlertDialog
        alerta = builder.create();
        //Exibe
        alerta.show();
    }

    public void confirmarVoto(View view){

            if(editTextToken.length() == 0){
                Toast.makeText(ConfirmarVotoActivity.this,"Digite o token!",Toast.LENGTH_SHORT).show();
            }else {
                int token = Integer.parseInt(editTextToken.getText().toString());
                Voto votoEnviar = MainActivity.voto;
                votoEnviar.getUsuario().setToken(token);
                Call<Voto> call = new RetrofitConfigWebService().webService().votar(votoEnviar);

                ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setMessage("Votando...");
                progressDialog.show();

                call.enqueue(new Callback<Voto>() {
                    @Override
                    public void onResponse(Call<Voto> call, Response<Voto> response) {
                        if(response.isSuccessful()){
                            progressDialog.dismiss();
                            criaAlertDialog("Voto computado com sucesso.");
                            MainActivity.user.setVotou(true);
                            jaVotou();
                        }else if(response.code() == 401){
                            progressDialog.dismiss();
                            criaAlertDialog("Token incorreto.");
                        }else {
                            progressDialog.dismiss();
                            criaAlertDialog("Erro ao computar voto.");
                        }
                    }

                    @Override
                    public void onFailure(Call<Voto> call, Throwable t) {
                        progressDialog.dismiss();
                        criaAlertDialog("Erro ao computar voto.");
                    }
                });
            }
    }
}