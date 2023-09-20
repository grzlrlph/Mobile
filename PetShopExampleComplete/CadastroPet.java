package com.example.petshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.ParseException;

public class CadastroPet extends AppCompatActivity {

    private EditText editTextNome, editTextSexo,
            editTextEspecie, editTextRaca,
            editTextIdade, editTextDono;
    private Button btnCadastrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pet);

        editTextNome = findViewById(R.id.editTextNome);
        editTextSexo = findViewById(R.id.editTextSexo);
        editTextEspecie = findViewById(R.id.editTextEspecie);
        editTextRaca = findViewById(R.id.editTextRaca);
        editTextIdade = findViewById(R.id.editTextIdade);
        editTextDono = findViewById(R.id.editTextDono);
        btnCadastrar = findViewById(R.id.btnCadastrar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                criarPet();
                finish();
            }
        });
    }



    private void criarPet(){
        long id = -1;
        String nome = editTextNome.getText().toString();
        String sexo = editTextSexo.getText().toString();
        String especie = editTextEspecie.getText().toString();
        String raca = editTextRaca.getText().toString();
        int idade = Integer.parseInt(editTextIdade.getText().toString());
        String dono = editTextDono.getText().toString();

        Pet pet = new Pet(id, nome, sexo,raca, especie, dono, idade);

    }
}