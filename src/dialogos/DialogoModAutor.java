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
 * Clase DialogoModAutor. Despliega un diálogo que nos permite modificar un autor.
 */
public class DialogoModAutor extends JDialog {
    private JPanel panel;
    private JPanel panelDatos;
    private JLabel txtNombre;
    private JLabel txtFechaNacimiento;
    private JLabel txtSexo;
    private JLabel txtPais;
    private JTextField cajaNombre;
    private JTextField cajaPais;
    private JRadioButton rbotMasculino;
    private JRadioButton rbotFemenino;
    private DatePicker datePicker;
    private JPanel panelBotones;
    private JButton botAceptar;
    private JButton botCancelar;
    private ButtonGroup grupo;

    private Modelo modelo;
    private Autor autorAModificar;
    private ResourceBundle resourceBundle;

    /**
     * Constructor de la clase. Construye el diálogo, inicia los componentes, ...
     * @param modelo Modelo El objeto modelo creado al iniciar la aplicación.
     * @param autorAModificar Autor El autor que se quiere modificar.
     */
    public DialogoModAutor(Modelo modelo, Autor autorAModificar) {
        this.modelo = modelo;
        this.autorAModificar = autorAModificar;
        setContentPane(panel);
        grupo = new ButtonGroup();

        resourceBundle = ResourceBundle.getBundle("idioma");

        iniciarComponentes();
        setValoresPorDefecto(autorAModificar);
        setVisible(true);
    }

    /**
     * Pone por defecto los valores del autor pasado por parámetro
     * @param autorAModificar El autor del cuál queremos coger los valores
     */
    private void setValoresPorDefecto(Autor autorAModificar) {
        cajaNombre.setText(autorAModificar.getNombrePersona());
        datePicker.setDate(autorAModificar.getFechaNacimiento());

        if (autorAModificar.getSexoPersona().equals("masculino")) {
            rbotMasculino.setSelected(true);
        } else {
            rbotFemenino.setSelected(true);
        }

        cajaPais.setText(autorAModificar.getPaisOrigen());
    }

    /**
     * Inicia los componentes gráficos de la aplicación.
     */
    private void iniciarComponentes() {
        setSize(600, 300);
        setModal(true);
        setLocationRelativeTo(null);
        setTitle(resourceBundle.getString("dialogoModAutor.titulo"));

        JRootPane rootPane = SwingUtilities.getRootPane(botAceptar);
        rootPane.setDefaultButton(botAceptar);

        configurarRadioButtons();
        configurarListeners();
        configurarDatePicker();
    }

    /**
     * Configura el datePicker.
     */
    private void configurarDatePicker() {
        datePicker.setDateToToday();
        JButton button = datePicker.getComponentToggleCalendarButton();
        button.setText("");
        button.setIcon(new ImageIcon(getClass().getResource("/iconos/calendar.png")));
    }

    /**
     * Crea los listeners de los botones aceptar y cancelar.
     */
    private void configurarListeners() {
        botAceptar.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) { aceptar(); }});
        botCancelar.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) { cancelar(); }});
    }

    /**
     * Añade los radiobuttons a un grupo.
     */
    private void configurarRadioButtons() {
        grupo.add(rbotMasculino);
        grupo.add(rbotFemenino);
    }

    /**
     * Modifica el autor con los valores actualmente seleccionados y cierra la ventana.
     */
    private void aceptar() {
        modValores();
        dispose();
    }

    /**
     * Modifica los valores del autor por los existentes actualmente.
     */
    private void modValores() {
        String nombre = cajaNombre.getText();
        LocalDate nacimiento = datePicker.getDate();
        String sexo = "";
        sexo = (rbotFemenino.isSelected()) ? "femenino" : "masculino";
        String pais = cajaPais.getText();

        if (!camposIntroducidosAutor()) {
            Util.mensajeError(resourceBundle.getString("error.todosLosCampos"));
        } else {
            int index = modelo.getAutores().indexOf(autorAModificar);
            modelo.getAutores().get(index).setNombrePersona(nombre);
            modelo.getAutores().get(index).setSexoPersona(sexo);
            modelo.getAutores().get(index).setFechaNacimiento(nacimiento);
            modelo.getAutores().get(index).setPaisOrigen(pais);
        }
    }

    /**
     * Cierra la ventana al pulsar cancelar
     */
    private void cancelar() {
        dispose();
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
