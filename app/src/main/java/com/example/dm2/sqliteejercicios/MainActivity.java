package com.example.dm2.sqliteejercicios;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listacontactos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //abrimos la base de datos "dbAgenda" en modo de escritura
        AgendaSqliteHelper agenda=new AgendaSqliteHelper(this,"dbAgenda",null,1);
        SQLiteDatabase db=agenda.getWritableDatabase();

        //comprobar apertura correcta de la BD
        if(db!=null)
        {
            for(int i=1;i<=5;i++)
            {
                String nombre="Contacto"+i;
                String email="contacto"+i+"@contactos.com";
                String numero="66666666"+i;

                db.execSQL("insert into usuarios(nombre,email,numero) values ('"+nombre+"','"+email+"','"+numero+"')");
            }

        }

        //adaptador de la lista y asociar lista al XML

        ArrayAdapter<Contacto> adaptador=new ArrayAdapter<Contacto>(this,android.R.layout.simple_list_item_1);

        listacontactos=(ListView)findViewById(R.id.listaAgenda);

        listacontactos.setAdapter(adaptador);

        //recuperar registros

       Cursor c=db.rawQuery("Select idUsuario,nombre,email,numero from usuarios",null);

        if(c.moveToFirst()) {

            do {
                Contacto con = new Contacto(c.getString(1), c.getInt(0), c.getString(2), c.getString(3));
                adaptador.add(con);
            } while (c.moveToNext());
        }



        db.close();


        listacontactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Contacto contactoRecogido= (Contacto) parent.getItemAtPosition(position);
                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                intent.putExtra("nombre",contactoRecogido.getNombre());
                intent.putExtra("numero",contactoRecogido.getNumero());
                intent.putExtra("email",contactoRecogido.getEmail());
                startActivity(intent);
            }
        });
    }
}
