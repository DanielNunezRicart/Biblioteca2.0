package dialogos;

import datos.Libro;
import jdk.nashorn.internal.objects.annotations.Constructor;
import mvc.Modelo;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Calse DialogoSeleccionLibros. Clase que nos permite seleccionar los libros del personaje creado.
 */
public class DialogoSeleccionLibros extends JDialog {

    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JList listaLibros;
    private DefaultListModel<Libro> dlmLibros;

    private Modelo modelo;
    private DialogoNuevoPersonaje d;

    /**
     * Constructor de la clase. Crea el diálogo, inicia los componentes, ...
     * @param modelo Modelo El objeto Modelo creado al iniciar la aplicación.
     * @param d DialogoNuevoPersonaje El diálogo desde el que se ha llamado a éste y desde el que cogeremos los datos.
     */
    public DialogoSeleccionLibros(Modelo modelo, DialogoNuevoPersonaje d) {
        this.modelo = modelo;
        this.d = d;
        iniciarComponentes();
    }

    /**
     * Configura la mayor parte del diálogo (componentes gráficos).
     */
    private void iniciarComponentes() {
        setContentPane(contentPane);
        setSize(600, 300);
        setLocationRelativeTo(null);
        setModal(true);

        JRootPane rootPane = SwingUtilities.getRootPane(buttonOK);
        rootPane.setDefaultButton(buttonOK);

        cargarModelo();
        mostrarLibros();

        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        setVisible(true);
    }

    /**
     * Carga los libros existentes en la lista.
     */
    private void mostrarLibros() {
        dlmLibros.clear();
        for (Libro libro : modelo.getLibros()) {
            dlmLibros.addElement(libro);
        }
    }

    /**
     * Se asignan los libros al personaje y se cierra la ventana al pulsar aceptar.
     */
    private void onOK() {
        addLibros();
        dispose();
    }

    /**
     * Añade los libros seleccionados al HashSet de libros de la clase DialogoNuevoPersonaje.
     */
    private void addLibros() {
        HashSet<Libro> libros = new HashSet<>();
        for (Object libro : listaLibros.getSelectedValuesList()) {
            Libro l = (Libro)libro;
            libros.add(l);
        }
        d.setLibros(libros);
    }

    /**
     * Se cierra la ventana al pulsar cancelar.
     */
    private void onCancel() {
        dispose();
    }

    /**
     * Asigna el modelo de la lista a la lista.
     */
    private void cargarModelo() {
        dlmLibros = new DefaultListModel<>();
        listaLibros.setModel(dlmLibros);
    }
}
