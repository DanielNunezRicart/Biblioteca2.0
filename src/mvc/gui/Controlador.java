package mvc.gui;

import mvc.Modelo;

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
            }

        }

        catch (IOException ioe) { ioe.printStackTrace(); }
        catch (ClassNotFoundException cnfe) { cnfe.printStackTrace(); }
    }

    private void seleccionarIdioma() {

    }

    private void cargarDatos() throws IOException, ClassNotFoundException {
        JFileChooser fc = new JFileChooser();
        int opcion = fc.showOpenDialog(vista.frame);
        if (opcion == JFileChooser.APPROVE_OPTION) {
            modelo.cargarDatos(fc.getSelectedFile());
        }

    }

    private void guardarDatos() throws IOException {
        JFileChooser fc = new JFileChooser();
        int opcion = fc.showSaveDialog(vista.frame);
        if (opcion == JFileChooser.APPROVE_OPTION) {
            modelo.guardarDatos(fc.getSelectedFile());
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }
}
