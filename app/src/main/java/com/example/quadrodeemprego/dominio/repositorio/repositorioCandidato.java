package com.example.quadrodeemprego.dominio.repositorio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.quadrodeemprego.Login;
import com.example.quadrodeemprego.dominio.entidades.Candidato;

import java.util.List;

public class repositorioCandidato {
    private SQLiteDatabase conexao;

    public repositorioCandidato(SQLiteDatabase conexao)
    {
        this.conexao = conexao;
    }

    public void inserir(Candidato candidato){
        ContentValues contentValues = new ContentValues();
        contentValues.put("nomeCandidato", candidato.nomeCandidato);
        contentValues.put("emailCandidato", candidato.emailCandidato);
        contentValues.put("telefoneCandidato", candidato.telefoneCandidato);
        contentValues.put("senhaCandidato", candidato.senhaCandidato);

        conexao.insertOrThrow("candidato", null, contentValues);
    }

    public void excluir(int idCandidato){
        String[] parametros = new String[1];
        parametros[0] = String.valueOf(idCandidato);
        conexao.delete("candidato","idCandidato = ?", parametros);

    }
    public void alterar(Candidato candidato){
        ContentValues contentValues = new ContentValues();
        contentValues.put("nomeCandidato", candidato.nomeCandidato);
        contentValues.put("emailCandidato", candidato.emailCandidato);
        contentValues.put("telefoneCandidato", candidato.telefoneCandidato);
        contentValues.put("senhaCandidato", candidato.senhaCandidato);

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(candidato.idCandidato);
        conexao.update("candidato", contentValues,"idCandidato = ?", parametros);

    }
    public List<Candidato> buscarTodos(){

        return null;
    }

    public Candidato buscarCandidatos(String emailCandidato, String senhaCandidato){
        Candidato candidato = new Candidato();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id, nomeCandidato, emailCandidato, telefoneCandidato, senhaCandidato");
        sql.append(" FROM candidato");
        sql.append("WHERE emailCandidato = ? AND senhaCandidato = ? ");

        Cursor  resultado = conexao.rawQuery(sql.toString(), null);

        return null;
    }



}
