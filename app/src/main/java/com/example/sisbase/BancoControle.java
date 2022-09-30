package com.example.sisbase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class BancoControle {
    private SQLiteDatabase db;
    private criaBancodeDados banco;

    public BancoControle(Context contexto) {
        this.banco = new criaBancodeDados(contexto);
    }

    public void insere_usuario(usuario usr){
        String INSERE_USUARIO = "INSERT INTO usuario (usuario_nome,usuario_email,usuario_senha) " +
                "values ('"+usr.getNome()+"','"+usr.getEmail()+"','"+usr.getSenha()+"')";
        try{
            SQLiteDatabase db=banco.getWritableDatabase();
            db.execSQL(INSERE_USUARIO);
            db.close();
        }catch(Exception ex){
            Log.d("Erro (criação tabela)",ex.getMessage());
        }


    }

    public void apaga_usuario(usuario usr){
        String APAGA_USUARIO = "DELETE FROM usuario WHERE usuario_id ="+usr.getId();
        try{
            SQLiteDatabase db=banco.getWritableDatabase();
            db.execSQL(APAGA_USUARIO);
            db.close();
        }catch(Exception ex){
            //("Erro (criação tabela)",ex.getMessage());
        }
    }
    public void atualiza_usuario(usuario usr){
        String ATUALIZA_USUARIO = "UPDATE TABLE usuario SET usuario_nome = "+usr.getNome()+", usuario_email ="+usr.getEmail()+",usuario_senha ="+usr.getSenha()+" WHERE usuario_id = "+usr.getId();
        try{
            SQLiteDatabase db=banco.getWritableDatabase();
            db.execSQL(ATUALIZA_USUARIO);
            db.close();
        }catch(Exception ex){
            //("Erro (criação tabela)",ex.getMessage());
        }
    }
    public List<usuario> Consulta_todos_usuarios() {

        List<usuario> listaDeUsuarios = new ArrayList<usuario>();
        String CONSULTA_USUARIO ="SELECT usuario_id,usuario_nome,usuario_email,usuario_senha FROM usuario ORDER BY usuario_nome";

        try{

            SQLiteDatabase bd=banco.getReadableDatabase();
            Cursor c=bd.rawQuery(CONSULTA_USUARIO,null);
            if (c.moveToFirst()) {
                do {
                    usuario usr = new usuario();
                    usr.setId(Integer.parseInt(c.getString(0)));
                    usr.setNome(c.getString(1));
                    usr.setEmail(c.getString(2));
                    usr.setSenha(c.getString(3));

                    listaDeUsuarios.add(usr);
                } while (c.moveToNext());
            }
            c.close();
            bd.close();
            return listaDeUsuarios;



        }catch(Exception ex){
            //  ("Erro (criação tabela)",ex.getMessage());
            return null;
        }

    }

    public boolean testaUsuario(String nome) {


        String PESQUISA_POR_NOME="SELECT usuario_id FROM usuario WHERE usuario_nome ='"+nome+"'";
    boolean ret=false;
        try{

            SQLiteDatabase bd=banco.getReadableDatabase();
            Cursor c=bd.rawQuery(PESQUISA_POR_NOME,null);
            int cursorCount = c.getCount();
            c.close();
            if (cursorCount > 0) {
                ret =true;

            }
            banco.close();


        }catch(Exception ex){
            //  ("Erro (criação tabela)",ex.getMessage());
            ret=false;
        }

 return ret;

    }


    public boolean checkusuario(String nome, String senha) {


        String PESQUISA_POR_NOME_SENHA="SELECT usuario_id  FROM usuario WHERE usuario_nome ='"+nome+"' AND usuario_senha='"+senha+"'";

        boolean ret=false;
        try{

            SQLiteDatabase bd=banco.getReadableDatabase();
            Cursor c=bd.rawQuery(PESQUISA_POR_NOME_SENHA,null);
            int cursorCount = c.getCount();
            c.close();
            if (cursorCount > 0) {
                ret =true;

            }
            banco.close();


        }catch(Exception ex){
            //  ("Erro (criação tabela)",ex.getMessage());
            ret=false;
        }

        return ret;

    }


}
