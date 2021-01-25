package mvc;

import datos.Autor;
import datos.Libro;
import datos.Personaje;

import java.io.*;
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
}
