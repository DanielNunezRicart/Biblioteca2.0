package mvc.gui;

import datos.Autor;
import datos.Libro;
import datos.Personaje;
import dialogos.DialogoNuevoAutor;
import dialogos.DialogoNuevoLibro;
import dialogos.DialogoNuevoPersonaje;
import mvc.Modelo;
import util.Util;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Clase Controlador
 */
public class Controlador implements ActionListener, ListSelectionListener {

    private Vista vista;
    private Modelo modelo;

    /**
     * Constructor de la clase Controlador
     * @param vista objeto de la clase Vista
     * @param modelo objeto de la clase Modelo
     */
    public Controlador(Vista vista, Modelo modelo) {
        this.vista = vista;
        this.modelo = modelo;

        //Añadimos los listeners
        addListListeners(this);
        addActionListeners(this);
    }

    /**
     * Método que asocia componentes con el listener ListSelectionListener
     * @param listener objeto ListSelectionListener
     */
    private void addListListeners(ListSelectionListener listener) {
        vista.listaAutores.addListSelectionListener(listener);
        vista.listaLibrosAutores.addListSelectionListener(listener);
        vista.listaLibros.addListSelectionListener(listener);
        vista.listaPersonajesLibro.addListSelectionListener(listener);
        vista.listaPersonajes.addListSelectionListener(listener);
        vista.listaLibrosPersonaje.addListSelectionListener(listener);
    }

    /**
     * Método que asocia componentes con el listener ActionListener
     * @param listener objeto ActionListener
     */
    private void addActionListeners(ActionListener listener) {
        //Botones menú
        vista.botGuardar.addActionListener(listener);
        vista.botCargar.addActionListener(listener);
        vista.botIdiomas.addActionListener(listener);
        //Botones Autor
        vista.botNuevoAutor.addActionListener(listener);
        vista.botEditarAutor.addActionListener(listener);
        vista.botEliminarAutor.addActionListener(listener);
        vista.botModificarRelAutor.addActionListener(listener);
        //Botones Libro
        vista.botNuevoLibro.addActionListener(listener);
        vista.botEditarLibro.addActionListener(listener);
        vista.botEliminarLibro.addActionListener(listener);
        vista.botModificarRelLibro.addActionListener(listener);
        //Botones Personaje
        vista.botNuevoPersonaje.addActionListener(listener);
        vista.botEditarPersonaje.addActionListener(listener);
        vista.botEliminarPersonaje.addActionListener(listener);
        vista.botModificarRelPersonaje.addActionListener(listener);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        try {
            switch (comando) {
                case "Guardar":
                    guardarDatos();
                    break;

                case "Cargar":
                    cargarDatos();
                    break;

                case "Idiomas":
                    seleccionarIdioma();
                    break;

                case "NuevoAutor":
                    nuevoAutor();
                    break;

                case "EditarAutor":
                    modificarAutor();
                    break;

                case "EliminarAutor":
                    eliminarAutor();
                    break;

                case "NuevoLibro":
                    nuevoLibro();
                    break;
                    
                case "EliminarLibro":
                    eliminarLibro();
                    break;

                case "NuevoPersonaje":
                    nuevoPersonaje();
                    break;

                case "EliminarPersonaje":
                    eliminarPersonaje();
                    break;
            }
        }

        catch (IOException ioe) { ioe.printStackTrace(); }
        catch (ClassNotFoundException cnfe) { cnfe.printStackTrace(); }
    }

    /**
     * Modifica un autor seleccionado en la lista de autores
     */
    private void modificarAutor() {
        if (vista.listaAutores.isSelectionEmpty()) {
            Util.mensajeError("No puede modificar un autor si no lo selecciona primero");
        } else {
            modelo.modAutor((Autor) vista.listaAutores.getSelectedValue());
            vista.listaLibros.clearSelection();
            vista.listaPersonajes.clearSelection();
            vista.dlmLibrosAutor.clear();
            vista.dlmPersonajesLibro.clear();
            vista.dlmLibrosPersonaje.clear();
            listarAutores();
            listarLibros();
            listarPersonajes();
        }
    }

    /**
     * Elimina un autor seleccionado de la lista de autores
     */
    private void eliminarAutor() {
        if (vista.listaAutores.isSelectionEmpty()) {
            Util.mensajeError("No puede eliminar un autor si no lo selecciona primero");
        } else {
            Autor autor = (Autor) vista.listaAutores.getSelectedValue();
            modelo.eliminarAutor(autor);
            vista.listaLibros.clearSelection();
            vista.listaPersonajes.clearSelection();
            vista.dlmLibrosAutor.clear();
            vista.dlmPersonajesLibro.clear();
            vista.dlmLibrosPersonaje.clear();
            listarAutores();
            listarLibros();
            listarPersonajes();
        }
    }

    /**
     * Elimina un personaje seleccionado en la lista de libros
     */
    private void eliminarLibro() {
        if (vista.listaLibros.isSelectionEmpty()) {
            Util.mensajeError("No puede eliminar un libro si no lo selecciona primero");
        } else {
            Libro libro = (Libro) vista.listaLibros.getSelectedValue();
            modelo.eliminarLibro(libro);
            listarLibros();
            vista.listaAutores.clearSelection();
            vista.dlmLibrosAutor.clear();
            vista.listaPersonajes.clearSelection();
            vista.listaLibrosPersonaje.clearSelection();
            vista.dlmLibrosPersonaje.clear();
            vista.dlmPersonajesLibro.clear();
            listarPersonajes();
        }
    }

