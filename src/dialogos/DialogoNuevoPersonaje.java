package dialogos;

import datos.Libro;
import datos.Personaje;
import mvc.Modelo;
import util.Util;

import javax.swing.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.ResourceBundle;

/**
 * Crea la clase DialogoNuevoPersonaje. Crea un diálogo que nos permite añadir un personaje nuevo.
 */
public class DialogoNuevoPersonaje extends JDialog {

    private JPanel panel;
    private JPanel panelBotones;
    private JButton botAceptar;
    private JButton botCancelar;
    private JPanel panelDatos;
    private JLabel txtNombre;
    private JTextField cajaNombre;
    private JLabel txtFechaNacimiento;
    private JLabel txtSexo;
    private JRadioButton rbotMasculino;
    private JRadioButton rbotFemenino;
    private JLabel txtRol;
    private JRadioButton rbotProtagonista;
    private JRadioButton rbotSecundario;
    private JButton botSelecLibros;
    private JTextField cajaEdad;
    private JLabel txtLibrosSelect;
    private ButtonGroup grupoSexo;
    private ButtonGroup grupoRol;
    private HashSet<Libro> libros;

    private Modelo modelo;
    private ResourceBundle resourceBundle;

    /**
     * Constructor de la clase. Crea el diálogo, inicia los componentes gráficos, ...
     * @param modelo Modelo El objeto Modelo creado al iniciar la aplicación.
     */
    public DialogoNuevoPersonaje(Modelo modelo) {
        this.modelo = modelo;
        resourceBundle = ResourceBundle.getBundle("idioma");
        setContentPane(panel);

        grupoSexo = new ButtonGroup();
        grupoRol = new ButtonGroup();

        iniciarComponentes();
        setVisible(true);
    }

    /**
     * Inicia los componentes del diálogo.
     */
    private void iniciarComponentes() {
        setUndecorated(false);
        setSize(600, 300);
        setModal(true);
        setLocationRelativeTo(null);
        setTitle(resourceBundle.getString("dialogoNuevoPersonaje.titulo"));

        JRootPane rootPane = SwingUtilities.getRootPane(botAceptar);
        rootPane.setDefaultButton(botAceptar);

        botSelecLibros.setMnemonic(KeyEvent.VK_P);

        asignarGruposBotones();
        crearListeners();
    }

    /**
     * Crea los listeners necesarios para el correcto funcionamiento del diálogo.
     */
    private void crearListeners() {
        //Listeners de los botones aceptar y cancelar
        botAceptar.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) { aceptar(); }});
        botCancelar.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) { cancelar(); }});
        botSelecLibros.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) { seleccionarLibros(); }});
    }

    /**
     * Método que despliega un diálogo que permite seleccionar los libros en los que sale el personaje.
     */
    private void seleccionarLibros(){ DialogoSeleccionLibros d = new DialogoSeleccionLibros(modelo, this); }

    /**
     * Asigna los radiobuttons a sus grupos correspondientes y selecciona por defecto el de Masculino y el de Protagonista.
     */
    private void asignarGruposBotones() {
        grupoSexo.add(rbotFemenino);
        grupoSexo.add(rbotMasculino);
        rbotMasculino.setSelected(true);
        grupoRol.add(rbotProtagonista);
        grupoRol.add(rbotSecundario);
        rbotProtagonista.setSelected(true);
    }

    /**
     * Método que, paándole un HashSet de libros los sobreescribe el el existente en esta clase.
     * @param libros Los libros en los que sale el personaje.
     */
    public void setLibros(HashSet<Libro> libros) {
        if (!libros.isEmpty()) {
            this.libros = libros;
            txtLibrosSelect.setText(resourceBundle.getString("dialogos.librosSeleccionados"));
        }
    }

    /**
     * Al aceptar se crea el nuevo personaje y se cierra la ventana.
     */
    private void aceptar() {
        nuevoPersonaje();
        dispose();
    }

    private void nuevoPersonaje() {
        if (!camposIntroducidosPersonaje()) {
            Util.mensajeError(resourceBundle.getString("error.todosLosCampos"));
        } else {
            try{
                String nombre = cajaNombre.getText();
                int edad = Integer.parseInt(cajaEdad.getText());
                String sexo = obtenerSexoPersonaje();
                String rol = obtenerRolPersonaje();

                Personaje personaje = new Personaje(nombre, sexo, edad, rol, libros);
                modelo.nuevoPersonaje(personaje);
            }
            catch (NumberFormatException nfe) { Util.mensajeError(resourceBundle.getString("error.numberFormatException.edad"));}
        }
    }

    /**
     * Indica el sexo seleccionado.
     * @return String el sexo del personaje dependiendo del radiobutton seleccionado.
     */
    private String obtenerSexoPersonaje() {
        String sexo = "";
        if (rbotMasculino.isSelected()) {
            sexo = "masculino";
        } else {
            sexo = "femenino";
        }
        return sexo;
    }

    /**
     * Indica el rol seleccionado.
     * @return String Protagonista o Secundario dependiendo del radiobutton seleccionado.
     */
    private String obtenerRolPersonaje() {
        String rol = "";
        if (rbotProtagonista.isSelected()) {
            rol = "protagonista";
        } else {
            rol = "secundario";
        }
        return rol;
    }

    /**
     * Al cancelar se cierra la ventana.
     */
    private void cancelar() {
        dispose();
    }

    /**
     * Comprueba que los datos del personaje son correctos.
     * @return boolean True si se ha introducido bien o false si se ha introducido mal.
     */
    public boolean camposIntroducidosPersonaje() {
        boolean flag = true;
        if (cajaNombre.getText().isEmpty()
                || cajaEdad.getText().isEmpty()
                || (!rbotMasculino.isSelected() && !rbotFemenino.isSelected())
                || (!rbotProtagonista.isSelected() && !rbotSecundario.isSelected())
                || txtLibrosSelect.getText().equals("")) {
            flag = false;
        }
        return flag;
    }
}
