package dialogos;

import com.github.lgooddatepicker.components.DatePicker;
import datos.Autor;
import mvc.Modelo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Locale;

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
    private ButtonGroup grupo;
    private Modelo modelo;

    public DialogoNuevoAutor(Modelo modelo) {
        this.modelo = modelo;
        setContentPane(panel);
        grupo = new ButtonGroup();

        iniciarComponentes();
        setVisible(true);
    }

    public void iniciarComponentes() {
        //Añadimos los radioButtons al grupo
        grupo.add(rbotMasculino);
        grupo.add(rbotFemenino);

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

    public void aceptar() {
        addAutor();
        dispose();
    }

    public void cancelar() {
        dispose();
    }

    private void addAutor() {
        String nombre = cajaNombre.getText();
        int edad = calcularEdad(datePicker.getDate());
        String sexo = obtenerSexoPersona();
        String pais = cajaPais.getText();

        Autor autor = new Autor(nombre, edad, sexo, pais);
        modelo.nuevoAutor(autor);
    }

    private String obtenerSexoPersona() {
        String devolver = "";
        if (rbotFemenino.isSelected()) {
            devolver = "Femenino";
        } else
            devolver = "Masculino";
        return devolver;
    }

    private int calcularEdad(LocalDate nacimiento) {
        LocalDate fechaActual = LocalDate.now();
        Period intervalo = Period.between(nacimiento, fechaActual);
        return intervalo.getYears();
    }

}
