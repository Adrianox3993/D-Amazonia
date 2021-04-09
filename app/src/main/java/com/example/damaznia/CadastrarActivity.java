package com.example.damaznia;


import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class CadastrarActivity extends AppCompatActivity {

    TextInputEditText senha, senha2;
    TextInputLayout txtConfSenha;
    TextView txtCondSenha;
    Button btnCadastrar;
    ProgressBar pb;
    Integer aux=0, getProgress=0;
    //Variaveis para validação e mudança de texto e cor da variavel txtCondSenha
    Boolean contCharTam=false, contCharMa=false, contCharMi=false,
            contCharNum=false, contCharS=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        senha = findViewById(R.id.idEditSenha);
        senha2 = findViewById(R.id.idEditSenha2);
        txtConfSenha = findViewById(R.id.idTxtSenha2);
        txtCondSenha = findViewById(R.id.txtCondSenha);
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
                String pass2 = senha2.getText().toString();
                validarSenhaRobusta(pass);
                //validarSenha(pass, pass2);
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
        if (pass2.equals(pass) && !pass2.isEmpty() && !pass.isEmpty()) {
            txtConfSenha.setHintTextColor(ColorStateList.valueOf(Color.rgb(101, 201, 132)));
            btnCadastrar.setEnabled(true);
            btnCadastrar.setTextColor(Color.WHITE);
        } else if (!pass2.equals(pass)) {
            btnCadastrar.setEnabled(false);
            txtConfSenha.setHintTextColor(ColorStateList.valueOf(Color.rgb(230,79,82)));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public boolean validarSenhaRobusta(String password) {

        int tamanho = password.length();
        int tamanhoAnt = tamanho;
        boolean isValid = true;
        String upperCaseChars = getString(R.string.stringCasoChaMa);
        String lowerCaseChars = getString(R.string.stringCasoCharMi);
        String numbers = getString(R.string.stringCasoCharNum);
        String special = getString(R.string.stringCasoCharS);

        //Condição para incremento da barra de progresso caso o tamanho seja 9
        //se  condição foi satisfeita e depois apagada, é decrementado.
        //(simplifiquei a condição para ficar mais facil de implementar a barra de progresso, 
        // futuramente volta a condição >8 <20)
        if (tamanho==9 && tamanhoAnt>aux){
            pb.incrementProgressBy(100/5);
            contCharTam=true;
        }else if (tamanho==8 && aux==9){
            pb.incrementProgressBy(-100/5);
            contCharTam=false;
        }
        //Condição --//-- caso o tenha uma letra maiúscula
        //se  condição foi satisfeita e depois apagada, é decrementado.
        if (password.matches(upperCaseChars) && !contCharMa) {
            pb.incrementProgressBy(100/5);
            contCharMa = true;
        }else if (!password.matches(upperCaseChars) && contCharMa) {
            pb.incrementProgressBy(-100/5);
            contCharMa=false;
            isValid = false;
        }
        //Condição --//-- --//-- uma letra minnúscula
        //se  condição foi satisfeita e depois apagada, é decrementado.
        if (password.matches(lowerCaseChars) && !contCharMi){
            pb.incrementProgressBy(100/5);
            contCharMi=true;
        }else if (!password.matches(lowerCaseChars) && contCharMi) {
            pb.incrementProgressBy(-100/5);
            contCharMi=false;
            isValid = false;
        }
        //Condição --//-- --//-- um número
        //se  condição foi satisfeita e depois apagada, é decrementado.
        if (password.matches(numbers) && !contCharNum){
            pb.incrementProgressBy(100/5);
            contCharNum=true;
        }else if (!password.matches(numbers) && contCharNum) {
            pb.incrementProgressBy(-100/5);
            contCharNum=false;
            isValid = false;
        }
        //Condição --//-- --//-- um char especial
        //se  condição foi satisfeita e depois apagada, é decrementado.
        if (password.matches(special) && !contCharS){
            pb.incrementProgressBy(100/5);
            contCharS=true;
        }else if (!password.matches(special) && contCharS) {
            pb.incrementProgressBy(-100/5);
            contCharS=false;
            isValid = false;
        }
        //Se as condições forem verdadeiras, o campo confirmar senha é habilitado
        //senão, o texto da variavel txtCondSenha muda de acordo com a condição não satisfeita
        //botão Cadastrar é desabilitado caso condições não satisfeitas.
        if (contCharTam && contCharMa && contCharMi && contCharNum && contCharS){
            txtCondSenha.setText(R.string.stringSenhaMForte);
            txtCondSenha.setTextColor(ColorStateList.valueOf(Color.rgb(101, 201, 132)));
            txtConfSenha.setVisibility(View.VISIBLE);
        }else if (!contCharMa){
            txtCondSenha.setText(R.string.stringCondSenhaMa);
            txtCondSenha.setTextColor(ColorStateList.valueOf(Color.rgb(230,79,82)));
            btnCadastrar.setEnabled(false);
            txtConfSenha.setVisibility(View.GONE);
            isValid = false;
        }else if (!contCharMi){
            txtCondSenha.setText(R.string.stringCondSenhaMi);
            txtCondSenha.setTextColor(ColorStateList.valueOf(Color.rgb(230,79,82)));
            txtConfSenha.setVisibility(View.GONE);
            btnCadastrar.setEnabled(false);
            isValid = false;
        }else if (!contCharNum){
            txtCondSenha.setText(R.string.stringCondSenhaNum);
            txtCondSenha.setTextColor(ColorStateList.valueOf(Color.rgb(230,79,82)));
            txtConfSenha.setVisibility(View.GONE);
            btnCadastrar.setEnabled(false);
            isValid = false;
        }else if (!contCharS){
            txtCondSenha.setText(R.string.stringCondSenhaS);
            txtCondSenha.setTextColor(ColorStateList.valueOf(Color.rgb(230,79,82)));
            txtConfSenha.setVisibility(View.GONE);
            btnCadastrar.setEnabled(false);
            isValid = false;
        }else if (!contCharTam){
            txtCondSenha.setText(R.string.stringCondSenhaTam);
            txtCondSenha.setTextColor(ColorStateList.valueOf(Color.rgb(230,79,82)));
            txtConfSenha.setVisibility(View.GONE);
            btnCadastrar.setEnabled(false);
            isValid = false;
        }
        getProgress = pb.getProgress();
        if (getProgress<100/3)
            pb.setProgressTintList(ColorStateList.valueOf(Color.rgb(230,79,82)));
        else if (getProgress>=100/3 && getProgress<=(100/3)*2)
            pb.setProgressTintList(ColorStateList.valueOf(Color.rgb(254,198,94)));
        else if (getProgress>(100/3)*2)
            pb.setProgressTintList(ColorStateList.valueOf(Color.rgb(101, 201, 132)));
        aux = tamanhoAnt;
        return isValid;
    }
    public void cadastrar(View view) {
        Intent intent = new Intent(CadastrarActivity.this, EntrarActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(getApplicationContext(), EntrarActivity.class);
        startActivityForResult(intent, 0);
        return true;
    }
}