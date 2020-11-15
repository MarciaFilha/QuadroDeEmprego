package com.example.quadrodeemprego.database;

public class ScriptCadEmpresa {

    public static String getCreateTableEmpresa(){

        StringBuilder sql = new StringBuilder();


                sql.append("CREATE TABLE empresa (");
                sql.append("  idEmpresa       INTEGER       PRIMARY KEY AUTOINCREMENT");
                sql.append("  NOT NULL,");
                sql.append("  nomeEmpresa     VARCHAR (250) NOT NULL ,");
                sql.append("  emailEmpresa    VARCHAR (250) NOT NULL ,");
                sql.append("  telefoneEmpresa VARCHAR (200) NOT NULL ,");
                sql.append("  cnpj            VARCHAR (30)  NOT NULL ,");
                sql.append("  senhaEmpresa    VARCHAR (8)   NOT NULL );");
        return sql.toString();
    }
}
