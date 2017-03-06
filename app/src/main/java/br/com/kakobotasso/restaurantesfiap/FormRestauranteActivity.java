package br.com.kakobotasso.restaurantesfiap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import br.com.kakobotasso.restaurantesfiap.database.DatabaseHelper;
import br.com.kakobotasso.restaurantesfiap.helpers.RestauranteHelper;
import br.com.kakobotasso.restaurantesfiap.models.Restaurante;
import br.com.kakobotasso.restaurantesfiap.utils.Constantes;

public class FormRestauranteActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private RestauranteHelper helper;
    private Restaurante restaurante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_restaurante);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        iniciaElementos();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if( restaurante != null ){
            helper.colocaNoFormulario(restaurante);
        }
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
                    salvaOuEditaRestaurante();
                }
                return false;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void salvaOuEditaRestaurante(){
        if( restaurante == null ){
            salvaRestaurante();
        }else{
            atualizaRestaurante();
        }
    }

    private void salvaRestaurante(){
        Restaurante restaurante = helper.pegaRestauranteDoFormulario();
        databaseHelper.insereRestaurante(restaurante);
        databaseHelper.close();
        Toast.makeText(this, R.string.sucesso_form, Toast.LENGTH_SHORT).show();
        finish();
    }

    private void atualizaRestaurante(){
        Restaurante tmp = helper.pegaRestauranteDoFormulario();

        restaurante.setNome( tmp.getNome() );
        restaurante.setPedido( tmp.getPedido() );
        restaurante.setOpiniao( tmp.getOpiniao() );

        databaseHelper.atualizaRestaurante(restaurante);
        Toast.makeText(this, R.string.sucesso_edita_form, Toast.LENGTH_SHORT).show();
        finish();
    }

    private void iniciaElementos(){
        helper = new RestauranteHelper(this);
        databaseHelper = new DatabaseHelper(this);
        restaurante = (Restaurante) getIntent().getSerializableExtra(Constantes.TAG_RESTAURANTE);
    }
}
