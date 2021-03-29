package com.example.damaznia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class CadastrarActivity extends AppCompatActivity {

    TextInputEditText senha, senha2;
    TextInputLayout txtConfSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        senha = findViewById(R.id.idEditSenha);
        senha2 = findViewById(R.id.idEditSenha2);
        txtConfSenha = findViewById(R.id.idTxtSenha2);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        senha.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //String pass = senha.getText().toString();
                //String pass2 = senha2.getText().toString();
                //validarSenhaRobusta(pass);
                //validarSenha(pass, pass2);
            }

            @Override
            public void afterTextChanged(Editable s) {
                String pass = senha.getText().toString();
                if (pass.length() > 8)
                validarSenhaRobusta(pass);
            }
        });

    }

    public void validarSenha(View v) {
        senha = findViewById(R.id.idEditSenha);
        senha2 = findViewById(R.id.idEditSenha2);
        String pass1 = senha.getText().toString();
        String pass2 = senha2.getText().toString();

        if (pass2.equals("") && pass1.equals("")){
            //txtConfSenha.setBoxStrokeErrorColor(ColorStateList.valueOf(Color.GREEN));
            //Toast.makeText(CadastrarActivity.this, "teste de comparacao 1", Toast.LENGTH_LONG).show();
            Toast.makeText(CadastrarActivity.this, "Senha não pode TEAMO ser vazia", Toast.LENGTH_SHORT).show();
        } else if (!pass1.equals(pass2)) {
            //txtConfSenha.setBoxStrokeColorStateList(ColorStateList.valueOf(Color.RED));
            Toast.makeText(CadastrarActivity.this, "Senha não confere", Toast.LENGTH_SHORT).show();
        } else if (pass1.length() <8 || pass2.length() < 8) {
            Toast.makeText(CadastrarActivity.this, "Senha nao pode conter menos de 8 caracteres", Toast.LENGTH_SHORT).show();
        } else{
            Intent intent = new Intent(getApplicationContext(), EntrarActivity.class);
            startActivityForResult(intent, 0);
        }

    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(getApplicationContext(), EntrarActivity.class);
        startActivityForResult(intent, 0);
        return true;
    }

    public boolean validarSenhaRobusta(String password)
    {

        //TextView senha = (TextView) findViewById(R.id.cadSenha);
        //String password = senha.getText().toString();
        boolean isValid = true;
        String upperCaseChars = "(.*[A-Z].*)";
        String lowerCaseChars = "(.*[a-z].*)";
        String numbers = "(.*[0-9].*)";
        String specialChars = "(.*[@,#,$,%].*$)";

        if (password.length() > 20 || password.length() < 8)
        {
            // System.out.println("Password must be less than 20 and more than 8 characters in length.");
            Toast.makeText(CadastrarActivity.this, "A senha deve ter menos de 20 e mais de 8 caracteres de comprimento", Toast.LENGTH_LONG).show();
            //senha2.setEnabled(false);
            senha2.setVisibility(View.GONE);
            isValid = false;
        }
        else if (!password.matches(upperCaseChars ))
        {
            Toast.makeText(CadastrarActivity.this, "A senha deve ter pelo menos um caractere maiúsculo", Toast.LENGTH_LONG).show();
            //senha2.setEnabled(false);
            senha2.setVisibility(View.GONE);
            isValid = false;
        }
        else if (!password.matches(lowerCaseChars ))
        {
            Toast.makeText(CadastrarActivity.this, "A senha deve ter pelo menos um caractere minúsculo", Toast.LENGTH_LONG).show();
            //senha2.setEnabled(false);
            senha2.setVisibility(View.GONE);
            isValid = false;
        }
        else if (!password.matches(numbers ))
        {
            Toast.makeText(CadastrarActivity.this, "A senha deve ter pelo menos um número", Toast.LENGTH_LONG).show();
            //senha2.setEnabled(false);
            senha2.setVisibility(View.GONE);
            isValid = false;
        }
        else if (!password.matches(specialChars ))
        {
            Toast.makeText(CadastrarActivity.this, "A senha deve ter pelo menos um caractere especial entre @ # $%", Toast.LENGTH_LONG).show();
            //senha2.setEnabled(false);
            senha2.setVisibility(View.GONE);
            isValid = false;
        }
        else{
            Toast.makeText(CadastrarActivity.this, "Ok, Sua senha atende os requisitos", Toast.LENGTH_LONG).show();
            senha2.setVisibility(View.VISIBLE);
        }

        return isValid;
    }
}