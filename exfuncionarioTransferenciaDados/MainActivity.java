package com.example.exfuncionario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fabAdicionar;
    ProgressBar progressBar;
    ListView listView;

    ItemFuncionarioAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        listView = findViewById(R.id.listView);
        adapter = new ItemFuncionarioAdapter(getApplicationContext());
        listView.setAdapter(adapter);
        progressBar.setVisibility(View.GONE);

        fabAdicionar = findViewById(R.id.floatingActionButton);
        fabAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getBaseContext(), CadastroFuncionarioActivity.class);
                startActivity(it);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizarLista();
    }

    private void atualizarLista() {
        FuncionarioDownload funcionarioDownload = new FuncionarioDownload();
        funcionarioDownload.execute();
    }

    class FuncionarioDownload extends AsyncTask{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            List<Funcionario> funcionarios = (List<Funcionario>) o;
            adapter.atualizarLista(funcionarios);
            progressBar.setVisibility(View.GONE);
        }

        @Override
        protected List<Funcionario> doInBackground(Object[] objects) {
            List<Funcionario> funcionarios = new ArrayList<>();
            try {
                URL url = new URL("http://10.100.36.19/pdm/carregaFuncionarios.php");
                HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
                conexao.setReadTimeout(1500);
                conexao.setConnectTimeout(500);
                conexao.setRequestMethod("GET");
                conexao.setDoInput(true);
                conexao.setDoOutput(false);
                conexao.connect();
                if (conexao.getResponseCode() == HttpURLConnection.HTTP_OK){
                    InputStream in = conexao.getInputStream();
                    String jsonString = converterParaJSONString(in);
                    funcionarios = converterParaListaFuncionario(jsonString);
                }
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return funcionarios;
        }

        public String converterParaJSONString(InputStream in){
            ByteArrayOutputStream dados = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            try{
                while (true) {
                    int qtde = in.read(buffer);
                    if (qtde == -1) break;
                    dados.write(buffer, 0, qtde);
                }
            }catch(Exception ex){
                Log.d("ERROR", ex.toString());
            }
            return dados.toString();
        }

        public List<Funcionario> converterParaListaFuncionario(String jsonString){
            List<Funcionario> funcionarios = new ArrayList<>();

            try {
                Log.d("dados", jsonString);
                JSONObject jsonObject = new JSONObject(jsonString);
                JSONArray jsonArray = jsonObject.getJSONArray("funcionarios");
                for (int i = 0; i < jsonArray.length(); i++){
                    Funcionario funcionario = new Funcionario();
                    JSONObject funcionarioJSON = jsonArray.getJSONObject(i);
                    funcionario.setNome(funcionarioJSON.getString("nome"));
                    funcionario.setCargo(funcionarioJSON.getString("cargo"));
                    funcionario.setSalario(funcionarioJSON.getDouble("salario"));
                    funcionarios.add(funcionario);
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            return funcionarios;
        }
    }


}