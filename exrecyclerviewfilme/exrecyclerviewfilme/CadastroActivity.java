package com.example.exrecyclerviewfilme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CadastroActivity extends AppCompatActivity {

    private EditText editTextNome, editTextGenero, editTextDiretor;
    private Button btnSalvar;
    private RecyclerView recyclerView;
     private List<Filme> listaFilmes = new ArrayList<>();
     private FilmeAdapter filmeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //vinculação e tal
        editTextNome = findViewById(R.id.editTextNome);
        editTextGenero = findViewById(R.id.editTextGenero);
        editTextDiretor = findViewById(R.id.editTextDiretor);
        btnSalvar = findViewById(R.id.btnSalvar);
        recyclerView = findViewById(R.id.recyclerView);

        filmeAdapter = new FilmeAdapter(listaFilmes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(filmeAdapter);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomeFilme = editTextNome.getText().toString();
                String generoFilme = editTextGenero.getText().toString();
                String diretor = editTextDiretor.getText().toString();

                if (nomeFilme.isEmpty() || generoFilme.isEmpty() || diretor.isEmpty()){
                    Toast.makeText(CadastroActivity.this, "Por favor! Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                }else {
                    Filme novoFilme = new Filme(nomeFilme, generoFilme, diretor);
                    listaFilmes.add(novoFilme);
                    filmeAdapter.notifyDataSetChanged();

                    editTextDiretor.getText().clear();
                    editTextGenero.getText().clear();
                    editTextNome.getText().clear();

                }

            }
        });

    }

}