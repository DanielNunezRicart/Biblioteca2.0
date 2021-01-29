package datos;

import java.util.HashSet;

public class Personaje extends Persona{

    private String rolPersonaje;
    private HashSet<Libro> librosPersonaje;

    public Personaje(String nombrePersona, int edadPersona, String sexoPersona, String rolPersonaje, HashSet<Libro> libros) {
        super(nombrePersona, edadPersona, sexoPersona);
        this.rolPersonaje = rolPersonaje;
        librosPersonaje = libros;
    }

    public String getRolPersonaje() {
        return rolPersonaje;
    }

    public void setRolPersonaje(String rolPersonaje) {
        this.rolPersonaje = rolPersonaje;
    }

    public HashSet<Libro> getLibrosPersonaje() {
        return librosPersonaje;
    }

    public void setLibrosPersonaje(HashSet<Libro> librosPersonaje) {
        this.librosPersonaje = librosPersonaje;
    }

    @Override
    public String toString() {
        return super.toString() + ", rol: " + rolPersonaje;
    }
}
