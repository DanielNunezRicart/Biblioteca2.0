package dialogos;

import datos.Autor;
import datos.Libro;
import datos.Personaje;
import mvc.Modelo;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import util.Util;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DialogoGraficos extends JDialog {

    private JPanel panel;
    private JTabbedPane panelGraficas;
    private JPanel panelLibros;
    private JPanel panelPersonajes;

    private ResourceBundle resourceBundle;

    private Modelo modelo;

    /**
     * Constructor de la clase DialogoGraficos. Construye el diálogo, inicia los componentes y crea los gráficos.
     * @param modelo
     */
    public DialogoGraficos(Modelo modelo) {
        this.modelo = modelo;
        resourceBundle = ResourceBundle.getBundle("idioma");

        setContentPane(panel);

        iniciarComponentes();
        crearGraficos();

        setVisible(true);
    }

    /**
     * Crea los dos gráficos
     */
    private void crearGraficos() {
        graficoLibrosPorAutor();
        graficoPersonajesPorAutor();
    }

    /**
     * Carga el gráfico que muestra el número de personajes por cada autor, y dentro de eso, hace distinción de sexo. Si no
     * hay valores que mostrar, mostrará un gráfico vacío.
     */
    private void graficoPersonajesPorAutor() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        if (!modelo.getAutores().isEmpty() || !modelo.getLibros().isEmpty() || !modelo.getPersonajes().isEmpty()) {
            int pFemeninos = 0;
            int pMasculinos = 0;
            String nombre = "";

            for (Autor a : modelo.getAutores()) {
                nombre = a.getNombrePersona();

                ArrayList<Personaje> pContados = new ArrayList<Personaje>();
                for (Libro l : a.getLibrosPublicados()) {
                    for (Personaje p : l.getPersonajesLibro()) {
                        if (!pContados.contains(p)) {
                            pContados.add(p);
                            if (p.getSexoPersona().equals("masculino")) {
                                pMasculinos++;
                            } else {
                                pFemeninos++;
                            }
                        }
                    }
                }

                dataset.setValue(pFemeninos, resourceBundle.getString("dialogoGraficas.Femeninos"), nombre);
                dataset.setValue(pMasculinos, resourceBundle.getString("dialogoGraficas.Masculinos"), nombre);

                pFemeninos = 0;
                pMasculinos = 0;
            }
        } else {
            dataset.setValue(0, "", "");
        }

        JFreeChart grafica = ChartFactory.createStackedBarChart( resourceBundle.getString("dialogoGraficas.personajesPorAutor"),
                resourceBundle.getString("principal.pestaAutores.titulo"), resourceBundle.getString("principal.pestaPersonajes.titulo"),
                dataset, PlotOrientation.VERTICAL, true, false, false);
        ChartPanel chart = new ChartPanel(grafica);
        panelPersonajes.add(chart);
    }

    /**
     * Carga el gráfico que muestra la cantidad de libros por autor. Si no hay valores que mostrar, mostrará un gráfico vacío.
     */
    private void graficoLibrosPorAutor() {
        DefaultPieDataset dataset = new DefaultPieDataset();

        if (!modelo.getLibros().isEmpty()) {
            for (Autor autor : modelo.getAutores()) {
                int nLibros = autor.getLibrosPublicados().size();
                dataset.setValue(autor.getNombrePersona(), Double.valueOf(nLibros));
            }
        } else {
            dataset.setValue("", 0);
        }

        JFreeChart diagrama = ChartFactory.createPieChart(resourceBundle.getString("grafico.titulo"), dataset,
                true, true, false);
        ChartPanel chart = new ChartPanel(diagrama);
        panelLibros.add(chart);
    }

    /**
     * Inicia los componentes gráficos del diálogo
     */
    private void iniciarComponentes() {
        setSize(800, 850);
        setTitle(resourceBundle.getString("dialogoGraficas.titulo"));
        setModal(true);
        setLocationRelativeTo(null);
    }
}
