import mvc.Modelo;
import mvc.gui.Controlador;
import mvc.gui.Vista;
import util.Util;

import java.util.Locale;

/**
 * Clase Main. Clase principal del programa. Desde ella se inician el Modelo, la Vista y el Controlador.
 * Básicamente, pone en marcha la aplicación.
 */
public class Main {

    /**
     * Método Main. Obtiene el locale guardado en la configuración, comprueba si existe la carpeta en la que se guardan los datos e
     * inica la Vista, el Modelo y el Controlador.
     * @param args Argumentos a pasar (ninguno)
     */
    public static void main(String[] args) {
        Locale locale = Util.obtenerLocale();
        Locale.setDefault(locale);

        Util.carpetaDatos();

        Vista vista = new Vista();
        Modelo modelo = new Modelo();
        Controlador controlador = new Controlador(vista, modelo);
    }
}
