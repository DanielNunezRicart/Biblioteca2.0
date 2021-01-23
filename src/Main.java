import mvc.Modelo;
import mvc.gui.Controlador;
import mvc.gui.Vista;

import javax.naming.ldap.Control;

public class Main {

    public static void main(String[] args) {
        Vista vista = new Vista();
        Modelo modelo = new Modelo();
        Controlador controlador = new Controlador(vista, modelo);
    }
}
