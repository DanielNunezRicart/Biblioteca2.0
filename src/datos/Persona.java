package datos;

import util.Util;

import java.io.Serializable;
import java.util.ResourceBundle;

public class Persona implements Comparable<Persona>, Serializable {

    private static final long serialVersionUID = 1L;

    private String nombrePersona;
    private String sexoPersona;

    public Persona(String nombrePersona, String sexoPersona) {
        this.nombrePersona = nombrePersona;
        this.sexoPersona = sexoPersona;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public String getSexoPersona() {
        return sexoPersona;
    }

    public void setSexoPersona(String sexoPersona) {
        this.sexoPersona = sexoPersona;
    }

    @Override
    public String toString() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("idioma");
        return nombrePersona + resourceBundle.getString("persona.sexo") + sexoPersona;
    }

    @Override
    public int compareTo(Persona persona) {
        return nombrePersona.compareTo(persona.nombrePersona);
    }
}
