package dialogos;

import datos.Libro;
import datos.Personaje;
import mvc.Modelo;
import util.Util;

import javax.swing.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.ResourceBundle;

public class DialogoModPersonaje extends JDialog {

    private JPanel panel;
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
    private JPanel panelBotones;
    private JButton botAceptar;
    private JButton botCancelar;

    private ButtonGroup grupoSexo;
    private ButtonGroup grupoRol;

    private Modelo modelo;
    private Personaje personajeAModificar;
    private HashSet<Libro> librosOriginalesPersonaje;
    private HashSet<Libro> nuevosLibros;

    private ResourceBundle resourceBundle;

    public DialogoModPersonaje(Modelo modelo, Personaje personaje) {
        this.modelo = modelo;
        resourceBundle = ResourceBundle.getBundle("idioma");
        personajeAModificar = personaje;
        nuevosLibros = new HashSet<>();

        setContentPane(panel);

        botSelecLibros.setMnemonic(KeyEvent.VK_P);

        iniciarComponentes();
        asignarGruposBotones();
        valoresPorDefecto(personajeAModificar);
        setVisible(true);
    }

    /**
     * Asigna los radioButtons a su grupo correspondiente
     */
    private void asignarGruposBotones() {
        grupoSexo = new ButtonGroup();
        grupoRol = new ButtonGroup();

        grupoSexo.add(rbotFemenino);
        grupoSexo.add(rbotMasculino);
        grupoRol.add(rbotProtagonista);
        grupoRol.add(rbotSecundario);
    }

    /**
     * Método que pone por defecto los valores del personaje pasado por parámetro
     * @param personaje El personaje del cual queremos coger los valores
     */
    private void valoresPorDefecto(Personaje personaje) {
        cajaNombre.setText(personaje.getNombrePersona());
        cajaEdad.setText(String.valueOf(personaje.getEdadPersonaje()));

        if (personaje.getSexoPersona().equals("masculino")) {
            rbotMasculino.setSelected(true);
        } else {
            rbotFemenino.setSelected(true);
        }

        if (personaje.getRolPersonaje().equals("protagonista")) {
            rbotProtagonista.setSelected(true);
        } else {
            rbotSecundario.setSelected(true);
        }

        librosOriginalesPersonaje = personaje.getLibrosPersonaje();
    }

    /**
     * Método que inicia los componentes gráficos de la aplicación
     */
    private void iniciarComponentes() {
        setSize(600, 300);
        setResizable(false);
        setModal(true);
        setLocationRelativeTo(null);
        setTitle(resourceBundle.getString("dialogoModPersonaje.titulo"));

        JRootPane rootPane = SwingUtilities.getRootPane(botAceptar);
        rootPane.setDefaultButton(botAceptar);

        configurarListeners();
    }

    /**
     * Crea los listeners de los botones
     */
    private void configurarListeners() {
        botAceptar.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) { aceptar(); }});
        botCancelar.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) { cancelar(); }});
        botSelecLibros.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) { seleccionarLibros(); }});
    }

    /**
     * Crea un diálogo para seleccionar los libros a los que pertenece el personaje
     */
    private void seleccionarLibros() {
        DialogoSeleccionLibros2 d = new DialogoSeleccionLibros2(modelo, this);
        txtLibrosSelect.setText(resourceBundle.getString("dialogos.librosSeleccionados"));
    }

    /**
     * Método que, pasándole un HashSet de libros los sobreescribe el el existente en esta clase
     * @param libros Los libros en los que sale el personaje
     */
    public void setLibros(HashSet<Libro> libros) {
        nuevosLibros = libros;
    }

    /**
     * Modifica el personaje y cierra la ventana al pulsar aceptar
     */
    private void aceptar() {
        modValores();
        dispose();
    }

    /**
     * Modifica los valores del Personaje
     */
    private void modValores() {
        if (!camposIntroducidosPersonaje()) {
            Util.mensajeError("error.todosLosCampos");
        } else {
            try {
                String nombre = cajaNombre.getText();
                int edad = Integer.parseInt(cajaEdad.getText());
                String sexo = obtenerSexoPersonaje();
                String rol = obtenerRolPersonaje();

                //Comprobamos que si un personaje se quita de un libro, se quite también de la lista del libro
                for (Libro libro : personajeAModificar.getLibrosPersonaje()) {
                    if (!nuevosLibros.contains(libro)) {
                        int indexL = modelo.getLibros().indexOf(libro);
                        modelo.getLibros().get(indexL).getPersonajesLibro().remove(personajeAModificar);
                    }
                }

                int index = modelo.getPersonajes().indexOf(personajeAModificar);
                modelo.getPersonajes().get(index).setNombrePersona(nombre);
                modelo.getPersonajes().get(index).setEdadPersonaje(edad);
                modelo.getPersonajes().get(index).setSexoPersona(sexo);
                modelo.getPersonajes().get(index).setRolPersonaje(rol);
                modelo.getPersonajes().get(index).setLibrosPersonaje(nuevosLibros);
            }
            catch (NumberFormatException nfe) { Util.mensajeError(resourceBundle.getString("error.numberFormatException.edad"));}
        }
    }

    /**
     * Indica el sexo seleccionado
     * @return String el sexo del personaje dependiendo del radiobutton seleccionado
     */
    private String obtenerSexoPersonaje() {
        String sexo = "";
        sexo = (rbotMasculino.isSelected()) ? "masculino" : "femenino";
        return sexo;
    }

    /**
     * Indica el rol seleccionado
     * @return String Protagonista o Secundario dependiendo del radiobutton seleccionado
     */
    private String obtenerRolPersonaje() {
        String rol = "";
        rol = (rbotProtagonista.isSelected()) ? "protagonista" : "secundario";
        return rol;
    }

    /**
     * Cierra la ventana al pulsar cancelar
     */
    private void cancelar() {
        dispose();
    }
    /**
     * Comprueba que los datos del personaje son correctos
     * @return boolean True si se ha introducido bien o false si se ha introducido mal
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
