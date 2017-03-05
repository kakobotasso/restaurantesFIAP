package br.com.kakobotasso.restaurantesfiap.helpers;

import android.app.Activity;
import android.widget.EditText;

import br.com.kakobotasso.restaurantesfiap.R;
import br.com.kakobotasso.restaurantesfiap.models.Restaurante;

public class RestauranteHelper {
    private Activity activity;
    private EditText nomeEdit, pedidoEdit, opiniaoEdit;
    private Restaurante restaurante;

    public RestauranteHelper(Activity activity){
        this.activity = activity;
        nomeEdit = (EditText) activity.findViewById(R.id.form_nome_restaurante);
        pedidoEdit = (EditText) activity.findViewById(R.id.form_pedido_restaurante);
        opiniaoEdit = (EditText) activity.findViewById(R.id.form_opiniao_restaurante);
        restaurante = new Restaurante();
    }

    public Restaurante pegaRestauranteDoFormulario(){
        restaurante.setNome( nomeEdit.getText().toString() );
        restaurante.setPedido( pedidoEdit.getText().toString() );
        restaurante.setOpiniao( opiniaoEdit.getText().toString() );

        return restaurante;
    }

    public boolean formularioValido(){
        boolean retorno = true;

        if( nomeEdit.getText().toString().equals("") ){
            nomeEdit.setError( activity.getString(R.string.erro_form_nome) );
            retorno = false;
        }

        if( pedidoEdit.getText().toString().equals("") ){
            pedidoEdit.setError( activity.getString(R.string.erro_form_pedido) );
            retorno = false;
        }

        if( opiniaoEdit.getText().toString().equals("") ){
            opiniaoEdit.setError( activity.getString(R.string.erro_form_opiniao) );
            retorno = false;
        }

        return retorno;
    }
}
