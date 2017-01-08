package com.example.dm2.sqliteejercicios;

/**
 * Created by iban on 31/12/2016.
 */
public class Contacto {
    private String nombre;
    private int codigo;
    private String email;
    private String numero;

    public Contacto(String nombre, int codigo, String email,String numero) {

        this.nombre = nombre;
        this.codigo = codigo;
        this.email = email;
        this.numero=numero;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }

    public String getNumero() {
        return numero;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getEmail() {
        return email;
    }

}
