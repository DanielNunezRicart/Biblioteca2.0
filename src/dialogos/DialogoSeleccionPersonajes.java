package dialogos;

import datos.Personaje;
import mvc.Modelo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

/**
 * Clase DialogoSeleccionPersonajes. Esta clase crea un diálogo que nos permite modifcar los personajes que aparecen en un libro.
 */
public class DialogoSeleccionPersonajes extends JDialog {

    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JList listaPersonajes;
    private DefaultListModel<Personaje> dlmPersonajes;

    private Modelo modelo;
    private DialogoModLibro d;

    /**
     * Constructor de la clase. Crea el diálogo, inicia los componentes, ...
     * @param modelo Modelo El objeto Modelo que se crea al iniciar la aplicación.
     * @param d DialogoModLibro El diálogo que llama a éste.
     */
    public DialogoSeleccionPersonajes(Modelo modelo, DialogoModLibro d) {
        this.modelo = modelo;
        this.d = d;

        setContentPane(contentPane);
        setSize(600, 300);
        setLocationRelativeTo(null);
        setModal(true);

        JRootPane rootPane = SwingUtilities.getRootPane(buttonOK);
        rootPane.setDefaultButton(buttonOK);

        cargarModelo();
        mostrarPersonajes();

        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) { aceptar(); }});
        buttonCancel.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) { cancelar(); }});

        setVisible(true);
    }

    /**
     * Método que carga todos los personajes en la lista.
     */
    private void mostrarPersonajes() {
        dlmPersonajes.clear();
        for (Personaje personaje : modelo.getPersonajes()) {
            dlmPersonajes.addElement(personaje);
        }
        seleccionarPersonajesOriginales();
    }

    /**
     * Método que cambia los personajes antiguos por los seleccionados actualmente y cierra la ventana.
     */
    private void aceptar() {
        cambiarPersonajes();
        dispose();
    }

    /**
     * Marca como seleccionados a los personajes originales
     */
    private void seleccionarPersonajesOriginales() {
        listaPersonajes.setSelectedIndices(d.getIndicesPersonajesOriginales());
    }

    /**
     * Cambia los personajes antiguos por los nuevos seleccionados. Si no se cambian los personajes
     * seguirán siendo los de antes.
     */
    private void cambiarPersonajes() {
        if (listaPersonajes.isSelectionEmpty()) {
            HashSet<Personaje> personajes = new HashSet<>();
            d.setPersonajes(personajes);
        } else {
            HashSet<Personaje> personajes = new HashSet<>();
            for (Object personaje : listaPersonajes.getSelectedValuesList()) {
                Personaje p = (Personaje) personaje;
                personajes.add(p);
            }
            d.setPersonajes(personajes);
        }
    }

    /**
     * Método que se ejecuta al pulsar el botón cancelar. Cierra la ventana.
     */
    private void cancelar() {
        dispose();
    }

    /**
     * Asigna el modelo de la lista a la lista.
     */
    private void cargarModelo() {
        dlmPersonajes = new DefaultListModel<>();
        listaPersonajes.setModel(dlmPersonajes);
    }
}
