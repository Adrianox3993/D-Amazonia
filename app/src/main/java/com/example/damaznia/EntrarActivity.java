package com.example.damaznia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class EntrarActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView cadastrar, esqSenha;
    private TextInputEditText editSenha, editEmail;
    private Button entrar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        cadastrar = findViewById(R.id.txtCadastrar);
        cadastrar.setOnClickListener(this);
        entrar = findViewById(R.id.btnEntrar);
        entrar.setOnClickListener(this);
        esqSenha = findViewById(R.id.txtEsqSenha);
        esqSenha.setOnClickListener(this);

        editEmail = findViewById(R.id.editEmail);
        editSenha = findViewById(R.id.editSenha);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(getApplicationContext(), OnBoarding3Activity.class);
        startActivityForResult(intent, 0);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txtCadastrar:
                startActivity(new Intent(this, CadastrarActivity.class));
                break;
            case R.id.btnEntrar:
                logarUsuario();
                break;
            case R.id.txtEsqSenha:
                startActivity(new Intent(this, RecuperarSenhaActivity.class));
                break;
        }

    }

    private void logarUsuario() {
        String email = editEmail.getText().toString().trim();
        String senha = editSenha.getText().toString().trim();

        if (email.isEmpty()){
            editEmail.setError("Email é necessário!");
            editEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editEmail.setError("Por favor insira um email válido!");
            editEmail.requestFocus();
            return;
        }
        if (senha.isEmpty()){
            editSenha.setError("Senha é necessária!");
            editSenha.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            startActivity(new Intent(EntrarActivity.this, HomeActivity.class));

                        }else {
                            Toast.makeText(EntrarActivity.this, "Error ao entrar! Por favor confira seus dados.", Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }
}