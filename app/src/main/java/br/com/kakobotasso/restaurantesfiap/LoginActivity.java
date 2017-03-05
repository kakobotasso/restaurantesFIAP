package br.com.kakobotasso.restaurantesfiap;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import br.com.kakobotasso.restaurantesfiap.helpers.Constantes;
import br.com.kakobotasso.restaurantesfiap.helpers.Preferencias;
import br.com.kakobotasso.restaurantesfiap.models.Usuario;

public class LoginActivity extends AppCompatActivity {
    private Usuario usuario;
    private EditText loginEdit, senhaEdit;
    private CheckBox logadoCheck;
    private Preferencias preferencias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inicializaElementos();
        validaNavegacao();
    }

    private void inicializaElementos(){
        usuario = (Usuario) getIntent().getSerializableExtra(Constantes.TAG_USUARIO);
        loginEdit = (EditText) findViewById(R.id.edt_login);
        senhaEdit = (EditText) findViewById(R.id.edt_senha);
        logadoCheck = (CheckBox) findViewById(R.id.check_manter_logado);
        preferencias = new Preferencias(this);
    }

    private void validaNavegacao(){
        if( preferencias.estaLogado() ){
            vaiParaDashboard();
        }
    }

    public void logaUsuario(View v){
        String login = loginEdit.getText().toString();
        String senha = senhaEdit.getText().toString();

        if( formularioPreenchido() ){
            if( usuario != null ){
                logar(login, senha);
            }else{
                Toast.makeText(this, R.string.erro_usuario, Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    private boolean formularioPreenchido(){
        boolean retorno = true;

        if( loginEdit.getText().toString().equals("") ){
            loginEdit.setError( getString(R.string.login_vazio) );
            retorno = false;
        }
        if( senhaEdit.getText().toString().equals("") ){
            senhaEdit.setError( getString(R.string.senha_vazia) );
            retorno = false;
        }

        return retorno;
    }

    private void logar(String login, String senha){
        if( usuario.getLogin().equals(login) && usuario.getSenha().equals(senha) ){

            if( logadoCheck.isChecked() ){
                preferencias.manterLogado();
            }

            vaiParaDashboard();

        }else{
            Toast.makeText(this, R.string.erro_login, Toast.LENGTH_SHORT).show();
        }
    }

    private void vaiParaDashboard(){
        Intent dashboard = new Intent(this, DashboardActivity.class);
        startActivity(dashboard);
        finish();
    }


}
