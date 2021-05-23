package com.example.damaznia;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class menuSuspenso extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private TextView textView;

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menususpenso);

        navigationView = (NavigationView) findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(this);

        toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);



        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);

        toggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_item_one: {
               Intent intent = new Intent(menuSuspenso.this,    buscarProduto.class);
               startActivity(intent);
               break;
            }
            case R.id.nav_item_two: {
                Intent intent = new Intent(menuSuspenso.this,    carrinho.class);
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
            default: {
                Toast.makeText(this, "Menu Default", Toast.LENGTH_SHORT).show();
                break;
            }
        }

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

/*
        textView = (TextView) findViewById(R.id.text);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(toolbar);

        createDrawer();
    }


    private void createDrawer(){
        //Itens do Drawer
        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName(R.string.menu1);
        PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withName(R.string.menu2);
        PrimaryDrawerItem item3 = new PrimaryDrawerItem().withIdentifier(3).withName(R.string.menu3);


        //Definição do Drawer
        Drawer drawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .addDrawerItems(
                        item1,
                        new DividerDrawerItem(),//Divisor
                        item2,
                        new SectionDrawerItem().withName(R.string.section),//Seção
                        item3

                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        textView.setText("Você clicou em: "+ ((Nameable) drawerItem).getName().getText(entrada6.this));
                        return false;
                    }
                })
                .withSelectedItemByPosition(0)
                .build();
    }*/

}