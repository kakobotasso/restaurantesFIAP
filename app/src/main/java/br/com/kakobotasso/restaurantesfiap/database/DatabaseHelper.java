package br.com.kakobotasso.restaurantesfiap.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.com.kakobotasso.restaurantesfiap.models.Restaurante;
import br.com.kakobotasso.restaurantesfiap.models.Usuario;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String NOME_BD = "restaurantes.db";
    public static final int VERSAO_BD = 1;

    public static final String TABELA_USUARIOS = "usuarios";
    public static final String TABELA_RESTAURANTES = "restaurantes";

    public DatabaseHelper(Context context){
        super(context, NOME_BD, null, VERSAO_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL( criaTabelaUsuarios() );
        sqLiteDatabase.execSQL( criaTabelaRestaurantes() );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int versaoAntiga, int versaoNova) {}

    // USUARIOS ***************
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

    public int procuraUsuario(String login, String senha){
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM " + TABELA_USUARIOS
                + " WHERE login='"+ login.trim() +"' AND senha='"+ senha.trim() +"';";

        Cursor c = getReadableDatabase().rawQuery(sql, null);

        while(c.moveToNext()){
            Usuario usuario = new Usuario();
            usuarios.add(usuario);
        }

        return usuarios.size();
    }

    // RESTAURANTES ***************
    private String criaTabelaRestaurantes(){
        String sql = "CREATE TABLE " + TABELA_RESTAURANTES
                + " (id INTEGER PRIMARY KEY, "
                + " nome TEXT, "
                + " pedido TEXT, "
                + " opiniao TEXT);";

        return sql;
    }

    public void insereRestaurante(Restaurante restaurante){
        ContentValues values = new ContentValues();

        values.put("nome", restaurante.getNome());
        values.put("pedido", restaurante.getPedido());
        values.put("opiniao", restaurante.getOpiniao());

        getWritableDatabase().insert(TABELA_RESTAURANTES, null, values);
    }

    public List<Restaurante> getRestaurantes(){
        List<Restaurante> restaurantes = new ArrayList<>();
        String sql = "SELECT * FROM " + TABELA_RESTAURANTES + " ORDER BY id DESC;";

        Cursor c = getReadableDatabase().rawQuery(sql, null);

        while(c.moveToNext()){
            Restaurante restaurante = new Restaurante();

            restaurante.setId(c.getLong(c.getColumnIndex("id")));
            restaurante.setNome(c.getString(c.getColumnIndex("nome")));
            restaurante.setPedido(c.getString(c.getColumnIndex("pedido")));
            restaurante.setOpiniao(c.getString(c.getColumnIndex("opiniao")));

            restaurantes.add(restaurante);
        }

        return restaurantes;
    }

    public void atualizaRestaurante(Restaurante restaurante){
        ContentValues values = new ContentValues();

        values.put("nome", restaurante.getNome());
        values.put("pedido", restaurante.getPedido());
        values.put("opiniao", restaurante.getOpiniao());

        String[] args = { restaurante.getId().toString() };
        getWritableDatabase().update(TABELA_RESTAURANTES, values, "id=?", args);
    }

    public void deletaRestaurante(Restaurante restaurante){
        String[] args = { restaurante.getId().toString() };
        getWritableDatabase().delete(TABELA_RESTAURANTES, "id=?", args);
    }

}
