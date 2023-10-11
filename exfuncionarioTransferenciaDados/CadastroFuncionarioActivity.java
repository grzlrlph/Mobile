package com.example.exfuncionario;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class CadastroFuncionarioActivity extends AppCompatActivity {

    EditText editTextNome, editTextCargo, editTextSalario;
    Button btnCadastrar, btnCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_funcionario);

        btnCancelar = findViewById(R.id.activity_cadastro_button_cancelar);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        editTextNome = findViewById(R.id.activity_cadastro_textView_nome);
        editTextCargo = findViewById(R.id.activity_cadastro_textView_cargo);
        editTextSalario = findViewById(R.id.activity_cadastro_textView_salario);
        btnCadastrar = findViewById(R.id.activity_cadastro_button_cadastrar);
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrar();
                finish();
            }
        });

    }

    private void cadastrar() {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(editTextNome.getText().toString());
        funcionario.setCargo(editTextCargo.getText().toString());
        funcionario.setSalario(Double.parseDouble(editTextSalario.getText().toString()));
        FuncionarioUpload funcionarioUpload = new FuncionarioUpload();
        funcionarioUpload.execute(funcionario);
    }

    class FuncionarioUpload extends AsyncTask<Funcionario, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Funcionario[] funcionarios) {
            Funcionario funcionario = funcionarios[0];
            try {
                URL url = new URL("http://10.100.36.19:80/pdm/cadastraFuncionario.php");
                HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
                conexao.setReadTimeout(5000);
                conexao.setConnectTimeout(5000);
                conexao.setRequestMethod("POST");
                conexao.setRequestProperty("Content-Type","application/json");
                conexao.setDoInput(true);
                conexao.setDoOutput(true);
                conexao.connect();

                OutputStream out = conexao.getOutputStream();
                out.write(funcionarioParaJsonBytes(funcionario));
                out.flush();
                out.close();

                if (conexao.getResponseCode() == HttpURLConnection.HTTP_OK){
                    InputStream in = conexao.getInputStream();
                    String jsonString = converterParaJSONString(in);
                    JSONObject jsonObject = new JSONObject(jsonString);
                    funcionario.setCodigo(jsonObject.getInt("id"));
                    Toast.makeText(getBaseContext(), funcionario.toString(), Toast.LENGTH_SHORT).show();
                    return true;
                }

            } catch (Exception e) {
                Log.d("ERRO", e.toString());
            }
            return false;
        }

        private byte[] funcionarioParaJsonBytes(Funcionario funcionario){
            try{
                JSONObject jsonFuncionario = new JSONObject();
                jsonFuncionario.put("nome", funcionario.getNome());
                jsonFuncionario.put("cargo", funcionario.getCargo());
                jsonFuncionario.put("salario", funcionario.getSalario());
                Log.d("funcionario", jsonFuncionario.toString());
                byte[] dados = jsonFuncionario.toString().getBytes();
                return dados;
            }catch (Exception ex){
                ex.printStackTrace();
            }
            return null;
        }

        private String converterParaJSONString(InputStream in) {
            byte[] buffer = new byte[1024];
            ByteArrayOutputStream dados = new ByteArrayOutputStream();
            try{
                while(true) {
                    int qtdqBytesLido = in.read(buffer, 0, buffer.length);
                    if (qtdqBytesLido == -1)
                        break;
                    dados.write(buffer, 0, qtdqBytesLido);
                }
            }catch (Exception e){
                Log.d("ERRO", e.toString());
            }
            return dados.toString();
        }

    }
}