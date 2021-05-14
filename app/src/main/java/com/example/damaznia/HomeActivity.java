package com.example.damaznia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity {

    private FirebaseUser usuario;
    private DatabaseReference referencia;
    private String idUsuario;

    private Button btnSair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Botão de navegação
        BottomNavigationView btnNav = findViewById(R.id.bottomNavegationView);
        btnNav.setOnNavigationItemSelectedListener(navListener);

        //Definir HomeFragment como principal
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_layout, new HomeFragment()).commit();

        /*btnSair = findViewById(R.id.btnSair);
        btnSair.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(HomeActivity.this, EntrarActivity.class));
        });*/

        /*usuario = FirebaseAuth.getInstance().getCurrentUser();
        referencia = FirebaseDatabase.getInstance().getReference("Usuarios");
        idUsuario = usuario.getUid();

        final TextView txtNome = findViewById(R.id.txtNome);

        referencia.child(idUsuario).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Usuario perfilUsuario = snapshot.getValue(Usuario.class);

                if (perfilUsuario!=null){
                    String nome = perfilUsuario.nome;

                    txtNome.setText(nome);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HomeActivity.this, "Algum erro aconteceu!", Toast.LENGTH_LONG).show();
            }
        });*/
    }

    //Listener barra de navegação
    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new
            BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragmentoSelecionado = null;
            switch (item.getItemId()){
                case R.id.item1:
                    fragmentoSelecionado = new HomeFragment();
                    break;
                case R.id.item2:
                    fragmentoSelecionado = new BuscarFragment();
                    break;
                case R.id.item3:
                    fragmentoSelecionado = new CarrinhoFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_layout
                    , fragmentoSelecionado).commit();
            return true;
        }
    };
}