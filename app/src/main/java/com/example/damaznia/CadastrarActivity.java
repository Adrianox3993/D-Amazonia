package com.example.damaznia;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class CadastrarActivity extends AppCompatActivity {

    TextInputEditText senha, senha2;
    TextInputLayout txtConfSenha;
    Button btnCadastrar;
    ProgressBar pb;
    Integer progresso = 0, aux = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        senha = findViewById(R.id.idEditSenha);
        senha2 = findViewById(R.id.idEditSenha2);
        txtConfSenha = findViewById(R.id.idTxtSenha2);
        btnCadastrar = findViewById(R.id.btnCadastrar);
        pb = findViewById(R.id.progressBar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        senha.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void afterTextChanged(Editable s) {
                String pass = senha.getText().toString();
                //if (pass.length() > 8)
                    validarSenhaRobusta(pass);
                //Garante que o campo confirmar senha volte a ficar oculto, caso o campo senha volte
                    // a ter menos que 9 caracteres
                //else txtConfSenha.setVisibility(View.GONE);

            }
        });

        senha2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String pass = senha.getText().toString();
                String pass2 = senha2.getText().toString();
                validarSenha(pass, pass2);
            }
        });
    }

    //A função validarSenha habilita ou desabilita o Botão Cadastrar, dependendo se as senhas conferem
    //ou não
    public void validarSenha(String pass, String pass2) {
        if (pass2.equals(pass)) {
            txtConfSenha.setHintTextColor(ColorStateList.valueOf(Color.GREEN));
            btnCadastrar.setEnabled(true);
        } else if (!pass2.equals(pass)) {
            btnCadastrar.setEnabled(false);
            txtConfSenha.setHintTextColor(ColorStateList.valueOf(Color.RED));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public boolean validarSenhaRobusta(String password) {

        Integer tamanho = password.length();
        Integer tamanhoAnt = tamanho;
        //TextView senha = (TextView) findViewById(R.id.cadSenha);
        //String password = senha.getText().toString();
        boolean isValid = true;
        String upperCaseChars = "(.*[A-Z].*)";
        String lowerCaseChars = "(.*[a-z].*)";
        String numbers = "(.*[0-9].*)";
        String specialChars = "(.*[@,#,$,%].*$)";

        //pb.setProgressTintList(ColorStateList.valueOf(Color.rgb(230,79,82)));
        //pb.setProgressTintList(ColorStateList.valueOf(Color.rgb(254,198,94)));
        //pb.setProgressTintList(ColorStateList.valueOf(Color.rgb(101,201,132)));

        /*if (tamanhoAnt>aux){
            pb.incrementProgressBy(5);
            progresso += 5;
        }else if (tamanhoAnt<aux){
            pb.incrementProgressBy(-5);
            progresso -= 5;
        }*/
        if (tamanho > 20 || tamanho < 8) {
            Toast.makeText(CadastrarActivity.this, "A senha deve ter menos de 20 e mais de 8 caracteres de comprimento", Toast.LENGTH_LONG).show();
            txtConfSenha.setVisibility(View.GONE);
            isValid = false;
        } else if (tamanho>7){
            if (tamanhoAnt>aux) {
                pb.setProgressTintList(ColorStateList.valueOf(Color.rgb(230, 79, 82)));
                pb.incrementProgressBy(5);
            }else{
                pb.setProgressTintList(ColorStateList.valueOf(Color.rgb(230,79,82)));
                pb.incrementProgressBy(-5);
            }
            //progresso += 5;
        }
        else if (!password.matches(upperCaseChars)) {

            Toast.makeText(CadastrarActivity.this, "A senha deve ter pelo menos um caractere maiúsculo", Toast.LENGTH_LONG).show();
            //senha2.setEnabled(false);
            txtConfSenha.setVisibility(View.GONE);
            isValid = false;
        } else if (!password.matches(lowerCaseChars)) {

            Toast.makeText(CadastrarActivity.this, "A senha deve ter pelo menos um caractere minúsculo", Toast.LENGTH_LONG).show();
            //senha2.setEnabled(false);
            txtConfSenha.setVisibility(View.GONE);
            isValid = false;
        } else if (!password.matches(numbers)) {

            Toast.makeText(CadastrarActivity.this, "A senha deve ter pelo menos um número", Toast.LENGTH_LONG).show();
            //senha2.setEnabled(false);
            txtConfSenha.setVisibility(View.GONE);
            isValid = false;
        } else if (!password.matches(specialChars)) {

            Toast.makeText(CadastrarActivity.this, "A senha deve ter pelo menos um caractere especial entre @ # $%", Toast.LENGTH_LONG).show();
            //senha2.setEnabled(false);
            txtConfSenha.setVisibility(View.GONE);
            isValid = false;
        } else {
            Toast.makeText(CadastrarActivity.this, "Ok, Sua senha atende os requisitos", Toast.LENGTH_LONG).show();
            txtConfSenha.setVisibility(View.VISIBLE);
        }

        /*if (progresso<100/3){
            pb.setProgressTintList(ColorStateList.valueOf(Color.rgb(230,79,82)));
        }else if (progresso>100/3 && progresso<(100/3)*2){
            pb.setProgressTintList(ColorStateList.valueOf(Color.rgb(254,198,94)));
        }else if (progresso>(100/3)*2){
            pb.setProgressTintList(ColorStateList.valueOf(Color.rgb(101,201,132)));
        }*/
        aux = tamanhoAnt;
        return isValid;
    }
    public void cadastrar(View view) {
        Intent intent = new Intent(CadastrarActivity.this, EntrarActivity.class);
        startActivity(intent);
        finish();
    }
}