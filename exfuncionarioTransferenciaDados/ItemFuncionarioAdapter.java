package com.example.exfuncionario;


import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ItemFuncionarioAdapter extends BaseAdapter {
    private Context context;
    private List<Funcionario> funcionarios;
    public ItemFuncionarioAdapter(Context context){
        this.context = context;
        funcionarios = new ArrayList<>();
    }
    public ItemFuncionarioAdapter(Context context, List<Funcionario> funcionarios){
        this.context = context;
        this.funcionarios = funcionarios;
    }

    @Override
    public int getCount() {
        return funcionarios.size();
    }
    @Override
    public Object getItem(int i) {
        return funcionarios.get(i);
    }
    @Override
    public long getItemId(int i) {
        return funcionarios.get(i).getCodigo();
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = LayoutInflater.from(context).inflate(
                R.layout.item_lista, viewGroup, false);

        TextView textViewNome = v.findViewById(R.id.textView);
        TextView textViewCargo = v.findViewById(R.id.textView2);
        TextView textViewSalario = v.findViewById(R.id.textView3);

        Funcionario funcionario = funcionarios.get(i);
        textViewNome.setText(funcionario.getNome());
        textViewCargo.setText(funcionario.getCargo());
        textViewSalario.setText(String.valueOf(funcionario.getSalario()));

        return v;
    }

    public void atualizarLista(List<Funcionario> funcionarios){
        this.funcionarios.clear();
        this.funcionarios.addAll(funcionarios);
        notifyDataSetChanged();
    }

}
