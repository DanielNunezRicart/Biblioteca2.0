package datos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.ResourceBundle;

public class Personaje extends Persona implements Serializable {

    private int edadPersonaje;
    private String rolPersonaje;
    private HashSet<Libro> librosPersonaje;
    private ResourceBundle resourceBundle;

    public Personaje(String nombrePersona, String sexoPersona, int edadPersonaje, String rolPersonaje, HashSet<Libro> libros) {
        super(nombrePersona, sexoPersona);
        this.edadPersonaje = edadPersonaje;
        this.rolPersonaje = rolPersonaje;
        librosPersonaje = libros;
        resourceBundle = ResourceBundle.getBundle("idioma");
    }

    public int getEdadPersonaje() { return edadPersonaje; }

    public void setEdadPersonaje(int edadPersonaje) { this.edadPersonaje = edadPersonaje; }

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
        return super.toString() + resourceBundle.getString("personaje.edad") + edadPersonaje +
                ", rol: " + rolPersonaje;
    }
}
