package adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oscarapp.MainActivity;
import com.example.oscarapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import model.Filmes;


public class ListaFilmeAdapter extends RecyclerView.Adapter<ListaFilmeAdapter.MyViewHolder> {
    private List<Filmes> list;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nome, genero;
        ImageView foto;
        View holderSelecionado;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.textViewNome);
            genero = itemView.findViewById(R.id.textViewGenero);
            foto = itemView.findViewById(R.id.imageViewFilme);
            holderSelecionado = itemView;
        }
    }

    public ListaFilmeAdapter(List<Filmes> list){this.list = list; System.out.println(list.size()); }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listaItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.filme_lista_cell, parent, false);
        return new MyViewHolder(listaItem);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Filmes filme = list.get(position);

        Picasso.get().load(filme.getFoto()).into(holder.foto);
        holder.nome.setText(filme.getNome());
        holder.genero.setText((filme.getGenero()));

        if(filme.getId() == MainActivity.voto.getIdFilme()){
            holder.holderSelecionado.setBackgroundColor(Color.parseColor("#567845"));
            holder.nome.setText(filme.getNome() + " (SELECIONADO)");
        }

    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }
}
