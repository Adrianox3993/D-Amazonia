package com.example.damaznia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class vendaEncerrada extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venda_encerrada);
    }

    public void home(View view) {
        Intent intent = new Intent(vendaEncerrada.this,    menuSuspenso.class);
        startActivity(intent);
    }
}