package adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
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
import java.net.URI;
import java.net.URL;
import java.util.List;

import model.Filmes;


public class ListaFilmeAdapter extends RecyclerView.Adapter<ListaFilmeAdapter.MyViewHolder> {
    private List<Filmes> list;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nome, genero;
        ImageView foto;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.textViewNome);
            genero = itemView.findViewById(R.id.textViewGenero);
            foto = itemView.findViewById(R.id.imageViewFilme);
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

        /*Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream((InputStream)new URL(filme.getFoto()).getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        holder.nome.setText(filme.getNome());
        holder.genero.setText((filme.getGenero()));

        holder.foto.setMaxHeight(50);
        holder.foto.setMaxWidth(50);
        //holder.foto.setImageBitmap(bitmap);

    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }
}
