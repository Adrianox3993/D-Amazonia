package com.example.damaznia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class RecuperarSenhaActivity extends AppCompatActivity {

    private TextInputEditText editRecSenha;
    private Button btnRecSenha;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_senha);

        editRecSenha = findViewById(R.id.editRecSenha);

        auth = FirebaseAuth.getInstance();

        btnRecSenha = findViewById(R.id.btnRecSenha);

        btnRecSenha.setOnClickListener(v -> recuperarSenha());

    }

    private void recuperarSenha(){
        String email = editRecSenha.getText().toString().trim();

        if (email.isEmpty()){
            editRecSenha.setError("Email é necessário!");
            editRecSenha.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editRecSenha.setError("Por favor informe um email válido!!");
            editRecSenha.requestFocus();
            return;
        }

        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(RecuperarSenhaActivity.this, "Confira seu email!", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(RecuperarSenhaActivity.this, "Tente novamente, confira se seu email está correto!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}