package com.example.dm2.sqliteejercicios;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //abrimos la base de datos "dbAgenda" en modo de escritura
        AgendaSqliteHelper agenda=new AgendaSqliteHelper(this,"dbAgenda",null,1);
        SQLiteDatabase db=agenda.getWritableDatabase();



    }
}
