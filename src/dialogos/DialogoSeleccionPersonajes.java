package dialogos;

import datos.Libro;
import datos.Personaje;
import mvc.Modelo;

import javax.swing.*;
import java.awt.event.*;
import java.util.HashSet;

public class DialogoSeleccionPersonajes extends JDialog {

    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JList listaPersonajes;
    private DefaultListModel<Personaje> dlmPersonajes;

    private Modelo modelo;
    private DialogoModLibro d;

    public DialogoSeleccionPersonajes(Modelo modelo, DialogoModLibro d) {
        this.modelo = modelo;
        this.d = d;

        setContentPane(contentPane);
        setSize(600, 300);
        setResizable(false);
        setUndecorated(false);
        setLocationRelativeTo(null);
        setModal(true);

        cargarModelo();
        mostrarPersonajes();

        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) { aceptar(); }});
        buttonCancel.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) { cancelar(); }});

        setVisible(true);
    }

    /**
     * Método que carga todos los personajes en la lista
     */
    private void mostrarPersonajes() {
        dlmPersonajes.clear();
        for (Personaje personaje : modelo.getPersonajes()) {
            dlmPersonajes.addElement(personaje);
        }
    }

    /**
     * Método que cambia los personajes antiguos por los seleccionados actualmente y cierra la ventana
     */
    private void aceptar() {
        cambiarPersonajes();
        dispose();
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

    private void cancelar() {
        dispose();
    }

    /**
     * Asigna el modelo de la lista a la lista
     */
    private void cargarModelo() {
        dlmPersonajes = new DefaultListModel<>();
        listaPersonajes.setModel(dlmPersonajes);
    }
}
