package com.example.quadrodeemprego.database;

public class ScriptCad {

    public static String getCreateTableCandidato(){

        StringBuilder sql = new StringBuilder();

         sql.append("      CREATE TABLE candidato ( ");
         sql.append("      id                INTEGER       PRIMARY KEY AUTOINCREMENT");
         sql.append("      NOT NULL,");
         sql.append("      nomeCandidato     VARCHAR (250),");
         sql.append("      emailCandidato    VARCHAR (200) NOT NULL,");
         sql.append("      telefoneCandidato VARCHAR (250) NOT NULL,");
         sql.append("      senhaCandidato    VARCHAR (8)   NOT NULL);");

         return sql.toString();
    }


}
