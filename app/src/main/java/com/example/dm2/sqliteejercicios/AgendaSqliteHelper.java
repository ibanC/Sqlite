package com.example.dm2.sqliteejercicios;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dm2 on 20/12/2016.
 */
public class AgendaSqliteHelper extends SQLiteOpenHelper{

    //sentencia sql para creare la tabla de usuarios
    String sqlCreate="Create Table usuarios (idUsuario INTEGER PRIMARY KEY AUTOINCREMENT,nombre TEXT,email TEXT)";
    public AgendaSqliteHelper(Context contexto, String nombre, SQLiteDatabase.CursorFactory factory,int version)
    {
        super(contexto,nombre,factory,version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //se ejecuta la sentencia
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //se elimina la version anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS Usuarios");

        //se crea la nueva version de la tabla
        db.execSQL(sqlCreate);
    }
}
