package com.example.quadrodeemprego.database;

public class ScripVagas {
    public static String getCreateTableVagas() {

        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE Vagas" );
        sql.append("       nomeVaga  VARCHAR (200) NOT NULL,");
        sql.append("       Descrição VARCHAR (250) NOT NULL,");
        sql.append("        IdVaga    INTEGER       PRIMARY KEY AUTOINCREMENT");
        sql.append("        NOT NULL,");
        sql.append("       idEmpresa   REFERENCES empresa (idEmpresa)");
        sql.append("      NOT NULL);");


        return sql.toString();
    }

}
