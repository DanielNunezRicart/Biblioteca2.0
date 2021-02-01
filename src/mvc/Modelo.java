package mvc;

import datos.Autor;
import datos.Libro;
import datos.Personaje;
import dialogos.DialogoModAutor;
import dialogos.DialogoModLibro;
import dialogos.DialogoModPersonaje;
import util.Util;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class Modelo {

    private ArrayList<Autor> autores;
    private ArrayList<Libro> libros;
    private ArrayList<Personaje> personajes;

    /**
     * Constructor de la clase Modelo. No necesita parámetros. Simplemente inicializa los
     * HashSet que contienen los objetos de tipo Autor, Libro y Personaje
     */
    public Modelo() {
        autores = new ArrayList<>();
        libros = new ArrayList<>();
        personajes = new ArrayList<>();
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

        autores = (ArrayList<Autor>) deserializador.readObject();
        libros = (ArrayList<Libro>) deserializador.readObject();
        personajes = (ArrayList<Personaje>) deserializador.readObject();

        deserializador.close();
    }

    /**
     * Método que añade un personaje nuevo al sistema. También lo añade a cualquiera de los libros en los que sale
     * @param personaje El personaje que se desea añadir
     */
    public void nuevoPersonaje(Personaje personaje) {
        if (!comprobarPersonajeExiste(personaje)) {
            personajes.add(personaje);
            for (Libro libro : personaje.getLibrosPersonaje()) {
                libro.anadirPersonaje(personaje);
            }
        } else {
            Util.mensajeError("¡El personaje que intenta introducir ya existe!");
        }
    }

    /**
     * Método que comprueba si el personaje pasado por parámetro ya existe en el sistema
     * @param personaje El personaje que deseamos comprobar si existe
     * @return boolean True si existe false si no
     */
    private boolean comprobarPersonajeExiste(Personaje personaje) {
        boolean existe = false;
        for (Personaje p : personajes) {
            if (p.getNombrePersona().toLowerCase().equals(personaje.getNombrePersona().toLowerCase())) {
                existe = true;
            }
        }
        return existe;
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
     * Llama a un diálogo que modifica al autor pasado por parámetro
     * @param autor El autor que se quiere modificar
     */
    public void modAutor(Autor autor) { DialogoModAutor d = new DialogoModAutor(this, autor); }

    /**
     * Llama a un diálogo que modifica el libro pasado por parámetro
     * @param libro El libro que se quiere modificar
     */
    public void modLibro(Libro libro) { DialogoModLibro d = new DialogoModLibro(this, libro); }

    /**
     * Modifica el personaje pasado por parámetro llamando a un diálogo para introducir nuevos datos.
     * Además, si el personaje se queda sin libros, se elimina.
     * @param personaje El personaje que se quiere modificar
     */
    public void modPersonaje(Personaje personaje) {
        int index = personajes.indexOf(personaje);
        DialogoModPersonaje d = new DialogoModPersonaje(this, personaje);
        if (personajes.get(index).getLibrosPersonaje().isEmpty()) {
            personajes.remove(index);
        }
    }

    /**
     * Elimina el personaje pasado por parámetro de la lista de personajes y de la lista de personajes de los
     * libros correspondientes
     * @param personaje El personaje que queremos eliminar
     */
    public void eliminarPersonaje(Personaje personaje) {
        HashSet<Libro> librosPersonaje = personaje.getLibrosPersonaje();
        Iterator<Libro> it = librosPersonaje.iterator();
        while (it.hasNext()) {
            it.next().getPersonajesLibro().remove(personaje);
        }
        personajes.remove(personaje);
    }

    /**
     * Elimina el libro pasado por parámetro de la lista de libros, de la lista de libros de los autores y de la
     * lista de libros de los personajes
     * @param libro El libro que queremos eliminar
     */
    public void eliminarLibro(Libro libro) {
        //Primero eliminamos el libro del autor
        libro.getAutorLibro().getLibrosPublicados().remove(libro);
        //Ahora lo eliminamos de la lista de libros de sus personajes
        HashSet<Personaje> borrar = new HashSet<>();
        Iterator<Personaje> it = libro.getPersonajesLibro().iterator();
        while (it.hasNext()) {
            Personaje p = it.next();
            if (p.getLibrosPersonaje().contains(libro) && p.getLibrosPersonaje().size() > 1) {
                p.getLibrosPersonaje().remove(libro);
                //Si el personaje sólo tiene ese libro lo añadimos a borrar
            } else if (p.getLibrosPersonaje().contains(libro) && p.getLibrosPersonaje().size() == 1) {
                borrar.add(p);
            }
        }
        //Comprobamos si borrar está vacío, y si no, eliminamos a los personajes que haya dentro
        if (!borrar.isEmpty()) {
            for (Personaje p : borrar) {
                personajes.remove(p);
            }
        }
        //Finalmente borramos el libro
        libros.remove(libro);
    }

    /**
     * Elimina el autor pasado por parámetro de la lista de autores, así como todos los libros y personajes que
     * dependen de él
     * @param autor
     */
    public void eliminarAutor(Autor autor) {
        //Creamos 2 listas y metemos dentro los libros y personajes que dependen del autor
        ArrayList<Personaje> borrarPersonajes = new ArrayList<>();
        ArrayList<Libro> borrarLibros = new ArrayList<>();
        for (Libro libro : autor.getLibrosPublicados()) {
            borrarLibros.add(libro);
            for (Personaje personaje : libro.getPersonajesLibro()) {
                if (!borrarPersonajes.contains(personaje)) {
                    borrarPersonajes.add(personaje);
                }
            }
        }
        //Una vez los tenemos todos procedemos a borrar los personajes
        for (Personaje personaje : borrarPersonajes) {
            personajes.remove(personaje);
        }
        //Y ahora los libros
        for (Libro libro : borrarLibros) {
            libros.remove(libro);
        }
        //Por último eliminamos el autor
        autores.remove(autor);
    }

    /**
     * Método que devuelve la lista de autores
     * @return HashSet<Autor> La lista de autores añadidos en el sistema
     */
    public ArrayList<Autor> getAutores() {
        return autores;
    }

    /**
     * Método que devuelve la lista de libros
     * @return HashSet<Libro> La lista de libros añadidos en el sistema
     */
    public ArrayList<Libro> getLibros() {
        return libros;
    }

    /**
     * Método que devuelce la lista de personajes
     * @return HashSet<Personaje> La lista de personajes añadidos al sistema
     */
    public ArrayList<Personaje> getPersonajes() {
        return personajes;
    }
}
