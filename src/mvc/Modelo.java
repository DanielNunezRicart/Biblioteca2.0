package mvc;

import datos.Autor;
import datos.Libro;
import datos.Personaje;
import util.Util;

import java.io.*;
import java.util.HashSet;

public class Modelo {

    private HashSet<Autor> autores;
    private HashSet<Libro> libros;
    private HashSet<Personaje> personajes;

    /**
     * Constructor de la clase Modelo. No necesita parámetros. Simplemente inicializa los
     * HashSet que contienen los objetos de tipo Autor, Libro y Personaje
     */
    public Modelo() {
        autores = new HashSet<>();
        libros = new HashSet<>();
        personajes = new HashSet<>();
    }

    /**
     * Método que nos permite guardar datos introducidos en un fichero
     * @param file El archivo en el que queremos guardar los datos
     * @throws IOException
     */
    public void guardarDatos(File file) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream serializador = new ObjectOutputStream(fos);

        serializador.writeObject(autores);
        serializador.writeObject(libros);
        serializador.writeObject(personajes);

        serializador.close();
    }

    /**
     * Método que carga datos previamente guardados en un fichero
     * @param file El fichero del que queremos obtener los datos
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void cargarDatos(File file) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream deserializador = new ObjectInputStream(fis);

        autores = (HashSet<Autor>) deserializador.readObject();
        libros = (HashSet<Libro>) deserializador.readObject();
        personajes = (HashSet<Personaje>) deserializador.readObject();

        deserializador.close();
    }

    /**
     * Método que añade un libro al sistema
     * @param libro El objeto libro que queremos añadir
     */
    public void nuevoLibro(Libro libro) {
        if (!comprobarLibroExiste(libro)) {
            libros.add(libro);
            libro.getAutorLibro().addLibro(libro);
        } else {
            Util.mensajeError("¡El libro que intenta introducir ya existe!");
        }
    }

    /**
     * Método que comprueba si el libro pasado por parámetro ya existe
     * @param libro El libro que queremos saber si existe ya en el sistema
     * @return boolean True si existe, false si no
     */
    private boolean comprobarLibroExiste(Libro libro) {
        boolean existe = false;
        for (Libro l : libros) {
            if (l.getNombreLibro().toLowerCase().equals(libro.getNombreLibro().toLowerCase())) {
                existe = true;
            }
        }
        return existe;
    }

    /**
     * Método que añade un autor a la lista de autores
     * @param autor El objeto Autor a añadir
     */
    public void nuevoAutor(Autor autor) {
        if (!comprobarAutorExiste(autor)) {
            autores.add(autor);
        } else {
            Util.mensajeError("¡El autor que intenta introducir ya existe!");
        }
    }

    /**
     * Método que comprueba si el nombre del autor introducido por parámetro el el mismo
     * que el de alguno de los autores ya existentes
     * @param autor Objeto autor para comprobar el nombre
     * @return boolean False si no existe y True si existe
     */
    private boolean comprobarAutorExiste(Autor autor) {
        boolean existe = false;

        for (Autor a : autores) {
            if (a.getNombrePersona().toLowerCase().equals(autor.getNombrePersona().toLowerCase())) {
                existe = true;
            }
        }

        return existe;
    }

    /**
     * Método que devuelve la lista de autores
     * @return HashSet<Autor> La lista de autores añadidos en el sistema
     */
    public HashSet<Autor> getAutores() {
        return autores;
    }

    /**
     * Método que devuelve la lista de libros
     * @return HashSet<Libro> La lista de libros añadidos en el sistema
     */
    public HashSet<Libro> getLibros() {
        return libros;
    }
}
