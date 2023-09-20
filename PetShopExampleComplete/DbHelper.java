package com.example.petshop;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper  extends SQLiteOpenHelper {
    private static final String DbNome = "CadastroPet";
    private static final int VERSAO = 1;

    public static final String TABELA_PET = "Pet";
    public static final String COLUNA_ID_PET = "_id";
    public static final String COLUNA_NOME_PET = "nome";
    public static final String COLUNA_IDADE_PET = "idade";
    public static final String COLUNA_SEXO_PET = "sexo";
    public static final String COLUNA_RACA_PET = "raça";
    public static final String COLUNA_ESPECIE_PET = "espécie";
    public static final String COLUNA_DONO_PET = "dono";



    public DbHelper(Context context) {
        super(context, DbNome, null, VERSAO);
        //pra que serve o factory?
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABELA_PET + "("+
                COLUNA_ID_PET + "INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUNA_NOME_PET + "TEXT NOT NULL, "+
                COLUNA_IDADE_PET + "INTEGER NOT NULL, "+
                COLUNA_SEXO_PET + "TEXT NOT NULL, "+
                COLUNA_RACA_PET + "TEXT NOT NULL, "+
                COLUNA_ESPECIE_PET + "TEXT NOT NULL, "+
                COLUNA_DONO_PET + "TEXT NOT NULL"+");";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int VERSAO, int i1) {
        // Atualiza o banco de dados, se necessário
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_PET);
        onCreate(db);

    }
}
