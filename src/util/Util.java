package util;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.Period;

public class Util {

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
     * Método que calcula la edad a partir de la fecha de nacimiento
     * @param nacimiento LocalDate con la fecha de nacimiento
     * @return int Años que tiene la persona
     */
    public static int calcularEdad(LocalDate nacimiento) {
        LocalDate fechaActual = LocalDate.now();
        Period intervalo = Period.between(nacimiento, fechaActual);
        return intervalo.getYears();
    }
}
