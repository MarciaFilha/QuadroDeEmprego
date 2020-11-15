package com.example.quadrodeemprego;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.quadrodeemprego.database.BancoDeDados;
import com.google.android.material.snackbar.Snackbar;

public class cadastro extends AppCompatActivity {

    private SQLiteDatabase conexao;
    private BancoDeDados bancoDeDados;
    private ConstraintLayout layoutMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        layoutMain = (ConstraintLayout)findViewById(R.id.layoutMain);
        criarConexao();



        final Button cadCandidato1 = (Button) findViewById(R.id.cadCandidato1);
        cadCandidato1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(cadastro.this, cadCandidato1.class);
                startActivity(it);
            }
        });
        final Button cadEmpresa = (Button) findViewById(R.id.cadEmpresa);
        cadEmpresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(cadastro.this, cadEmpresa.class);
                startActivity(it);
            }
        });
        final Button Login = (Button) findViewById(R.id.Login);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(cadastro.this, Login.class);
                startActivity(it);
            }
        });

    }
    private void criarConexao(){
        try{
            bancoDeDados = new BancoDeDados(this);
            conexao = bancoDeDados.getWritableDatabase();
            Snackbar.make(layoutMain, R.string.msg_conexao_sucesso, Snackbar.LENGTH_SHORT)
                    .setAction(R.string.action_ok, null).show();

        }catch (SQLException ex){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle(R.string.title_erro);
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton(R.string.action_ok, null);
            dlg.show();
        }
    }


}