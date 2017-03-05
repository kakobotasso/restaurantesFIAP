package br.com.kakobotasso.restaurantesfiap.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.kakobotasso.restaurantesfiap.models.Usuario;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String NOME_BD = "restaurantes.db";
    public static final int VERSAO_BD = 1;

    public static final String TABELA_USUARIOS = "usuarios";

    public DatabaseHelper(Context context){
        super(context, NOME_BD, null, VERSAO_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL( criaTabelaUsuarios() );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int versaoAntiga, int versaoNova) {}

    private String criaTabelaUsuarios(){
        String sql = "CREATE TABLE " + TABELA_USUARIOS
                + " (id INTEGER PRIMARY KEY, "
                + " login TEXT, "
                + " senha TEXT);";

        return sql;
    }

    public void insereUsuario(Usuario usuario){
        ContentValues values = new ContentValues();

        values.put("login", usuario.getLogin());
        values.put("senha", usuario.getSenha());

        getWritableDatabase().insert(TABELA_USUARIOS, null, values);
    }

}
