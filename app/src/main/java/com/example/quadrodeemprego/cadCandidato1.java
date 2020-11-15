package com.example.quadrodeemprego;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quadrodeemprego.database.BancoDeDados;
import com.example.quadrodeemprego.dominio.entidades.Candidato;
import com.example.quadrodeemprego.dominio.repositorio.repositorioCandidato;
import com.google.android.material.snackbar.Snackbar;

import java.util.regex.Pattern;

public class cadCandidato1 extends AppCompatActivity {

    private long id;
    private EditText nomeCandidato;
    private EditText emailCandidato;
    private EditText telefoneCandidato;
    private EditText senhaCandidato;

    private repositorioCandidato repositorioCandidato;

    private SQLiteDatabase conexao;
    private BancoDeDados bancoDeDados;
    private ConstraintLayout layoutConstraintCadCandidato;
    private Candidato candidato;

    private void criarConexao(){
        try{
            bancoDeDados = new BancoDeDados(this);
            conexao = bancoDeDados.getWritableDatabase();
            Snackbar.make(layoutConstraintCadCandidato, R.string.msg_conexao_sucesso, Snackbar.LENGTH_SHORT)
                    .setAction(R.string.action_ok, null).show();
            repositorioCandidato = new repositorioCandidato(conexao);

        }catch (SQLException ex){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle(R.string.title_erro);
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton(R.string.action_ok, null);
            dlg.show();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_candidato1);

        nomeCandidato =     (EditText)findViewById(R.id.nomeCandidato);
        emailCandidato =    (EditText)findViewById(R.id.emailCandidato);
        telefoneCandidato = (EditText)findViewById(R.id.telefoneCandidato);
        senhaCandidato =    (EditText)findViewById(R.id.senhaCandidato);

        layoutConstraintCadCandidato = (ConstraintLayout) findViewById(R.id.layoutConstraintCadCandidato);
        criarConexao();
    }

    private void confirmar(){
        candidato = new Candidato();

       if (validaCampos()==false){
           try {

           repositorioCandidato.inserir(candidato);
           finish();

       }catch (SQLException ex){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle(R.string.title_erro);
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton(R.string.action_ok, null);
            dlg.show();
        }
       }

    }

    private boolean validaCampos(){
        boolean res = false;

        String nome = nomeCandidato.getText().toString();
        String email = emailCandidato.getText().toString();
        String telefone = telefoneCandidato.getText().toString();
        String senha = senhaCandidato.getText().toString();

        candidato.nomeCandidato = nome;
        candidato.emailCandidato = email;
        candidato.telefoneCandidato = telefone;
        candidato.senhaCandidato = senha;

        if( res = isCampoVazio(nome)){
            nomeCandidato.requestFocus();
        }
        else  if ( res = !isEmailValido(email)){
            emailCandidato.requestFocus();
        }
        else if (res = isCampoVazio(telefone)){
            telefoneCandidato.requestFocus();
        }
        else if (res = isCampoVazio(senha)){
            senhaCandidato.requestFocus();
        }
       if (res){
           AlertDialog.Builder dlg = new AlertDialog.Builder(this);
           dlg.setTitle(R.string.titleAviso);
           dlg.setMessage(R.string.msg_campos_invalidos_brancos);
           dlg.setNeutralButton(R.string.action_ok, null);
           dlg.show();
       }
        return res;
    }

    private boolean isCampoVazio(String valor){
        boolean resultado = (TextUtils.isEmpty(valor) || valor.trim().isEmpty());
        return resultado;
    }

    private boolean isEmailValido(String email){
        boolean resultado = (!isCampoVazio(email)&& Patterns.EMAIL_ADDRESS.matcher(email).matches());
        return resultado;

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cad_candidato, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        switch (id){
            case R.id.actionOk:
                confirmar();
                //Toast.makeText(this,"Botão OK Selecionado", Toast.LENGTH_LONG).show();
                break;
            case R.id.actionCancelar:

                //Toast.makeText(this,"Botão Cancelar Selecionado", Toast.LENGTH_LONG).show();
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}