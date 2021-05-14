package com.example.damaznia;


import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class CadastrarActivity extends AppCompatActivity {

    private TextInputEditText senha, senha2, editEmail, editNome;
    private TextInputLayout txtSenha, txtConfSenha;
    private TextView txtCondSenha;
    private Button btnCadastrar;
    private ProgressBar pb;
    private Integer aux=0, getProgress=0;
    private FirebaseAuth mAuth;
    //Variaveis para validação e mudança de texto e cor da variavel txtCondSenha
    private Boolean contCharTam=false, contCharMa=false, contCharMi=false,
            contCharNum=false, contCharS=false, contEmail=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        senha = findViewById(R.id.idEditSenha);
        senha2 = findViewById(R.id.idEditSenha2);
        editEmail = findViewById(R.id.idEditEmail);
        editNome = findViewById(R.id.idEditUsuario);
        txtSenha = findViewById(R.id.idTxtSenha);
        txtConfSenha = findViewById(R.id.idTxtSenha2);
        txtCondSenha = findViewById(R.id.txtCondSenha);
        btnCadastrar = findViewById(R.id.btnCadastrar);
        pb = findViewById(R.id.progressBar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        mAuth = FirebaseAuth.getInstance();

        btnCadastrar.setOnClickListener(v -> cadastrar());

        editNome.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String pass = senha.getText().toString().trim();
                String pass2 = senha2.getText().toString().trim();
                String email = editEmail.getText().toString().trim();
                String nome = editNome.getText().toString().trim();
                if ( !nome.isEmpty() && validarEmail(email) && !pass.isEmpty() && pass.equals(pass2)){
                    btnCadastrar.setEnabled(true);
                }else
                    btnCadastrar.setEnabled(false);
            }
        });

        editEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void afterTextChanged(Editable s) {
                String pass = senha.getText().toString().trim();
                String pass2 = senha2.getText().toString().trim();
                String email = editEmail.getText().toString().trim();
                String nome = editNome.getText().toString().trim();
                if (!nome.isEmpty() && validarEmail(email) && !pass.isEmpty() && pass.equals(pass2)){
                    btnCadastrar.setEnabled(true);
                }else
                    btnCadastrar.setEnabled(false);
            }
        });

        senha.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String pass = senha.getText().toString().trim();
                String pass2 = senha2.getText().toString().trim();
                validarSenhaRobusta(pass);
                if (pass.equals(pass2)){
                    txtConfSenha.setBoxStrokeColor(Color.GREEN);
                    habilitarCadastro();
                }else{
                    btnCadastrar.setEnabled(false);
                    txtConfSenha.setBoxStrokeColor(Color.RED);
                }
            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        senha2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String pass = senha.getText().toString().trim();
                String pass2 = senha2.getText().toString().trim();
                if (pass2.equals(pass)){
                    txtConfSenha.setBoxStrokeColor(Color.GREEN);
                    habilitarCadastro();
                }else{
                    txtConfSenha.setBoxStrokeColor(Color.RED);
                    btnCadastrar.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    //A função validarSenha habilita ou desabilita o Botão Cadastrar, dependendo se as senhas conferem
    //ou não
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void habilitarCadastro() {
        if (contCharTam && contCharMa && contCharMi && contCharNum && contCharS && contEmail){
            btnCadastrar.setEnabled(true);
        }else
            btnCadastrar.setEnabled(false);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void validarSenhaRobusta(String password) {
        int tamanho = password.length();
        int tamanhoAnt = tamanho;

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
        }
        //Condição --//-- --//-- uma letra minnúscula
        //se  condição foi satisfeita e depois apagada, é decrementado.
        if (password.matches(lowerCaseChars) && !contCharMi){
            pb.incrementProgressBy(100/5);
            contCharMi=true;
        }else if (!password.matches(lowerCaseChars) && contCharMi) {
            pb.incrementProgressBy(-100/5);
            contCharMi=false;
        }
        //Condição --//-- --//-- um número
        //se  condição foi satisfeita e depois apagada, é decrementado.
        if (password.matches(numbers) && !contCharNum){
            pb.incrementProgressBy(100/5);
            contCharNum=true;
        }else if (!password.matches(numbers) && contCharNum) {
            pb.incrementProgressBy(-100/5);
            contCharNum=false;
        }
        //Condição --//-- --//-- um char especial
        //se  condição foi satisfeita e depois apagada, é decrementado.
        if (password.matches(special) && !contCharS){
            pb.incrementProgressBy(100/5);
            contCharS=true;
        }else if (!password.matches(special) && contCharS) {
            pb.incrementProgressBy(-100/5);
            contCharS=false;
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
            txtConfSenha.setVisibility(View.GONE);
            btnCadastrar.setEnabled(false);
        }else if (!contCharMi){
            txtCondSenha.setText(R.string.stringCondSenhaMi);
            txtCondSenha.setTextColor(ColorStateList.valueOf(Color.rgb(230,79,82)));
            txtConfSenha.setVisibility(View.GONE);
            btnCadastrar.setEnabled(false);
        }else if (!contCharNum){
            txtCondSenha.setText(R.string.stringCondSenhaNum);
            txtCondSenha.setTextColor(ColorStateList.valueOf(Color.rgb(230,79,82)));
            txtConfSenha.setVisibility(View.GONE);
            btnCadastrar.setEnabled(false);
        }else if (!contCharS){
            txtCondSenha.setText(R.string.stringCondSenhaS);
            txtCondSenha.setTextColor(ColorStateList.valueOf(Color.rgb(230,79,82)));
            txtConfSenha.setVisibility(View.GONE);
            btnCadastrar.setEnabled(false);
        }else if (!contCharTam){
            txtCondSenha.setText(R.string.stringCondSenhaTam);
            txtCondSenha.setTextColor(ColorStateList.valueOf(Color.rgb(230,79,82)));
            txtConfSenha.setVisibility(View.GONE);
            btnCadastrar.setEnabled(false);
        }
        getProgress = pb.getProgress();
        if (getProgress<100/3)
            pb.setProgressTintList(ColorStateList.valueOf(Color.rgb(230,79,82)));
        else if (getProgress>=100/3 && getProgress<=(100/3)*2)
            pb.setProgressTintList(ColorStateList.valueOf(Color.rgb(254,198,94)));
        else if (getProgress>(100/3)*2)
            pb.setProgressTintList(ColorStateList.valueOf(Color.rgb(101, 201, 132)));

        aux = tamanhoAnt;
    }
    private void cadastrar() {
        String nome = editNome.getText().toString().trim();
        String email = editEmail.getText().toString().trim();
        String pass = senha.getText().toString().trim();

        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        Usuario user = new Usuario(nome, email);

                        FirebaseDatabase.getInstance().getReference("Usuarios")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .setValue(user).addOnCompleteListener(task1 -> {
                                    if (task1.isSuccessful()) {
                                        Toast.makeText(CadastrarActivity.this, "Usuario foi cadastrado com sucesso!", Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(CadastrarActivity.this, EntrarActivity.class));
                                    }else {
                                        Toast.makeText(CadastrarActivity.this, "Erro ao cadastrar! Por favor tente novamente.", Toast.LENGTH_LONG).show();
                                    }
                                });
                    }else{
                        Toast.makeText(CadastrarActivity.this, "Erro ao cadastrar!", Toast.LENGTH_LONG).show();
                    }
                });
    }
    private Boolean validarEmail(String email){
        if (email.isEmpty()){
            editEmail.setError("Email é necessário!");
            contEmail = false;
            return false;
        }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editEmail.setError("Por favor informe um email válido!");
            contEmail = false;
            return false;
        }else
            contEmail = true;
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(getApplicationContext(), EntrarActivity.class);
        startActivityForResult(intent, 0);
        return true;
    }
}