package datos;

import java.io.Serializable;

public class Persona implements Comparable<Persona>, Serializable {

    private String nombrePersona;
    private int edadPersona;
    private String sexoPersona;

    public Persona(String nombrePersona, int edadPersona, String sexoPersona) {
        this.nombrePersona = nombrePersona;
        this.edadPersona = edadPersona;
        this.sexoPersona = sexoPersona;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public int getEdadPersona() {
        return edadPersona;
    }

    public void setEdadPersona(int edadPersona) {
        this.edadPersona = edadPersona;
    }

    public String getSexoPersona() {
        return sexoPersona;
    }

    public void setSexoPersona(String sexoPersona) {
        this.sexoPersona = sexoPersona;
    }

    @Override
    public String toString() {
        return nombrePersona + ", edad: " + edadPersona + ", sexo: " + sexoPersona;
    }

    @Override
    public int compareTo(Persona persona) {
        return nombrePersona.compareTo(persona.nombrePersona);
    }
}
