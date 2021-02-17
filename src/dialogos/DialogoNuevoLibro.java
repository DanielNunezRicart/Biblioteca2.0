package dialogos;

import com.github.lgooddatepicker.components.DatePicker;
import datos.Autor;
import datos.Libro;
import mvc.Modelo;
import util.Util;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * Clase DialogoNuevoLibro. Crea un diálogo que nos permite añadir un libro nuevo.
 */
public class DialogoNuevoLibro extends JDialog{

    private JPanel panel;
    private JPanel panelDatos;
    private JLabel txtNombre;
    private JLabel txtPrecio;
    private JTextField cajaNombre;
    private JTextField cajaPrecio;
    private JPanel panelBotones;
    private JButton botAceptar;
    private JButton botCancelar;
    private JLabel txtAutor;
    private JComboBox cboxAutores;
    private JLabel txtFechaPublicacion;
    private DatePicker datePicker;
    private JButton botPortada;
    private JLabel txtRutaImagen;

    private DefaultComboBoxModel<Autor> cboxModel;

    private Modelo modelo;
    private ImageIcon portada;
    private ResourceBundle resourceBundle;

    /**
     * Constructor del diálogo.
     * @param modelo Objeto Modelo que guarda todos los datos manejados.
     */
    public DialogoNuevoLibro(Modelo modelo) {
        this.modelo = modelo;
        resourceBundle = ResourceBundle.getBundle("idioma");
        setContentPane(panel);

        iniciarComponentes();
        setVisible(true);
    }

    /**
     * Método que inicia los componentes gráficos de la aplicación.
     */
    private void iniciarComponentes() {

        //Características del JDialog
        setSize(600, 400);
        setModal(true);
        setLocationRelativeTo(null);
        setTitle(resourceBundle.getString("dialogoNuevoLibro.titulo"));
        configComboBox();

        JRootPane rootPane = SwingUtilities.getRootPane(botAceptar);
        rootPane.setDefaultButton(botAceptar);

        botPortada.setMnemonic(KeyEvent.VK_P);

        //Listeners de los botones aceptar y cancelar
        botAceptar.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) { aceptar(); }});
        botCancelar.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) { cancelar(); }});

        //Listener botPortada
        botPortada.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                int opcion = fc.showOpenDialog(null);
                if (opcion == JFileChooser.APPROVE_OPTION) {
                    ImageIcon imagen = new ImageIcon(fc.getSelectedFile().getAbsolutePath());
                    portada = Util.escalarPortadaLibro(imagen);
                    txtRutaImagen.setIcon(portada);
                }
            }
        });

        //Cambiamos un poco el DatePicker
        datePicker.setDateToToday();
        JButton button = datePicker.getComponentToggleCalendarButton();
        button.setText("");
        button.setIcon(new ImageIcon(getClass().getResource("/iconos/calendar.png")));
    }

    /**
     * Método que asigna el modelo al combobox de autores y además lo rellena con los autores existentes.
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
     * Método que se ejecuta al pulsar el botón aceptar.
     */
    public void aceptar() {
        addLibro();
        dispose();
    }

    /**
     * Método que recoge los elementos seleccionados para crear un libro.
     */
    private void addLibro() {
        String nombre = cajaNombre.getText();
        Autor autor = (Autor) cboxAutores.getSelectedItem();
        LocalDate fecha = datePicker.getDate();

        //Con esto hacemos que si el float no es un float, cambie a false
        try {
            Float precio = Float.parseFloat(cajaPrecio.getText());

            Libro libro = new Libro(nombre, autor, fecha, precio, portada);
            if (!camposIntroducidosLibro()) {
                Util.mensajeError(resourceBundle.getString("error.todosLosCampos"));
            } else {
                modelo.nuevoLibro(libro);
            }
        }
        catch (NumberFormatException nfe) { Util.mensajeError(resourceBundle.getString("error.numberFormatException.precio"));}
    }

    /**
     * Comprueba que los datos del libro son correctos.
     * @return boolean True si se ha introducido bien o false si se ha introducido mal.
     */
    private boolean camposIntroducidosLibro() {
        boolean flag = true;
        if (cajaNombre.getText().isEmpty()
                || datePicker.getText().isEmpty()
                || cboxAutores.getSelectedItem() == null
                || cajaPrecio.getText().isEmpty()
                || txtRutaImagen.getIcon() == null) {
            flag = false;
        }

        return flag;
    }

    /**
     * Método que se ejecuta al pulsar el botón cancelar. Cierra la ventana (el diálogo).
     */
    public void cancelar() {
        dispose();
    }

}
