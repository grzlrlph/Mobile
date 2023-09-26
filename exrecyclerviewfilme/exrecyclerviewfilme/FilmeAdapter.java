package com.example.exrecyclerviewfilme;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FilmeAdapter extends RecyclerView.Adapter<FilmeAdapter.ViewHolder> {

    private List<Filme> listaFilmes;

    public FilmeAdapter(List<Filme> listaFilmes) {
        this.listaFilmes = listaFilmes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista_filme, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Filme filme = listaFilmes.get(position);

        holder.textNome.setText(filme.getNome());
        holder.textGenero.setText(filme.getGenero());
        holder.textDiretor.setText(filme.getDiretor());


    }

    @Override
    public int getItemCount() {
        return listaFilmes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textNome, textGenero, textDiretor;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textNome = itemView.findViewById(R.id.textViewNome);
            textGenero = itemView.findViewById(R.id.textViewGenero);
            textDiretor = itemView.findViewById(R.id.textViewDiretor);

        }
    }
}
