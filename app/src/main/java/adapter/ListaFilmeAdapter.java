package adapter;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oscarapp.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import model.Filmes;

public class ListaFilmeAdapter extends RecyclerView.Adapter<ListaFilmeAdapter.MyViewHolder> {
    private List<Filmes> list;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nome, genero;
        ImageView imagem;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.textViewNome);
            genero = itemView.findViewById(R.id.textViewGenero);
            imagem = itemView.findViewById(R.id.imageViewFilme);
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
        InputStream is = null;
        try {
            is = (InputStream) new URL(filme.getUrl()).getContent();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Drawable d = Drawable.createFromStream(is, "src name");
        holder.nome.setText(filme.getTitle());
        holder.genero.setText((filme.getGenero()));
        holder.imagem.setImageDrawable(d);
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }
}
