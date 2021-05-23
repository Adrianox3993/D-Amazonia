package com.example.damaznia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class OnBoarding3Activity extends AppCompatActivity {

    private boolean isTouch = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding3);
        Button btnAnt = findViewById(R.id.btnAnt);
        Button btnComecar = findViewById(R.id.btnComecar);

        btnComecar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OnBoarding3Activity.this, EntrarActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnAnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OnBoarding3Activity.this, OnBoarding2Activity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int X = (int) event.getX();
        int Y = (int) event.getY();
        int eventaction = event.getAction();

        switch (eventaction) {
            case MotionEvent.ACTION_DOWN:
                isTouch = true;
                Intent intent = new Intent(OnBoarding3Activity.this,  menuSuspenso.class);
                //Toast.makeText(this, "MOVE "+"X: "+X+" Y: "+Y, Toast.LENGTH_SHORT).show();
                startActivity(intent);
                break;
        }
        return true;
    }
}