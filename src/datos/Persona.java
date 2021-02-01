package datos;

import java.io.Serializable;
import java.util.ResourceBundle;

public class Persona implements Comparable<Persona>, Serializable {

    private String nombrePersona;
    private String sexoPersona;
    private ResourceBundle resourceBundle;

    public Persona(String nombrePersona, String sexoPersona) {
        this.nombrePersona = nombrePersona;
        this.sexoPersona = sexoPersona;
        resourceBundle = ResourceBundle.getBundle("idioma");
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
        return nombrePersona + resourceBundle.getString("persona.sexo") + sexoPersona;
    }

    @Override
    public int compareTo(Persona persona) {
        return nombrePersona.compareTo(persona.nombrePersona);
    }
}
