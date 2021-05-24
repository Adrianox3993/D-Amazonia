package com.example.damaznia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.damaznia.databinding.NavHeaderBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseUser usuario;
    private DatabaseReference referencia;
    private String idUsuario;

    //Variáveis para Menu Lateral
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Menu Lateral
        navigationView = findViewById(R.id.navView);
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawerLayout);

        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_item_one);

        //Botão de navegação
        BottomNavigationView btnNav = findViewById(R.id.bottomNavegationView);
        btnNav.setOnNavigationItemSelectedListener(navListener);

        //Definir HomeFragment como principal
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_layout, new HomeFragment()).commit();

        usuario = FirebaseAuth.getInstance().getCurrentUser();
        referencia = FirebaseDatabase.getInstance().getReference("Usuarios");
        idUsuario = usuario.getUid();
    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //Listener barra de navegação
    private BottomNavigationView.OnNavigationItemSelectedListener navListener = item -> {
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
    };

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_item_one: {
                Intent intent = new Intent(HomeActivity.this, buscarProduto.class);
                startActivity(intent);
                break;
            }
            case R.id.nav_item_two: {
                Intent intent = new Intent(HomeActivity.this,    carrinho.class);
                startActivity(intent);
                break;
            }
            case R.id.nav_item_thirteen: {
                Toast.makeText(this, "Menu 3", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.nav_item_four: {
                Toast.makeText(this, "Menu 4", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.nav_logout: {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(HomeActivity.this, EntrarActivity.class));
                break;
            }
            default: {
                Toast.makeText(this, "Menu Default", Toast.LENGTH_SHORT).show();
                break;
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}