package datos;

public class Personaje extends Persona{

    private String rolPersonaje;
    private Libro libroPersonaje;

    public Personaje(String nombrePersona, int edadPersona, String sexoPersona, String rolPersonaje, Libro libroPersonaje) {
        super(nombrePersona, edadPersona, sexoPersona);
        this.rolPersonaje = rolPersonaje;
        this.libroPersonaje = libroPersonaje;
    }

    public String getRolPersonaje() {
        return rolPersonaje;
    }

    public void setRolPersonaje(String rolPersonaje) {
        this.rolPersonaje = rolPersonaje;
    }

    public Libro getLibroPersonaje() {
        return libroPersonaje;
    }

    public void setLibroPersonaje(Libro libroPersonaje) {
        this.libroPersonaje = libroPersonaje;
    }

    @Override
    public String toString() {
        return super.toString() + ", rol: " + rolPersonaje + "libro: " + libroPersonaje.getNombreLibro();
    }
}
