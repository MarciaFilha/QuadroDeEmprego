package com.example.quadrodeemprego;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.quadrodeemprego.database.BancoDeDados;
import com.example.quadrodeemprego.dominio.entidades.Candidato;
import com.example.quadrodeemprego.dominio.repositorio.repositorioCandidato;
import com.example.quadrodeemprego.dominio.repositorio.repositorioEmpresa;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {


    EditText et_email, et_senha;
    Button bt_login;

    private SQLiteDatabase conexao;
    private BancoDeDados bancoDeDados;
    private ConstraintLayout layoutLogin;
    private Candidato candidato;
    private repositorioCandidato repositorioCandidato;
    private repositorioEmpresa repositorioEmpresa;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        layoutLogin = (ConstraintLayout)findViewById(R.id.layoutLogin);
        criarConexao();


        db = new BancoDeDados(this);

        et_email = (EditText)findViewById(R.id.et_reg_email);
        et_senha = (EditText)findViewById(R.id.et_senha);

        bt_login = (Button)findViewById(R.id.bt_login);


        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = et_email.getText().toString();
                String senha =  et_senha.getText().toString();
                //String emailEmpresa = et_email.getText().toString();
               // String senhaEmpresa =  et_senha.getText().toString();

                if (email.equals("")){
                    Toast.makeText(Login.this,"Email não inserido, tente novamente", Toast.LENGTH_SHORT).show();
                }
                else if (senha.equals("")){
                    Toast.makeText(Login.this, "Senha não informada, tente novamente", Toast.LENGTH_SHORT).show();
                }
                else {
                    String res = db.autenticaLogin(email, senha);

                    if (res.equals("ok")){
                        Toast.makeText(Login.this," Login OK", Toast.LENGTH_SHORT).show();
                    }
                    else if (res.equals("okEmpresa")){
                        Intent intent = new Intent(Login.this, Vagas.class);
                        startActivity(intent);

                    }
                }

            }

        });


    }

    BancoDeDados db;

    private void criarConexao(){
        try{
            bancoDeDados = new BancoDeDados(this);
            conexao = bancoDeDados.getWritableDatabase();
            Snackbar.make(layoutLogin, R.string.msg_conexao_sucesso, Snackbar.LENGTH_SHORT)
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