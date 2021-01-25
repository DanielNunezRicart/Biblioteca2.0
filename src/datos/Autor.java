package datos;

import java.io.Serializable;
import java.util.ArrayList;

public class Autor extends Persona implements Serializable {

    private String paisOrigen;
    private ArrayList<Libro> librosPublicados;

    public Autor(String nombrePersona, int edadPersona, String sexoPersona, String paisOrigen) {
        super(nombrePersona, edadPersona, sexoPersona);
        this.paisOrigen = paisOrigen;
        librosPublicados = new ArrayList<>();
    }

    public String getPaisOrigen() {
        return paisOrigen;
    }

    public void setPaisOrigen(String paisOrigen) {
        this.paisOrigen = paisOrigen;
    }

    public ArrayList<Libro> getLibrosPublicados() {
        return librosPublicados;
    }

    public void setLibrosPublicados(ArrayList<Libro> librosPublicados) {
        this.librosPublicados = librosPublicados;
    }

    @Override
    public String toString() {
        return super.toString() + ", país de origen: " + paisOrigen;
    }
}
