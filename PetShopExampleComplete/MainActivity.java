package com.example.petshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private Button cadastroPet;
    private ListView listaCadastros;
    private ListaPetAdapter listaPetAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        cadastroPet = findViewById(R.id.activity_main_iniciarCadastro);
        listaCadastros = findViewById(R.id.activity_main_listview_pet);
        listaPetAdapter = new ListaPetAdapter(this);

        listaCadastros.setAdapter(listaPetAdapter);

        cadastroPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CadastroPet.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}