package dialogos;

import javax.swing.*;
import java.awt.event.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;

import util.Util;

/**
 * Clase DialogoIdioma. Despliega un diálogo que nos permite elegir el idioma de la aplicación.
 */
public class DialogoIdioma extends JDialog {

    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JRadioButton rbotEspanol;
    private JRadioButton rbotIngles;
    private ButtonGroup grupo;
    private ResourceBundle resourceBundle;

    /**
     * Constructor del Diálogo. Carga el ResourceBundle, inicia los componentes y demás.
     */
    public DialogoIdioma() {
        resourceBundle = ResourceBundle.getBundle("idioma");

        setContentPane(contentPane);
        setModal(true);
        setLocationRelativeTo(null);
        setTitle(resourceBundle.getString("dialogoIdioma.titulo"));
        setSize(400, 200);

        grupo = new ButtonGroup();
        grupo.add(rbotEspanol);
        grupo.add(rbotIngles);

        cargarConfiguracion();

        getRootPane().setDefaultButton(buttonOK);

        listeners();
        setVisible(true);
    }

    /**
     * Selecciona el radioButton correspondiente al idioma guardado en el archivo de configuración.
     */
    private void cargarConfiguracion() {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("data/preferencias.conf"));

            String pais = properties.getProperty("pais");

            if(pais.equals("ES")){
                rbotEspanol.setSelected(true);
            }else {
                rbotIngles.setSelected(true);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Añade los listeners a los botones de OK y Cancelar.
     */
    private void listeners() {
        buttonOK.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) { onOK(); }});
        buttonCancel.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) { onCancel(); }});
    }

    /**
     * Método que se ejecuta al pulsar el botón de OK. Muestra un JDialog que nos pide confirmar y cierra el programa.
     */
    private void onOK() {
        int opt = Util.mensajeConfirmacion(resourceBundle.getString("dialogoIdioma.reinicio"));
        if(opt == JOptionPane.YES_OPTION){
            setIdioma();
            System.exit(2);
        }
        dispose();
    }

    /**
     * Cambia el idioma dependiendo del seleccionado
     */
    private void setIdioma() {
        Properties propiedades = new Properties();
        String idioma;
        String pais;
        if(rbotEspanol.isSelected()){
            idioma = "es";
            pais = "ES";
        } else {
            idioma = "en";
            pais = "UK";
        }
        propiedades.setProperty("idioma", idioma);
        propiedades.setProperty("pais", pais);
        try {
            propiedades.store(new FileWriter("data/preferencias.conf"), "Fichero de preferencias");
        }

        catch (IOException e) { e.printStackTrace(); }
    }

    /**
     * Método que se ejecuta al pulsar el botón de cancelar. Cierra la ventana.
     */
    private void onCancel() {
        dispose();
    }
}
