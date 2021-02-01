package datos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.ResourceBundle;

public class Autor extends Persona implements Serializable {

    private LocalDate fechaNacimiento;
    private String paisOrigen;
    private HashSet<Libro> librosPublicados;
    private ResourceBundle resourceBundle;

    public Autor(String nombrePersona, LocalDate fechaNacimiento, String sexoPersona, String paisOrigen) {
        super(nombrePersona, sexoPersona);
        this.fechaNacimiento = fechaNacimiento;
        this.paisOrigen = paisOrigen;
        librosPublicados = new HashSet<>();
        resourceBundle = ResourceBundle.getBundle("idioma");
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
        return super.toString() + resourceBundle.getString("autor.nacido") + fechaNacimiento.toString() +
                resourceBundle.getString("autor.en") + paisOrigen;
    }
}
