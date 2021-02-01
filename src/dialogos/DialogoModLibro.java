package dialogos;

import com.github.lgooddatepicker.components.DatePicker;
import datos.Autor;
import datos.Libro;
import datos.Personaje;
import mvc.Modelo;

import javax.swing.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.HashSet;

public class DialogoModLibro extends JDialog {

    private JPanel panel;
    private JPanel panelDatos;
    private JLabel txtNombre;
    private JLabel txtPrecio;
    private JTextField cajaNombre;
    private JTextField cajaPrecio;
    private JLabel txtAutor;
    private JComboBox cboxAutores;
    private JLabel txtFechaPublicacion;
    private DatePicker datePicker;
    private JButton botPortada;
    private JLabel txtRutaImagen;
    private JPanel panelBotones;
    private JButton botAceptar;
    private JButton botCancelar;
    private JButton botPersonajes;
    private JLabel txtPersonajes;

    private Libro libroAModificar;
    private DefaultComboBoxModel<Autor> cboxModel;
    private Modelo modelo;
    private HashSet<Personaje> personajesOriginalesLibro;
    private HashSet<Personaje> nuevosPersonajes;

    public DialogoModLibro(Modelo modelo, Libro libro) {
        this.modelo = modelo;
        nuevosPersonajes = new HashSet<>();

        libroAModificar = libro;

        setContentPane(panel);

        iniciarComponentes();
        valoresPorDefecto(libro);
        setVisible(true);
    }

    /**
     * Método que inicia los componentes gráficos de la aplicación
     */
    private void iniciarComponentes() {
        setSize(600, 400);
        setResizable(false);
        setModal(true);
        setLocationRelativeTo(null);
        setTitle("Modificar libro");

        configComboBox();
        configurarListeners();
        configurarDatePicker();
    }

    /**
     * Método que pone por defecto los valores del libro pasado por parámetro
     * @param libro El libro del cual queremos coger los valores
     */
    private void valoresPorDefecto(Libro libro) {
        txtRutaImagen.setText(libro.getPortada().toString());
        cajaNombre.setText(libro.getNombreLibro());
        cboxAutores.setSelectedItem(libro.getAutorLibro());
        cajaPrecio.setText(String.valueOf(libro.getPrecioLibro()));
        personajesOriginalesLibro = libro.getPersonajesLibro();
    }

    /**
     * Devuelve los personajes actuales del libro
     * @return HashSet<Personaje> Los personajes actuales del libro
     */
    public HashSet<Personaje> getPersonajes(){
        if (!nuevosPersonajes.isEmpty()) {
            return nuevosPersonajes;
        } else {
            return personajesOriginalesLibro;
        }
    }

    /**
     * Método que asigna el modelo al combobox de autores y además lo rellena con los autores existentes
     */
    private void configComboBox() {
        cboxModel = new DefaultComboBoxModel<>();
        cboxAutores.setModel(cboxModel);
        cboxModel.addElement(null);
        for (Autor a : modelo.getAutores()) {
            cboxModel.addElement(a);
        }
    }

    /**
     * Crea los listeners de los botones
     */
    private void configurarListeners() {
        botAceptar.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) { aceptar(); }});
        botCancelar.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) { cancelar(); }});

        botPortada.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                int opcion = fc.showOpenDialog(null);
                if (opcion == JFileChooser.APPROVE_OPTION) {
                    txtRutaImagen.setText(fc.getSelectedFile().getAbsolutePath());
                }
            }
        });

        botPersonajes.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) { seleccionarPersonajes(); }});
    }

    /**
     * Crea un nuevo diálogo para seleccionar personajes
     */
    private void seleccionarPersonajes() {
        DialogoSeleccionPersonajes d = new DialogoSeleccionPersonajes(modelo, this);
        txtPersonajes.setText("Personajes modificados");
    }

    /**
     * Cambiamos el aspecto del datePicker
     */
    private void configurarDatePicker() {
        JButton button = datePicker.getComponentToggleCalendarButton();
        button.setText("");
        button.setIcon(new ImageIcon(getClass().getResource("/iconos/calendar.png")));
    }

    /**
     * Cambia la lista de personajes actual por la pasada por parámetro
     * @param pjs La nueva lista de personajes
     */
    public void setPersonajes(HashSet<Personaje> pjs) {
        nuevosPersonajes = pjs;
    }

    /**
     * Modifica los valores y cierra la ventana al pulsar aceptar
     */
    private void aceptar() {
        modValores();
        dispose();
    }

    /**
     * Modifica los valores del libro en cuestión
     */
    private void modValores() {
        ImageIcon portada = new ImageIcon(txtRutaImagen.getText());
        String nombre = cajaNombre.getText();
        Autor autor = (Autor) cboxAutores.getSelectedItem();
        LocalDate fecha = datePicker.getDate();
        Float precio = Float.parseFloat(cajaPrecio.getText());

        int index = modelo.getLibros().indexOf(libroAModificar);

        //Si alguno de los personajes se queda sin libro, lo eliminamos
        for (Personaje p : modelo.getLibros().get(index).getPersonajesLibro()) {
            if (!nuevosPersonajes.contains(p)) {
                int indexP = modelo.getPersonajes().indexOf(p);
                modelo.getPersonajes().get(indexP).getLibrosPersonaje().remove(libroAModificar);
                if (modelo.getPersonajes().get(indexP).getLibrosPersonaje().isEmpty()) {
                    modelo.getPersonajes().remove(p);
                }
            }
        }

        modelo.getLibros().get(index).setPortada(portada);
        modelo.getLibros().get(index).setNombreLibro(nombre);
        modelo.getLibros().get(index).setAutorLibro(autor);
        modelo.getLibros().get(index).setFechaPublicacion(fecha);
        modelo.getLibros().get(index).setPrecioLibro(precio);
        modelo.getLibros().get(index).setPersonajesLibro(nuevosPersonajes);
    }

    /**
     * Cierra la ventana al pulsar cancelar
     */
    private void cancelar() {
        dispose();
    }
}