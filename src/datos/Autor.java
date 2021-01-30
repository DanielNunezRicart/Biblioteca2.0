package datos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

public class Autor extends Persona implements Serializable {

    private String paisOrigen;
    private HashSet<Libro> librosPublicados;

    public Autor(String nombrePersona, int edadPersona, String sexoPersona, String paisOrigen) {
        super(nombrePersona, edadPersona, sexoPersona);
        this.paisOrigen = paisOrigen;
        librosPublicados = new HashSet<>();
    }

    public String getPaisOrigen() {
        return paisOrigen;
    }

    public void setPaisOrigen(String paisOrigen) {
        this.paisOrigen = paisOrigen;
    }

    public HashSet<Libro> getLibrosPublicados() {
        return librosPublicados;
    }

    public void setLibrosPublicados(HashSet<Libro> librosPublicados) {
        this.librosPublicados = librosPublicados;
    }

    public void addLibro(Libro libro) {
        librosPublicados.add(libro);
    }

    @Override
    public String toString() {
        return super.toString() + ", país de origen: " + paisOrigen;
    }
}
