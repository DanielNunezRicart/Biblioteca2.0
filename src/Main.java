import mvc.Modelo;
import mvc.gui.Controlador;
import mvc.gui.Vista;
import util.Util;

import java.util.Locale;

public class Main {

    public static void main(String[] args) {
        Locale locale = Util.obtenerLocale();
        Locale.setDefault(locale);

        Util.carpetaDatos();

        Vista vista = new Vista();
        Modelo modelo = new Modelo();
        Controlador controlador = new Controlador(vista, modelo);
    }
}
