package com.example.petshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ListaPetAdapter extends BaseAdapter {

    Context context;
    PetController petController;
    public List<Pet> pets;

    public ListaPetAdapter(Context context) {
        this.context = context;
        petController = PetController.getInstancia(context);
        pets = petController.petRepositorio.buscarTodos();
        petController.atualizarLista();
    }

    @Override
    public int getCount() {
        return pets.size();
    }

    @Override
    public Object getItem(int position) {
        return pets.get(position);
    }

    @Override
    public long getItemId(int position) {
        return pets.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_lista_pets,
                parent,
                false);

        TextView textNome = v.findViewById(R.id.textView_nome);
        TextView textSexo = v.findViewById(R.id.textView_sexo);
        TextView textEspecie = v.findViewById(R.id.textView_especie);
        TextView textRaca = v.findViewById(R.id.textView_raca);
        TextView textIdade = v.findViewById(R.id.textView_idade);
        TextView textDono = v.findViewById(R.id.textView_dono);

        Pet pet = pets.get(position);
        textNome.setText(pet.getNome());
        textSexo.setText(pet.getSexo());
        textEspecie.setText(pet.getEspecie());
        textRaca.setText(pet.getRaca());
        textIdade.setText(pet.getIdade());
        textDono.setText(pet.getDono());

        return v;
    }
}
