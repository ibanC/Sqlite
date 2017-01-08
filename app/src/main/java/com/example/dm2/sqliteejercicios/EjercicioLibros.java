package com.example.dm2.sqliteejercicios;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLData;

public class EjercicioLibros extends AppCompatActivity {

    private Button butConsultar,butInsertar,butActualizar,butEliminar;
    private TextView txtConsulta;
    private EditText edCodigo,edTitulo,edAutor;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio_libros);

        butConsultar=(Button)findViewById(R.id.butConsultar);
        butInsertar=(Button)findViewById(R.id.butInsertar);
        butActualizar=(Button)findViewById(R.id.butActualizar);
        butEliminar=(Button)findViewById(R.id.butEliminar);

        txtConsulta=(TextView)findViewById(R.id.consultaLibros);

        edCodigo=(EditText)findViewById(R.id.edCodigo);
        edTitulo=(EditText)findViewById(R.id.edTitulo);
        edAutor=(EditText)findViewById(R.id.edAutor);

        SqliteLibros sqLibros=new SqliteLibros(this,"DBLIBROS",null,1);
        db = sqLibros.getWritableDatabase();

        butConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor c= db.rawQuery("select id,titulo,autor from libros",null);

                txtConsulta.setText("");

                if(c.moveToFirst())
                {
                    do{
                        txtConsulta.append(c.getInt(0)+"-"+c.getString(1)+"-"+c.getString(2)+"\n");
                    }while(c.moveToNext());
                }

            }
        });

        butInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String titulo=edTitulo.getText().toString();
                String autor=edAutor.getText().toString();

                if(titulo.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Debes introducir el titulo del libro obligatoriamente",Toast.LENGTH_LONG).show();
                }
                else
                {
                    //consulta de insercion
                    db.execSQL("insert into libros (titulo,autor) values('"+titulo+"','"+autor+"')");
                    Toast.makeText(getApplicationContext(),"Libro"+titulo+"insertado con éxito",Toast.LENGTH_LONG).show();

                    // Toast.makeText(getApplicationContext(),"Error al insertar el libro en la BD",Toast.LENGTH_LONG).show();
                }



            }
        });

        butActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String codigo=edCodigo.getText().toString();
                String titulo=edTitulo.getText().toString();
                String autor=edAutor.getText().toString();


                if(codigo.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Debes introducir el codigo del libro",Toast.LENGTH_LONG).show();
                }
                else
                {

                    if(existeCodigo(Integer.parseInt(codigo)))
                    {
                        //con ContentValues
                        ContentValues values=new ContentValues();
                        values.put("titulo",titulo);
                        values.put("autor",autor);
                        Integer cod=Integer.parseInt(codigo);
                        db.update("libros",values,"id='"+cod+"'",null);
                        Toast.makeText(getApplicationContext(),"Libro modificado con éxito",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Libro no existe",Toast.LENGTH_LONG).show();
                    }



                }
            }
        });

        butEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codigo=edCodigo.getText().toString();
                if(codigo.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Debes introducir el codigo del libro",Toast.LENGTH_LONG).show();
                }
                else
                {
                    String[] args=new String[]{codigo};
                    if(existeCodigo(Integer.parseInt(codigo)))
                    {
                        db.execSQL("delete from libros where id=?",args);
                        Toast.makeText(getApplicationContext(),"Libro eliminado con éxito",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Libro no existe",Toast.LENGTH_LONG).show();
                    }

                }

            }
        });
    }

    public boolean existeCodigo(int cod) {

        Cursor c=db.rawQuery("select id from libros where id="+cod,null);

        if(c.moveToFirst())
        {
            return true;
        }
        return false;
    }

}
