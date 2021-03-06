package dialogos;

import com.github.lgooddatepicker.components.DatePicker;
import datos.Autor;
import mvc.Modelo;
import util.Util;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * Clase DialogoNuevoAutor. Crea un diálogo que nos permite crear un autor nuevo.
 */
public class DialogoNuevoAutor extends JDialog {

    private JPanel panel;
    private JPanel panelDatos;
    private JPanel panelBotones;
    private JButton botAceptar;
    private JButton botCancelar;
    private JLabel txtNombre;
    private JLabel txtFechaNacimiento;
    private JTextField cajaNombre;
    private JTextField cajaPais;
    private JRadioButton rbotMasculino;
    private JRadioButton rbotFemenino;
    private JLabel txtPais;
    private DatePicker datePicker;
    private JLabel txtSexo;
    private ButtonGroup grupo;
    private Modelo modelo;

    private ResourceBundle resourceBundle;

    /**
     * Construye un objeto DialogoNuevoAutor.
     * @param modelo Objeto de la clase Modelo en el que se guardan los autores, libros y personajes.
     */
    public DialogoNuevoAutor(Modelo modelo) {
        this.modelo = modelo;
        resourceBundle = ResourceBundle.getBundle("idioma");
        setContentPane(panel);
        grupo = new ButtonGroup();

        iniciarComponentes();
        setVisible(true);
    }

    /**
     * Inicia los componentes de la aplicación.
     */
    private void iniciarComponentes() {
        //Características del diálogo
        setSize(600, 300);
        setModal(true);
        setLocationRelativeTo(null);
        setTitle(resourceBundle.getString("dialogoNuevoAutor.titulo"));

        configurarRadioButtons();
        configurarListeners();

        JRootPane rootPane = SwingUtilities.getRootPane(botAceptar);
        rootPane.setDefaultButton(botAceptar);

        //Cambiamos un poco el DatePicker
        datePicker.setDateToToday();
        JButton button = datePicker.getComponentToggleCalendarButton();
        button.setText("");
        button.setIcon(new ImageIcon(getClass().getResource("/iconos/calendar.png")));
    }

    /**
     * Añade los radiobuttons a un grupo y selecciona por defecto el de Masculino.
     */
    private void configurarRadioButtons() {
        grupo.add(rbotMasculino);
        grupo.add(rbotFemenino);
        rbotMasculino.setSelected(true);
    }

    /**
     * Crea los listeners de los botones aceptar y cancelar.
     */
    private void configurarListeners() {
        botAceptar.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) { aceptar(); }});
        botCancelar.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) { cancelar(); }});
    }

    /**
     * Método que llama a otro para que añada el autor y cierra la ventana al pulsar aceptar.
     */
    public void aceptar() {
        addAutor();
        dispose();
    }

    /**
     * Método que cierra la ventana al pulsar el botón cancelar.
     */
    public void cancelar() {
        dispose();
    }

    /**
     * Método que añade un autor nuevo.
     */
    private void addAutor() {
        if (!camposIntroducidosAutor()) {
            Util.mensajeError(resourceBundle.getString("error.todosLosCampos"));
        } else {
            String nombre = cajaNombre.getText();
            LocalDate fechaNac = datePicker.getDate();
            String sexo = obtenerSexoPersona();
            String pais = cajaPais.getText();

            Autor autor = new Autor(nombre, fechaNac, sexo, pais);
            modelo.nuevoAutor(autor);
        }
    }

    /**
     * Obtiene el sexo del autor a partir de los radiobuttons Masculino y Femenino.
     * @return String Masculino o Femenino dependiendo del radiobutton seleccionado.
     */
    private String obtenerSexoPersona() {
        String devolver = "";
        if (rbotFemenino.isSelected()) {
            devolver = "femenino";
        } else {
            devolver = "masculino";
        }
        return devolver;
    }

    /**
     * Comprueba que los datos del autor son correctos.
     * @return boolean True si se ha introducido bien o false si se ha introducido mal.
     */
    public boolean camposIntroducidosAutor() {
        boolean flag = true;
        if (cajaNombre.getText().isEmpty()
                || datePicker.getText().isEmpty()
                || cajaPais.getText().isEmpty()
                || (!rbotMasculino.isSelected() && !rbotFemenino.isSelected())) {
            flag = false;
        }
        return flag;
    }
}
