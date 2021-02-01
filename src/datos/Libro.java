package datos;

import javax.swing.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.ResourceBundle;

public class Libro implements Comparable<Libro>, Serializable {

    private String nombreLibro;
    private Autor autorLibro;
    private LocalDate fechaPublicacion;
    private float precioLibro;
    private HashSet<Personaje> personajesLibro;
    private ImageIcon portada;
    private ResourceBundle resourceBundle;

    public Libro(String nombreLibro, Autor autorLibro, LocalDate fechaPublicacion, float precioLibro, ImageIcon portada) {
        this.nombreLibro = nombreLibro;
        this.autorLibro = autorLibro;
        this.fechaPublicacion = fechaPublicacion;
        this.precioLibro = precioLibro;
        personajesLibro = new HashSet<>();
        this.portada = portada;
        resourceBundle = ResourceBundle.getBundle("idioma");
    }

    public String getNombreLibro() {
        return nombreLibro;
    }

    public void setNombreLibro(String nombreLibro) {
        this.nombreLibro = nombreLibro;
    }

    public Autor getAutorLibro() {
        return autorLibro;
    }

    public void setAutorLibro(Autor autorLibro) {
        this.autorLibro = autorLibro;
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public float getPrecioLibro() {
        return precioLibro;
    }

    public void setPrecioLibro(float precioLibro) {
        this.precioLibro = precioLibro;
    }

    public HashSet<Personaje> getPersonajesLibro() {
        return personajesLibro;
    }

    public void setPersonajesLibro(HashSet<Personaje> personajesLibro) {
        this.personajesLibro = personajesLibro;
    }

    public ImageIcon getPortada() {return portada;}

    public void setPortada(ImageIcon portada) {this.portada = portada;}

    public void anadirPersonaje(Personaje personaje) {personajesLibro.add(personaje);}

    @Override
    public String toString() {
        return nombreLibro + resourceBundle.getString("libro.escritoPor") +
                autorLibro.getNombrePersona() + resourceBundle.getString("libro.publicado") +
                fechaPublicacion.toString() + resourceBundle.getString("libro.precio") + precioLibro;
    }

    @Override
    public int compareTo(Libro libro) {
        return nombreLibro.compareTo(libro.nombreLibro);
    }
}
