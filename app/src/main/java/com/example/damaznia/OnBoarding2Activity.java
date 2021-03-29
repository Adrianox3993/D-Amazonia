package com.example.damaznia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OnBoarding2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding2);
        Button btnProx = findViewById(R.id.btnComecar);
        Button btnAnt = findViewById(R.id.btnAnt);

        btnProx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OnBoarding2Activity.this, OnBoarding3Activity.class);
                startActivity(intent);
                finish();
            }
        });
        btnAnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OnBoarding2Activity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}