package datos;

import util.Util;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.ResourceBundle;

public class Autor extends Persona implements Serializable {

    private static final long serialVersionUID = 1L;

    private LocalDate fechaNacimiento;
    private String paisOrigen;
    private HashSet<Libro> librosPublicados;

    public Autor(String nombrePersona, LocalDate fechaNacimiento, String sexoPersona, String paisOrigen) {
        super(nombrePersona, sexoPersona);
        this.fechaNacimiento = fechaNacimiento;
        this.paisOrigen = paisOrigen;
        librosPublicados = new HashSet<>();
    }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }

    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

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
        ResourceBundle resourceBundle = ResourceBundle.getBundle("idioma");
        return super.toString() + resourceBundle.getString("autor.nacido") + fechaNacimiento.toString() +
                resourceBundle.getString("autor.en") + paisOrigen;
    }
}
