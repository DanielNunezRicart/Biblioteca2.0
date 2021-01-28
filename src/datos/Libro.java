package datos;

import javax.swing.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Libro implements Comparable<Libro>, Serializable {

    private String nombreLibro;
    private Autor autorLibro;
    private LocalDate fechaPublicacion;
    private float precioLibro;
    private ArrayList<Personaje> personajesLibro;
    private ImageIcon portada;

    public Libro(String nombreLibro, Autor autorLibro, LocalDate fechaPublicacion, float precioLibro, ImageIcon portada) {
        this.nombreLibro = nombreLibro;
        this.autorLibro = autorLibro;
        this.fechaPublicacion = fechaPublicacion;
        this.precioLibro = precioLibro;
        personajesLibro = new ArrayList<>();
        this.portada = portada;
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

    public ArrayList<Personaje> getPersonajesLibro() {
        return personajesLibro;
    }

    public void setPersonajesLibro(ArrayList<Personaje> personajesLibro) {
        this.personajesLibro = personajesLibro;
    }

    public ImageIcon getPortada() {return portada;}

    public void setPortada(ImageIcon portada) {this.portada = portada;}

    @Override
    public String toString() {
        return nombreLibro + ", autor: " + autorLibro.getNombrePersona() + ", publicado: " + fechaPublicacion.toString() + ", precio: " + precioLibro;
    }

    @Override
    public int compareTo(Libro libro) {
        return nombreLibro.compareTo(libro.nombreLibro);
    }
}
