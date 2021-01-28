package datos;

import java.util.ArrayList;
import java.util.HashSet;

public class Personaje extends Persona{

    private String rolPersonaje;
    private HashSet<Libro> librosPersonaje;

    public Personaje(String nombrePersona, int edadPersona, String sexoPersona, String rolPersonaje) {
        super(nombrePersona, edadPersona, sexoPersona);
        this.rolPersonaje = rolPersonaje;
        librosPersonaje = new HashSet<>();
    }

    public String getRolPersonaje() {
        return rolPersonaje;
    }

    public void setRolPersonaje(String rolPersonaje) {
        this.rolPersonaje = rolPersonaje;
    }

    public HashSet<Libro> getLibroPersonaje() {
        return librosPersonaje;
    }

    public void setLibroPersonaje(HashSet<Libro> libroPersonaje) {
        librosPersonaje = libroPersonaje;
    }

    @Override
    public String toString() {
        return super.toString() + ", rol: " + rolPersonaje;
    }
}
