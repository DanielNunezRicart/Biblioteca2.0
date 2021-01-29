package dialogos;

import com.github.lgooddatepicker.components.DatePicker;
import util.Util;
import datos.Autor;
import mvc.Modelo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    /**
     * Construye un objeto DialogoNuevoAutor
     * @param modelo Objeto de la clase Modelo en el que se guardan los autores, libros y personajes
     */
    public DialogoNuevoAutor(Modelo modelo) {
        this.modelo = modelo;
        setContentPane(panel);
        grupo = new ButtonGroup();

        iniciarComponentes();
        setVisible(true);
    }

    /**
     * Inicia los componentes de la aplicación
     */
    public void iniciarComponentes() {
        configurarRadioButtons();

        //Características del diálogo
        setSize(600, 300);
        setResizable(false);
        setModal(true);
        setLocationRelativeTo(null);
        setTitle("Añadir autor");

        //Listeners de los botones aceptar y cancelar
        botAceptar.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) { aceptar(); }});
        botCancelar.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) { cancelar(); }});

        //Cambiamos un poco el DatePicker
        datePicker.setDateToToday();
        JButton button = datePicker.getComponentToggleCalendarButton();
        button.setText("");
        button.setIcon(new ImageIcon(getClass().getResource("/iconos/calendar.png")));
    }

    /**
     * Añade los radiobuttons a un grupo y selecciona por defecto el de Masculino
     */
    private void configurarRadioButtons() {
        grupo.add(rbotMasculino);
        grupo.add(rbotFemenino);
        rbotMasculino.setSelected(true);
    }

    /**
     * Método que llama a otro para que añada el autor y cierra la ventana al pulsar acepptar
     */
    public void aceptar() {
        addAutor();
        dispose();
    }

    /**
     * Método que cierra la ventana al pulsar el botón cancelar
     */
    public void cancelar() {
        dispose();
    }

    /**
     * Método que añade un autor nuevo
     */
    private void addAutor() {
        String nombre = cajaNombre.getText();
        int edad = Util.calcularEdad(datePicker.getDate());
        String sexo = obtenerSexoPersona();
        String pais = cajaPais.getText();

        Autor autor = new Autor(nombre, edad, sexo, pais);
        modelo.nuevoAutor(autor);
    }

    /**
     * Obtiene el sexo del autor a partir de los radiobuttons Masculino y Femenino
     * @return String Masculino o Femenino dependiendo del radiobutton seleccionado
     */
    private String obtenerSexoPersona() {
        String devolver = "";
        if (rbotFemenino.isSelected()) {
            devolver = "Femenino";
        } else {
            devolver = "Masculino";
        }
        return devolver;
    }



}
