package dialogos;

import datos.Autor;
import datos.Libro;
import mvc.Modelo;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.swing.JRViewer;
import util.Util;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Clase DialogoInformes. Se encarga de mostrar los dos informes disponibles en la aplicación.
 */
public class DialogoInformes extends JDialog {

    private JPanel panel;
    private JPanel panelBotones;
    private JPanel panelInformes;
    private JButton botCambiarInforme;

    private Modelo modelo;
    private boolean informeLibrosVisible;

    private ResourceBundle resourceBundle;

    public DialogoInformes(Modelo modelo) {
        this.modelo = modelo;
        resourceBundle = ResourceBundle.getBundle("idioma");
        informeLibrosVisible = true;

        setContentPane(panel);

        initComponentes();
        listenerBoton();
        informeLibros();

        setVisible(true);
    }

    /**
     * Listener del botón de cambiar informe. Se activa al pulsar dicho botón.
     */
    private void listenerBoton() {
        botCambiarInforme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarInforme();
            }
        });
    }

    /**
     * Cambia el informe mostrado por el otro
     */
    private void cambiarInforme() {
        if (informeLibrosVisible) {
            informeAutores();
        } else {
            informeLibros();
        }
    }

    /**
     * Modifica la forma del diálogo
     */
    private void initComponentes() {
        setLocationRelativeTo(null);
        setSize(1000, 1050);
        setModal(true);
        setTitle(resourceBundle.getString("dialogoInformes.titulo"));
    }

    /**
     * Crea el informe de los libros y lo muestra
     */
    private void informeLibros() {
        panelInformes.removeAll();
        informeLibrosVisible = true;
        try {
            JasperReport report = (JasperReport) JRLoader.loadObject(getClass().getResource("/InformeLibros.jasper"));
            //Comprobamos que no hay ningún valor nulo
            ArrayList<Libro> libros = new ArrayList<>();
            for (Libro l : modelo.getLibros()) {
                if (l != null) {
                    libros.add(l);
                }
            }
            JRBeanCollectionDataSource datos = new JRBeanCollectionDataSource(libros);
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, null, datos);
            panelInformes.add(new JRViewer(jasperPrint));
            panelInformes.validate();
            panelInformes.repaint();
        }

        catch (JRException e) { Util.mensajeError("Ha habido un problema con el informe."); }
    }

    /**
     * Crea el informe de los autores y lo muestra
     */
    private void informeAutores() {
        panelInformes.removeAll();
        informeLibrosVisible = false;
        try {
            JasperReport report = (JasperReport) JRLoader.loadObject(getClass().getResource("/InformeAutores.jasper"));
            //Comprobamos que no hay ningún valor nulo
            ArrayList<Autor> autores = new ArrayList<>();
            for (Autor a : modelo.getAutores()) {
                if (a != null) {
                    autores.add(a);
                }
            }
            JRBeanCollectionDataSource datos = new JRBeanCollectionDataSource(autores);
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, null, datos);
            panelInformes.add(new JRViewer(jasperPrint));
            panelInformes.validate();
            panelInformes.repaint();
        }

        catch (JRException e) { Util.mensajeError("Ha habido un problema con el informe."); }
    }
}
