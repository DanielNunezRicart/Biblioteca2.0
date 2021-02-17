package datos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.ResourceBundle;

public class Autor extends Persona implements Serializable {

    private static final long serialVersionUID = 1L;

    private LocalDate fechaNacimiento;
    private String paisOrigen;
    private HashSet<Libro> librosPublicados;

    /**
     * Constructor de la clase Autor
     * @param nombrePersona String El nombre del autor
     * @param fechaNacimiento LocalDate La fecha de nacimiento del autor
     * @param sexoPersona String El sexo del autor
     * @param paisOrigen String El país de origen del autor
     */
    public Autor(String nombrePersona, LocalDate fechaNacimiento, String sexoPersona, String paisOrigen) {
        super(nombrePersona, sexoPersona);
        this.fechaNacimiento = fechaNacimiento;
        this.paisOrigen = paisOrigen;
        librosPublicados = new HashSet<>();
    }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }

    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public String getPaisOrigen() { return paisOrigen; }

    public void setPaisOrigen(String paisOrigen) { this.paisOrigen = paisOrigen; }

    public HashSet<Libro> getLibrosPublicados() { return librosPublicados; }

    public void setLibrosPublicados(HashSet<Libro> librosPublicados) { this.librosPublicados = librosPublicados; }

    public void addLibro(Libro libro) { librosPublicados.add(libro); }

    /**
     * Método que devuelve la información del autor en forma de String
     * @return String Todos los datos (menos la lista de libros) del autor
     */
    @Override
    public String toString() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("idioma");
        return super.toString() + resourceBundle.getString("autor.nacido") + fechaNacimiento.toString() +
                resourceBundle.getString("autor.en") + paisOrigen;
    }
}
