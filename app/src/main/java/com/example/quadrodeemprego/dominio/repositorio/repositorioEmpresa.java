package com.example.quadrodeemprego.dominio.repositorio;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.example.quadrodeemprego.dominio.entidades.Empresa;

import java.util.List;

public class repositorioEmpresa {
    private SQLiteDatabase conexao;

    public repositorioEmpresa(SQLiteDatabase conexao){

        this.conexao = conexao;
    }

    public void inserir(Empresa empresa){
        ContentValues contentValues = new ContentValues();

        contentValues.put("nomeEmpresa", empresa.nomeEmpresa);
        contentValues.put("emailEmpresa", empresa.emailEmpresa);
        contentValues.put("telefoneEmpresa", empresa.telefoneEmpresa);
        contentValues.put("cnpj", empresa.cnpj);
        contentValues.put("senhaEmpresa", empresa.senhaEmpresa);

        conexao.insertOrThrow("empresa",null, contentValues);
    }
    public void excluir(int idEmpresa){

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(idEmpresa);
        conexao.delete("empresa", "idEmpresa = ?", parametros);


    }
    public void alterarDados(Empresa empresa){
        ContentValues contentValues = new ContentValues();
        contentValues.put("nomeEmpresa", empresa.nomeEmpresa);
        contentValues.put("emailEmpresa", empresa.emailEmpresa);
        contentValues.put("telefoneEmpresa", empresa.telefoneEmpresa);
        contentValues.put("cnpj", empresa.cnpj);
        contentValues.put("senhaEmpresa", empresa.senhaEmpresa);

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(empresa.idEmpresa);

        conexao.update("empresa", contentValues, "idEmpresa = ?", parametros);

    }
    public List<Empresa> buscarTodas(){
        return null;
    }
    public Empresa buscarEmpresa(int idEmpresa){
        return null;
    }
}
