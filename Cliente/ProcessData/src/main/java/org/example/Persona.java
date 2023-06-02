package org.example;

public class Persona {
    private long id;
    private String Nombre;
    private int edad;

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Persona(long id, String nombre, int edad) {
        this.id = id;
        Nombre = nombre;
        this.edad = edad;
    }
    public Persona(){

    }
}
