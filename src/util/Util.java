package util;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public class Util {

    private static ResourceBundle boundleUtil = ResourceBundle.getBundle("idioma");

    /**
     * Muestra un JOptionPane con un mensaje de error
     * @param mensaje El mensaje de error que queremos mostrar
     */
    public static void mensajeError(String mensaje){
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Método que escala la imagen para que siempre tenga un alto de 100 px
     * @param icon La imagen que queremos redimensionar
     * @return ImageIcon La imagen redimensionada
     */
    public static ImageIcon escalarPortadaLibro(ImageIcon icon) {
        //Datos originales de la imagen
        int anchoOriginal = icon.getIconWidth();
        int altoOriginal = icon.getIconHeight();

        //Calculamos la relación entre el alto original y 100px
        double relacion = (double)100 / altoOriginal;

        //Aplicamos la relacion y sacamos las dimensiones recomendadas
        int anchoRecomendado = (int)(anchoOriginal * relacion);
        int altoRecomendado = (int)(altoOriginal * relacion);

        Image image = icon.getImage();

        //Finalmente, redimensionamos la imagen
        ImageIcon imagenRedimensionada = new ImageIcon(image.getScaledInstance(anchoRecomendado, altoRecomendado, Image.SCALE_SMOOTH));

        return imagenRedimensionada;
    }

    /**
     * Obtiene el locale actual
     * @return Locale El locale actual
     */
    public static Locale obtenerLocale() {

        Locale locale = null;
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("data/preferencias.conf"));
            String pais = properties.getProperty("pais");
            String idioma = properties.getProperty("idioma");

            if(pais.equals("UK")){
                locale = new Locale("en", "UK");
            }

        } catch (IOException e) { e.printStackTrace(); }

        //Si no se ha podido cargar el fichero, idioma spanish
        if(locale == null){
            locale = new Locale("es", "ES");
        }
        return locale;
    }

    public static void carpetaDatos() {File directorio = new File("data");
        if(!directorio.exists()) {
            directorio.mkdir();
        }
    }

    public static int mensajeConfirmacion(String mensaje){
        return JOptionPane.showConfirmDialog(null, mensaje, boundleUtil.getString("util.mensajeConfirmacion.titulo"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    }
}
