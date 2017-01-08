package com.example.dm2.sqliteejercicios;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    private TextView info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        info=(TextView)findViewById(R.id.infor);

        Bundle extras=getIntent().getExtras();
        String nom=extras.getString("nombre");
        String num=extras.getString("numero");
        String mail=extras.getString("email");



        info.setText("Informacion del contacto:"+"\nNombre:"+nom+"\n"+"Numero:"+num+"\nEmail:"+mail);



    }
}
