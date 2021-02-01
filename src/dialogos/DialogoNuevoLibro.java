package dialogos;

import com.github.lgooddatepicker.components.DatePicker;
import datos.Autor;
import datos.Libro;
import mvc.Modelo;
import util.Util;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

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

    /**
     * Constructor del diálogo
     * @param modelo Objeto Modelo que guarda todos los datos manejados
     */
    public DialogoNuevoLibro(Modelo modelo) {
        this.modelo = modelo;
        setContentPane(panel);

        iniciarComponentes();
        setVisible(true);
    }

    /**
     * Método que inicia los componentes gráficos de la aplicación
     */
    private void iniciarComponentes() {

        //Características del JDialog
        setSize(600, 300);
        setResizable(false);
        setModal(true);
        setLocationRelativeTo(null);
        setTitle("Añadir libro");
        configComboBox();

        //Listeners de los botones aceptar y cancelar
        botAceptar.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) { aceptar(); }});
        botCancelar.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) { cancelar(); }});

        //Listener botPortada
        botPortada.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                int opcion = fc.showOpenDialog(null);
                if (opcion == JFileChooser.APPROVE_OPTION) {
                    txtRutaImagen.setText(fc.getSelectedFile().getAbsolutePath());
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
     * Método que se ejecuta al pulsar el botón aceptar
     */
    public void aceptar() {
        addLibro();
        dispose();
    }

    /**
     * Método que recoge los elementos seleccionados para crear un libro
     */
    private void addLibro() {
        String nombre = cajaNombre.getText();
        Autor autor = (Autor) cboxAutores.getSelectedItem();
        LocalDate fecha = datePicker.getDate();

        //Con esto hacemos que si el float no es un float, cambie a false
        try {
            Float precio = Float.parseFloat(cajaPrecio.getText());
            ImageIcon imagen = new ImageIcon(txtRutaImagen.getText());
            portada = Util.escalarPortadaLibro(imagen);

            Libro libro = new Libro(nombre, autor, fecha, precio, portada);
            if (!camposIntroducidosLibro()) {
                Util.mensajeError("No se han introducido todos los campos");
            } else {
                modelo.nuevoLibro(libro);
            }
        }
        catch (NumberFormatException nfe) { Util.mensajeError("El precio debe ser un número");}
    }

    /**
     * Comprueba que los datos del libro son correctos
     * @return boolean True si se ha introducido bien o false si se ha introducido mal
     */
    private boolean camposIntroducidosLibro() {
        boolean flag = true;
        if (cajaNombre.getText().isEmpty()
                || datePicker.getText().isEmpty()
                || cboxAutores.getSelectedItem() == null
                || cajaPrecio.getText().isEmpty()
                || txtRutaImagen.getText().equals("")) {
            flag = false;
        }

        return flag;
    }

    /**
     * Método que se ejecuta al pulsar el botón cancelar
     */
    public void cancelar() {
        dispose();
    }

}