    /**
     * Elimina un personaje seleccionado en la lista de personajes
     */
    private void eliminarPersonaje() {
        //Comprobamos que hay un personaje seleccionado, si no salta un mensaje de error
        if (vista.listaPersonajes.isSelectionEmpty()) {
            Util.mensajeError("No puede eliminar un personaje si no lo selecciona primero");
        } else {
            //Si hay un personaje llamamos al método para eliminarlo de la clase Modelo
            Personaje personaje = (Personaje) vista.listaPersonajes.getSelectedValue();
            modelo.eliminarPersonaje(personaje);
            listarPersonajes();
            vista.dlmLibrosPersonaje.clear();
            vista.listaLibros.clearSelection();
        }
    }

    /**
     * Crea un diálogo para añadir un personaje y refresca los elementos en los que aparecen
     */
    private void nuevoPersonaje() {
        DialogoNuevoPersonaje d = new DialogoNuevoPersonaje(modelo);
        listarPersonajes();
    }

    /**
     * Método que refresca la lista de personajes existentes
     */
    private void listarPersonajes() {
        vista.dlmPersonajes.clear();
        if (!modelo.getPersonajes().isEmpty()) {
            for (Personaje p : modelo.getPersonajes()) {
                vista.dlmPersonajes.addElement(p);
            }
        }
    }

    /**
     * Crea un diálogo para añadir un libro nuevo y refresca los elementos en los que aparecen libros
     */
    private void nuevoLibro() {
        DialogoNuevoLibro d = new DialogoNuevoLibro(modelo);
        listarLibros();
    }

    /**
     * Limpia el JList en el que se muestran los libros y lo actualiza
     */
    private void listarLibros() {
        vista.listaLibros.clearSelection();
        vista.dlmLibros.clear();
        for (Libro libro : modelo.getLibros()) {
            vista.dlmLibros.addElement(libro);
        }
    }

    /**
     * Crea un diálogo que permite añadir un nuevo autor y refresca los elementos en los que aparecen
     */
    private void nuevoAutor() {
        DialogoNuevoAutor d = new DialogoNuevoAutor(modelo);
        listarAutores();
    }

    /**
     * Muestra los libros de cada autor
     * @param autor El autor del que queremos que se muestren sus libros
     */
    private void listarLibrosAutor(Autor autor) {
        vista.dlmLibrosAutor.clear();
        if (autor.getLibrosPublicados() != null) {
            for (Libro libro : autor.getLibrosPublicados()){
                vista.dlmLibrosAutor.addElement(libro);
            }
        }
    }

    /**
     * Muestra los personajes del libro seleccionado
     * @param libro El libro del que queremos saber sus personajes
     */
    private void listarPersonajesLibro(Libro libro) {
        vista.dlmPersonajesLibro.clear();
        if (libro.getPersonajesLibro() != null) {
            for (Personaje personaje : libro.getPersonajesLibro()) {
                vista.dlmPersonajesLibro.addElement(personaje);
            }
        }
    }

    /**
     * Muestra los libros del personaje seleccionado
     * @param personaje El personaje del que queremos saber los libros en los que aparece
     */
    private void listarLibrosPersonaje(Personaje personaje) {
        vista.dlmLibrosPersonaje.clear();
        if (!personaje.getLibrosPersonaje().isEmpty()) {
            for (Libro libro : personaje.getLibrosPersonaje()) {
                vista.dlmLibrosPersonaje.addElement(libro);
            }
        }
    }

    /**
     * Refresca los elementos en los que aparecen autores
     */
    private void listarAutores() {
        vista.dlmAutores.clear();
        for (Autor autor : modelo.getAutores()) {
            vista.dlmAutores.addElement(autor);
        }
    }

    private void seleccionarIdioma() {

    }

    /**
     * Muestra una ventana que permite seleccionar un archivo para cargar datos ya guardados
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void cargarDatos() throws IOException, ClassNotFoundException {
        JFileChooser fc = new JFileChooser();
        int opcion = fc.showOpenDialog(vista.frame);
        if (opcion == JFileChooser.APPROVE_OPTION) {
            modelo.cargarDatos(fc.getSelectedFile());
        }
    }

    /**
     * Muestra una ventana que nos permite guardar datos en el lugar que seleccionemos
     * @throws IOException
     */
    private void guardarDatos() throws IOException {
        JFileChooser fc = new JFileChooser();
        int opcion = fc.showSaveDialog(vista.frame);
        if (opcion == JFileChooser.APPROVE_OPTION) {
            modelo.guardarDatos(fc.getSelectedFile());
        }
    }

    /**
     * Método que se ejecuta cuando se selecciona un valor en uno de los JList
     * @param e El evento producido
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(e.getSource() == vista.listaAutores) {
            if (vista.listaAutores.getSelectedValue() != null) {
                listarLibrosAutor((Autor) vista.listaAutores.getSelectedValue());
            }
        } else if (e.getSource() == vista.listaLibros) {
            if (vista.listaLibros.getSelectedValue() != null) {
                listarPersonajesLibro((Libro) vista.listaLibros.getSelectedValue());
            }
        } else if (e.getSource() == vista.listaPersonajes) {
            if (vista.listaPersonajes.getSelectedValue() != null) {
                listarLibrosPersonaje((Personaje) vista.listaPersonajes.getSelectedValue());
            }
        }
    }
}
