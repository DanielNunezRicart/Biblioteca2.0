package mvc;

import datos.Autor;
import datos.Libro;
import datos.Personaje;
import util.Util;

import java.io.*;
import java.util.ArrayList;
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

    public void guardarDatos(File file) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream serializador = new ObjectOutputStream(fos);

        serializador.writeObject(autores);
        serializador.writeObject(libros);
        serializador.writeObject(personajes);

        serializador.close();
    }


    public void cargarDatos(File file) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream deserializador = new ObjectInputStream(fis);

        autores = (HashSet<Autor>) deserializador.readObject();
        libros = (HashSet<Libro>) deserializador.readObject();
        personajes = (HashSet<Personaje>) deserializador.readObject();

        deserializador.close();
    }

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

    public HashSet<Autor> getAutores() {
        return autores;
    }
}
