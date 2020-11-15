package com.example.quadrodeemprego;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.quadrodeemprego.database.BancoDeDados;
import com.example.quadrodeemprego.dominio.entidades.Empresa;
import com.example.quadrodeemprego.dominio.repositorio.repositorioEmpresa;
import com.google.android.material.snackbar.Snackbar;

public class cadEmpresa extends AppCompatActivity {

    private long idEmpresa;
    private EditText nomeEmpresa;
    private EditText emailEmpresa;
    private EditText telefoneEmpresa;
    private EditText senhaEmpresa;
    private EditText cnpj;

    private repositorioEmpresa repositorioEmpresa;

    private SQLiteDatabase conexao;
    private BancoDeDados bancoDeDados;
    private ConstraintLayout layoutConstraintCadEmpresa;
    private Empresa empresa;

    private void criarConexao(){
        try{
            bancoDeDados = new BancoDeDados(this);
            conexao = bancoDeDados.getWritableDatabase();
            Snackbar.make(layoutConstraintCadEmpresa, R.string.msg_conexao_sucesso, Snackbar.LENGTH_SHORT)
                    .setAction(R.string.action_ok, null).show();
            repositorioEmpresa = new repositorioEmpresa(conexao);

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
        setContentView(R.layout.activity_cad_empresa);

        nomeEmpresa     = (EditText)findViewById(R.id.nomeEmpresa);
        emailEmpresa    = (EditText)findViewById(R.id.emailEmpresa);
        telefoneEmpresa = (EditText)findViewById(R.id.telefoneEmpresa);
        senhaEmpresa    = (EditText)findViewById(R.id.senhaEmpresa);
        cnpj            = (EditText)findViewById(R.id.cnpj);

        layoutConstraintCadEmpresa = (ConstraintLayout) findViewById(R.id.layoutConstraintCadEmpresa);
        criarConexao();
    }




    private void confirmar(){

        empresa = new Empresa();

        if (validaCampos() ==false){

            try {
                repositorioEmpresa.inserir(empresa);
                finish();

            } catch (SQLException ex){
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

        String nome     = nomeEmpresa.getText().toString();
        String email    = emailEmpresa.getText().toString();
        String telefone = telefoneEmpresa.getText().toString();
        String Cnpj     = cnpj.getText().toString();
        String senha    = senhaEmpresa.getText().toString();

        empresa.nomeEmpresa = nome;
        empresa.emailEmpresa = email;
        empresa.telefoneEmpresa = telefone;
        empresa.cnpj = Cnpj;
        empresa.senhaEmpresa = senha;


        if ( res = isCampoVazio(nome)){
            nomeEmpresa.requestFocus();
        }
        else if ( res = isCampoVazio(telefone)){
            telefoneEmpresa.requestFocus();
        }
        else if (res = !isEmailValido(email)){
            emailEmpresa.requestFocus();
        }
        else if ( res = isCampoVazio(Cnpj)){
            cnpj.requestFocus();
        }
        else if ( res = isCampoVazio(senha)){
            senhaEmpresa.requestFocus();
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
        boolean resultado = (TextUtils.isEmpty(valor)|| valor.trim().isEmpty());
        return resultado;
    }

    private boolean isEmailValido(String email){
        boolean resultado = (!isCampoVazio(email)&& Patterns.EMAIL_ADDRESS.matcher(email).matches());
        return resultado;
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cad_empresa, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.actionOKempresa:
                confirmar();
                //Toast.makeText(this, "Botão OK Selecionado", Toast.LENGTH_LONG).show();
                break;
            case R.id.actionCancelar:

                //Toast.makeText(this, "Botão Cancelar Selecionado", Toast.LENGTH_LONG).show();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}