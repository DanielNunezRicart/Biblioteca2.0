package mvc.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ResourceBundle;

import datos.Autor;
import datos.Libro;
import datos.Personaje;
import layouts.WrapLayout;

/**
 * Clase Vista. Esta clase se ocupa del entorno gráfico de la aplicación.
 */
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
    JLabel txtLibros;
    JLabel txtPersonajesLibro;
    JList listaLibros;
    JList listaPersonajesLibro;
    JPanel panelDatosLibro;
    JPanel panelBotonesPersonaje;
    JButton botEliminarPersonaje;
    JButton botEditarPersonaje;
    JButton botNuevoPersonaje;
    JPanel panelDatosPersonaje;
    JLabel txtPersonajes;
    JLabel txtLibrosPersonaje;
    JList listaPersonajes;
    JList listaLibrosPersonaje;
    JButton botGraficos;
    JButton botInformeAutores;
    JButton botInformeLibros;
    JButton botAyuda;
    DefaultListModel<Autor> dlmAutores;
    DefaultListModel<Libro> dlmLibrosAutor;
    DefaultListModel<Libro> dlmLibros;
    DefaultListModel<Personaje> dlmPersonajesLibro;
    DefaultListModel<Personaje> dlmPersonajes;
    DefaultListModel<Libro> dlmLibrosPersonaje;

    /**
     * Constructor de la clase Vista. Se inician todos los componentes, se asignan los modelos de las listas, las especificaciones de los
     * botones, ..., y por último, se hace visible.
     */
    public Vista() {
        //Creamos el JFrame
        frame = new JFrame(ResourceBundle.getBundle("idioma").getString("tituloAplicacion"));
        frame.setContentPane(panelPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Iniciamos los componentes gráficos, ...
        initLayout();

        //Botón por defecto de la ventana principal
        JRootPane rootPane = SwingUtilities.getRootPane(botNuevoAutor);
        rootPane.setDefaultButton(botNuevoAutor);

        //Asignamos los modelos
        asignarModelos();

        //Activamos el control por teclado
        controlPorTeclado();

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Hace que sea posible usar el WrapLayout
     */
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

    /**
     * Define las teclas a pulsar para realizar acciones por teclado
     */
    private void controlPorTeclado() {
        botGuardar.setMnemonic(KeyEvent.VK_G);
        botCargar.setMnemonic(KeyEvent.VK_C);
        botIdiomas.setMnemonic(KeyEvent.VK_I);
        botEditarAutor.setMnemonic(KeyEvent.VK_Q);
        botEliminarAutor.setMnemonic(KeyEvent.VK_W);
        botNuevoLibro.setMnemonic(KeyEvent.VK_A);
        botEditarLibro.setMnemonic(KeyEvent.VK_S);
        botEliminarLibro.setMnemonic(KeyEvent.VK_D);
        botNuevoPersonaje.setMnemonic(KeyEvent.VK_Z);
        botEditarPersonaje.setMnemonic(KeyEvent.VK_X);
        botEliminarPersonaje.setMnemonic(KeyEvent.VK_V);
        botGraficos.setMnemonic(KeyEvent.VK_P);
        botInformeAutores.setMnemonic(KeyEvent.VK_R);
        botInformeLibros.setMnemonic(KeyEvent.VK_T);
        botAyuda.setMnemonic(KeyEvent.VK_H);
    }
}
