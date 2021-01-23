package mvc;

import datos.Autor;
import datos.Libro;
import datos.Personaje;

import java.util.HashSet;

public class Modelo {

    private HashSet<Autor> autores;
    private HashSet<Libro> libros;
    private HashSet<Personaje> personajes;

    public Modelo() {
        autores = new HashSet<>();
        libros = new HashSet<>();
        personajes = new HashSet<>();
    }

}
