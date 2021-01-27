package mvc.gui;

import javax.swing.*;
import java.awt.*;

import datos.Autor;
import datos.Libro;
import datos.Personaje;
import layouts.WrapLayout;

public class Vista {

    JFrame frame;
    JPanel panelPrincipal;
    JPanel panelMenu;
    JTabbedPane pestaPanel;
    JPanel autoresPanel;
    JPanel librosPanel;
    JPanel personajesPanel;
    JPanel panelBotonesAutor;
    JPanel panelDatosAutor;
    JButton botEliminarAutor;
    JButton botEditarAutor;
    JButton botNuevoAutor;
    JButton botGuardar;
    JButton botCargar;
    JButton botIdiomas;
    JButton botModificarRelAutor;
    JList listaAutores;
    JList listaLibrosAutores;
    JLabel txtListaAutores;
    JLabel txtListaLibrosAutores;
    JPanel panelSeparador1;
    JPanel panelSeparador2;
    JPanel panelBotonesLibro;
    JButton botEliminarLibro;
    JButton botEditarLibro;
    JButton botNuevoLibro;
    JButton botModificarRelLibro;
    JLabel txtLibros;
    JLabel txtPersonajesLibro;
    JList listaLibros;
    JList listaPersonajesLibro;
    JPanel panelDatosLibro;
    JPanel panelBotonesPersonaje;
    JButton botEliminarPersonaje;
    JButton botEditarPersonaje;
    JButton botNuevoPersonaje;
    JButton botModificarRelPersonaje;
    JPanel panelDatosPersonaje;
    JLabel txtPersonajes;
    JLabel txtLibrosPersonaje;
    JList listaPersonajes;
    JList listaLibrosPersonaje;
    DefaultListModel<Autor> dlmAutores;
    DefaultListModel<Libro> dlmLibrosAutor;
    DefaultListModel<Libro> dlmLibros;
    DefaultListModel<Personaje> dlmPersonajesLibro;
    DefaultListModel<Personaje> dlmPersonajes;
    DefaultListModel<Libro> dlmLibrosPersonaje;

    public Vista() {
        //Creamos el JFrame
        frame = new JFrame("Biblioteca 2.0");
        frame.setContentPane(panelPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Iniciamos los componentes gráficos, creamos menús, ...
        initLayout();

        //Asignamos los modelos
        asignarModelos();

        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public void initLayout() {
        panelMenu.setLayout(new WrapLayout(FlowLayout.LEADING, 3, 3));
    }

    /**
     * Método que inicializa los DefaultListModel y se los asigna a sus JList correspondientes
     */
    private void asignarModelos(){
        //Inicializamos los dlm
        dlmAutores = new DefaultListModel<>();
        dlmLibrosAutor = new DefaultListModel<>();
        dlmLibros = new DefaultListModel<>();
        dlmPersonajesLibro = new DefaultListModel<>();
        dlmPersonajes = new DefaultListModel<>();
        dlmLibrosPersonaje = new DefaultListModel<>();

        //Se los asignamos a las listas
        listaAutores.setModel(dlmAutores);
        listaLibrosAutores.setModel(dlmLibrosAutor);
        listaLibros.setModel(dlmLibros);
        listaPersonajesLibro.setModel(dlmPersonajesLibro);
        listaPersonajes.setModel(dlmPersonajes);
        listaLibrosPersonaje.setModel(dlmLibrosPersonaje);
    }
}
