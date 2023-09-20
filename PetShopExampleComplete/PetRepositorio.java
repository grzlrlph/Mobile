package com.example.petshop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class PetRepositorio {

    private DbHelper helper;

    public PetRepositorio (Context context){
        helper = new DbHelper(context);
    }

    public long cadastrar(Pet pet){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(DbHelper.COLUNA_NOME_PET, pet.getNome());
        valores.put(DbHelper.COLUNA_SEXO_PET, pet.getSexo());
        valores.put(DbHelper.COLUNA_RACA_PET, pet.getRaca());
        valores.put(DbHelper.COLUNA_ESPECIE_PET, pet.getEspecie());
        valores.put(DbHelper.COLUNA_DONO_PET, pet.getDono());
        valores.put(DbHelper.COLUNA_IDADE_PET, pet.getIdade());

        long id = db.insert(DbHelper.TABELA_PET, DbHelper.COLUNA_ID_PET, valores);

        db.close();
        return id;
    }

    public boolean atualizar (Pet pet){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(DbHelper.COLUNA_NOME_PET, pet.getNome());
        valores.put(DbHelper.COLUNA_SEXO_PET, pet.getSexo());
        valores.put(DbHelper.COLUNA_RACA_PET, pet.getRaca());
        valores.put(DbHelper.COLUNA_ESPECIE_PET, pet.getEspecie());
        valores.put(DbHelper.COLUNA_DONO_PET, pet.getDono());
        valores.put(DbHelper.COLUNA_IDADE_PET, pet.getIdade());
        try{
            int linhasAfetadas = db.update(DbHelper.TABELA_PET, valores,
                    DbHelper.COLUNA_ID_PET + " =?",
                    new String[]{String.valueOf(pet.getId())});
            return linhasAfetadas>0;

        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }finally {
            db.close();
        }
    }

    public List<Pet> buscarTodos(){
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "SELECT * FROM " + DbHelper.TABELA_PET + " ORDER BY "+ DbHelper.COLUNA_NOME_PET;
        Cursor cursor = db.rawQuery(sql, null);

        List<Pet> pets = new ArrayList<>();
        while(cursor.moveToNext()){
            long id = cursor.getLong(0);
            String nome = cursor.getString(1);
            String sexo = cursor.getString(2);
            String raca = cursor.getString(3);
            String especie = cursor.getString(4);
            String dono = cursor.getString(5);
            int idade = cursor.getInt(6);

            Pet pet = new Pet(id, nome, sexo, raca, especie, dono,idade);
            pets.add(pet);
        }
        cursor.close();
        db.close();
        return pets;
    }
}
