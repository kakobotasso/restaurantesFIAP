package br.com.kakobotasso.restaurantesfiap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import br.com.kakobotasso.restaurantesfiap.database.DatabaseHelper;
import br.com.kakobotasso.restaurantesfiap.helpers.RestauranteHelper;
import br.com.kakobotasso.restaurantesfiap.models.Restaurante;

public class FormRestauranteActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private RestauranteHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_restaurante);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        iniciaElementos();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cadastro, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;

            case R.id.action_salvar:
                if( helper.formularioValido() ){
                    Restaurante restaurante = helper.pegaRestauranteDoFormulario();
                    databaseHelper.insereRestaurante(restaurante);
                    Toast.makeText(this, R.string.sucesso_form, Toast.LENGTH_SHORT).show();
                    finish();
                }
                return false;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void iniciaElementos(){
        helper = new RestauranteHelper(this);
        databaseHelper = new DatabaseHelper(this);
    }
}
