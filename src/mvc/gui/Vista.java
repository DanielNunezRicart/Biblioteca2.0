package mvc.gui;

import javax.swing.*;
import java.awt.*;
import layouts.WrapLayout;

public class Vista {

    JFrame frame;
    JPanel panelPrincipal;
    JPanel panelMenu;
    JTabbedPane pestaPanel;
    JPanel autoresPanel;
    JPanel librosPanel;
    JPanel personajesPanel;
    private JPanel panelBotonesAutor;
    private JPanel panelDatosAutor;
    private JButton botEliminarAutor;
    private JButton botEditarAutor;
    private JButton botNuevoAutor;
    private JButton botGuardar;
    private JButton botCargar;
    private JButton botIdiomas;
    private JButton botModificarRelAutor;
    private JList listaAutores;
    private JList listaLibrosAutores;
    private JLabel txtListaAutores;
    private JLabel txtListaLibrosAutores;
    private JPanel panelSeparador1;
    private JPanel panelSeparador2;
    private JPanel panelBotonesLibro;
    private JButton botEliminarLibro;
    private JButton botEditarLibro;
    private JButton botNuevoLibro;
    private JButton botModificarRelLibro;
    private JLabel txtLibros;
    private JLabel txtPersonajesLibro;
    private JList listaLibros;
    private JList listaPersonajesLibro;
    private JPanel panelDatosLibro;
    private JPanel panelBotonesPersonaje;
    private JButton botEliminarPersonaje;
    private JButton botEditarPersonaje;
    private JButton botNuevoPersonaje;
    private JButton botModificarRelPersonaje;
    private JPanel panelDatosPersonaje;
    private JLabel txtPersonajes;
    private JLabel txtLibrosPersonaje;
    private JList listaPersonajes;
    private JList listaLibrosPersonaje;

    public Vista() {
        //Creamos el JFrame
        frame = new JFrame("Biblioteca 2.0");
        frame.setContentPane(panelPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Iniciamos los componentes gráficos, creamos menús, ...
        initLayout();

        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public void initLayout() {
        panelMenu.setLayout(new WrapLayout(FlowLayout.LEADING, 3, 3));
    }
}
