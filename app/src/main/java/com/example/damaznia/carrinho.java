package com.example.damaznia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class carrinho extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);

        if(!somaTotal.getTotal().equals("")){
            TextView textView1 = ( TextView ) findViewById ( R.id.encerrar);
            textView1.setText (somaTotal.getTotal());
        }
    }

    public void adicionarCarrinho(View view) {
        Intent intent = new Intent(carrinho.this,    adicionarProduto.class);
        startActivity(intent);
    }

    public void fecharVenda(View view) {
        Intent intent = new Intent(carrinho.this,    vendaEncerrada.class);
        startActivity(intent);
    }
}