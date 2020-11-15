package com.example.quadrodeemprego.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.quadrodeemprego.Login;
import com.example.quadrodeemprego.dominio.entidades.Candidato;


public class BancoDeDados extends SQLiteOpenHelper {


    public BancoDeDados(Context context) {

        super(context, "DBHelper", null, 5);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ScriptCad.getCreateTableCandidato());
        db.execSQL(ScriptCadEmpresa.getCreateTableEmpresa());
        db.execSQL(ScripVagas.getCreateTableVagas());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }

    public String autenticaLogin(String email, String senha){
        SQLiteDatabase db =  getReadableDatabase();
        Cursor c = db.rawQuery(" SELECT * from candidato WHERE emailCandidato =? AND senhaCandidato = ?", new String[]{email, senha});
        Cursor ce = db.rawQuery(" SELECT * from empresa WHERE emailEmpresa =? AND senhaEmpresa = ?", new String[]{email, senha});
            if (c.getCount() > 0){
                return "ok";
            }
            else if (ce.getCount()>0){
                return "okEmpresa";
            }
            else return "Falhou";

    }
}
