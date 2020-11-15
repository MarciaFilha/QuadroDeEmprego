package com.example.quadrodeemprego;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.quadrodeemprego.database.BancoDeDados;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class cadVagas extends AppCompatActivity {

    private EditText nomeVaga;
    private EditText descricaoVaga;

    private SQLiteDatabase conexao;
    private BancoDeDados bancoDeDados;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_vagas);

        nomeVaga = (EditText)findViewById(R.id.nomeVaga);
        descricaoVaga = (EditText)findViewById(R.id.descricaoVaga);
     }

     private void validaCampos() {

         boolean res = false;

         String nome = nomeVaga.getText().toString();
         String descricao = descricaoVaga.getText().toString();

         if (res = isCampoVazio(nome)) {
             nomeVaga.requestFocus();
         } else if (res = isCampoVazio(descricao)) {
             descricaoVaga.requestFocus();
         }

         if (res) {
             AlertDialog.Builder dlg = new AlertDialog.Builder(this);
             dlg.setTitle(R.string.titleAviso);
             dlg.setMessage(R.string.msg_campos_invalidos_brancos);
             dlg.setNeutralButton(R.string.action_ok, null);
             dlg.show();
         }
     }

     private boolean isCampoVazio(String valor){

        boolean resultado = (TextUtils.isEmpty(valor) ||  valor.trim().isEmpty());
        return resultado;
     }

     public boolean onCreateOptionsMenu(Menu menu){
         MenuInflater inflater = getMenuInflater();
         inflater.inflate(R.menu.menu_cad_vagas, menu);

         return super.onCreateOptionsMenu(menu);
     }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        switch (id){
            case R.id.action_ok_cad_vagas:
                validaCampos();
              //  Toast.makeText(this, "Botão OK Selecionado", Toast.LENGTH_LONG).show();
                break;
            case R.id.actionCancelar:
                Toast.makeText(this, "Botão cancelar Selecionado", Toast.LENGTH_LONG).show();
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}