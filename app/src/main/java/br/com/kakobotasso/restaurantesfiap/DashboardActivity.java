package br.com.kakobotasso.restaurantesfiap;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import br.com.kakobotasso.restaurantesfiap.adapters.RestaurantesAdapter;
import br.com.kakobotasso.restaurantesfiap.helpers.Preferencias;
import br.com.kakobotasso.restaurantesfiap.models.Restaurante;

public class DashboardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Preferencias prefs;
    private RecyclerView recyclerView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        iniciaElementos();
        trataToolbar();
        validaNavegacao();
        trataFAB();
        trataNavigationDrawer();
    }

    @Override
    protected void onResume() {
        super.onResume();
        populaTela();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.nav_adiciona_restaurante:
                break;

            case R.id.nav_sobre:
                Intent sobre = new Intent(this, SobreActivity.class);
                startActivity(sobre);
                break;

            case R.id.nav_sair:
                prefs.deslogar();
                validaNavegacao();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void validaNavegacao(){
        if( !prefs.estaLogado() ){
            Intent login = new Intent(this, LoginActivity.class);
            startActivity(login);
            finish();
        }
    }

    private void iniciaElementos(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        prefs = new Preferencias(this);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
    }

    private void trataToolbar(){
        setSupportActionBar(toolbar);
    }

    private void trataFAB(){
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void trataNavigationDrawer(){
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void populaTela(){
        List<Restaurante> restaurantes = new ArrayList<>();
        restaurantes.add(new Restaurante("Tiu Ze", "Açaí", "Muito bom"));
        restaurantes.add(new Restaurante("Black dog", "Dog", "Muito bom"));
        restaurantes.add(new Restaurante("Habibs", "Esfiha de queijo", "Bom"));
        restaurantes.add(new Restaurante("Mexicanissimo", "Rodizio", "Excelente"));

        RestaurantesAdapter adapter = new RestaurantesAdapter(this, restaurantes);
        recyclerView.setAdapter(adapter);

        RecyclerView.LayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layout);
    }

}
