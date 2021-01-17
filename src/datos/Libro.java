package datos;

import java.util.ArrayList;

public class Libro {

    private String nombreLibro;
    private Autor autorLibro;
    private String generoLibro;
    private float precioLibro;
    private ArrayList<Personaje> personajesLibro;

    public Libro(String nombreLibro, Autor autorLibro, String generoLibro, float precioLibro, ArrayList<Personaje> personajesLibro) {
        this.nombreLibro = nombreLibro;
        this.autorLibro = autorLibro;
        this.generoLibro = generoLibro;
        this.precioLibro = precioLibro;
        this.personajesLibro = personajesLibro;
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

    public String getGeneroLibro() {
        return generoLibro;
    }

    public void setGeneroLibro(String generoLibro) {
        this.generoLibro = generoLibro;
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

    @Override
    public String toString() {
        return nombreLibro + ", autor: " + autorLibro.getNombrePersona() + ", género: " + generoLibro + ", precio: " + String.valueOf(precioLibro);
    }
}
