package com.example.sisbase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class criaBancodeDados extends SQLiteOpenHelper {
    private static final int VERSAO= 1;
    private static final String NOME_BANCO = "bancosistema";
    private static final String TABELA_USUARIO = "usuario";
    private static final String COLUNA_USUARIO_ID = "usuario_id";
    private static final String COLUNA_USUARIO_NOME = "usuario_nome";
    private static final String COLUNA_USUARIO_EMAIL = "usuario_email";
    private static final String COLUNA_USUARIO_SENHA = "usuario_senha";

    String CRIA_TABELA_USUARIO = "CREATE TABLE IF NOT EXISTS " + TABELA_USUARIO + "("
            + COLUNA_USUARIO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUNA_USUARIO_NOME + " TEXT,"
            + COLUNA_USUARIO_EMAIL + " TEXT," + COLUNA_USUARIO_SENHA + " TEXT" + ")";

    public criaBancodeDados(Context context) {
        super(context, NOME_BANCO,null,VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CRIA_TABELA_USUARIO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String RECRIA="DROP TABLE IF EXISTS "+TABELA_USUARIO;
        sqLiteDatabase.execSQL(RECRIA);
        onCreate(sqLiteDatabase);
    }
}
